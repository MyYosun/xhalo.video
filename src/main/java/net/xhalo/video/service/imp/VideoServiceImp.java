package net.xhalo.video.service.imp;

import com.github.pagehelper.PageHelper;
import net.xhalo.video.dao.VideoDao;
import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import net.xhalo.video.security.utils.SecurityUserUtil;
import net.xhalo.video.service.IUserVideoService;
import net.xhalo.video.service.IVideoService;
import net.xhalo.video.utils.FFmpegUtil;
import net.xhalo.video.utils.HashCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static net.xhalo.video.config.ConstantProperties.*;
import static net.xhalo.video.config.FilePathProperties.VIDEO_SAVE_PATH;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ZERO;

@Service
@Transactional
public class VideoServiceImp implements IVideoService {

    private static Logger logger = LogManager.getLogger(VideoServiceImp.class);

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private IUserVideoService userVideoService;

    @Autowired
    private SecurityUserUtil securityUserUtil;

    @Override
    @CacheEvict(value = "video", allEntries = true, beforeInvocation = true)
    public Video addVideo(MultipartFile upload, Video video) {
        String md5 = null;
        String address = null;
        String view = null;
        String videoPath = null;
        User author = securityUserUtil.getLoginCusUser();
        if (author == null)
            return null;
        try {
            md5 = HashCodeUtil.md5HashCode(upload.getInputStream());
            if (StringUtils.isEmpty(md5))
                return null;
            address = video.getTitle() + md5 + VIDEO_FILE_FORMAT;
            videoPath = VIDEO_SAVE_PATH + address;
            File target = new File(videoPath);
            upload.transferTo(target);
        } catch (IOException e) {
            logger.error("ERROR GET MD5 FOR UPLOAD FILE:", e);
            e.printStackTrace();
            return null;
        }
        view = video.getTitle() + md5 + IMAGE_FILE_FORMAT;
        Video obj = new Video();
        obj.setTitle(video.getTitle());
        obj.setAuthor(author);
        obj.setAddress(address);
        obj.setCategory(video.getCategory());
        obj.setInfo(video.getInfo());
        obj.setClick(NUM_ZERO);
        obj.setDate(new Date());
        obj.setView(view);
        obj.setDuration(FFmpegUtil.getDuration(videoPath));
        if (videoDao.addVideo(obj) == NUM_ONE)
            return obj;
        return null;
    }

    @Override
    @Cacheable(value = "video", key = "#root.methodName+'_'+#pageNum+'_'+#pageSize")
    public List<Video> getNewVideos(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosOrderByWhat(VIDEO_DATE);
    }

    @Override
    public List<Video> getRecommendVideos(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosOrderByWhat(VIDEO_CLICK);
    }

    @Override
    public List<Video> getRecommendVideosByCategoryAndPage(Video video, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosByCategoryAndOrderByWhat(video, null, VIDEO_CLICK);
    }

    //使用Aop实现点击量增加
    @Override
    public Video getVideoById(Long videoId) {
        return videoDao.getVideoById(videoId);
    }

    @Override
    public Video getVideoByIdNotAddClick(Long videoId) {
        return videoDao.getVideoById(videoId);
    }

    @Override
    public List<Video> getVideosByCategory(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String sqlEL = translateOptionDuration(optionDuration);
        if (!(StringUtils.equals(optionOrder, VIDEO_DATE) || StringUtils.equals(optionOrder, VIDEO_DURATION) || StringUtils.equals(optionOrder, VIDEO_CLICK)))
            return null;
        return videoDao.getVideosByCategoryAndOrderByWhat(video, sqlEL, optionOrder);
    }

    @Override
    public List<Video> getVideosByTitle(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String sqlEL = translateOptionDuration(optionDuration);
        if (!(StringUtils.equals(optionOrder, VIDEO_DATE) || StringUtils.equals(optionOrder, VIDEO_DURATION) || StringUtils.equals(optionOrder, VIDEO_CLICK)))
            return null;
        return videoDao.getVideosByTitleAndOrderByWhat(video, sqlEL, optionOrder);
    }

    private String translateOptionDuration(String optionDuration) {
        String sqlEL = null;
        switch (optionDuration) {
            case VIDEO_DURATION_ALL:
                break;
            case VIDEO_DURATION_SHORT:
                sqlEL = VIDEO_DURATION_SHORT_SQL;
                break;
            case VIDEO_DURATION_MEDIUM:
                sqlEL = VIDEO_DURATION_MEDIUM_SQL;
                break;
            case VIDEO_DURATION_LONG:
                sqlEL = VIDEO_DURATION_LONG_SQL;
                break;
            case VIDEO_DURATION_OTHER:
                sqlEL = VIDEO_DURATION_OTHER_SQL;
                break;
        }
        return sqlEL;
    }

    @Override
    public boolean addClickById(Long videoId) {
        return videoDao.addClickById(videoId) == NUM_ONE;
    }

    @Override
    public List<Video> getUserUploadVideos() {
        User author = securityUserUtil.getLoginCusUser();
        if (author == null)
            return null;
        return getVideosByAuthor(author);
    }

    @Override
    public List<Video> getVideosByAuthor(User author) {
        return videoDao.getVideosByAuthor(author);
    }

    //使用Aop删除关联的用户喜欢视频和文件
    @Override
    public boolean deleteUserUploadVideo(Video video) {
        User author = securityUserUtil.getLoginCusUser();
        if (null == author)
            return false;
        video.setAuthor(author);
        return deleteVideoByAuthorAndId(video);
    }

    @Override
    public boolean deleteVideoByAuthorAndId(Video video) {
        return videoDao.deleteVideoByAuthorAndId(video) == NUM_ONE;
    }

    @Override
    public List<Video> getUserLikeVideos() {
        User user = securityUserUtil.getLoginCusUser();
        if (null == user) {
            return null;
        }
        return videoDao.getLikeVideosByUser(user);
    }

    @Override
    public boolean deleteUserLikeVideo(Video video) {
        return userVideoService.deleteLoginUserLikeVideo(video);
    }
}

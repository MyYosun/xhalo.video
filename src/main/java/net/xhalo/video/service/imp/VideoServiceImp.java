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
import static net.xhalo.video.config.FilePathProperties.*;
import static net.xhalo.video.config.MaginNumberProperties.*;

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

    @Autowired
    private IVideoService self;

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
            address = md5 + VIDEO_FILE_FORMAT;
            videoPath = VIDEO_SAVE_PATH + address;
            File target = new File(videoPath);
            upload.transferTo(target);
        } catch (IOException e) {
            logger.error("ERROR GET MD5 FOR UPLOAD FILE:", e);
            e.printStackTrace();
            return null;
        }
        view = md5 + IMAGE_FILE_FORMAT;
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
    public Video getVideoByIdNotRelated(Long videoId) {
        return videoDao.getVideoByIdNotRelated(videoId);
    }

    @Override
    public List<Video> getVideosByCategory(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize) {
        String sqlEL = translateOptionDuration(optionDuration);
        if (!(StringUtils.equals(optionOrder, VIDEO_DATE) || StringUtils.equals(optionOrder, VIDEO_DURATION) || StringUtils.equals(optionOrder, VIDEO_CLICK)))
            return null;
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosByCategoryAndOrderByWhat(video, sqlEL, optionOrder);
    }

    @Override
    public List<Video> getVideosByTitle(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize) {
        String sqlEL = translateOptionDuration(optionDuration);
        if (!(StringUtils.equals(optionOrder, VIDEO_DATE) || StringUtils.equals(optionOrder, VIDEO_DURATION) || StringUtils.equals(optionOrder, VIDEO_CLICK)))
            return null;
        PageHelper.startPage(pageNum, pageSize);
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
    public List<Video> getUserUploadVideos(Integer pageNum, Integer pageSize) {
        User author = securityUserUtil.getLoginCusUser();
        if (author == null)
            return null;
        return getVideosByAuthor(author, pageNum, pageSize);
    }

    @Override
    public List<Video> getVideosByAuthor(User author, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosByAuthor(author);
    }

    @Override
    public boolean deleteUserUploadVideo(Video video) {
        User author = securityUserUtil.getLoginCusUser();
        if (null == author)
            return false;
        video.setAuthor(author);
        return self.deleteVideoByAuthorAndId(video);
    }

    //使用Aop删除关联的用户喜欢视频和文件
    @Override
    public boolean deleteVideoByAuthorAndId(Video video) {
        return videoDao.deleteVideoByAuthorAndId(video) == NUM_ONE;
    }

    @Override
    public List<Video> getUserLikeVideos(Integer pageNum, Integer pageSize) {
        User user = securityUserUtil.getLoginCusUser();
        if (null == user) {
            return null;
        }
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getLikeVideosByUser(user);
    }

    @Override
    public boolean removeUserLikeVideo(Video video) {
        return userVideoService.deleteLoginUserLikeVideo(video);
    }

    @Override
    public List<Video> getAllVideos(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getAllVideos();
    }

    //使用AOP删除文件
    @Override
    public boolean deleteById(Video video) {
        return videoDao.deleteById(video) == NUM_ONE;
    }

    @Override
    public boolean deleteUselessVideos() {
        int i = NUM_ZERO;
        while (true) {
            //根据实际视频数目来选定次数
            if (i >= NUM_ONE_HUNDRED) {
                break;
            }
            List<Video> videos = getAllVideos(i, NUM_FIFTY);
            if (null == videos || videos.size() == 0) {
                break;
            }
            for (Video video : videos) {
                if (null == video.getId()) {
                    continue;
                }
                if (!validateVideoOk(video)) {
                    self.deleteById(video);
                }
            }
            i++;
        }
        return true;
    }

    @Override
    public boolean repairUselessVideos() {
        int i = NUM_ZERO;
        while (true) {
            //根据实际视频数目来选定次数
            if (i >= NUM_ONE_HUNDRED) {
                break;
            }
            List<Video> videos = getAllVideos(i, NUM_FIFTY);
            if (null == videos || videos.size() == 0) {
                break;
            }
            for (Video video : videos) {
                repairVideo(video);
            }
            i++;
        }
        return true;
    }

    private boolean validateVideoOk(Video video) {
        if (StringUtils.isEmpty(video.getTitle()) || StringUtils.isAllBlank(video.getTitle())) {
            return false;
        }
        if (null == video.getAuthor() || StringUtils.isEmpty(video.getAuthor().getUsername())) {
            return false;
        }
        if (null == video.getCategory() || StringUtils.isEmpty(video.getCategory().getName())) {
            return false;
        }
        if (StringUtils.isEmpty(video.getAddress())) {
            return false;
        }
        File videoFile = new File(VIDEO_SAVE_PATH + video.getAddress());
        if (!videoFile.exists()) {
            return false;
        }
        if (StringUtils.isEmpty(video.getDuration().toString())) {
            return false;
        }
        if (video.getDuration() < 10) {
            return false;
        }
        return true;
    }

    private boolean repairVideo(Video video) {
        if (null == video.getView() || StringUtils.isAllBlank(video.getView())) {
            if (null == video.getAddress() || StringUtils.isAllBlank(video.getAddress())) {
                return false;
            }
            video.setView(video.getAddress());
            processVideoImage(video);
            return true;
        }
        processVideoImage(video);
        return true;
    }

    private void processVideoImage(Video video) {
        File image = new File(IMAGE_SAVE_PATH + video.getView());
        if (!image.exists()) {
            FFmpegUtil.videoCutImg(video.getAddress(), video.getView());
        }
        File imageBig = new File(BIG_IMAGE_SAVE_PATH + video.getView());
        if (!imageBig.exists()) {
            FFmpegUtil.videoCutImgBig(video.getAddress(), video.getView());
        }
    }
}

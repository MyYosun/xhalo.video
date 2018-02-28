package net.xhalo.video.service.imp;

import com.github.pagehelper.PageHelper;
import net.xhalo.video.dao.UserDao;
import net.xhalo.video.dao.VideoDao;
import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import net.xhalo.video.service.IVideoService;
import net.xhalo.video.utils.FFmpegUtil;
import net.xhalo.video.utils.HashCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static net.xhalo.video.config.FilePathConfig.VIDEO_SAVE_PATH;
import static net.xhalo.video.config.MaginNumberConfig.NUM_ONE;
import static net.xhalo.video.config.MaginNumberConfig.NUM_ZERO;

@Service
@Transactional
public class VideoServiceImp implements IVideoService {

    private static Logger logger = LogManager.getLogger(VideoServiceImp.class);

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private UserDao userDao;

    @Override
    @CacheEvict(value = "video", allEntries = true , beforeInvocation = true)
    public Video addVideo(MultipartFile upload, Video video) {
        String md5 = null;
        String address = null;
        String view = null;
        String videoPath = null;
        if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails))
            return null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = null;
        author = userDao.findByUsername(userDetails.getUsername());
        try {
            md5 = HashCodeUtil.md5HashCode(upload.getInputStream());
            if(StringUtils.isEmpty(md5))
                return null;
            address = video.getTitle() + md5 + ".mp4";
            videoPath = VIDEO_SAVE_PATH + address;
            File target = new File(videoPath);
            upload.transferTo(target);
        } catch (IOException e){
            logger.error("ERROR GET MD5 FOR UPLOAD FILE:",e);
            e.printStackTrace();
            return null;
        }
        view = video.getTitle() + md5 + ".jpg";
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
        if(videoDao.addVideo(obj) == NUM_ONE)
            return obj;
        return null;
    }

    @Override
    @Cacheable(value = "video", key = "#root.methodName")
    public List<Video> getNewVideos(HttpServletRequest request, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosOrderByWhat("date");
    }

    @Override
    @Cacheable(value = "video", key = "'category_'+#video.category.id")
    public List<Video> getRecommendVideosByCategoryAndPage(Video video, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return videoDao.getVideosByCategoryAndOrderByWhat(video, "click");
    }

    @Override
    @Cacheable(value = "video", key = "#videoId")
    public Video getVideoById(Integer videoId) {
        return videoDao.getVideoById(videoId);
    }
}

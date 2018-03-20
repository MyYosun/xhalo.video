package net.xhalo.video.service.imp;

import com.github.pagehelper.PageHelper;
import net.xhalo.video.dao.UserVideoDao;
import net.xhalo.video.model.Comment;
import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import net.xhalo.video.security.utils.SecurityUserUtil;
import net.xhalo.video.service.IUserVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ZERO;

@Service
@Transactional
public class UserVideoServiceImp implements IUserVideoService {

    @Autowired
    private UserVideoDao userVideoDao;

    @Autowired
    private SecurityUserUtil securityUserUtil;

    @Override
    public boolean addUserLikeVideo(User user, Video video) {
        if (userVideoDao.validateUserLikeVideo(user, video) > NUM_ZERO) {
            return false;
        }
        return userVideoDao.addUserLikeVideo(user, video) == NUM_ONE;
    }

    @Override
    public boolean addLoginUserLikeVideo(Video video) {
        User user = securityUserUtil.getLoginCusUser();
        if (null == user) {
            return false;
        }
        return addUserLikeVideo(user, video);
    }

    @Override
    public boolean deleteUserLikeVideo(User user, Video video) {
        if (userVideoDao.validateUserLikeVideo(user, video) == NUM_ZERO) {
            return false;
        }
        return userVideoDao.deleteUserLikeVideo(user, video) == NUM_ONE;
    }

    @Override
    public boolean deleteLikeVideo(Video video) {
        return userVideoDao.deleteUserLikeVideo(null, video) == NUM_ONE;
    }

    @Override
    public boolean deleteLoginUserLikeVideo(Video video) {
        User user = securityUserUtil.getLoginCusUser();
        if (null == user) {
            return false;
        }
        return deleteUserLikeVideo(user, video);
    }

    @Override
    public boolean validateUserLikeVideo(Video video) {
        User user = securityUserUtil.getLoginCusUser();
        if (null == user) {
            return false;
        }
        if (userVideoDao.validateUserLikeVideo(user, video) > NUM_ZERO) {
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> getVideoCommentByVideo(Video video, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return userVideoDao.selectVideoCommentByVideo(video);
    }

    @Override
    public boolean deleteVideoCommentByVideo(Video video) {
        return userVideoDao.deleteVideoCommentByVideo(video) == NUM_ONE;
    }

    @Override
    public boolean addVideoComment(Comment comment) {
        return userVideoDao.addVideoComment(comment) == NUM_ONE;
    }

    @Override
    public boolean userAddVideoComment(Comment comment) {
        User user = securityUserUtil.getLoginCusUser();
        if (null == user) {
            return false;
        }
        comment.setUser(user);
        return addVideoComment(comment);
    }
}

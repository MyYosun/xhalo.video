package net.xhalo.video.service.imp;

import net.xhalo.video.dao.UserVideoDao;
import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import net.xhalo.video.service.IUserVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;

@Service
@Transactional
public class UserVideoServiceImp implements IUserVideoService {

    @Autowired
    private UserVideoDao userVideoDao;

    @Override
    public boolean addUserLikeVideo(User user, Video video) {
        return userVideoDao.addUserLikeVideo(user, video) == NUM_ONE;
    }
}

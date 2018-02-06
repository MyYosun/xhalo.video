package net.xhalo.video.service.imp;

import net.xhalo.video.dao.UserDao;
import net.xhalo.video.model.User;
import net.xhalo.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.xhalo.video.config.ConstantConfig.AUTHORITY_USER;

@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    private UserDao userDao;

    public boolean addUser(User user) {
        user.setAuthority(AUTHORITY_USER);
        user.setEnabled(true);
        //TODO:头像
        return userDao.addUser(user) == 1;
    }

    public User validateUser(User user) {
        User result = null;
        result = userDao.validateUser(user);
        if(result != null)
            userDao.updateLoginTime(result);
        return result;
    }

    public boolean validateUsername(User user) {
        return userDao.validateUsername(user) == 0;
    }
}

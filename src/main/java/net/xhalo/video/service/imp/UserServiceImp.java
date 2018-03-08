package net.xhalo.video.service.imp;

import net.xhalo.video.dao.UserDao;
import net.xhalo.video.model.User;
import net.xhalo.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.xhalo.video.config.ConstantProperties.AUTHORITY_USER;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ZERO;

@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    private UserDao userDao;

    public boolean addUser(User user) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setAuthority(AUTHORITY_USER);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //TODO:头像
        return userDao.addUser(user) == NUM_ONE;
    }

    public boolean validateUsername(User user) {
        return userDao.validateUsername(user) == NUM_ZERO;
    }
}

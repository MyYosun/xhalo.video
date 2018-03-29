package net.xhalo.video.service.imp;

import net.xhalo.video.dao.UserDao;
import net.xhalo.video.model.User;
import net.xhalo.video.service.IUserService;
import net.xhalo.video.utils.RedisCacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static net.xhalo.video.config.ConstantProperties.*;
import static net.xhalo.video.config.FilePathProperties.HEAD_IMAGE_SAVE_PATH;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ZERO;

@Service
@Transactional
public class UserServiceImp implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    private Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    //使用AOP执行密码加密和自动生成头像，详见UserAop
    @Override
    public boolean addUser(User user) {
        user.setAuthority(AUTHORITY_USER);
        user.setEnabled(true);
        user.setSign(USER_DEFAULT_SIGN);
        return userDao.addUser(user) == NUM_ONE;
    }

    public boolean validateUsername(User user) {
        return userDao.validateUsername(user) == NUM_ZERO;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getUserByUsername(User user) {
        return userDao.getUserByUsername(user);
    }

    //对updateUser***进行Aop验证user信息,详见UserAop
    @Override
    @CacheEvict(value = "video", allEntries = true, beforeInvocation = true)
    public boolean updateUserInfoByIdAndUsername(User user) {
        return userDao.updateUserInfoByIdAndUsername(user) == NUM_ONE;
    }

    @Override
    @CacheEvict(value = "video", allEntries = true, beforeInvocation = true)
    public boolean updateUserHeadImgByIdAndUsername(User user, MultipartFile upload) {
        User userDetail = getUserByUsername(user);
        if (StringUtils.isEmpty(userDetail.getHeadImg())) {
            userDetail.setHeadImg(userDetail.getUsername() + "." + IMAGE_HEAD_FORMAT);
            userDao.updateUserHeadImgByIdAndUsername(userDetail);
        }
        File headImg = new File(HEAD_IMAGE_SAVE_PATH + userDetail.getHeadImg());
        try {
            upload.transferTo(headImg);
            return true;
        } catch (IOException e) {
            logger.error("MULTIPARTFILE TRANSFER TO FILE ERROR:", e);
        }
        return false;
    }

    @Override
    public boolean updateUserPasswordByIdAndUsername(User user) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.updateUserPasswordByIdAndUsername(user) == NUM_ONE;
    }
}

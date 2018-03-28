package net.xhalo.video.aop;

import net.xhalo.video.dao.UserDao;
import net.xhalo.video.utils.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.File;

import static net.xhalo.video.config.ConstantProperties.*;
import static net.xhalo.video.config.FilePathProperties.HEAD_IMAGE_SAVE_PATH;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ONE;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ZERO;

@Component
@Aspect
public class UserAop {

    @Autowired
    private UserDao userDao;

    Logger logger = LoggerFactory.getLogger(UserAop.class);


    @Pointcut("execution(* net.xhalo.video.security.service.imp.CusAuthenticationSuccessHandler.onAuthenticationSuccess(..))")
    public void loginSuccess() {
    }

    @Pointcut("execution(* net.xhalo.video.service.imp.UserServiceImp.addUser(..))")
    public void processUserRegister() {
    }

    @Pointcut("execution(* net.xhalo.video.service.imp.UserServiceImp.updateUser*(..))")
    public void validateUserUpdate() {
    }

    @AfterReturning(pointcut = "loginSuccess()")
    public void updateLoginTime(JoinPoint point) {
        Object[] args = point.getArgs();
        for (int i = args.length - NUM_ONE; i >= NUM_ZERO; i++) {
            if (args[i] instanceof Authentication) {
                Authentication authentication = (Authentication) args[i];
                //小试反射
                /*Object user = authentication.getPrincipal();
                Class userClass = user.getClass();
                try {
                    Field field = userClass.getDeclaredField("username");
                    field.setAccessible(true);
                    String username = (String) field.get(user);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }*/
                User user = (User) authentication.getPrincipal();
                net.xhalo.video.model.User cusUser = new net.xhalo.video.model.User();
                cusUser.setUsername(user.getUsername());
                userDao.updateLoginTime(cusUser);
                return;
            }
        }
    }

    @Transactional
    @Around("processUserRegister()")
    public Object processUserRegister(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof net.xhalo.video.model.User) {
                net.xhalo.video.model.User user = (net.xhalo.video.model.User) arg;
                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setHeadImg(user.getUsername() + "." + IMAGE_HEAD_FORMAT);
                Object[] modifiedArgs = new Object[1];
                modifiedArgs[0] = user;
                try {
                    Object result = null;
                    result = proceedingJoinPoint.proceed(modifiedArgs);
                    if ((boolean) result == true) {
                        ImageUtil.createImage(" " + user.getNickname().substring(0, 1) + " ",
                                new Font(IMAGE_HEAD_FONT_FORMAT, Font.BOLD, IMAGE_HEAD_FONT_SIZE),
                                new File(HEAD_IMAGE_SAVE_PATH + user.getHeadImg()),
                                IMAGE_HEAD_WIDTH, IMAGE_HEAD_HEIGHT);
                    }
                    return result;
                } catch (Throwable throwable) {
                    logger.error("AFTER ENCODE PASSWORD GET ERROR:", throwable);
                    return false;
                }
            }

        }
        return false;
    }

    @Around("validateUserUpdate()")
    public boolean validateUserInfo(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof net.xhalo.video.model.User) {
                net.xhalo.video.model.User user = (net.xhalo.video.model.User) arg;
                if (user == null || StringUtils.isEmpty(user.getId().toString()) || StringUtils.isEmpty(user.getUsername())) {
                    return false;
                }
                net.xhalo.video.model.User userDetail = userDao.getUserByUsername(user);
                if (!(StringUtils.equals(userDetail.getId().toString(), user.getId().toString())))
                    return false;
            }
        }
        try {
            boolean result = (boolean) proceedingJoinPoint.proceed(args);
            return result;
        } catch (Throwable throwable) {
            logger.error("AFTER VALIDATE USER INFO ERROR:", throwable);
            return false;
        }
    }

}

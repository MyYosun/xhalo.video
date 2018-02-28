package net.xhalo.video.aop;

import net.xhalo.video.dao.UserDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import static net.xhalo.video.config.MaginNumberConfig.NUM_ONE;
import static net.xhalo.video.config.MaginNumberConfig.NUM_ZERO;

@Component
@Aspect
public class UserAop {

    @Autowired
    UserDao userDao;

    @Pointcut("execution(* net.xhalo.video.security.service.imp.CusAuthenticationSucessHandler.onAuthenticationSuccess(..))")
    public void loginSuccess() {}

    @AfterReturning(pointcut = "loginSuccess()")
    public void updateLoginTime(JoinPoint point) {
        Object[] args = point.getArgs();
        for(int i = args.length - NUM_ONE; i >= NUM_ZERO; i++) {
            if(args[i] instanceof Authentication) {
                Authentication authentication = (Authentication) args[i];
                //小试反射
                /*Object user = authentication.getPrincipal();
                Class userClass = user.getClass();
                try {
                    Field field = userClass.getDeclaredField("username");
                    field.setAccessible(true);
                    username = (String) field.get(user);
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
}

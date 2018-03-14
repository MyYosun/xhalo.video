package net.xhalo.video.security.utils;

import net.xhalo.video.model.User;
import net.xhalo.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserUtil {

    @Autowired
    private IUserService userService;

    public UserDetails getLoginSecurityUser() {
        UserDetails userDetails = null;
        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails))
            return userDetails;
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }

    public User getLoginCusUser() {
        UserDetails userDetails = getLoginSecurityUser();
        if (null == userDetails)
            return null;
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user = userService.getUserByUsername(user);
        return user;
    }
}

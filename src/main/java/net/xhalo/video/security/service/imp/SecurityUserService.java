//package net.xhalo.video.security.service.imp;
//
//        import net.xhalo.video.dao.UserDao;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.security.core.GrantedAuthority;
//        import org.springframework.security.core.authority.SimpleGrantedAuthority;
//        import org.springframework.security.core.userdetails.UserDetails;
//        import org.springframework.security.core.userdetails.UserDetailsService;
//        import org.springframework.security.core.userdetails.UsernameNotFoundException;
//        import org.springframework.stereotype.Service;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//@Service
//public class SecurityUserService implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//    private static final String AUTHORITY_ADMIN = "admin";
////    private static final String AUTHORITY_USER = "user";
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        net.xhalo.video.model.User user = userDao.findByUsername(username);
//        if(null != user) {
//            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            if (user.getAuthority() == AUTHORITY_ADMIN)
//                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(), user.getPassword(), authorities);
//        }
//        throw new UsernameNotFoundException("USER: '" + username + "' NOT FOUND!");
//    }
//
//}

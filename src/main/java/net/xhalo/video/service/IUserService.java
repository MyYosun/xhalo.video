package net.xhalo.video.service;

import net.xhalo.video.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

    boolean addUser(User user);

    boolean validateUsername(User user);

    User findByUsername(String username);

    User getUserByUsername(User user);

    boolean updateUserInfoByIdAndUsername(User user);

    boolean updateUserHeadImgByIdAndUsername(User user, MultipartFile upload);

    boolean updateUserPasswordByIdAndUsername(User user);
}

package net.xhalo.video.service;

import net.xhalo.video.model.User;

public interface IUserService {
    boolean addUser(User user);

    User validateUser(User user);

    boolean validateUsername(User user);
}

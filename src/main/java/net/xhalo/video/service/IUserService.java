package net.xhalo.video.service;

import net.xhalo.video.model.User;

public interface IUserService {

    boolean addUser(User user);

    boolean validateUsername(User user);
}

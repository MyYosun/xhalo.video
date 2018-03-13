package net.xhalo.video.service;

import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;

public interface IUserVideoService {
    public boolean addUserLikeVideo(User user, Video video);
}

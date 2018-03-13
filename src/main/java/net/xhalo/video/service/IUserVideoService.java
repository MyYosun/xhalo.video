package net.xhalo.video.service;

import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;

public interface IUserVideoService {
    public boolean addUserLikeVideo(User user, Video video);
    public boolean addLoginUserLikeVideo(Video video);
    public boolean deleteUserLikeVideo(User user, Video video);
    public boolean deleteLikeVideo(Video video);
    public boolean deleteLoginUserLikeVideo(Video video);
    public boolean validateUserLikeVideo(Video video);
}

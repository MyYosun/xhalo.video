package net.xhalo.video.service;

import net.xhalo.video.model.Comment;
import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;

import java.util.List;

public interface IUserVideoService {
    boolean addUserLikeVideo(User user, Video video);
    boolean addLoginUserLikeVideo(Video video);
    boolean deleteUserLikeVideo(User user, Video video);
    boolean deleteLikeVideo(Video video);
    boolean deleteLoginUserLikeVideo(Video video);
    boolean validateUserLikeVideo(Video video);
    List<Comment> getVideoCommentByVideo(Video video);
    boolean deleteVideoCommentByVideo(Video video);
    boolean addVideoComment(Comment comment);
    boolean userAddVideoComment(Comment comment);
}

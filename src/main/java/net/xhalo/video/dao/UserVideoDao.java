package net.xhalo.video.dao;

import net.xhalo.video.model.Comment;
import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVideoDao {
    Integer addUserLikeVideo(@Param("user") User user, @Param("video") Video video);

    Integer deleteUserLikeVideo(@Param("user") User user, @Param("video") Video video);

    Integer validateUserLikeVideo(@Param("user") User user, @Param("video") Video video);

    Integer addVideoComment(Comment comment);

    Integer deleteVideoCommentByVideo(Video video);

    List<Comment> selectVideoCommentByVideo(Video video);
}

package net.xhalo.video.dao;

import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao {
    int addVideo(Video video);

    List<Video> getVideosOrderByWhat(@Param("orderItem") String orderItem);

    List<Video> getVideosByCategoryAndOrderByWhat(@Param("video") Video video, @Param("optionDurationSql") String optionDurationSql, @Param("orderItem") String orderItem);

    List<Video> getVideosByTitleAndOrderByWhat(@Param("video") Video video, @Param("optionDurationSql") String optionDurationSql, @Param("orderItem") String orderItem);

    Video getVideoById(Long videoId);

    Video getVideoByIdNotRelated(Long videoId);

    Integer addClickById(Long videoId);

    List<Video> getVideosByAuthor(User author);

    Integer deleteVideoByAuthorAndId(Video video);

    List<Video> getLikeVideosByUser(User user);

    List<Video> getAllVideos();

    Integer deleteById(Video video);
}

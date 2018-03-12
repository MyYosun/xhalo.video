package net.xhalo.video.dao;

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
    Integer addClickById(Long videoId);
}

package net.xhalo.video.dao;

import net.xhalo.video.model.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao {
    int addVideo(Video video);
    List<Video> getVideosOrderByWhat(@Param("orderItem") String orderItem);
}

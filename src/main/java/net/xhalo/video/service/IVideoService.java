package net.xhalo.video.service;

import net.xhalo.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public interface IVideoService {
    Video addVideo(MultipartFile upload, Video video);
    List<Video> getNewVideos(int pageNum, int pageSize);
    List<Video> getRecommendVideos(int pageNum, int pageSize);
    List<Video> getRecommendVideosByCategoryAndPage(Video video, int pageNum, int pageSize);
    Video getVideoById(Integer videoId);
    List<Video> getVideosByCategory(Video video, int pageNum, int pageSize);
    List<Video> getVideosByTitle(Video video, int pageNum, int pageSize);
}

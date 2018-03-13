package net.xhalo.video.service;

import net.xhalo.video.model.User;
import net.xhalo.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVideoService {

    Video addVideo(MultipartFile upload, Video video);

    List<Video> getNewVideos(int pageNum, int pageSize);

    List<Video> getRecommendVideos(int pageNum, int pageSize);

    List<Video> getRecommendVideosByCategoryAndPage(Video video, int pageNum, int pageSize);

    Video getVideoById(Long videoId);

    List<Video> getVideosByCategory(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize);

    List<Video> getVideosByTitle(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize);

    boolean addClickById(Long videoId);

    List<Video> getUserUploadVideos();

    List<Video> getVideosByAuthor(User author);

    boolean deleteUserUploadVideo(Video video);

    boolean deleteVideoByAuthorAndId(Video video);
}

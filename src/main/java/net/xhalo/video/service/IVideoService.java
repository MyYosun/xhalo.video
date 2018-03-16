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

    Video getVideoByIdNotRelated(Long videoId);

    List<Video> getVideosByCategory(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize);

    List<Video> getVideosByTitle(Video video, String optionDuration, String optionOrder, int pageNum, int pageSize);

    boolean addClickById(Long videoId);

    List<Video> getUserUploadVideos(Integer pageNum, Integer pageSize);

    List<Video> getVideosByAuthor(User author, Integer pageNum, Integer pageSize);

    boolean deleteUserUploadVideo(Video video);

    boolean deleteVideoByAuthorAndId(Video video);

    List<Video> getUserLikeVideos(Integer pageNum, Integer pageSize);

    boolean removeUserLikeVideo(Video video);

    List<Video> getAllVideos(Integer pageNum, Integer pageSize);

    boolean deleteById(Video video);

    boolean deleteUselessVideos();

    boolean repairUselessVideos();

}

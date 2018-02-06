package net.xhalo.video.service;

import net.xhalo.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IVideoService {
    Video addVideo(MultipartFile upload, Video video, HttpServletRequest request);
    List<Video> getNewVideos(HttpServletRequest request, int page, int pageSize);
}

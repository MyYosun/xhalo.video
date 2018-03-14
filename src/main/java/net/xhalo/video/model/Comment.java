package net.xhalo.video.model;

import com.google.gson.annotations.JsonAdapter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Long id;
    private User user;
    private Video video;
    @Size(max = 200)
    private String content;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

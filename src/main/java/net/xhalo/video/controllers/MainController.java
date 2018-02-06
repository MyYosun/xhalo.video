package net.xhalo.video.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.xhalo.video.config.FilePathConfig.IMAGE_SAVE_PATH;
import static net.xhalo.video.config.FilePathConfig.VIDEO_SAVE_PATH;

@Controller
public class MainController {

    private static final long serialVersionUID = 1L;
    private static final int BUFFER_LENGTH = 1024 * 16;
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final Pattern RANGE_PATTERN = Pattern.compile("bytes=(?<start>\\d*)-(?<end>\\d*)"); // range的获取数据的格式为:如byte=0-500


    @RequestMapping(value = "videoPlay")
    public void videoPlay(@RequestParam String videoAddress, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String videoFilename = URLDecoder.decode(videoAddress, "UTF-8");
        Path video = Paths.get(VIDEO_SAVE_PATH, videoFilename);

        File file = video.toFile();
        if(! (file.exists() || file.isFile()))
            return;

        int length = (int) Files.size(video);
        int start = 0;
        int end = length - 1;

        String range = request.getHeader("Range"); // 获取request的获取数据的范围
        range = range == null ? "" : range;
        Matcher matcher = RANGE_PATTERN.matcher(range);

        if (matcher.matches()) {
            String startGroup = matcher.group("start");
            start = startGroup.isEmpty() ? start : Integer.valueOf(startGroup);
            start = start < 0 ? 0 : start;

            String endGroup = matcher.group("end");
            end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
            end = end > length - 1 ? length - 1 : end;
        }

        int contentLength = end - start + 1;

        response.reset();
        response.setBufferSize(BUFFER_LENGTH);
        response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", videoFilename));
        response.setHeader("Accept-Ranges", "bytes");
        response.setDateHeader("Last-Modified", Files.getLastModifiedTime(video).toMillis());
        response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
        response.setContentType(Files.probeContentType(video));
        response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
        response.setHeader("Content-Length", String.format("%s", contentLength));
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

        int bytesRead;
        int bytesLeft = contentLength;
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);

        try (SeekableByteChannel input = Files.newByteChannel(video, StandardOpenOption.READ);
             OutputStream output = response.getOutputStream()) {

            input.position(start);

            while ((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
                buffer.clear();
                output.write(buffer.array(), 0, bytesLeft < bytesRead ? bytesLeft : bytesRead);
                bytesLeft -= bytesRead;
            }
        }
    }

    @RequestMapping(value = "showImg")
    public void showImage(@RequestParam(name = "video.view") String imgFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imgSavePath = IMAGE_SAVE_PATH + imgFile;
        FileInputStream fis = new FileInputStream(imgSavePath);
        byte data[] = new byte[1024];
        response.setContentType("image/*");
        OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        while (fis.read(data) != -1)
            toClient.write(data); // 输出数据
        fis.close();
        toClient.close();
    }
}

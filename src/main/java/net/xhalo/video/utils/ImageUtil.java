package net.xhalo.video.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static net.xhalo.video.config.ConstantProperties.IMAGE_HEAD_FORMAT;

public class ImageUtil {

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    // 根据str,font的样式以及输出文件目录
    public static void createImage(String str, Font font, File outFile,
                                   Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);// 先用灰色填充整张图片,也就是背景
        g.setColor(Color.white);
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        for (int i = 0; i < 6; i++) {
            g.drawString(str, i * 680, y);// 画出字符串
        }
        g.dispose();
        // 输出png图片
        if(! ImageIO.write(image, IMAGE_HEAD_FORMAT, outFile)){
            logger.error("ERROR WHEN PRODUCE USER HEAD, SOME REASONS:" +
                    "1.FORMAT ERROR. 2.OUT FILE DIRECTORY NOT EXIST");
        }
    }

/*     public static void main(String[] args) throws Exception {
         createImage(" 谢 ",
                 new Font("黑体", Font.BOLD, 60),
                 new File("D:\\1.png"), 100, 100);
     }*/

}

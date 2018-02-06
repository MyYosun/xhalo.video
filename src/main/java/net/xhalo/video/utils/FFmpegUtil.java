package net.xhalo.video.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.oro.text.regex.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static net.xhalo.video.config.ConstantConfig.IMAGE_SIZE;
import static net.xhalo.video.config.FilePathConfig.*;

public class FFmpegUtil {

	private static Logger logger = LogManager.getLogger(FFmpegUtil.class);


	public static boolean makeScreenCut(String videoPath, String imageOutPath) {
		List<String> command = new ArrayList<String>();
		command.add(FFMPEG_PATH);
		command.add("-i");
		command.add(VIDEO_SAVE_PATH + videoPath);
		command.add("-y");
		command.add("-f");
		command.add("image2");
		command.add("-ss");
		command.add("8");     //截图位置
		command.add("-t");
		command.add("0.001");  //截图时长
		command.add("-s");
		command.add(IMAGE_SIZE);  //截图大小
		command.add(IMAGE_SAVE_PATH + imageOutPath);

		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			builder.redirectErrorStream(true);
			logger.info("视频:" + videoPath + "截图开始...");

			Process process;
			process = builder.start();
			InputStream in = process.getInputStream();
			byte[] bytes = new byte[1024];
			while (in.read(bytes) != -1) {
				logger.info("......");
			}
			logger.info("截图输出路径" + IMAGE_SAVE_PATH + imageOutPath);
		} catch (IOException e) {
			logger.error("FFmpegUtil:ERROR WHEN CATCH VIDEO SHOT:",e);
			return false;
		}

		return true;
	}

	public static Integer getDuration(String videoPath) {
		String duration = getSingleInfo(videoPath, 1, 1);
		String pattern = "(\\d+):(\\d+):(\\d+)\\.(\\d+)";
		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
		Matcher m = r.matcher(duration);
		if(m.find()) {
			Integer hour = Integer.parseInt(m.group(1));
			Integer minute = Integer.parseInt(m.group(2));
			Integer second = Integer.parseInt(m.group(3));
			Integer millis = Integer.parseInt(m.group(4));
			return (3600*hour) + (60*minute) + (second) + (millis>=50?1:0);
		}
		return 0;
	}

	/**
	 *
	 * @param videoPath:视频地址
	 * @param which:选择需要的信息类型(时长、视频、音频)
	 * @param detailWhich:选择详细信息
	 * @return
	 */
	private static String getSingleInfo(String videoPath, int which, int detailWhich) {
		String result = getVideoInfo(videoPath);
		PatternCompiler compiler = new Perl5Compiler();
		String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
		String regexVideo = "Video: (.*?), (.*?), (.*?)[,\\s]";
		String regexAudio = "Audio: (\\w*), (\\d*) Hz";
		try {
			switch (which) {
				case 1: {
					org.apache.oro.text.regex.Pattern patternDuration = compiler
							.compile(regexDuration, Perl5Compiler.CASE_INSENSITIVE_MASK);
					PatternMatcher matcherDuration = new Perl5Matcher();
					if (matcherDuration.contains(result, patternDuration)) {
						org.apache.oro.text.regex.MatchResult re = matcherDuration
								.getMatch();

						logger.info("提取出播放时间  ===" + re.group(1));
						logger.info("开始时间        =====" + re.group(2));
						logger.info("bitrate 码率 单位 kb==" + re.group(3));
						return re.group(detailWhich);
					}
					break;
				}

				case 2: {

					org.apache.oro.text.regex.Pattern patternVideo = compiler
							.compile(regexVideo, Perl5Compiler.CASE_INSENSITIVE_MASK);
					PatternMatcher matcherVideo = new Perl5Matcher();

					if (matcherVideo.contains(result, patternVideo)) {
						org.apache.oro.text.regex.MatchResult re = matcherVideo.getMatch();
						logger.info("编码格式  ===" + re.group(1));
						logger.info("视频格式 ===" + re.group(2));
						logger.info(" 分辨率  == =" + re.group(3));
						return re.group(detailWhich);
					}
					break;
				}

				case 3: {
					org.apache.oro.text.regex.Pattern patternAudio = compiler
							.compile(regexAudio, Perl5Compiler.CASE_INSENSITIVE_MASK);
					PatternMatcher matcherAudio = new Perl5Matcher();

					if (matcherAudio.contains(result, patternAudio)) {
						org.apache.oro.text.regex.MatchResult re = matcherAudio.getMatch();
						logger.info("音频编码             ===" + re.group(1));
						logger.info("音频采样频率  ===" + re.group(2));
						return re.group(detailWhich);
					}
					break;
				}
			}
		} catch (MalformedPatternException e) {
			logger.error("ERROR WHEN TRANSPORT VIDEOINFO:",e);
		}
		return null;
	}

	private static String getVideoInfo(String inputPath) {
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(FFMPEG_PATH);
		commend.add("-i");
		commend.add(inputPath);

		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			Process p = builder.start();

			// 保存ffmpeg的输出结果流
			BufferedReader buf = null;
			String line = null;

			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			StringBuffer sb = new StringBuffer();
			while ((line = buf.readLine()) != null) {
				sb.append(line);
				continue;
			}
			// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
			p.waitFor();
			return sb.toString();
		} catch (Exception e) {
			logger.error("ERROR WHEN GET VIDEO INFO:", e);
		}
		return null;
	}
}

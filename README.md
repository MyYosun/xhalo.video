**此项目为谢浩的毕业设计，本人版权所有，未经允许不能转载和下载**

**项目名:Xhalo-Video**

**版本:SnapShot-1.0**


**内容与功能:**

`实现视频传播与与用户分享的视频共享网站，此网站功能有:视频上传、视频浏览、视频观看、VR视频观看、视频查找与分类、视频分享、视频点赞、用户登录与注册、
查看其它用户信息与他上传的视频，管理员删除视频、增加类别，自动删除无用视频和修复视频封面`

**使用技术:**

`此项目使用maven作为项目依赖管理工具，使用Spring、SpringMvc、MyBatis、SpringSecurity框架，使用JavaxValidation作为验证工具、
使用Redis作为数据缓存服务器、使用Mysql作为数据存储服务器，使用ffmpeg工具做视频和图片处理，前端使用bootstrap作为开发框架，使用jquery和其他插件:jquery-form.js、
jquery-zclip.js、toastr.js、video.js
`

**开始前配置:**

`需要搭建环境java + tomcat8 + mysql + redis + ffmpeg
项目数据库使用jndi获取，需要修改tomcat服务器conf/context.xml
添加<Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" name="jdbc/video" password="your password" type="javax.sql.DataSource" url="jdbc:mysql://your sql ip/video?serverTimezone=GMT%2B8" username="your username"/>，
启动项目前需开启Redis服务器，Redis的设置根据配置修改，配置文件为resources-filter/profiles/{env}/config/properties/redis.properties，启动项目需要搭建ffmpeg工具，
并在resources-filter/profiles/{env}/config/properties/path.properties修改ffmpeg的启动路径，
请根据需求创建媒体存储目录，并在resources-filter/profiles/{env}/config/properties/path.properties中修改相应路径`


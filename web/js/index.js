function getRequest() {
	var request;
	try {
		request = new XMLHttpRequest();
	} catch (e) {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return request;

}

function formatTime(seconds) {
	return [
		parseInt(seconds / 60),
		parseInt(seconds % 60)
	]
		.join(":")
		.replace(/\b(\d)\b/g, "0$1");
}


function getIndex() {
	getNew();
	getRecommendByCategoryAndPage(1);
	getRecommendByCategoryAndPage(2);
	getRecommendByCategoryAndPage(3);
}



function getNew() {
	var request = getRequest();
	var action = "getNewVideos";
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var json = request.responseText;
			showNew(json);
		}
	};
}

function getPopular() {
	var request = getRequest();
	var action = "getPopularVideos";
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var json = request.responseText;
			showPopular(json);
		}
	};
}

function getRecommendByCategoryAndPage(category) {
	var request = getRequest();
	var action = "getRecommendVideosByCategoryAndPage?category.id=" + category;
	var json;
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			json = request.responseText;
			showRecommendFirst(json, category);
		}
	};
}


function showNew(json) {
	var videoList = eval('(' + json + ')');
	var divTop = document.getElementById('latestVideoDiv');
	divTop.innerHTML = "";


	var divSecond_latest = document.createElement('div');
	divSecond_latest.setAttribute("class", "clearfix");
	divTop.appendChild(divSecond_latest);

	for (var i = 0; i < videoList.length; i++) {
		var video = videoList[i];
		var divSecond_content = document.createElement('div');
		divSecond_content.setAttribute("class", "col-md-4 resent-grid recommended-grid slider-top-grids");
		divTop.appendChild(divSecond_content);

		var divThird_img = document.createElement('div');
		divThird_img.setAttribute("class", "resent-grid-img recommended-grid-img");
		divSecond_content.appendChild(divThird_img);
		var divThird_img_a = document.createElement('a');
		divThird_img_a.setAttribute("href", "video-" + video.id + ".html");
		divThird_img.appendChild(divThird_img_a);
		divThird_img_a.innerHTML = "<img class='img-rounded' height='300px' src='showImg?video.view=" + video.view + "' alt='' />";

		var divThird_img_time = document.createElement('div');
		divThird_img_time.setAttribute("class", "time");
		divThird_img.appendChild(divThird_img_time);
		divThird_img_time.innerHTML = "<p>" + formatTime(video.duration) + "</p>";

		var divThird_img_clck = document.createElement('div');
		divThird_img_clck.setAttribute("class", "clck");
		divThird_img.appendChild(divThird_img_clck);
		var divThird_img_clck_span = document.createElement('span');
		divThird_img_clck_span.setAttribute("class", "glyphicon glyphicon-time");
		divThird_img_clck_span.setAttribute("aria-hidden", "true");
		divThird_img_clck.appendChild(divThird_img_clck_span);

		var divThird_info = document.createElement('div');
		divThird_info.setAttribute("class", "resent-grid-info recommended-grid-info");
		divSecond_content.appendChild(divThird_info);

		var divThird_info_h3 = document.createElement('h3');

		var divThird_info_h3_a = document.createElement('a');
		divThird_info_h3_a.setAttribute("href", "video-" + video.id + ".html" );
		divThird_info_h3_a.setAttribute("class", "title title-info");
		divThird_info.appendChild(divThird_info_h3);
		divThird_info_h3.appendChild(divThird_info_h3_a);
		divThird_info_h3_a.innerHTML = video.title ;

		var divThird_info_ul = document.createElement('ul');
		divThird_info.appendChild(divThird_info_ul);

		var divThird_info_ul_li_1 = document.createElement('li');
		divThird_info_ul.appendChild(divThird_info_ul_li_1);
		divThird_info_ul_li_1.innerHTML = "<p class='author author-info'><a href='#' onclick='return false;' class='author'>" +
			video.author.username + "</a></p>";

		var divThird_info_ul_li_2 = document.createElement('li');
		divThird_info_ul_li_2.setAttribute("class", "right-list");
		divThird_info_ul.appendChild(divThird_info_ul_li_2);
		divThird_info_ul_li_2.innerHTML = "<p class='views views-info'>" + video.click + "views</p>";
	//留空
	}
	var divSecond_empty = document.createElement('div');
	divSecond_empty.setAttribute("class", "clearfix");
	divTop.appendChild(divSecond_empty);
}

function showRecommendFirst(json, id) {
	var videoList = eval('(' + json + ')');
	var divTop = document.getElementById("recommand" + id);
	divTop.innerHTML = "";
	for (var i = 0; i < videoList.length; i++) {
		var video = videoList[i];
		var divSecond_content = document.createElement('div');
		divSecond_content.setAttribute("class", "col-md-3 resent-grid recommended-grid slider-first");
		divTop.appendChild(divSecond_content);

		var divThird_img = document.createElement('div');
		divThird_img.setAttribute("class", "resent-grid-img recommended-grid-img");
		divSecond_content.appendChild(divThird_img);
		var divThird_img_a = document.createElement('a');
		divThird_img_a.setAttribute("href", "video-" + video.id + ".html");
		divThird_img.appendChild(divThird_img_a);
		divThird_img_a.innerHTML = "<img class='img-rounded' style='height:220px;' src='showImg?video.view=" + video.view + "' alt='' />";

		var divThird_img_time = document.createElement('div');
		divThird_img_time.setAttribute("class", "time small-time slider-time");
		divThird_img.appendChild(divThird_img_time);
		divThird_img_time.innerHTML = "<p>" + formatTime(video.duration) + "</p>";

		var divThird_img_clck = document.createElement('div');
		divThird_img_clck.setAttribute("class", "clck small-clck");
		divThird_img.appendChild(divThird_img_clck);
		var divThird_img_clck_span = document.createElement('span');
		divThird_img_clck_span.setAttribute("class", "glyphicon glyphicon-time");
		divThird_img_clck_span.setAttribute("aria-hidden", "true");
		divThird_img_clck.appendChild(divThird_img_clck_span);

		var divThird_info = document.createElement('div');
		divThird_info.setAttribute("class", "resent-grid-info recommended-grid-info");
		divSecond_content.appendChild(divThird_info);

		var divThird_info_h5 = document.createElement('h5');

		var divThird_info_h5_a = document.createElement('a');
		divThird_info_h5_a.setAttribute("href", "video-" + video.id + ".html");
		divThird_info_h5_a.setAttribute("class", "title");
		divThird_info.appendChild(divThird_info_h5);
		divThird_info_h5.appendChild(divThird_info_h5_a);
		divThird_info_h5_a.innerHTML = video.title ;

		var divThird_info_div = document.createElement('div');
		divThird_info_div.setAttribute("class", "slid-bottom-grids");
		divThird_info.appendChild(divThird_info_div);

		var divThird_info_div_1 = document.createElement('div');
		divThird_info_div_1.setAttribute("class", "slid-bottom-grid");
		divThird_info_div.appendChild(divThird_info_div_1);
		divThird_info_div_1.innerHTML = "<p class='author author-info'><a href='#' onclick='return false;' class='author'>" +
			video.author.username + "</a></p>";

		var divThird_info_div_2 = document.createElement('div');
		divThird_info_div_2.setAttribute("class", "slid-bottom-grid slid-bottom-right");
		divThird_info_div.appendChild(divThird_info_div_2);
		divThird_info_div_2.innerHTML = "<p class='views views-info'>" + video.click + "views</p>";

	}
	var divSecond_empty = document.createElement('div');
	divSecond_empty.setAttribute("class", "clearfix");
	divTop.appendChild(divSecond_empty);
}

function showPopular(json) {
	videoList = eval('(' + json + ')');
	var divTop = document.getElementById('popularVideos');
	for (var i = 0; i < videoList.length; i++) {
		var video = videoList[i];
		var divSecond = document.createElement('div');
		divSecond.setAttribute("class", "single-right-grids");
		divTop.appendChild(divSecond);
		var divThird_1 = document.createElement('div');
		divThird_1.setAttribute("class", "col-md-4 single-right-grid-left");
		divSecond.appendChild(divThird_1);
		var divThird_1_a = document.createElement('a');
		divThird_1_a.setAttribute("href", "video-" + video.id + ".html");
		divThird_1.appendChild(divThird_1_a);
		divThird_1_a.innerHTML = "<img class='img-rounded' style='height:9%;' src='showImg?video.view=" + video.view + "' alt='' />";
		var divThird_2 = document.createElement('div');
		divThird_2.setAttribute("class", "col-md-8 single-right-grid-right");
		divSecond.appendChild(divThird_2);
		var divThird_2_a = document.createElement('a');
		divThird_2_a.setAttribute("href", "video-" + video.id + ".html");
		divThird_2_a.setAttribute("class", "title");
		divThird_2.appendChild(divThird_2_a);
		divThird_2_a.innerHTML = video.title;
		var divThird_2_p1 = document.createElement('p');
		divThird_2_p1.setAttribute("class", "author");
		divThird_2.appendChild(divThird_2_p1);
		divThird_2_p1.innerHTML = "<a href='#' onclick='return false;' class='author'>" + video.author.username + "</a>";
		var divThird_2_p2 = document.createElement('p');
		divThird_2_p2.setAttribute("class", "views");
		divThird_2.appendChild(divThird_2_p2);
		divThird_2_p2.innerHTML = video.click + "views";

		var divSecond_empty = document.createElement('div');
		divSecond_empty.setAttribute("class", "clearfix");
		divSecond.appendChild(divSecond_empty);
	}
}


function getResultVideos() {
	var request = getRequest();
	var categoryId = document.getElementById("categoryId");
	var videoTitle = document.getElementById("videoTitle");
	var currentPage = document.getElementById("currentPage").value;
	var action;
	if (categoryId != null) {
		action = "getVideosByCategoryAndPage?category.id=" + categoryId.value + "&pageNum=" + currentPage;
	} else {
        action = "getVideosByTitleAndPage?title=" + videoTitle.value + "&pageNum=" + currentPage;
	}
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var json = request.responseText;
			showResultVideos(json);
		}
	};
}

function showResultVideos(json) {
	var videoList = eval('(' + json + ')');
	var divTop = document.getElementById("videoList");
	var videoListLength = videoList.length;
	var rows = 0;
	if (videoListLength % 4 != 0)
		rows = parseInt(videoListLength / 4) + 1;
	else
		rows = videoListLength / 4;
	for (var i = 0; i < rows; i++) {
		var divSecond = document.createElement('div');
		divSecond.setAttribute("class", "recommended-grids english-grid");
		divTop.appendChild(divSecond);

		var divThird_info = document.createElement('div');
		divThird_info.setAttribute("class", "recommended-info");
		divSecond.appendChild(divThird_info);
		divThird_info.innerHTML = "<div class='clearfix'> </div>";
		var relaxNum;
		if ((videoListLength % 4 == 0) || (videoListLength > 4 && i == 0))
			relaxNum = 4;
		else
			relaxNum = videoListLength % 4;
		for (var x = 0; x < relaxNum; x++) {
			video = videoList[i*4 + x];

			var divThird_md3 = document.createElement('div');
			divThird_md3.setAttribute("class", "col-md-3 resent-grid recommended-grid movie-video-grid");
			divSecond.appendChild(divThird_md3);

			var divForth_img = document.createElement('div');
			divForth_img.setAttribute("class", "resent-grid-img recommended-grid-img");
			divThird_md3.appendChild(divForth_img);

			var divFifth_a = document.createElement('a');
			divFifth_a.setAttribute("href", "video-" + video.id + ".html");
			divForth_img.appendChild(divFifth_a);
			divFifth_a.innerHTML = "<img class='img-rounded' style='height:220px;' src='showImg?video.view=" + video.view + "' alt='' />";

			var divFifth_time = document.createElement('div');
			divFifth_time.setAttribute("class", "time small-time show-time movie-time");
			divForth_img.appendChild(divFifth_time);
			divFifth_time.innerHTML = "<p>" + formatTime(video.duration) + "</p>";

			var divFifth_clck = document.createElement('div');
			divFifth_clck.setAttribute("class", "clck movie-clock");
			divForth_img.appendChild(divFifth_clck);
			divFifth_clck.innerHTML = "<span class='glyphicon glyphicon-time' aria-hidden='true'></span>";

			var divForth_info = document.createElement('div');
			divForth_info.setAttribute("class", "resent-grid-info recommended-grid-info recommended-grid-movie-info");
			divThird_md3.appendChild(divForth_info);

			var divForth_info_h5 = document.createElement('h5');
			divForth_info.appendChild(divForth_info_h5);
			divForth_info_h5.innerHTML = "<a href='video-" + video.id + "' class='title'>" + video.title + "</a>";

			var divForth_info_ul = document.createElement('ul');
			divForth_info.appendChild(divForth_info_ul);

			var divFifth_li_1 = document.createElement('li');
			divForth_info_ul.appendChild(divFifth_li_1);
			divFifth_li_1.innerHTML = "<p class='author author-info'><a href='#' class='author'>" + video.author.username + "</a></p>";

			var divFifth_li_2 = document.createElement('li');
			divFifth_li_2.setAttribute("class", "right-list");
			divForth_info_ul.appendChild(divFifth_li_2);
			divFifth_li_2.innerHTML = "<p class='views views-info'>" + video.click + "views</p>";


		}
		var divThird_clear = document.createElement('div');
		divThird_clear.setAttribute("class", "clearfix");
		divSecond.appendChild(divThird_clear);
	}
	if(videoList.length == 8){
		document.getElementById("currentPage").value ++;
	}else
		document.getElementById("getMoreBtn").setAttribute("disabled", "disabled");
}


function regist() {
	var request = getRequest();
	var registUsername = document.getElementById('registUsername').value;
	var registPassword = document.getElementById('registPassword').value;
	var registPasswordConfirm = document.getElementById('registPasswordConfirm').value;
	if(registUsername.length >18 || registUsername.length < 3){
		alert('用户名长度需为3到18位');
		return;
	}
	if (registPassword != registPasswordConfirm) {
		alert("两次密码输入不一致！");
	} else {
		if (registPassword.length < 6)
			alert("密码长度必须大于六位！");
		else if (registPassword.length > 18)
			alert("密码长度不能大于十八位！");else {
			var action = "regist?user.username=" + registUsername + "&user.password=" + registPassword;
			request.open("get", action, true);
			request.send();
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200) {
					var result = request.responseText;
					if (result.indexOf("registSuccess") != -1)
						alert("注册成功！");
					else if (result.indexOf("userExist") != -1)
						alert("用户已存在！");
					else
						alert("注册失败！");
					window.location.reload();
				}
			};

		}
	}

}



function login() {
	// var request = getRequest();
	// var loginUsername = document.getElementById('loginUsername').value;
	// var loginPassword = document.getElementById('loginPassword').value;
	// var action = "processLogin?username=" + loginUsername + "&password=" + loginPassword;
	// request.open("post", action, true);
	// request.send();
	$.ajax({
		type:"post",
		url:"processLogin",
		data:{
			"username" : $("#loginUsername").val(),
			"password" : $("#loginPassword").val()
		},
		// contentType:"application/json",
		async:false
	});

}



//从这里开始
//设置提示框的参数
toastr.options = {
    closeButton: false,
    debug: false,
    progressBar: false,
    positionClass: "toast-bottom-right", //显示位置
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

function getCategory() {
    var request = getRequest();
    var action = "getAllCategories";
    request.open("get", action, true);
    request.send();
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200) {
            var json = request.responseText;
            showCategory(json);
        }
    };
}

function showCategory(json) {
    var categoryList = eval('(' + json + ')');
    var otherVideos = document.getElementById("categoryOther");
    for (var i = 0; i < categoryList.length; i++) {
        var category = categoryList[i];
        if(!category.belongToOther){
            continue;
        }
        var id = "category" + (i + 1).toString();
        var li = document.createElement("li");
        otherVideos.appendChild(li);
        li.innerHTML = "<a href='category-" + category.id + ".html" + "'>" + category.name + "</a>";
    }
}

function createNewVideoModel(video) {
    var divTop = $("<div></div>");
    divTop.addClass("col-sm-6 col-md-4");

    var divSecond = $("<div></div>");
    divTop.append(divSecond);
    divSecond.addClass("thumbnail");

    var imgAndA = $("<a></a>");
    divSecond.append(imgAndA);
    imgAndA.addClass("lightBox");
    imgAndA.attr("href","/video-" + video.id + ".html");
	var videoImg = $("<img></img>");
	imgAndA.append(videoImg);
	videoImg.attr("src","/showImg?video.view=" + video.view);
	videoImg.attr("alt",video.title);

	var divInfo = $("<div></div>");
	divSecond.append(divInfo);
	divInfo.addClass("caption");
	var divTitle = $("<div></div>");
	divInfo.append(divTitle);
	divTitle.addClass("video-title");
	var title = $("<h3></h3>");
	divTitle.append(title);
	title.html("<span class='badge'>" + video.click + "views</span> " + video.title + "");
	var divVideoInfo = $("<div></div>");
    divInfo.append(divVideoInfo);
    divVideoInfo.addClass("video-info");
    var videoInfoP = $("<p></p>");
    divVideoInfo.append(videoInfoP);
    videoInfoP.text(video.info);
	var otherInfo = $("<ul></ul>");
	divInfo.append(otherInfo);
	var otherInfoUser = $("<li></li>");
	otherInfo.append(otherInfoUser);
	var userLogo = $("<span></span>");
	otherInfoUser.append(userLogo);
    userLogo.addClass("glyphicon glyphicon-user");
    var userName = $("<span></span>");
    otherInfoUser.append(userName);
    userName.html("<a href='/user-" + video.author.id + "'>" + video.author.nickname + "</a>");
    var otherInfoDuration = $("<li></li>");
	otherInfo.append(otherInfoDuration);
	otherInfoDuration.addClass("right-list");
    var timeLogo = $("<span></span>");
    otherInfoDuration.append(timeLogo);
    timeLogo.addClass("glyphicon glyphicon-time");
    var duration = $("<span></span>");
    otherInfoDuration.append(duration);
    duration.html(formatTime(video.duration));

    return divTop;
}

function getPopularVideos() {
	$.ajax({
        type:"get",
        url:"getLatestVideos",
        // contentType:"application/json",
        async:true,
		success : showPopularVideos
	});
}

function showPopularVideos(videoList) {
	for(var i = 0; i < videoList.length; i++) {
        var divTop = createNewVideoModel(videoList[i]);
        $('#mostPopular').append(divTop);
	}
}

function getRecommendVideos() {
    $.ajax({
        type:"get",
        url:"getRecommendVideos",
        // contentType:"application/json",
        async:true,
        success : showRecommendVideos
    });
}

function showRecommendVideos(videoList) {
	for(var i = 0; i < videoList.length; i++) {
		var video = videoList[i];
		if(i == 0) {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>'));
            var divSingle = $('<div class="item active"></div>');
            divSingle.append($('<img src="/showImg?isBig=true&video.view=' + video.view + '" alt="First slide"></img>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        } else {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '"></li>'));
            var divSingle = $('<div class="item"></div>');
            divSingle.append($('<img src="/showImg?isBig=true&video.view=' + video.view + '" alt="First slide"></img>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
		}

	}
}

//TODO
function likeVideo(btnId) {

}

function lightHeart(btnId) {
    $("#"+btnId).css("color","red");
    $("#"+btnId).children().removeClass("glyphicon-heart-empty");
    $("#"+btnId).children().addClass("glyphicon-heart");
}

function clearHeart(btnId) {
    $("#"+btnId).css("color","black");
    $("#"+btnId).children().removeClass("glyphicon-heart");
    $("#"+btnId).children().addClass("glyphicon-heart-empty");
}

function getPopularList() {
    $.ajax({
        type:"get",
        url:"getRecommendVideos?pageSize=6",
        // contentType:"application/json",
        async:true,
        success : showPopularList
    });
}

function showPopularList(videoList) {
	for(var i = 0; i < videoList.length; i++) {
		var video = videoList[i];
		var li = $("<li class='video-li'></li>");
		$("#popular-list").append(li);
		var div_top = $('<div class="media"></div>');
		li.append(div_top);
		var a_img = $('<a class="media-left"></a>');
		div_top.append(a_img);
		a_img.attr("href","video-" + video.id + ".html");
		a_img.append($('<img class="media-object img-rounded video-img" src="/showImg?video.view=' + video.view + '" alt="head">'));
		var div_info = $('<div class="media-body"></div>');
		div_top.append(div_info);
		div_info.append($('<p class="media-heading video-title">' + video.title + '</p>'));
		var info_p = $("<p></p>");
		div_info.append(info_p);
		info_p.append($('<span class="video-info">' + video.author.nickname + '</span>'));
		info_p.append($('<br>'));
		info_p.append($('<span class="video-info">' + video.click + '次观看</span>'));
	}
}


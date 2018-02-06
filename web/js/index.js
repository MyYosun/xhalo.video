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
	getRecommandByCategoryAndPage(1, 1);
	getRecommandByCategoryAndPage(2, 1);
	getRecommandByCategoryAndPage(3, 1);
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

function getRecommandByCategoryAndPage(category, currentPage) {
	var request = getRequest();
	var action = "getRecommandVideosByCategoryAndPage?video.category.id=" + category + "&currentPage=" + currentPage;
	var json;
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			json = request.responseText;
			showRecommandFirst(json, category);
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
		divThird_img_a.innerHTML = "<img height='300px' src='showImg?video.view=" + video.view + "' alt='' />";

		var divThird_img_time = document.createElement('div');
		divThird_img_time.setAttribute("class", "time");
		divThird_img.appendChild(divThird_img_time);
		divThird_img_time.innerHTML = "<p>" + formatTime(video.duration) + "</p>";

		//谢浩截止

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

function showRecommandFirst(json, id) {
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
		divThird_img_a.innerHTML = "<img stye='height:220px;' src='showImg?video.view=" + video.view + "' alt='' />";

		var divThird_img_time = document.createElement('div');
		divThird_img_time.setAttribute("class", "time small-time slider-time");
		divThird_img.appendChild(divThird_img_time);
		divThird_img_time.innerHTML = "<p>" + formatTime(video.duration) + "</p>";

		//谢浩截止

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
		divThird_1_a.innerHTML = "<img style='height:9%;' src='showImg?video.view=" + video.view + "' alt='' />";
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
	for (var i = 0; i < categoryList.length; i++) {
		var category = categoryList[i];
		var id = "category" + (i + 1).toString();
		var li = document.getElementById(id);
		if ((i + 1) != 1)
			li.innerHTML = "<a href='category-" + category.id + ".html" + "'>" + category.name + "</a>";
		else
			li.innerHTML = "<a href='category-" +
				category.id + ".html" + "' class='user-icon'><span class='glyphicon glyphicon-home glyphicon-blackboard' aria-hidden='true'></span>" +
				category.name + "</a>";
	}
}
function getResultVideos() {
	var request = getRequest();
	var categoryId = document.getElementById("categoryId");
	var videoTitle = document.getElementById("videoTitle");
	var currentPage = document.getElementById("currentPage").value;
	var action;
	if (categoryId != null) {
		action = "getVideosByCategoryAndPage?video.category.id=" + categoryId.value + "&currentPage=" + currentPage;
	} else {
		action = "getVideosByTitleAndPage?video.title=" + videoTitle.value + "&currentPage=" + currentPage;
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
			video = videoList[x];

			var divThird_md3 = document.createElement('div');
			divThird_md3.setAttribute("class", "col-md-3 resent-grid recommended-grid movie-video-grid");
			divSecond.appendChild(divThird_md3);

			var divForth_img = document.createElement('div');
			divForth_img.setAttribute("class", "resent-grid-img recommended-grid-img");
			divThird_md3.appendChild(divForth_img);

			var divFifth_a = document.createElement('a');
			divFifth_a.setAttribute("href", "video-" + video.id + ".html");
			divForth_img.appendChild(divFifth_a);
			divFifth_a.innerHTML = "<img style='height:220px;' src='showImg?video.view=" + video.view + "' alt='' />";

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

function getCategoryOption() {
	var request = getRequest();
	var action = "getAllCategories";
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var json = request.responseText;
			showCategoryOption(json);
		}
	};
}
function showCategoryOption(json) {
	var categoryList = eval('(' + json + ')');
	var select = document.getElementById('categoryId');
	for (var i = 0; i < categoryList.length; i++) {
		var category = categoryList[i];
		var option1 = document.createElement('option');
		option1.setAttribute("value", category.id);
		option1.innerHTML = category.name;
		select.appendChild(option1);
	}
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
	var request = getRequest();
	var loginUsername = document.getElementById('loginUsername').value;
	var loginPassword = document.getElementById('loginPassword').value;
	var action = "login?user.username=" + loginUsername + "&user.password=" + loginPassword;
	request.open("get", action, true);
	request.send();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var result = request.responseText;
			if (result.indexOf("loginSuccess") != -1)
				alert("登录成功！");
			else
				alert("用户名/密码错误！");
			window.location.reload();
		}
	}

}

function uploadCheck(){
	if(document.getElementById("isExist").value == "false"){
		alert('请先登录!');
	}else{
		document.getElementById("uploadBtn").removeAttribute("disabled");
	}
	
}
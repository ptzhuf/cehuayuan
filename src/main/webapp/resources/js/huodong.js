/**
 * 请求地址.
 */
var HUODONG_DISCOVER_URL = "huodong/discover.do";
var AUTO_DISCOVER_URL = "huodong/autoDiscover.do";
var AUTO_SIGN_UP = "huodong/autoSignUp";
/**
 * 定时器名称.
 */
var timerName;
/**
 * 版本号.
 */
var version = 1.1;
var HUODONG_STATUS = {
	OPENED : 1,
	UNOPENED : 2,
	UNCREATE : 3
};
$(document).ready(function() {
	init();
	// document.cookie="name=zhufu_value; path=/; domain=.baidu.com";
});
var init = function() {
	$("#submitBtn").click(activeDiscover);
	$("#clearTimerBtn").click(closeDiscover);
	$("#autoDiscoverBtn").click(autoDiscover);
	$("#testBtn").click(testSomething);
	var randomWidth = Math.floor(Math.random() * 5 + 1);
	var randomNumber = Math
			.floor(Math.random() * Math.pow(10, randomWidth) + 1); // 1-10
	$("#emailText").val(randomNumber + "@" + randomNumber + ".com");
	$("#bodyText").val(randomNumber);
	initTour();
	// $("#testBtn").click(test);
	// topicId = $("#topicId").val();
};

/**
 * 测试.
 */
var testSomething = function() {
	$.ajax({

		type : 'GET',
		url : "http://dev.currency.shop.17173.com/api/branch/getlist",
		data : {},
		success : function(data) {
			for ( var r in data.data) {
				alert(r.id + " : " + r.name);
			}
		}
	});
	// alert("xxx");
};

/**
 * 激活活动探索.
 */
var activeDiscover = function() {
	submitQuery();
	// timerName = setInterval(submitQuery, 20000);
	// 六秒一次
	timerName = setInterval(submitQuery, 6000);
	$("#submitBtn").prop("disabled", true);
	var cookieValue = $("#cookieTextarea").val();
	if (cookieValue != null && cookieValue != "") {
		var expires = new Date();
		/* 1000个月 x 一个月当作 30 天 x 一天 24 小时 x 一小时 60 分 x 一分 60 秒 x 一秒 1000 毫秒 */
		expires.setTime(expires.getTime() + 1000 * 30 * 24 * 60 * 60 * 1000);
		document.cookie = $.trim("huodong_cehuayuan_cookie="
				+ escape(cookieValue) + ';expires=' + expires.toGMTString());
	}
};

/**
 * 关闭定时器.
 */
var closeDiscover = function() {
	clearInterval(timerName);
	$("#submitBtn").prop("disabled", false);
};

/**
 * 自动探索即将开放的活动URL.
 */
var autoDiscover = function() {
	$.ajax({
		type : 'POST',
		url : AUTO_DISCOVER_URL,
		data : {
			huodongListUrl : $("#huodongListUrlText").val(),
			cookie : $("#cookieTextarea").val()
		},
		success : function(data) {
			$("#urlInput1").val(data[0].url);
			$("#currentStatusA1").html("活动还未开放");
			$("#currentStatusA1").prop("href", data[0].url);
			$("#urlInput2").val(data[1].url);
			$("#currentStatusA2").html("活动还未开放");
			$("#currentStatusA2").prop("href", data[1].url);
			alert("探索完成,请点击开启查询");
		}
	});
};

/**
 * 提交查询.
 */
var submitQuery = function() {
	$.ajax({
		type : 'POST',
		url : HUODONG_DISCOVER_URL,
		data : $("#queryForm").serializeArray(),
		success : function(data) {
			var idIndexAcc = 0;
			$(data).each(function(index, element) {
				var idIndex = index + 1;
				var urlInputId = "#urlInput" + idIndex;
				var currentStatusId = "#currentStatusA" + idIndex;
				$(urlInputId).val(element.url);
				switch (element.currentStatus) {
				case HUODONG_STATUS.OPENED:
					$(currentStatusId).html("活动已经开放请及时进入活动页面");
					$(currentStatusId).prop("href", element.url);
					// alert("活动已经开放请及时登录活动页面<a href=\"" + element.url
					// + "\" target=\"_blank\">点击前往</a>");
					// 根据index，做累加判断，判断是否需要关闭
					idIndexAcc += 1;
					if ($("#isAutoSignUpCheckbox").prop("checked")) {
						autoSignUp(idIndex);
					} else {
						location.href = $("#mailA").prop("href");
						location.href = element.url;
					}
					break;
				case HUODONG_STATUS.UNOPENED:
					$(currentStatusId).html("活动还未开放");
					$(currentStatusId).prop("href", element.url);
					break;
				case HUODONG_STATUS.UNCREATE:
					$(currentStatusId).html("活动还未创建");
					$(currentStatusId).prop("href", element.url);
					break;
				}
			});
			// 如果每个url都是开放的，就停止定时器，即acc与data大小相等
			if (idIndexAcc == data.length) {
				closeDiscover();
			}
		}
	});
};
/**
 * 自动报名.
 */
var autoSignUp = function(idIndex) {
	var urlInputId = "#urlInput" + idIndex;
	// 查询参数
	var params = $("#autoSignUpForm").serializeArray();
	var urlInput = $(urlInputId);
	var name = urlInput.prop('name');
	var value = urlInput.val();
	params.push({
		"name" : "url",
		"value" : value
	});
	var name = $("#cookieTextarea").prop('name');
	var value = $("#cookieTextarea").val();
	params.push({
		"name" : name,
		"value" : value
	});
	$.ajax({
		async : false,
		type : 'POST',
		url : AUTO_SIGN_UP,
		data : params,
		success : function(data) {
			urlInput.popover({
				content : data,
				delay : {
					show : 0,
					hide : 500
				}
			});
			urlInput.popover("show");
			setTimeout(function() {
				urlInput.popover("destroy")
			}, 5000);
		}
	});
};
/**
 * 初始化tour.
 */
var initTour = function() {

}
/**
 * 打开回复对话框.
 * 
 * @param {String}
 *            commentId commentId
 */
var openReplyDIV = function(commentId) {
	$("#replyDiv").dialog({
		width : "auto",
		close : function() {
			// 清空内容
			$("#replyContent").val("");
		}
	});
	$("#replyCommentId").val(commentId);
};
/**
 * 取cookies函数.
 */
var getCookie = function(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return unescape(arr[2]);
	return null;
};
/**
 * 设置cookies函数.
 */
var setCookie = function(name, value) {// 两个参数，一个是cookie的名子，一个是值
	var Days = 30; // 此 cookie 将被保存 30 天
	var exp = new Date(); // new Date("December 31, 9998");
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
};
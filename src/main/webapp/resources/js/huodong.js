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
			$("#urlInput").val(data.url);
			$("#currentStatusA").html("活动还未开放");
			$("#currentStatusA").prop("href", data.url);
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
			$("#urlInput").val(data.url);
			switch (data.currentStatus) {
			case HUODONG_STATUS.OPENED:
				$("#currentStatusA").html("活动已经开放请及时进入活动页面");
				$("#currentStatusA").prop("href", data.url);
				// alert("活动已经开放请及时登录活动页面<a href=\"" + data.url
				// + "\" target=\"_blank\">点击前往</a>");
				closeDiscover();
				if ($("#isAutoSignUpCheckbox").prop("checked")) {
					autoSignUp();
				} else {
					location.href = $("#mailA").prop("href");
					location.href = data.url;
				}
				break;
			case HUODONG_STATUS.UNOPENED:
				$("#currentStatusA").html("活动还未开放");
				$("#currentStatusA").prop("href", data.url);
				break;
			case HUODONG_STATUS.UNCREATE:
				$("#currentStatusA").html("活动还未创建");
				$("#currentStatusA").prop("href", data.url);
				break;
			}
		}
	});
};
/**
 * 自动报名.
 */
var autoSignUp = function() {
	// 查询参数
	var params = $("#autoSignUpForm").serializeArray();
	var name = $("#urlInput").prop('name');
	var value = $("#urlInput").val();
	params.push({
		"name" : name,
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
			alert(data);
		}
	});
};
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

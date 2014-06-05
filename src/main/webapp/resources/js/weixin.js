/**
 * 请求地址.
 */
var HUODONG_DISCOVER_URL = "huodong/discover.do";
var AUTO_DISCOVER_URL = "huodong/autoDiscover.do";
var AUTO_SIGN_UP = "huodong/autoSignUp";
var WEIXIN_REPLY_URL = "weixin/reply";
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
	$("#signupBtn").click(signup);
	$("#closeSignupBtn").click(closeSignup);
	$("#closeSignupBtn").button("loading");

};
var signup = function() {
	$("#signupBtn").button("loading");
	$("#closeSignupBtn").button("reset");
	// 六秒一次
	timerName = setInterval(submitQuery, 6000);
};
var submitQuery = function() {
	$.ajax({
		type : 'POST',
		url : WEIXIN_REPLY_URL,
		data : $("#replyForm").serializeArray(),
		success : function(data) {
			if (data.currentStatus == HUODONG_STATUS.OPENED) {
				// 关闭定时回复
				closeSignup();
				// 弹出提示
				$("#zhouyeImg").popover({
					content : "恭喜你，报名成功~！！！",
					delay : {
						show : 0,
						hide : 500
					}
				});
				$("#zhouyeImg").popover("show");
				setTimeout(function() {
					$("#zhouyeImg").popover("destroy")
				}, 5000);
			}
		}
	});
};
var closeSignup = function() {
	$("#signupBtn").button("reset");
	$("#closeSignupBtn").button("loading");
	closeTimer();
};
/**
 * 关闭定时器.
 */
var closeTimer = function() {
	clearInterval(timerName);
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
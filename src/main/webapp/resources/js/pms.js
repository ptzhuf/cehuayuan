/**
 * 请求地址.
 */
var HUODONG_DISCOVER_URL = "huodong/discover.do";
var AUTO_DISCOVER_URL = "huodong/autoDiscover.do";
var AUTO_SIGN_UP = "huodong/autoSignUp";
var WEIXIN_REPLY_URL = "weixin/reply";
var PMS_STAT_URL = "pms/stat";
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
	$("#statBtn").click(stat);
};
var stat = function() {
	$.ajax({
		type : 'POST',
		url : PMS_STAT_URL,
		data : $("#statForm").serializeArray(),
		beforeSend : function() {
			$("#statBtn").button("loading");
		},
		success : function(data) {
			// 弹出提示
			$("#zhouyeImg").popover({
				content : "统计完成，ご主人さま，あなた的统计结果是" + data + "~！！！",
				delay : {
					show : 0,
					hide : 500
				}
			});
			$("#zhouyeImg").popover("show");
			setTimeout(function() {
				$("#zhouyeImg").popover("destroy")
			}, 5000);
		},
		complete : function() {
			$("#statBtn").button("reset");
		}
	});
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
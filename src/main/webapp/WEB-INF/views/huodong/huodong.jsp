<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<base href="<%=basePath%>">
<title>活动自动探索-RELEASE 0.1</title>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="resources/jquery/jquery-ui-1.10.0.custom.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
/*#content td {
	border : 1px;
	border-style: solid;
}*/
</style>
<!-- <script type="text/javascript" src="resources/jquery/jquery-1.9.0.js"></script> -->
<script type="text/javascript"
	src="resources/jquery/jquery-ui-1.10.0.js"></script>
<script type="text/javascript" src="resources/js/huodong.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body
	style="font-family: 造字工房版黑 G0v1 常规体, 造字工房丁丁手绘 G1v1 常规体, 微软雅黑; font-size: 26px; margin-left: 150px; margin-top: 50px; margin-right: 150px;">
	<meta http-equiv=Set-Cookie
		content="ASP.NET_SessionId=1234; expires=86400000; path=/">
	<div align="center" style="margin-bottom: 50px;">
		<div>
			<span class="text-success">Come on~~ It's RELEASE 1.0.
				提供建议请到讨论组- -.</span><br /> <br /> <span>
		</div>
		<div>
			<ul class="list-group" style="text-align: left">
				<span class="alert alert-warning list-group-item">使用方法(因为暂时没办法让弹窗弹到最前，所以用邮件提示，自动跳转到页面，
					<strong style="color: red;">如果开启自动报名将不再弹邮件</strong>)：
				</span>
				<a class="list-group-item" href="huodong#autoDiscoverBtnA">1.点击自动探索即将开放URL</a>
				<a class="list-group-item" href="huodong#queryActBtnA">2.单击"启动查询活动"(每20秒一次)</a>
			</ul>
			<br />
			<p class="text-danger">仅限内部使用请勿外传</p>
			</span>
		</div>
	</div>

	<div align="center" style="margin-bottom: 15px;">
		<span>使用这个url可以进行相关功能测试，测试活动上限人数为5，http://10.5.17.74/whatEvents.aspx/T-115</span>
	</div>

	<table id="queryTable"
		class="table table-bordered table-hover table-striped">
		<tbody>

			<form id="queryForm">
				<tr>
					<td>Cookie</td>
					<td><textarea rows="15" cols="120" name="cookie"
							id="cookieTextarea" class="form-control"></textarea></td>
					<td><p class="text-danger">
							cookie必须是单行的，可以通过firefox访问策花园，打开firebug，点击网络打开任意一个请求，查看请求头信息，原始头信息，拷贝其中的cookie值（如下图）到左框中<br />
							<img alt="拷贝步骤" src="resources/image/step.jpg"
								class="img-rounded" />
						</p></td>

				</tr>
				<tr>
					<td>活动url:</td>
					<td><input type="text" id="urlInput" name="url" size="40"
						maxlength="100" value="" class="form-control" /></td>
					<td><a name="queryActBtnA" id="queryActBtnA"></a><input
						type="button" id="submitBtn" value="启动查询活动"
						class="btn btn-default" /><input type="button" id="clearTimerBtn"
						value="停止查询活动" class="btn btn-default" /><span
						class="text-danger"> <input type="checkbox"
							id="isAutoSignUpCheckbox" name="isAutoSignUp" value=""> <label
							for="isAutoSignUpCheckbox">自动报名开放的活动 </label></input></span></td>
				</tr>

			</form>
			<tr>
				<td width="330">获取活动列表的url<br /> <span class="text-danger">如果是健身活动,可不填写</span>
				</td>
				<td><input id="huodongListUrlText" type="text"
					name="huodongListUrl" size="40" class="form-control" /></td>
				<td><a name="autoDiscoverBtnA" id="autoDiscoverBtnA"></a><input
					type="button" id="autoDiscoverBtn" value="自动探索即将开放的活动URL"
					class="btn btn-default" /></td>
			</tr>
		</tbody>
	</table>

	<!-- <p>
						你不知道我为什麽狠下心 盘旋在你看不见的高空里 <strong style="color: red;">多的是
							你不知道的事</strong>
					</p>
					<p>
						<strong style="color: red;">不知道的事将在下一版本公布。。。。</strong>
					</p> -->

	<table
		style="border: 10px; border-style: solid; border-color: green; margin-top: 10px;">
		<tr>
			<td style="text-align: center; font-size: 20px;">当前状态：<a
				id="currentStatusA" href="" target="_blank">未探索</a></td>
		</tr>
	</table>

	<!-- <table>
		<form id="autoSignUpForm">
			<tr>
				<td>邮箱:</td>
				<td><input type="text" name="email" value="" id="emailText"
					class="form-control" /></td>
			</tr>
			<tr>
				<td>申请留言:</td>
				<td><input type="text" name="body" id="bodyText"
					class="form-control" /></td>
			</tr>
		</form>
	</table> -->
	<table>
		<form id="autoSignUpForm">
			<tr>
				<td>邮箱:</td>
				<td><input type="text" name="email" value="" id="emailText"
					class="form-control" /></td>
			</tr>
			<tr>
				<td>申请留言:</td>
				<td><input type="text" name="body" id="bodyText"
					class="form-control" /></td>
			</tr>
		</form>
	</table>

	<table>
		<tr>
			<td>过往的活动列表：(未开放)</td>
		</tr>
		<tr>
			<td>当前查询的活动页面截图：(未开放)</td>
		</tr>
	</table>


	<table>
		<tr>
			<td><a id="mailA"
				href="mailto:17173-dev1@cyou-inc.com?subject=活动了&body=我真是个大傻逼。。。想报名吗，点击发送吧~">发邮件</a><input
				type="button" value="孤单的测试按钮请不要理我~~~" class="btn btn-default" /><input
				type="button" id="testBtn" value="测试" class="btn btn-default" /></td>
			<!-- 邮件参数里 cc是抄送，bcc是密件抄送，subject是主题，body是内容 -->
		</tr>
	</table>

	<div style="margin-top: 30px;">
		<strong style="color: red;">版本号： 1399604044195</strong>
	</div>

	<div style="margin-top: 30px;">
		<strong style="color: red;">我怎么可能告诉你，活动不用开放也可以报名的呢。<img
			alt="" src="resources/image/yun.gif">那样会被抓的~~~
		</strong><br /> <strong style="color: red;">我怎么可能告诉你，活动报上名的人还可以踢掉呢。<img
			alt="" src="resources/image/yun.gif">臣妾做不到啊~~~
		</strong>
	</div>

</body>
</html>
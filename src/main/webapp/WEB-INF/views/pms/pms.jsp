<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<base href="<%=basePath%>">
<title>pms统计 0.1-alpha</title>
<!-- 图标 -->
<link rel="shortcut icon" type="image/ico"
	href="resources/image/zt_128X128.ico" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link href="resources/jquery/jquery-ui-1.10.0.custom.css"
	rel="stylesheet" type="text/css" />
<link href="resources/css/bootstrap-tour.css" rel="stylesheet" />
<style type="text/css">
/*#content td {
	border : 1px;
	border-style: solid;
}*/
.starter-template {
	padding: 40px 15px;
	text-align: center;
}

.my-tour-title {
	font-family: 微软雅黑;
	font-size: 16px;
}

h1 {
	font-family: 微软雅黑;
}
</style>
</head>
<body style="font-family: 微软雅黑; font-size: 20px;">

	<div id="containerDiv" classs="container">
		<div class="starter-template">
			<p class="lead">
				pms工时统计系统.<br> alpha.
			</p>
		</div>
	</div>

	<div>
		<form id="statForm" role="form" class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="cookieInput">PMS中的cookie</label>
				<div class="col-sm-10">
					<input name="cookie" type="text" class="form-control"
						id="cookieInput"></input>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="regexInput"
					data-toggle="tooltip" title="一般是.*支付平台.*的模式">项目名正则</label>
				<div class="col-sm-10">
					<input name="projectKeyWord" type="text" class="form-control"
						id="regexInput" value=".*.*"><small>一般是.*支付平台.*的模式</small></input>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="startTimeInput"
					data-toggle="tooltip" title="2014-01-01">开始时间</label>
				<div class="col-sm-10">
					<input name="startTime" type="text" class="form-control"
						id="startTimeInput" value="" placeholder="2014-01-01"><small>eg
						: 2014-01-01</small></input>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="endTimeInput"
					data-toggle="tooltip" title="2014-06-01">结束时间</label>
				<div class="col-sm-10">
					<input name="endTime" type="text" class="form-control"
						id="endTimeInput" value=""><small>eg : 2014-06-30</small></input>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="statBtn" type="button" data-loading-text="正在统计..."
						class="btn btn-default">统计</button>
				</div>
			</div>
		</form>
	</div>

	<div id="footer" class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<img id="zhouyeImg" src="resources/image/zt_128X128.ico"
				alt="我是区域Boss宙爷" class="img-rounded" data-toggle="popover"
				data-container="body" data-placement="top" /> <span
				class="text-muted">完全开源随意使用，被抓不要犯傻就行~.</span>
		</div>
	</div>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
	<!-- jqueryUi 插件必须先于bootStrap载入 不然button不能正常调用方法，比如loading 见http://stackoverflow.com/questions/13235578/bootstrap-radio-buttons-toggle-issue http://blog.csdn.net/remote_roamer/article/details/14105999?reload -->
	<script type="text/javascript"
		src="resources/jquery/jquery-ui-1.10.0.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-tour.js"></script>
	<!-- <script type="text/javascript" src="resources/jquery/jquery-1.9.0.js"></script> -->
	<script type="text/javascript" src="resources/js/pms.js"></script>
</body>
</html>
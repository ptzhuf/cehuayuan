<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<base href="<%=basePath%>">
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
<link href="resources/css/bootstrap-tour.min.css" rel="stylesheet" >
<script src="resources/js/bootstrap-tour.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var tour = new Tour(
								{
									name : "tour",
									steps : [ {
										element : "#demo",
										placement : "bottom",
										title : "Title of my step",
										backdrop : true,
										content : "Content of my step"
									}, {
										element : "#demo2",
										placement : "top",
										title : "Title of my step",
										backdrop : true,
										content : "Content of my step"
									} ],
									container : "body",
									keyboard : true,
									storage : window.localStorage,
									debug : false,
									backdrop : false,
									redirect : true,
									orphan : false,
									duration : false,
									basePath : "",
									template : "<div class='popover tour'>			    <div class='arrow'></div>			    <h3 class='popover-title'></h3>			    <div class='popover-content'></div>			    <div class='popover-navigation'>			        <button class='btn btn-default' data-role='prev'>« Prev</button>			        <span data-role='separator'>|</span>			        <button class='btn btn-default' data-role='next'>Next »</button>			    </div>			    <button class='btn btn-default' data-role='end'>End tour</button>			    </nav>			  </div>",
									afterGetState : function(key, value) {
									},
									afterSetState : function(key, value) {
									},
									afterRemoveState : function(key, value) {
									},
									onStart : function(tour) {
									},
									onEnd : function(tour) {
									},
									onShow : function(tour) {
									},
									onShown : function(tour) {
									},
									onHide : function(tour) {
									},
									onHidden : function(tour) {
									},
									onNext : function(tour) {
									},
									onPrev : function(tour) {
									},
									onPause : function(tour, duration) {
									},
									onResume : function(tour, duration) {
									}
								});
						/**var tour = new Tour({
							steps : [ {
								element : "#demo",
								placement : "bottom",
								title : "Title of my step",
								backdrop : true,
								content : "Content of my step"
							}, {
								element : "#demo2",
								placement : "top",
								title : "Title of my step",
								backdrop : true,
								content : "Content of my step"
							} ]
						});

						// Initialize the tour
						tour.init();
						tour.start();
						var a = 1;**/
					});
</script>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<div id="demo"></div>
	<div id="demo2"></div>

	<table style="margin: 50px;">
		<tr>
			<td><div>
					<a href="cardInfo/list">全表</a>
				</div></td>
		</tr>
		<tr>
			<td><div>
					<a href="cardInfo/listByPage">分页</a>
				</div></td>
		</tr>
		<tr>
			<td><div>
					<form action="cardInfo/activeScan" method="post">
						休眠间隔时间（每次查询）:<input type="text" name="sleepTime" value="100" />
						end number:<input type="text" name="endNum" /> start number(like
						8059111060000):<input type="text" name="startNum" /> interval:<input
							type="text" name="interval" value="1" /> <input type="submit"
							value="激活后台扫描" />
						<c:out value="${message }" />
					</form>
				</div></td>
		</tr>
	</table>
</body>
</html>

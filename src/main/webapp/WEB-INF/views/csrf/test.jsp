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
<link href="resources/css/bootstrap-tour-standalone.min.css"
	rel="stylesheet">
<script src="resources/jquery/jquery-1.9.0.js"></script>
<script src="resources/js/bootstrap-tour-standalone.min.js"></script>
<script type="text/javascript">
	//Instance the tour
	var tour = new Tour({
		steps : [ {
			element : "#demo",
			title : "Title of my step",
			placement : "right",
			container : "body",
			content : "Content of my step"
		}, {
			element : "#demo2",
			title : "Title of my step",
			placement : "left",
			container : "body",
			content : "Content of my step"
		} ]
	});

	// Initialize the tour
	tour.init();

	// Start the tour
	tour.start();
</script>
</head>
<body>
	<div id="demo">123</div>
	<div id="demo2">321</div>
	<h1>Hello world!</h1>
	<img alt="" src="resources/image/zhuru.bmp" />
	<!--  -->
	<script src="resources/image/zhuru.bmp">
		
	</script>
	<P>The time on the server is ${serverTime}.</P>
	<table style="margin: 50px;">
		<tr>
			<td><div>
					<a
						href="https://pay.test.17173.com/pp-service-impl/front/order/createOrder4G.do?content=%E8%B4%AD%E4%B9%B0G%E5%AE%9D&goodsName=G%E5%AE%9D%E8%B4%AD%E4%B9%B0&dicId=999801&account=cuishijie%4017173.com&unid=87038970&payAccount=123%4017173.com&totalPrice=10&notifyMobile=18650366687">来买G宝吧</a>
				</div></td>
		</tr>
	</table>
</body>
</html>

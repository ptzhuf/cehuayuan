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
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
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
					<form action="cardInfo/activeScan" method="post">休眠间隔时间（每次查询）:<input
							type="text" name="sleepTime" value="100" /> end number:<input type="text" name="endNum" />
						start number(like 8059111060000):<input type="text"
							name="startNum" />
						interval:<input type="text" name="interval" value="1"/> <input type="submit"
							value="激活后台扫描" />
						<c:out value="${message }" />
					</form>
				</div></td>
		</tr>
	</table>
</body>
</html>

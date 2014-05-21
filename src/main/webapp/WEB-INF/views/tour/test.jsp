<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="resources/css/bootstrap-tour-standalone.min.css"
	rel="stylesheet">
<script src="resources/jquery/jquery-1.9.0.js"></script>
<script src="resources/js/bootstrap-tour-standalone.min.js"></script>
<script type="text/javascript">
	var tour = new Tour(
			{
				debug : true,
				steps : [
						{
							element : "#demo",
							placement : "bottom",
							title : "Welcome to Bootstrap Tour!",
							orphan : true,
							content : "Introduce new users to your product by walking them through it step by step."
						},
						{
							element : "#demo1",
							placement : "top",
							title : "A super simple setup",
							orphan : true,
							content : "Easy is better, right? The tour is up and running with just a few options and steps."
						}]
			});
	tour.init();
	tour.restart();
</script>
</head>
<body>
	<div id="top"></div>
	<div id="demo" name="demo"></div>
	<div id="demo1" name="demo1"></div>
	<div id="bottom"></div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>InstaKgram</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp">
			</c:import>

		</div>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					
					 <h1>최고의 성형은 다이어트!!</h1>
					<img src="/InstaKgram/assets/images/main.jpg" id="mainPicture">
					
					<p>
					
					<h4>인스타키로그램에서</h4>

					<h3>목표를 공유하고</h3>
					<h3>하루하루를 기록하세요!</h3>

					</p>
				</div>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp">
			</c:import>



		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp">
			</c:import>

		</div>
	</div>
</body>
</html>
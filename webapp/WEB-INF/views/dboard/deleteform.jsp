<%@ page contentType="text/html;charset=UTF-8" %>


<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/InstaKgram/assets/css/dboard.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container"> 
		<div id="header">
			<jsp:include page = "/WEB-INF/views/include/header.jsp" flush="false"/>
		</div>
		<div id="content">
			<div id="dboard" class="delete-form">
				<form method="post" action="/InstaKgram/dboard/delete">
					<input type='hidden' name="no" value="${param.no}">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="/InstaKgram/dboard/index">방명록 리스트</a>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page = "/WEB-INF/views/include/navigation.jsp" flush = "false"/>
		</div>
		<div id="footer">
			<jsp:include page = "/WEB-INF/views/include/footer.jsp" flush = "false"/>
		</div>
	</div>
</body>
</html>
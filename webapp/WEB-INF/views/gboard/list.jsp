<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>      

<title>InstaKgram</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/InstaKgram/assets/css/gboard.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp" />
		</div>
		<div id="content">
			<div id="gboard">
				<form id="search_form" action="/InstaKgram/gboard/search" method="post">
				
					<input type="text" id="content" name="content" value=""> 
					<input type="submit" value="찾기">
				</form>



				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${vo.no}</td>
							<td><a
								href="/InstaKgram/gboard/view?no=${vo.no}&view_cnt=${vo.view_cnt}">${vo.title}</a></td>
							<td><a
								href="/InstaKgram/dboard/searchDBoard?member_no=${vo.member_no}">${vo.member_name}</a></td>
							<td>${vo.view_cnt}</td>
							<td>${vo.reg_date}</td>
							<td><a href="/InstaKgram/gboard/delete?no=${vo.no}" class="del">삭제</a></td>
						</tr>

					</c:forEach>
				</table>
			
			
			<c:if test="${ !empty authMember }">
			
				<div class="bottom">
					<a href="/InstaKgram/gboard/write" id="new-book">글쓰기</a>
				</div>
				
				</c:if>
				
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation_board.jsp" />
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp" />
		</div>
	</div>
</body>
</html>
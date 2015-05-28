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
	
	 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

  
  
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp" />
		</div>
		<div id="content">
			<div id="gboard">
				<form id="search_form" action="/InstaKgram/gboard/search" method="post">
				<select name = "search_what" class="form-control" style = "width : 20%; float:left;"> 
 
   <option value = "title" selected = "selected">제목</option>
   <option value = "content"  >내용</option>
   <option value = "member_name" >글쓴이</option>
  </select>
					<input type="text" id="content" name="content" value=""  class="form-control" style = "width : 55%;float:left;" > 
					<input type="submit" value="찾기" class="btn btn-primary"style = "width : 10%;">
					<option>
				</form>



				<table class="table table-hover">
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
							<td>${status.count}</td>
							<td><a
								href="/InstaKgram/gboard/view?no=${vo.no}&view_cnt=${vo.view_cnt}">${vo.title}</a></td>
							<td><a
								href="/InstaKgram/dboard/searchDBoard?member_no=${vo.member_no}">${vo.member_name}</a></td>
							<td>${vo.view_cnt}</td>
							<td>${vo.reg_date}</td>
												<c:if test="${authMember.name == vo.member_name}">
							
							<td><a href="/InstaKgram/gboard/delete?no=${vo.no}" class="del"><img src = "/InstaKgram/assets/images/recycle.png" ></a></td>
					</c:if>
						</tr>

					</c:forEach>
				</table>
			
			
			<c:if test="${ !empty authMember }">
			
				<div class="bottom">
					<a href="/InstaKgram/gboard/write" id="new-book"  class="btn btn-default">글쓰기</a>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/InstaKgram/assets/css/gboard.css" rel="stylesheet" type="text/css">
	 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp" />
		</div>
		<div id="content">
			<div id="gboard" class="board-form">
				<table class="table table-hover" id = "table2">
				
					<tr>
					
						<th>	
							${list.title}</th>
					</tr>
					<tr>
					
						<td>
							<div class="view-content">
							${fn:replace(list.content, newLineChar, "<br>" ) }
							</div>
						</td>
					</tr>
					
					<tr>
					
						<td>
							<div class="view-img">
								
							
							<img src = "/InstaKgram/image/${list.pic_ref}" >
							
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="/InstaKgram/gboard/index"  class="btn btn-default">글목록</a>
					
					<c:if test="${authMember.name == list.member_name}">
					
					<a href="/InstaKgram/gboard/modify?no=${list.no}"  class="btn btn-default">글수정</a>
					</c:if>
					
				</div>
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
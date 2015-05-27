<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	pageContext.setAttribute("newLineChar", "\n");
%>

<!doctype html>



<c:set var="count" value="${count }" />

<c:set var="pageSize" value="5" />
<%-- 
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); %>
--%>
<c:set var="id" value="" />


<c:set var="pageNum" value="${param.pageNum}" />
<c:if test="${pageNum == null}">
	<c:set var="pageNum" value="1" />
</c:if>

<c:set var="currentPage" value="${pageNum}" />

<c:set var="number" value="0" />

<c:set var="number" value="${count-(currentPage-1)*pageSize}" />


<html>
<script type="text/javascript"
	src="/InstaKgram/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$(".like").click(function() {
			var likeCnt =  $(this).attr( "data-like-count" );
			var no = $(this).attr("data-no");
			
			var postData = "likeCnt=" + likeCnt+"&no="+no;
			$.ajax({
				url : "/InstaKgram/dboard/like_cnt",
				type : "post",
				data : postData,
				
				
				success : function(response) {
					/*
					if (response.result == false) {
						$("#keydown-ok").show();
						$("#keydown-no").hide();
					} else {
						$("#keydown-no").show();
						$("#keydown-ok").hide();
					}
					if (email == "") {
						$("#keydown-ok").hide();
					}
					console.log(response);
					*/
				},
				error : function(jqXHR, status, e) {
					//console.error(status + " : " + e);
				}
				
			});

		});
	});
</script>

<head>
<title>InstaKgram</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/InstaKgram/assets/css/dboard.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false" />
		</div>
		<div id="content">
			<div id="dboard">
				<form action="/InstaKgram/dboard/insert" method="post">

					<table>
						<tr>
							<td colspan=4><img
								src="http://tv03.search.naver.net/thm?size=120x150&quality=9&q=http://sstatic.naver.net/people/portrait/201404/20140403155326747-6772174.jpg">
							</td>
						</tr>
						<tr>
							<td colspan=4><textarea name=content id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li><c:forEach items="${list }" var="vo" varStatus="status">
							<div id="dboard">
								<form action="/InstaKgram/dboard/reply" method="post">

									<table>
										<td>[${vo.no}]</td>
										<td>${vo.member_name}</td>
										<td>${vo.reg_date}</td>

										<!--  
										<td><a class="like" data-like-count="${vo.like_cnt }"
												data-no="${vo.no}"
											 
											href="/InstaKgram/dboard/like?no=${vo.no}&like_cnt=${vo.like_cnt}">♥</a>
											${vo.like_cnt}
										</td>
										-->
										
										<td><a class="like" data-like-count="${vo.like_cnt }"
												data-no="${vo.no}"
											href="">♥</a>
										</td>
										
										<td>
											${vo.like_cnt}
										</td>	
											
										
										
										</tr>
										<tr>
											<td colspan=4><img
												src="http://tv03.search.naver.net/thm?size=120x150&quality=9&q=http://sstatic.naver.net/people/portrait/201404/20140403155326747-6772174.jpg">
											</td>
										</tr>
										<tr>
											<td colspan=3>${fn:replace(vo.content, newLineChar, "<br>"
										) }</td>



											<td><c:if test="${authMember.no == vo.member_no}">

													<a href="/InstaKgram/dboard/delete?no=${vo.no}">삭제</a>

												</c:if></td>

										</tr>
										<tr>
											<input type='hidden' name='no' value="${vo.no}">
											<td colspan=3><input type='text' name='reply' id='reply'
												value=""></td>
											<td><input type="submit" VALUE=" 댓글 "></td>
										</tr>


										<li><c:forEach items="${reply }" var="vo2"
												varStatus="status">
												<c:if test="${vo.no == vo2.daily_no}">
													<tr>
														<td>${vo2.member_name}</td>
														<td colspan=3>${vo2.content}</td>
													</tr>
													<br>
												</c:if>
											</c:forEach></li>

									</table>
								</form>
							</div>
							<br>
						</c:forEach> <br></li>


				</ul>

			</div>

			<div id="paging">
				<c:if test="${count>0}">

					<fmt:formatNumber var="pageCount"
						value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"
						pattern="0" />
					<!-- 	<fmt:formatNumber var="valueA" value="${pageNum/5}" pattern="0" />  -->

					<fmt:formatNumber var="valueA"
						value="${((pageNum)/5)+(1-(((pageNum)/5)%1))%1}" pattern="0" />


					<fmt:formatNumber var="startPage" value="${valueA*5-4}" pattern="0" />

					<c:set var="valueC" value="${((count)/5)+(1-(((count)/5)%1))%1}" />
					<c:set var="pageBlock" value="5" />
					<c:set var="endPageC" value="${startPage + pageBlock-1}" />

					<fmt:formatNumber var="endPage" value="${startPage + pageBlock-1}"
						pattern="0" />
					<c:if test="${ endPage > pageCount}">

						<fmt:formatNumber var="endPageC" value="${pageCount}" pattern="0" />
					</c:if>
					<c:if test="${startPage > 5 }">
						<a href="/InstaKgram/dboard?pageNum=${startPage - 5}">[이전]</a>
					</c:if>

					<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
						<c:if test="${valueC >= i}">
							<a href="/InstaKgram/dboard?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>


					<c:if test="${endPage < valueC }">
						<a href="/InstaKgram/dboard?pageNum=${startPage + 5}">[다음]</a>
					</c:if>
				</c:if>


				<%--<%for (int i = startPage ; i <= endPage ; i++) {  %>
        <a>[<%= i %>]</a>--%>
			</div>


		</div>





		<div id="navigation">
			<jsp:include page="/WEB-INF/views/include/navigation.jsp" />
		</div>




		<div id="footer">
			<p>(c)opyright 2014</p>
		</div>
	</div>
</body>
</html>
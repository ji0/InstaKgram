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
		$(".like").click(
				function() {
					var likeCnt = $(this).attr("data-like-count");
					var no = $(this).attr("data-no");
					var id = "#" + no;

					var postData = "likeCnt=" + likeCnt + "&no=" + no;
					$.ajax({
						url : "/InstaKgram/dboard/like_cnt",
						type : "post",
						data : postData,

						success : function(response) {

							$("#" + no + ".likeUp").text(response.data);
							$("#" + no + ".like").attr("data-like-count",
									response.data);

						},
						error : function(jqXHR, status, e) {

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
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false" />
		</div>
		<div id="content">
		
			<div id="dboard">
				<c:if test="${ !empty authMember }">
				<form action="/InstaKgram/dboard/insert" method="post"
					enctype="multipart/form-data">

					<table class="table table-hover">
						<tr>
							<td colspan=4><img class="myPicture" id="dboard"
								src="/InstaKgram/image/${authMember.pic_ref}"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name=content id="content"
									class="form-control"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="hidden" name="deptNo"
								value="10"> <input type="file" id="file" name="file"
								value="사진등록하기"><br>
							<br> <input type="submit" VALUE=" 확인 "
								class="btn btn-primary"></td>
						</tr>
					</table>

				</form>
				</c:if>
				<ul>
					<li><c:forEach items="${list }" var="vo" varStatus="status">
							<div id="dboard">
								<form action="/InstaKgram/dboard/reply" method="post">

									<table class="table table-hover">
										<td>[${status.count}]</td>
										<td>${vo.member_name}</td>
										<td>${vo.reg_date}</td>


										<td><input type="button" class="like" id="${vo.no }"
											data-like-count="${vo.like_cnt }" data-no="${vo.no}"
											value="좋아요"></td>

										<td class="likeUp" id="${vo.no }">${vo.like_cnt}</td>



										</tr>
										<tr>
											<td colspan=5><img class="dboardPicture" id="dboard"
												src="/InstaKgram/image/${vo.pic_ref}"></td>
										</tr>
										<tr>
											<td colspan=4>${fn:replace(vo.content, newLineChar, "<br>"
										) }</td>



											<td><c:if test="${authMember.no == vo.member_no}">

													<a href="/InstaKgram/dboard/delete?no=${vo.no}"><img
														src="/InstaKgram/assets/images/recycle.png"></a>

												</c:if></td>

										</tr>
										<tr>
											<input type='hidden' name='no' value="${vo.no}">
											<td colspan=4><input type='text' name='reply' id='reply'
												value="" class="form-control"></td>
											<td>
											
											
											<c:if test="${!empty authMember.no}">
											<input type="submit" VALUE=" 댓글 "
												class="btn btn-primary">
												</c:if></td>
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
						<ul class="pagination">
						<c:if test="${startPage > 5 }">
						<li>	<a href="/InstaKgram/dboard?pageNum=${startPage - 5}">Previous</a></li>
						</c:if>
				
				
						<c:forEach begin="${startPage }" end="${endPage }" step="1"
							var="i">
							<c:if test="${valueC >= i}">
								<li><a href="/InstaKgram/dboard?pageNum=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
					

					
						<c:if test="${endPage < valueC }">
							<li>	<a href="/InstaKgram/dboard?pageNum=${startPage + 5}">Next</a></li>
						</c:if>
						</ul>
				</c:if>


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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>



<!doctype html>
<html>
<head>
<title>InstaKgram</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/InstaKgram/assets/css/user.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			<div id="user">

				<form id="uinfo-form" name="uinfoForm" method="post"
					action="/InstaKgram/member/uinfo">
					<input type="hidden" name="no" value="${authMember.no}"> <label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${authMember.name }"> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value="${authMember.email }"> <input
						type="button" value="id 중복체크"> <label class="block-label">패스워드</label>
					<input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>


						<c:if test="${authMember.gender == 'female'}">
							<label>여</label>
							<input type="radio" name="gender" value="female"
								checked="checked">
							<label>남</label>
							<input type="radio" name="gender" value="male">
						</c:if>

						<c:if test="${authMember.gender == 'male'}">
							<label>여</label>
							<input type="radio" name="gender" value="female">
							<label>남</label>
							<input type="radio" name="gender" value="male" checked="checked">
						</c:if>


					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="수정하기">

				</form>
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
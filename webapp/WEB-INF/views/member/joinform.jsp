<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<script type="text/javascript"
	src="/InstaKgram/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$("#join-form").submit(function() {
			
			var file = $("#file").val();
			
			if ($("#keydown-ok").is(":visible") == false &&
					$("#keydown-no").is(":visible") == false		
			) {
				alert("이메일을 입력해 주세요.");
				return false;
			} 
			else if($("#keydown-no").is(":visible") == true){
				alert("중복된 이메일 입니다.");
				return false;
			}else if(file==""){
				alert("사진을 등록해 주세요.");
				return false;
			}else if($("#agree-prov").is(":checked") == false){
				alert("약관에 동의해 주세요.");
				return false;
			}
		});
		
		$("#email").keyup(function() {

			var email = $("#email").val();
			
			var postData = "email=" + email;
			$.ajax({
				url : "/InstaKgram/member/email_check",
				type : "post",
				data : postData,
				
				success : function(response) {
					if (response.result == false) {
						$("#keydown-ok").show();
						$("#keydown-no").hide();
					} else {
						$("#keydown-no").show();
						$("#keydown-ok").hide();
					}
					if(email == "") {
						$("#keydown-ok").hide();
					}
					console.log(response);
				},
				error : function(jqXHR, status, e) {
					console.error(status + " : " + e);
				}

			});
		});
		
		$("#email").change(function() {
			$("#check-email").show();
			$("#email-checked").hide();
		});
		
	});
</script>

<head>
<title>InstaKgram</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/InstaKgram/assets/css/user.css" rel="stylesheet"
	type="text/css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false" />
		</div>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="" enctype="multipart/form-data">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="" class="form-control"> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value="" class="form-control"> <img id="email-checked"
						src="/InstaKgram/assets/images/check.png"
						style="width: 20px; display: none"> <label id="keydown-ok"
						style="display: none">사용가능</label> <label id="keydown-no"
						style="display: none">사용불가</label> <label class="block-label">패스워드</label>
					<input name="password" type="password" value="" class="form-control"> 
					
					<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">



						<input type="hidden" name="deptNo" value="10">
						<br><br>
						<input type="file" id ="file" name="file"><br><br>


						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
				<label>	개인정보는 중국으로 팔려나갈 수도 있습니다.</label>
						<input type="submit" value="가입하기"  class="btn btn-info">
				</form>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/include/navigation.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</div>
	</div>
</body>
</html>
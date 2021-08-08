<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body class="login_body">
	<div class="loginBox">
		<img class="login_logo" src="./images/login_logo.png">
		<div class="loginForm">
			<form method="post" action="login.do">
				<div>
					<label class="fl w_20">昵称：</label>
					<span class="fr w_80"><input type="text" class="text_txt" name="username"></span>
				</div>
				<div>
						<%
							String login_msg = (String)request.getAttribute("login_msg"); 
						%>
					<label class="fl w_20">密码：</label>
					<span class="fr w_80"><input type="password" class="text_txt" name="password"></span>
					<span class="msg"  style="color:red">
						<%=login_msg == null ?"":login_msg %>
					</span>
				</div>
				<div>
					<label class="fl w_20">验证码：</label>
					<span class="fr w_80 verify"><input type="text" class="text_txt code" name="">
					<img id="num" src="checkCode"  />
					<a href="javascript:;" 
					onclick="document.getElementById('num').src = 'checkCode?'+(new Date()).getTime()">
					换一张</a>			
					</span>
					<span class="msg"  style="color:red">
					
					</span>
				</div>
				<div>
					<label class="fl w_20"></label>
					<div class="fr w_80">
						<span class="fl"><input type="checkbox" name=""> 下次自动登录</span>
					</div>
				</div>
				<div>
					<span class="fr w_80">
						<input type="submit" style="height: 50px;" value="登录">
					</span>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
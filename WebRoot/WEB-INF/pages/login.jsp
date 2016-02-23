<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${webName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link rel="shortcut icon" href="${ctx}/favicon.ico" />
<link href="${ctx}/resource/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<%-- <a href="#"><img src="${ctx}/resource/dwz/themes/default/images/login_logo.gif" /></a> --%>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="${ctx}/">返回首页</a></li>
					</ul>
				</div>
				<h2 class="login_title"><!-- <img src="${ctx}/resource/dwz/themes/default/images/login_title.png" /> --></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm"><!-- ${ctx}/loading.html -->
				<form method="post" action="${ctx}/check">
					<p>
						<label>用户名：</label>
						<input type="text" name="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					<!--  <p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="${ctx}/resource/dwz/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p> -->
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					<div id="login-error" style="color: red; margin:15px;">
						<c:if test="${not empty param.msg}">
							提示：登录失败，用户名或密码错误
						</c:if>
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${ctx}/resource/dwz/themes/default/images/login_banner.png" /></div>
			<div class="login_main">
				<div class="login_inner">
					<p>欢迎使用 ${webName}</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2013 ${webName}. All Rights Reserved.
		</div>
	</div>
</body>
</html>
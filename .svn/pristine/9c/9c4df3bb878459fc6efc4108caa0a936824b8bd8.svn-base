<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>用户密码修改</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<br/>
		<c:if test="${not empty tips}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>系统提示</h4>
				${tips}!
			</div>
		</c:if>
		<form class="form-signin" action="${ctx}/base/user/updatePw.html" method="post">
			<div class="row-fluid">
				<p>当前用户：${username}</p>
			</div>
			<div class="row-fluid">
				<p>输入原密码</p>
				<input type="password" name="oldPassword" class="input-block-level" />
			</div>
			<div class="row-fluid">
				<p>输入新密码</p>
				<input type="password" name="newPassword" class="input-block-level"/>
			</div>
			<div class="row-fluid">
				<p>确认新密码</p>
				<input type="password" name="newPasswd" class="input-block-level"/>
			</div>
			<div class="row-fluid">
				<div class="span9">
					<i class="icon-question-sign"></i>请牢记新密码，如有问题请联系管理员！
				</div>
				<div class="span3">
					<p class="text-right">
						<button class="btn btn-primary" type="submit" data-loading-text="提交...">提交</button>
					</p>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>
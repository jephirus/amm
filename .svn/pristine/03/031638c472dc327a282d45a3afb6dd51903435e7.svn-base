<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/updatePw.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">
					当前用户：
				</label>
				${username}
			</p>
			<p>
				<label style="text-align: right">
					输入原密码
				</label>
				<input name="oldPassword" class="required" type="password" size="30" alt="请输入原密码"/>
			</p>
			<p>
				<label style="text-align: right">
					输入新密码
				</label>
				<input name="newPassword" class="required" type="password" size="30" alt="请输入新密码"/>
			</p>
			<p>
				<label style="text-align: right">
					确认新密码
				</label>
				<input name="newPasswd" class="required" type="password" size="30" alt="请确认新密码"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
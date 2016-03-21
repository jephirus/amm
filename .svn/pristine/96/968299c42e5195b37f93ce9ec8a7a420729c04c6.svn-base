<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/base/role/save.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">部门名称：</label>
				<input name="roleName" class="required" type="text" size="30" value="${role.roleName}" alt="请输入部门名称"/>
				<input name="roleId" type="hidden" value="${role.roleId}"/>
			</p>
			<p class="nowrap">
				<label style="text-align: right">描述:</label>
				<textarea rows="3" cols="40" name="roleDesc">${role.roleDesc}</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
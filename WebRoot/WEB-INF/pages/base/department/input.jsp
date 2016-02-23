<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/base/department/save.php" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">单位名称：</label>
				<input name="departmentName" class="required" type="text" size="34" value="${department.departmentName}" alt="请输入单位名称"/>
				<input name="departmentId" type="hidden" value="${department.departmentId}"/>
			</p>
			<p>
				<label style="text-align: right">单位号码：</label>
				<input name="phoneNumber" class="required" type="text" size="34" value="${department.phoneNumber}" alt="请输入电话号码"/>
			</p>
			<p>
				<label style="text-align: right">单位地址：</label>
				<input name="address" class="required" type="text" size="34" value="${department.address}" alt="请输入单位地址"/>
			</p>
			<p class="nowrap">
				<label style="text-align: right">描述:</label>
				<textarea rows="4" cols="36" name="departmentDesc">${department.departmentDesc}</textarea>
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
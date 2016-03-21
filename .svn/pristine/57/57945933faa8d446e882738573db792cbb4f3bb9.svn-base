<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/base/deparea/save.php"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">区域名称：</label> 
				<input
					name="areaName" class="required" type="text" size="30"
					value="${area.areaName}" alt="请输入区域名称" /> 
				<input name="areaId"
					type="hidden" value="${area.areaId}" />
			</p>
			<p>
				<label style="text-align: right">用户单位：</label>
				<select name="departmentId">
					<c:forEach var="dept" items="${departments}">
						<option value="${dept.departmentId}" <c:if test="${dept.departmentId == user.userDep.departmentId}">selected="selected"</c:if>>${dept.departmentName}</option>
					</c:forEach>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
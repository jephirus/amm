<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent">
	<form method="post" action="${ctx}/attachDevice/save.php" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">外控器编号：</label>
				<input name="attachDeviceName" readonly type="text" size="30" value="${attachDevice.attachDeviceNum}" />
				<input name="id" type="hidden" value="${attachDevice.attachDeviceId}"/>
			</p>
			<p>
				<label style="text-align: right">外控器地址：</label>
				<input name="location" class="required" type="text" size="30" value="${attachDevice.location}" />
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
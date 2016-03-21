<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent" >
	<form method="post" action="${ctx}/device/update.php" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p style="width:310px;">
				<label style="text-align: right;width:80px;">控制器名称：</label>
				<input type="text" size="30" value="${device.deviceName}" readonly />
			</p>
			<p style="width:310px;">
				<label style="text-align: right;width:80px;">控制器编号：</label>
				<input value="${device.deviceCode}" type="text" size="30" readonly/>
			</p>
			<p style="width:310px;">
				<label style="text-align: right;width:80px;">所属单位：</label>
				<input value="${device.area.department.departmentName}" type="text" size="30" readonly/>
			</p>
			<p style="width:310px;">
				<label style="text-align: right;width:80px;">区域：</label>
				<input value="${device.area.areaName}" type="text" size="30" readonly/>
			</p>
			<p style="width:310px;">
				<label style="text-align: right;width:80px;">探测器数量：</label>
				<input value="${device.proberCount}" type="text" size="30" readonly/>
			</p>
			<p style="width:310px;">
				<label style="text-align: right;width:80px;">外控器数量：</label>
				<input type="text" size="30" value="${device.attachDeviceCount}" readonly/>
			</p>
			<c:forEach var="manager" items="${device.deviceManagers}">
				<p>
					<label style="text-align: right">管理员：</label>
					<input type="text" size="20" value="${manager.realName}" readonly/>
				</p>
			</c:forEach>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
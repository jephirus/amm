<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<script type="text/javascript">

	$(document).ready(function() {
		
		$("#department").change(function() {
			$.post('/amm/device/getAreasAndUsersByDepartment.php', {
				departmentId : $("#department").val(),
			}, 
			function(data) {
				
				var aOptions;
				var aList = eval("(" + data.areas + ")");
				for ( var i = 0; i < aList.length; i++) {
					aOptions+= "<option value="+aList[i]['areaId']+">"+aList[i]['areaName']+"</option>";
				} 
				$("#areas").html(aOptions);
				
				var uOptions;
				var uList = eval("(" + data.users + ")");
				for ( var i = 0; i < uList.length; i++) {
					uOptions+= "<option value="+uList[i]['userId']+">"+uList[i]['userName']+"</option>";
				} 
				$("#user1").html(uOptions);
				$("#user2").html(uOptions);
				$("#user3").html(uOptions);
				$("#user4").html(uOptions);
			});
		});
	});
	
</script>
<div class="pageContent">
	<form method="post" action="${ctx}/device/save.php" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">控制器名称1：</label>
				<input name="deviceName" class="required" type="text" size="30" value="${device.deviceName}" alt="请输入控制器名称"/>
				<input name="deviceId" type="hidden" value="${device.deviceId}"/>
			</p>
			<p>
				<label style="text-align: right">控制器编号及地址：</label>
				<input name="deviceCode" class="required" type="text" size="30" value="${device.deviceCode}" alt="通信协议中信息模块ID+控制器地址"/>
			</p>
			<p>
				<label style="text-align: right">用户单位：</label>
					<select name="departmentId" id="department">
						<option value="null">请选择用户单位</option>
						<c:forEach var="dept" items="${departments}">
							<option value="${dept.departmentId}" <c:if test="${device.area.department.departmentId eq dept.departmentId}">selected</c:if> >${dept.departmentName}</option>
						</c:forEach>
					</select>
			</p>
			<p>
				<label style="text-align: right">区域：</label>
					<select id ="areas" name="areaId">
						<c:forEach var="area" items="${areas}" >
							<option value="${area.areaId}" <c:if test="${device.area.areaId eq area.areaId}">selected</c:if> >${area.areaName}</option>
						</c:forEach>
					</select>
			</p>
			<p>
				<label style="text-align: right">探测器数量：</label>
				<input name="proberCount" class="required" type="text" size="30" value="${device.proberCount}" alt="请输入探测器数量"/>
			</p>
			<p>
				<label style="text-align: right">外控器数量：</label>
				<input name="attachDeviceCount" class="required" type="text" size="30" value="${device.attachDeviceCount}" alt="请输入外控器数量"/>
			</p>
			<p style="width:200px;">
				<label style="text-align:right;width:50px;">管理员1:</label>
				<select id ="user1" name="userIds" >
					<c:forEach var="user" items="${users}">
						<option value="${user.userId}" <c:if test="${user1.userId eq user.userId}">selected</c:if> >${user.userName}</option>
					</c:forEach>
				</select>
			</p>
			<p style="width:200px;">
				<label style="text-align:right;width:50px;">管理员2:</label>
				<select id ="user2" name="userIds" >
					<c:forEach var="user" items="${users}">
						<option value="${user.userId}" <c:if test="${user2.userId eq user.userId}">selected</c:if> >${user.userName}</option>
					</c:forEach>
				</select>
			</p>
			<p style="width:200px;">
				<label style="text-align:right;width:50px;">管理员3:</label>
				<select id ="user3" name="userIds" >
					<c:forEach var="user" items="${users}">
						<option value="${user.userId}" <c:if test="${user3.userId eq user.userId}">selected</c:if> >${user.userName}</option>
					</c:forEach>
				</select>
			</p>
			<p style="width:200px;">
				<label style="text-align:right;width:50px;">管理员4:</label>
				<select id ="user4" name="userIds" >
					<c:forEach var="user" items="${users}">
						<option value="${user.userId}" <c:if test="${user4.userId eq user.userId}">selected</c:if> >${user.userName}</option>
					</c:forEach>
				</select>
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
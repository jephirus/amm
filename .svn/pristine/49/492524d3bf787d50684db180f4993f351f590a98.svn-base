<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h2 class="contentTitle">
	短信查看
</h2>

<div class="pageContent" layoutH="42">
	<div class="pageFormContent">
		<p class="nowrap">
			<label style="text-align: right;width: 78px">
				设备ID：
			</label>
			<input name="deviceId" type="text"  value="${sms.deviceId}"/>
		</p>
		<p class="nowrap">
			<label style="text-align: center;">
				电话号码：
			</label>
			<textarea style="margin-left: 80px; margin-top: 2px"
				id="phonoNumbers" rows="8" cols="60" name="phonoNumbers"
				class="text">${sms.phonoNumbers}</textarea>
		</p>
		<p class="nowrap">
			<label style="text-align: center">
				短信内容：
			</label>
			<textarea id="content" style="margin-left: 80px; margin-top: 2px"
				rows="8" cols="60" name="content" class="text">${sms.content}</textarea>
		</p>

	</div>

	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">
							取消
						</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>

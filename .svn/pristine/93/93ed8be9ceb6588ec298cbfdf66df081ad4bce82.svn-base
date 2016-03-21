<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h2 class="contentTitle">
	短信修改
</h2>

<div class="pageContent" layoutH="42">
	<form method="post" action="${ctx}/shortMessage/save.php"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input name="id" type="hidden" value="${sms.id}" />
		<div class="pageFormContent">
			<p class="nowrap">
				<label style="text-align: left;width: 60px;margin-left: 30px">
					短信号码：
				</label>
				<select name="phonoNumbers" width="30" multiple="multiple" size="8">
					<option value="0">
						--请选择电话号码,按住ctrl多选--
					</option>
					<c:forEach var="user" items="${users}">
						<option value="${user.phoneNumber}">
							${user.userName}
						</option>
					</c:forEach>
				</select>
			</p>
			<p class="nowrap">
				<label style="text-align: left;width: 60px;margin-left: 30px">
					设备：
				</label>
				<select name="deviceId" width="30">
					<option value="0">
						--请选择设备(ID)--
					</option>
					<c:forEach var="pointInfo" items="${pointInfos}">
						<option value="${pointInfo.numId}">
							${pointInfo.numId}
						</option>
					</c:forEach>
				</select>
			</p>
			<p class="nowrap">
				<label style="text-align: left;width: 60px;margin-left: 30px">
					短信内容：
				</label>
				<textarea id="content" style="margin-left: 100px; margin-top: 2px"
					rows="8" cols="50" name="content" class="text">${sms.content}</textarea>
			</p>

		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
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
	</form>
</div>



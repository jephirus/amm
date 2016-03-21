<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/base/config/save.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p class="nowrap">
				<label style="text-align: right">项目取值：</label>
				<%-- <input name="configValue" class="required" type="text" size="40" value="${config.configValue}" alt="请输入项目取值"/> --%>
				<textarea rows="7" cols="50" name="configValue" class="required">${config.configValue}</textarea>
				<input name="configKey" type="hidden" value="${config.configKey}"/>
				<input name="configId" type="hidden" value="${config.configId}"/>
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/khpj/deptQuota/save.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">部门类型：</label>
				<select name="deptType" class="required">
					<option value="A" <c:if test="${deptQuota.deptType == 'A'}">selected="selected"</c:if>>A类</option>
					<option value="B" <c:if test="${deptQuota.deptType == 'B'}">selected="selected"</c:if>>B类</option>
					<option value="C" <c:if test="${deptQuota.deptType == 'C'}">selected="selected"</c:if>>C类</option>
				</select>
				<input type="hidden" name="deptQuotaId" value="${deptQuota.deptQuotaId}"/>
			</p>
			<p>
				<label style="text-align: right">指标：</label>
				<select name="quotaId" class="required">
					<c:forEach var="quota" items="${quotas}">
						<option value="${quota.quotaId}" <c:if test="${deptQuota.quota.quotaId == quota.quotaId}">selected="selected"</c:if>>(<c:if test="${quota.quotaType eq 1}">固定指标</c:if><c:if test="${quota.quotaType eq 2}">动态指标</c:if>)${quota.quotaName}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label style="text-align: right">状态：</label>
				<select name="inused" class="required">
					<option value="1" <c:if test="${deptQuota.inused == 1}">selected="selected"</c:if>>启用</option>
					<option value="0" <c:if test="${deptQuota.inused == 0}">selected="selected"</c:if>>禁用</option>
				</select>
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/khpj/quota/save.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">指标名称：</label>
				<input name="quotaName" class="required" type="text" size="30" value="${quota.quotaName}" alt="请输入指标名称"/>
				<input name="quotaId" type="hidden" value="${quota.quotaId}"/>
			</p>
			<p>
				<label style="text-align: right">指标类型：</label>
				<select name="quotaType">
					<option value="1" <c:if test="${type == '1' || quota.quotaType == '1'}">selected="selected"</c:if>>固定指标</option>
					<option value="2" <c:if test="${type == '2' || quota.quotaType == '2'}">selected="selected"</c:if>>动态指标</option>
				</select>
			</p>
			<p>
				<label style="text-align: right">指标分值：</label>
				<input name="quotaScore" class="number" type="text" size="15" value="${quota.quotaScore}" alt="请输入指标分值"/>
			</p>
			<p class="nowrap">
				<label style="text-align: right">描述:</label>
				<textarea rows="6" cols="50" name="quotaDesc">${quota.quotaDesc}</textarea>
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
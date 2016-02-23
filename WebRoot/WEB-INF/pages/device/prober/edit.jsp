<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent">
	<form method="post" action="${ctx}/prober/save.php" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">探测器编号：</label>
				<input name="proberName" readonly type="text" size="30" value="${prober.proberNum}" />
				<input name="id" type="hidden" value="${prober.proberId}"/>
			</p>
			<p>
				<label style="text-align: right">探测器地址：</label>
				<input name="location" class="required" type="text" size="30" value="${prober.location}" />
			</p>
			<p>
				<label style="text-align: right">量程：</label>
				<input name="proberRange" class="required" type="text" size="30" value="${prober.proberRange}" />
			</p>
			<p>
				<label style="text-align: right">预警值：</label>
				<input name="alarmValue" class="required" type="text" size="30" value="${prober.alarmValue}" />
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
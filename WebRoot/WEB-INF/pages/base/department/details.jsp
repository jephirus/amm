<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>单位信息维护</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<br/>
		<c:if test="${not empty tips}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>系统提示</h4>
				${tips}!
			</div>
		</c:if>
		<form class="form-signin" action="${ctx}/base/department/updateDetails.html" method="post">
			<div class="row-fluid">
				<div class="span3"><p>当前用户：${username}</p></div>
				<c:if test="${type == 'A'}">
					<div class="span4">
						<div class="input-prepend">
							<span class="add-on">辖区总人口数</span>
							<input type="text" name="details1" class="span3" value="${detail['辖区总人口数']}"/>
						</div>
					</div>
					<div class="span1">&nbsp;</div>
					<div class="span4">
						<div class="input-prepend">
							<span class="add-on">村（居）数</span>
							<input type="text" name="details2" class="span3" value="${detail['村数']}"/>
						</div>
					</div>
				</c:if>
				<c:if test="${type != 'A'}">
					<div class="input-prepend span8">
						<span class="add-on">单位职工数</span>
						<input type="text" name="details3" class="span6" value="${detail['单位职工数']}"/>
					</div>
				</c:if>
			</div>
			<table class="table">
				<tr>
					<td class="span3">
						<label>主要领导</label>
						<input type="text" name="details4" class="input-block-level" value="${detail['主要领导']}"/>
					</td>
					<td class="span5">
						<label>职务</label>
						<input type="text" name="details9" class="input-block-level" value="${detail['主要领导职务']}"/>
					</td>
					<td class="span4">
						<label>手机号码</label>
						<input type="text" name="details10" class="input-block-level" value="${detail['主要领导联系电话']}"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>分管领导</label>
						<input type="text" name="details5" class="input-block-level" value="${detail['分管领导']}"/>
					</td>
					<td>
						<label>职务</label>
						<input type="text" name="details11" class="input-block-level" value="${detail['分管领导职务']}"/>
					</td>
					<td>
						<label>手机号码</label>
						<input type="text" name="details12" class="input-block-level" value="${detail['分管领导联系电话']}"/>
					</td>
				</tr>
				<tr>
					<td class="span3">
						<label>综治工作人员<br/>姓名</label>
						<input type="text" name="details6" class="input-block-level" value="${detail['综治工作人员1']}"/>
						<input type="text" name="details14" class="input-block-level" value="${detail['综治工作人员2']}"/>
						<input type="text" name="details15" class="input-block-level" value="${detail['综治工作人员3']}"/>
						<input type="text" name="details16" class="input-block-level" value="${detail['综治工作人员4']}"/>
						<input type="text" name="details17" class="input-block-level" value="${detail['综治工作人员5']}"/>
					</td>
					<td>
						<label>&nbsp;<br/>职务</label>
						<input type="text" name="details7" class="input-block-level" value="${detail['职务1']}"/>
						<input type="text" name="details18" class="input-block-level" value="${detail['职务2']}"/>
						<input type="text" name="details19" class="input-block-level" value="${detail['职务3']}"/>
						<input type="text" name="details20" class="input-block-level" value="${detail['职务4']}"/>
						<input type="text" name="details21" class="input-block-level" value="${detail['职务5']}"/>
					</td>
					<td>
						<label>&nbsp;<br/>手机号码</label>
						<input type="text" name="details13" class="input-block-level" value="${detail['联系电话1']}"/>
						<input type="text" name="details22" class="input-block-level" value="${detail['联系电话2']}"/>
						<input type="text" name="details23" class="input-block-level" value="${detail['联系电话3']}"/>
						<input type="text" name="details24" class="input-block-level" value="${detail['联系电话4']}"/>
						<input type="text" name="details25" class="input-block-level" value="${detail['联系电话5']}"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label>办公地址</label>
						<input type="text" name="details8" class="input-block-level" value="${detail['办公地址']}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<i class="icon-question-sign"></i>如有人员变动，请及时更新！
					</td>
					<td>
						<p class="text-right">
							<button class="btn btn-primary" type="submit" data-loading-text="提交...">提交</button>
						</p>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
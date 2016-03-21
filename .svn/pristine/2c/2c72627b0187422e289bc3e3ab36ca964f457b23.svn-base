<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<html lang="zh-cn">
<head>
<title>考核数据打分</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body data-spy="scroll">
	<div class="container">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li>控制面板 <span class="divider">/</span>
					</li>
					<li>阶段考核数据维护</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/base/department/rank.html">单位排名</a></li>
					<li class="divider"></li>
					<li><a href="javascript:openChangePw()">密码修改</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/score/audit.html">月考核数据</a></li>
					<li class="divider"></li>
					<li><a href="javascript:open('${ctx}/khpj/sysData/work_list.html');">专门小组工作</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/auditScore/audit/1.html">半年考核</a></li>
					<li><a href="${ctx}/khpj/auditScore/audit/2.html">年度考核</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/reports/con_disputes_report/list.html"><i class="i"></i> 矛盾纠纷报表</a></li>
					<li><a href="${ctx}/khpj/print_report/list.html"><i class="i"></i> 政绩档案报表</a></li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<form action="${ctx}/khpj/auditScore/audit/${scoreType}.html" method="post" id="queryForm">
				<div class="span2">
					<select class="span10" name="type" onchange="javascript:$('#queryForm').submit();">
						<option value="A" <c:if test="${type == 'A'}">selected="selected"</c:if>>A类单位</option>
						<option value="B" <c:if test="${type == 'B'}">selected="selected"</c:if>>B类单位</option>
						<option value="C" <c:if test="${type == 'C'}">selected="selected"</c:if>>C类单位</option>
					</select>
			</div>
			<div class="span4">
				<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
					<p class="text-right">
						<a class="btn btn-success" href="javascript:auditScoreBatch('${ctx}/khpj/auditScore/vice_pass_batch.html?deptType=${type}&scoreType=${scoreType}&year=${year}','是否批量审核通过所有数据？')"><i class="icon-check icon-white"></i> 批量通过 </a>
						&nbsp;&nbsp;
					</p>
				</sec:authorize>
				<sec:authorize ifAnyGranted="LABEL_SYS_AUDIT">
					<p class="text-right">
						<a class="btn btn-success" href="javascript:auditScoreBatch('${ctx}/khpj/auditScore/pass_batch.html?deptType=${type}&scoreType=${scoreType}&year=${year}','是否批量审核通过所有数据？')"><i class="icon-check icon-white"></i> 批量通过 </a>
						&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript:publish('${ctx}/khpj/auditScore/publish/${scoreType}.html?year=${year}','发布前请确认是否完全审核！<br/>是否发布<c:if test="${scoreType == '1'}">${year}半年考核</c:if><c:if test="${scoreType == '2'}">${year}年度考核</c:if>分值？');"> <i class="icon-white icon-share"></i>排名发布</a>
					</p>
				</sec:authorize>
			</div>
			<div class="span3">
				<span class="text-info lead">
					<c:if test="${scoreType == '1'}">
						${year}半年考核
					</c:if>
					<c:if test="${scoreType == '2'}">
						${year}年度考核
					</c:if>
				</span>
			</div>
			<div class="span3">
						<div class="input-append">
							<select class="span9" name="year">
								<c:forEach var="y" items="${years}">
									<option value="${y}" <c:if test="${year == y}">selected="selected"</c:if>>${y}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">数据查询</button>
						</div>
					
				</div>
			</form>
		</div>
		<!--Body content-->
		<c:if test="${not empty tips}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>系统提示</h4>
				${tips}!
			</div>
		</c:if>
		<div class="row-fluid">
			<table class="table table-bordered table-condensed">
				<thead>
					<tr>
						<th width="40">序号</th>
						<th>单位</th>
						<th width="140">得分</th>
						<th width="200">审核状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dept" items="${departments}" varStatus="statue">
						<tr>
							<td>${statue.index+1}</td>
							<td><a href="javascript:tips('${dept.departmentId}')">${dept.departmentName}</a>
							</td>
							<td><c:if test="${empty scores[dept.departmentId].scoreValue}">
									<p class="text-error"><strong>未提交</strong></p>
								</c:if> <c:if test="${not empty scores[dept.departmentId].scoreValue}">
									${scores[dept.departmentId].scoreValue}
									<p id="${scores[dept.departmentId].auditScoreId}panel" class="text-right">
										<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
											<c:if test="${scores[dept.departmentId].auditStat == unaudited}">
												<button class="btn btn-mini btn-success" onclick="auditVicePass('${scores[dept.departmentId].auditScoreId}')" type="button">通过</button>
												<button class="btn btn-mini btn-warning" onclick="viceRefuse('${scores[dept.departmentId].auditScoreId}')" type="button">拒绝</button>
											</c:if>
										</sec:authorize>
										<sec:authorize ifAnyGranted="LABEL_SYS_AUDIT">
											<c:if test="${scores[dept.departmentId].auditStat == vpass}">
												<button class="btn btn-mini btn-success" onclick="auditPass('${scores[dept.departmentId].auditScoreId}')" type="button">通过</button>
												<button class="btn btn-mini btn-warning" onclick="refuse('${scores[dept.departmentId].auditScoreId}')" type="button">拒绝</button>
											</c:if>
										</sec:authorize>
									</p>
								</c:if>
							</td>
							<td id="${scores[dept.departmentId].auditScoreId}statue">
								<c:if test="${not empty scores[dept.departmentId]}">
									${auditScoreState[scores[dept.departmentId].auditStat]}
									<span style="color: red; font-weight: bold;">${scores[dept.departmentId].refuseInfo}</span>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- /body content -->
			<div id="refuse" style="padding:10px;display: none">
				<form action="javascript:void(0);" method="post">
					<fieldset>
						<legend>新增拒绝原因</legend>
						<!-- <table class="table table-striped">
							<thead>
								<tr>
									<th>审核意见记录</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>审核意见111</td>
								</tr>
							</tbody>
						</table> -->
						<input type="hidden" id="sid" name="sid"/>
						<textarea id="refuseInfo" name="refuseInfo" rows="5" class="input-block-level"></textarea>
						<span class="help-block"></span>
						<div class="controls">
							<p class="text-right"><button type="submit" class="btn" onclick="javascript:auditRefuse();">提交</button></p>
						</div>
					</fieldset>
				</form>
			</div>
			
			<div id="viceRefuse" style="padding:10px;display: none">
				<form action="javascript:void(0);" method="post">
					<fieldset>
						<legend>新增拒绝原因</legend>
						<!-- <table class="table table-striped">
							<thead>
								<tr>
									<th>审核意见记录</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>审核意见111</td>
								</tr>
							</tbody>
						</table> -->
						<input type="hidden" id="viceSid" name="viceSid"/>
						<textarea id="viceRefuseInfo" name="viceRefuseInfo" rows="5" class="input-block-level"></textarea>
						<span class="help-block"></span>
						<div class="controls">
							<p class="text-right"><button type="submit" class="btn" onclick="javascript:auditViceRefuse();">提交</button></p>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/auditScore/audit.js"></script>
	<input type="hidden" id="ctx" value="${ctx}" />
</body>
</html>
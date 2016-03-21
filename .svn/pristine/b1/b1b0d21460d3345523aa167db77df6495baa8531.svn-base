<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>定南县社会管理综合治理网上考核系统单位排名（${year}年）</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<!--Body content-->
			<div class="row-fluid">
				<div class="span4">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;当前考核排名年份 ${year}</p>
				</div>
				<div class="span8 text-right">
					<div class="input-append">
						<select id="year" class="span4">
							<c:forEach var="y" items="${years}">
								<option value="${y}" <c:if test="${y == year}">selected="selected"</c:if>>${y}</option>
							</c:forEach>
						</select>
						<a href="javascript:open('单位排名查询打印','${ctx}/base/department/rank_print.html?year='+$('#year').val());" role="button" class="btn btn-primary"> <i class="icon-search icon-white"></i> 查询</a>
						<a href="javascript:open('单位排名打印','${ctx}/base/department/rank_print.html');" role="button" class="btn btn-primary"> <i class="icon-print icon-white"></i> 打印</a>
						<a href="${ctx}/khpj/score/audit.html" role="button" class="btn btn-primary">返回</a>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
			<br />
			<div class="row">
				<div class="span4">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th colspan="4">A类单位考核排名</th>
							</tr>
							<tr>
								<th width="40">排名</th>
								<th>单位</th>
								<th width="40">上月得分</th>
								<th width="40">累计分值</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="score" value="0"></c:set>
							<c:set var="rank" value="0"></c:set>
							<c:forEach var="dept" items="${Atable}" varStatus="statue">
								<tr>
									<td>
										<c:if test="${dept.totalScore == score}">
											${rank}
										</c:if> 
										<c:if test="${dept.totalScore != score}">
											${rank+1}
											<c:set var="rank" value="${rank+1}"></c:set>
										</c:if> 
										<c:set var="score" value="${dept.totalScore}"></c:set>
									</td>
									<td>
										<a href="javascript:open('${dept.departmentName}考核信息查看','${ctx}/khpj/score/view/${dept.departmentId}.html');">${dept.departmentName}</a>
									</td>
									<td>${dept.lastMonthScore}</td>
									<td>${dept.totalScore}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span4">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th colspan="4">B类单位考核排名</th>
							</tr>
							<tr>
								<th width="40">排名</th>
								<th>单位</th>
								<th width="40">上月得分</th>
								<th width="40">累计分值</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="score" value="0"></c:set>
							<c:set var="rank" value="0"></c:set>
							<c:forEach var="dept" items="${Btable}" varStatus="statue">
								<tr>
									<td>
										<c:if test="${dept.totalScore == score}">
											${rank}
										</c:if> 
										<c:if test="${dept.totalScore != score}">
											${rank+1}
											<c:set var="rank" value="${rank+1}"></c:set>
										</c:if> 
										<c:set var="score" value="${dept.totalScore}"></c:set>
									</td>
									<td>
										<a href="javascript:open('${dept.departmentName}考核信息查看','${ctx}/khpj/score/view/${dept.departmentId}.html');">${dept.departmentName}</a>
									</td>
									<td>${dept.lastMonthScore}</td>
									<td>${dept.totalScore}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span4">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th colspan="4">C类单位考核排名</th>
							</tr>
							<tr>
								<th width="40">排名</th>
								<th>单位</th>
								<th width="40">上月得分</th>
								<th width="40">累计分值</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="score" value="0"></c:set>
							<c:set var="rank" value="0"></c:set>
							<c:forEach var="dept" items="${Ctable}" varStatus="statue">
								<tr>
									<td>
										<c:if test="${dept.totalScore == score}">
											${rank}
										</c:if> 
										<c:if test="${dept.totalScore != score}">
											${rank+1}
											<c:set var="rank" value="${rank+1}"></c:set>
										</c:if> 
										<c:set var="score" value="${dept.totalScore}"></c:set>
									</td>
									<td>
										<a href="javascript:open('${dept.departmentName}考核信息查看','${ctx}/khpj/score/view/${dept.departmentId}.html');">${dept.departmentName}</a>
									</td>
									<td>${dept.lastMonthScore}</td>
									<td>${dept.totalScore}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/department/rank.js"></script>
	<input id="ctx" type="hidden" value="${ctx}" />
</body>
</html>
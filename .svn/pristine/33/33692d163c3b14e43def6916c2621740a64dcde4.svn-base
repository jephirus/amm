<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
	<title>定南县社会管理综合治理网上考核系统单位排名（${year}年）</title>
</head>
<body>
	<div class="container">
		<div class="container mainbody">
			<!--Body content-->
			<table></table>
			<textarea style="width:100%;height:580px;visibility:hidden;" readonly="readonly">
				<c:if test="${not empty Atable || not empty Btable || not empty Ctable}">
				<table class="table table-hover table-striped table-bordered table-condensed" width="100%" >
					<thead>
						<tr>
							<th colspan="3" align="center" height="40">
								定南县社会管理综合治理网上考核系统单位排名（${year}年）
								<br/><br/>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td width="33%" valign="top" align="center">
							<table class="table table-bordered" width="90%" cellpadding="0" cellspacing="0" border="1">
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
											<td style="padding:5px;">
												<c:if test="${dept.totalScore == score}">
													${rank}
												</c:if> 
												<c:if test="${dept.totalScore != score}">
													${rank+1}
													<c:set var="rank" value="${rank+1}"></c:set>
												</c:if> 
												<c:set var="score" value="${dept.totalScore}"></c:set>
											</td>
											<td>${dept.departmentName}</td>
											<td>${dept.lastMonthScore}</td>
											<td>${dept.totalScore}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
						<td width="33%" valign="top" align="center">
							<table class="table table-bordered" width="90%" cellpadding="0" cellspacing="0" border="1">
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
											<td style="padding:5px;">
												<c:if test="${dept.totalScore == score}">
													${rank}
												</c:if> 
												<c:if test="${dept.totalScore != score}">
													${rank+1}
													<c:set var="rank" value="${rank+1}"></c:set>
												</c:if> 
												<c:set var="score" value="${dept.totalScore}"></c:set>
											</td>
											<td>${dept.departmentName}</td>
											<td>${dept.lastMonthScore}</td>
											<td>${dept.totalScore}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
						<td width="33%" valign="top" align="center">
							<table class="table table-bordered" width="90%" cellpadding="0" cellspacing="0" border="1">
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
											<td style="padding:5px;">
												<c:if test="${dept.totalScore == score}">
													${rank}
												</c:if> 
												<c:if test="${dept.totalScore != score}">
													${rank+1}
													<c:set var="rank" value="${rank+1}"></c:set>
												</c:if> 
												<c:set var="score" value="${dept.totalScore}"></c:set>
											</td>
											<td>${dept.departmentName}</td>
											<td>${dept.lastMonthScore}</td>
											<td>${dept.totalScore}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
					</tbody>
				</table>
				</c:if>
				<c:if test="${not empty At || not empty Bt || not empty Ct}">
				<table class="table table-hover table-striped table-bordered table-condensed" width="100%" >
					<thead>
						<tr>
							<th colspan="3" align="center" height="40">
								定南县社会管理综合治理网上考核系统单位排名（${year}年）
								<br/><br/>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td width="33%" valign="top" align="center">
							<table class="table table-bordered" width="90%" cellpadding="0" cellspacing="0" border="1">
								<thead>
									<tr>
										<th colspan="3">A类单位考核排名</th>
									</tr>
									<tr>
										<th width="40">排名</th>
										<th>单位</th>
										<th width="40">累计分值</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="score" value="0"></c:set>
									<c:set var="rank" value="0"></c:set>
									<c:forEach var="dept" items="${At}" varStatus="statue">
										<tr>
											<td style="padding:5px;">
												<c:if test="${dept[1] == score}">
													${rank}
												</c:if> 
												<c:if test="${dept[1] != score}">
													${rank+1}
													<c:set var="rank" value="${rank+1}"></c:set>
												</c:if> 
												<c:set var="score" value="${dept[1]}"></c:set>
											</td>
											<td>${dept[0]}</td>
											<td>${dept[1]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
						<td width="33%" valign="top" align="center">
							<table class="table table-bordered" width="90%" cellpadding="0" cellspacing="0" border="1">
								<thead>
									<tr>
										<th colspan="3">B类单位考核排名</th>
									</tr>
									<tr>
										<th width="40">排名</th>
										<th>单位</th>
										<th width="40">累计分值</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="score" value="0"></c:set>
									<c:set var="rank" value="0"></c:set>
									<c:forEach var="dept" items="${Bt}" varStatus="statue">
										<tr>
											<td style="padding:5px;">
												<c:if test="${dept[1] == score}">
													${rank}
												</c:if> 
												<c:if test="${dept[1] != score}">
													${rank+1}
													<c:set var="rank" value="${rank+1}"></c:set>
												</c:if> 
												<c:set var="score" value="${dept[1]}"></c:set>
											</td>
											<td>${dept[0]}</td>
											<td>${dept[1]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
						<td width="33%" valign="top" align="center">
							<table class="table table-bordered" width="90%" cellpadding="0" cellspacing="0" border="1">
								<thead>
									<tr>
										<th colspan="3">C类单位考核排名</th>
									</tr>
									<tr>
										<th width="40">排名</th>
										<th>单位</th>
										<th width="40">累计分值</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="score" value="0"></c:set>
									<c:set var="rank" value="0"></c:set>
									<c:forEach var="dept" items="${Ct}" varStatus="statue">
										<tr>
											<td style="padding:5px;">
												<c:if test="${dept[1] == score}">
													${rank}
												</c:if> 
												<c:if test="${dept[1] != score}">
													${rank+1}
													<c:set var="rank" value="${rank+1}"></c:set>
												</c:if> 
												<c:set var="score" value="${dept[1]}"></c:set>
											</td>
											<td>${dept[0]}</td>
											<td>${dept[1]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
					</tbody>
				</table>
				</c:if>
			</textarea>
			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/department/rank_print.js"></script>
</body>
</html>
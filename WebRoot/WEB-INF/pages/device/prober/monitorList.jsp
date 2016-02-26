<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<table class="table" width="100%" layoutH="76" rel="quotaBox">
		<thead>
			<tr>
				<th width="40" align="center">
					序号
				</th>
				<th width="100" align="center">
					安装地点
				</th>
				<th width="60" align="center">
					状态
				</th>
				<th width="60" align="center">
					报警/故障时间
				</th>
				<th width="60" align="center">
					浓度
				</th>
				<th width="130" align="center">
					预警值
				</th>
				<th width="130" align="center">
					联动设置
				</th>
				<th width="80" align="center">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${probers}" varStatus="statue">
				<tr>
					<td align="center">
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.location}
					</td>
						<!-- status字段中已带颜色和td -->
						${entity.currentStatus}
						<!-- status字段中已带颜色和td -->
						${entity.alarmTime}
					<td>
						${entity.currentThickness}
					</td>
					<td>
						${entity.alarmValue}
					</td>
					<td>
						
					</td>
					<td>
						<a class="btnEdit"
							href="${ctx}/device/view/${entity.proberId}.php"
							target="dialog" rel="addDialog" title="查看控制器" mask="true"
							width="680" height="190">复位</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" ${pageData.pagination.pageSize==20?
					"selected=selected":"" }>
					20
				</option>
				<option value="50" ${pageData.pagination.pageSize==50?
					"selected=selected":"" }>
					50
				</option>
				<option value="100" ${pageData.pagination.pageSize==100?
					"selected=selected":"" }>
					100
				</option>
				<option value="200" ${pageData.pagination.pageSize==200?
					"selected=selected":"" }>
					200
				</option>
			</select>
			<span>条，共${pageData.pagination.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab"
			totalCount="${pageData.pagination.totalCount}"
			numPerPage="${pageData.pagination.pageSize}"
			pageNumShown="${pageData.pagination.totalCount}"
			currentPage="${pageData.pagination.pageNo}"></div>
	</div>
</div>

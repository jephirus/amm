<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<sec:authorize ifAnyGranted="LABEL_SYS_ADMIN,LABEL_SYS_DEPT_ADMIN">
				<li><a class="add" href="${ctx}/prober/addProberCount/${deviceId}.php" target="dialog" rel="addDialog" title="探测器添加数量" mask="true" width="500" height="128"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="${ctx}/base/user/delete.php" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
			</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="76" rel="quotaBox">
		<thead>
			<tr>
				<th width="40" align="center">
					<input type="checkbox" class="checkboxCtrl" group="items" />
				</th>
				<th width="40" align="center">
					序号
				</th>
				<th width="100" align="center">
					安装地点
				</th>
				<th width="60" align="center">
					量程
				</th>
				<th width="60" align="center">
					低端浓度
				</th>
				<th width="60" align="center">
					高端浓度
				</th>
				<th width="130" align="center">
					安装时间
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
						<input type="checkbox" name="items" value="${entity.proberId}" />
					</td>
					<td align="center">
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.location}
					</td>
					<td>
						${entity.proberRange}
					</td>
					<td>
						${entity.lowThickness}
					</td>
					<td>
						${entity.highThickness}
					</td>
					<td>
						${entity.installDate}
					</td>
					<td>
						${entity.installDate}
					</td>
					<td>
						<a class="btnEdit"
							href="${ctx}/prober/edit/${entity.proberId}.php"
							target="dialog" rel="addDialog" title="修改探测器" mask="true"
							width="800" height="190">修改</a>
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

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<sec:authorize ifAnyGranted="LABEL_SYS_ADMIN,LABEL_SYS_DEPT_ADMIN">
				<li><a class="add" href="${ctx}/device/new.php" target="dialog" rel="addDialog" title="控制器信息新增" mask="true" width="830" height="224"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="${ctx}/device/delete.php" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
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
					设备名称
				</th>
				<th width="40" align="center">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
				<tr>
					<td align="center">
						<input type="checkbox" name="items" value="${entity.deviceId}" />
					</td>
					<td align="center">
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.deviceName}
					</td>
					<td>
						<a class="view"
							href="${ctx}/prober/list/${entity.deviceId}.php"
							target="navTab" rel="proberList" title="探测器列表">探测器</a>&nbsp;
						<a class="view"
							href="${ctx}/attachDevice/list/${entity.deviceId}.php"
							target="navTab" rel="attachDeviceList" title="查看外控器">外控器</a>&nbsp;
						<a href="${ctx}/device/reset/${entity.deviceId}.php"
							target="ajaxTodo" title="确定要复位吗？">复位</a>
						<a href="${ctx}/device/edit/${entity.deviceId}.php" target="dialog" width="830" height="224">修改</a>
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

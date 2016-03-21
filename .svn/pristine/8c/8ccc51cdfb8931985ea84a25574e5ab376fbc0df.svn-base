<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent">
<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/alarmParameter/new.php" target="dialog" rel="addMessionDialog" title="参数新增" width="540" height="300"><span>新增参数</span></a></li>
			</ul>
		</div>
	<table class="table" width="1149px" layoutH="45" rel="quotaBox">
		<thead>
			<tr>
				<th width="20" align="center">
					<input type="checkbox" class="checkboxCtrl" group="items" />
				</th>
				<th width="20" align="center">
					序号
				</th>
				<th width="40" align="center">
					浓度(百分数)
				</th>
				<th width="40" align="center">
					单位
				</th>
				<th width="20" align="center">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
				<tr>
					<td align="center">
						<input type="checkbox" name="items" value="${entity.id}" />
					</td>
					<td align="center">
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.standConcentration}%
					</td>
					<td>
						${entity.unit}
					</td>
					<td>&nbsp;
						<a class="btnEdit" href="${ctx}/alarmParameter/edit/${entity.id}.php" target="dialog" rel="modifyDialog" title="修改报警参数" mask="true" width="540" height="300">修改</a>
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

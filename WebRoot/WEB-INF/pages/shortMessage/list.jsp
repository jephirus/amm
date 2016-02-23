<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent">
<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/shortMessage/new.php" target="dialog" rel="addMessionDialog" title="短信新增" width="500" height="515"><span>短信新增</span></a></li>
			</ul>
		</div>
	<table class="table" width="100%" layoutH="45" rel="newsBox">
		<thead>
			<tr>
				<th width="40" align="center">
					<input type="checkbox" class="checkboxCtrl" group="items" />
				</th>
				<th width="40" align="center">
					序号
				</th>
				<th width="100" align="center">
					设备ID
				</th>
				<th width="100" align="center">
					电话号码
				</th>
				<th width="100" align="center">
					短信内容
				</th>
				<th width="50" align="center">
					修改
				</th>
				<th width="50" align="center">
					查看
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
						${entity.deviceId}
					</td>
					<td>
						${entity.phonoNumbers}
					</td>
					<td>
						${entity.content}
					</td>
					<td>
						&nbsp;
						<a class="btnEdit"
							href="${ctx}/shortMessage/edit/${entity.id}.php" target="dialog"
							rel="modifyDialog" title="短信修改" mask="true" width="540"
							height="491">修改</a> &nbsp; &nbsp;
					</td>
					<td>
						&nbsp;
						<a class="btnView"
							href="${ctx}/shortMessage/view/${entity.id}.php" target="dialog"
							rel="modifyDialog" height="491" width="540" title="任务查看"
							mask="true">查看</a>
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

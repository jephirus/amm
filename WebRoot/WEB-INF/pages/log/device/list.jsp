<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/contracts/contracts/statQuery.html" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td style="width:130px;">
						<select class="combox" name="device" ref="prober_select" refUrl="demo/combox/city_{value}.html">
							<option value="all">选择控制器</option>
							<option value="bj">北京</option>
							<option value="sh">上海</option>
						</select>
					</td>
					<td style="width:130px;">
						<select class="combox" name="prober" id="prober_select" ref="w_combox_area">
							<option value="all">探测器</option>
						</select>
					</td>
					<td style="width:230px;">
						<p>
						<label style="width:50px;">时间：</label>
						<input type="text" readonly="readonly" size="8" class="date" name="beginDate" value="${param['beginDate']}">
							<span class="limit">-</span>
						<input type="text" readonly="readonly" size="8" class="date" name="endDate" value="${param['endDate']}">
						</p>
					</td>
					<td>
						<button type="submit">检索</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="86" rel="quotaBox">
		<thead>
			<tr>
				<th width="40" align="center">
					<input type="checkbox" class="checkboxCtrl" group="items" />
				</th>
				<th width="40" align="center">
					序号
				</th>
				<th width="100" align="center">
					控制器名称
				</th>
				<th width="100" align="center">
					区域
				</th>
				<th width="100" align="center">
					所属单位
				</th>
				<th width="100" align="center">
					故障类型
				</th>
				<th width="100" align="center">
					故障时间
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
				<tr>
					<td align="center">
						<input type="checkbox" name="items" value="" />
					</td>
					<td align="center">
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.deviceName}
					</td>
					<td>
						${entity.areaName}
					</td>
					<td>
						${entity.departmentName}
					</td>
					<td>
						${entity.controllerStatus}
					</td>
					<td>
						${entity.timeLaber}
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

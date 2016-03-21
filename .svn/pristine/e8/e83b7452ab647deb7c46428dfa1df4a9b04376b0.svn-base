<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent">
	<table class="table" width="100%" layoutH="45" rel="quotaBox">
		<thead>
			<tr>
				<th width="40" align="center">
					序号
				</th>
				<th width="50" align="center">
					版本号
				</th>
				<th width="100" align="center">
					时间标签
				</th>
				<th width="100" align="center">
					信息模块ID
				</th>
				<th width="80" align="center">
					数据长度
				</th>
				<th width="100" align="center">
					命令
				</th>
				<th width="100" align="center">
					校验和
				</th>
				<th width="100" align="center">
					数据单元
				</th>
				<th width="100" align="center">
					浓度
				</th>
				<th width="100" align="center">
					设备状态
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
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.versionNum}
					</td>
					<td>
						${entity.timeLaber}
					</td>
					<td>
						${entity.infoID}
					</td>
					<td>
						${entity.dataLength}
					</td>
					<td>
						${entity.command}
					</td>
					<td>
						${entity.checkSum}
					</td>
					<td>
						${entity.dataUnit}
					</td>
					<td>
						${entity.concentration}
					</td>
					<td>
						${entity.unitStatus}
					</td>
					<td>
						&nbsp;
						<a class="btnEdit"
							href="${ctx}/khpj/quota/edit/${entity.id}.html"
							target="dialog" rel="modifyDialog" title="指标信息更新" mask="true"
							width="540" height="300">修改</a>
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

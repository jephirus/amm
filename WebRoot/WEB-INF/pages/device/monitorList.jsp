<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%-- <script src="${ctx}/resource/avalon/monitor.js" type="text/javascript"></script> --%>
<script type="text/javascript">

function refreshNavTab()
{
		navTab.openTab("deviceList","/amm/device/monitorList/2.php");
}

</script>

<div class="pageContent" ms-controller="Monitor" id="box">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="javascript:refreshNavTab();"><span>刷新</span></a></li>
<%-- 			<sec:authorize ifAnyGranted="LABEL_SYS_ADMIN,LABEL_SYS_DEPT_ADMIN">
				<li><a class="add" href="${ctx}/device/new.php" target="dialog" rel="addDialog" title="控制器信息新增" mask="true" width="840" height="224"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="${ctx}/device/delete.php" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
			</sec:authorize>
 --%>
 		</ul>
	</div>

	<table class="table" width="100%" layoutH="76" rel="quotaBox">
		<thead>
			<tr>
				<th width="30" align="center">
					序号
				</th>
				<th width="40" align="center">
					区域
				</th>
				<th width="70" align="center">
					控制器名称
				</th>
				<th width="70" align="center">
					探测器报警数量
				</th>
				<th width="70" align="center">
					探测器故障数量
				</th>
				<th width="70" align="center">
					探测器安装总数
				</th>
				<th width="70" align="center">
					外控器故障数量
				</th>
				<th width="70" align="center">
					外控器安装数量
				</th>
				<th width="70" align="center">
					设备编号
				</th>
				<th width="50" align="center">
					状态
				</th>
				<th width="90" align="center">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${devices}" varStatus="statue">
				<tr ms-each-entity="devices">
					<td align="center">
						${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}
					</td>
					<td>
						${entity.area.areaName}
					</td>
					<td>
						${entity.deviceName}
					</td>
					<c:if test="${entity.proberAlarmCount !='0'}">
						<td style="color:red;">
					</c:if>					
					<c:if test="${entity.proberAlarmCount =='0'}">
						<td style="color:black;">
					</c:if>					
						${entity.proberAlarmCount}
					</td>
					<c:if test="${entity.proberFaultCount !='0'}">
						<td style="color:#FFCC00;">
					</c:if>
					<c:if test="${entity.proberFaultCount =='0'}">
						<td style="color:black;">
					</c:if>					
						${entity.proberFaultCount}
					</td>
					<td>
						${entity.proberCount}
					</td>
					<c:if test="${entity.attachDeviceFaultCount !='0'}">
						<td style="color:#FFCC00;">
					</c:if>					
					<c:if test="${entity.attachDeviceFaultCount =='0'}">
						<td style="color:black;">
					</c:if>
						${entity.attachDeviceFaultCount}
					</td>
					<td>
						${entity.attachDeviceCount}
					</td>
					<td>
						${entity.deviceCode}
					</td>
						<!-- status字段中已带颜色和td -->
						${entity.status}
					<td>
						<a class="view"
							href="${ctx}/prober/monitorList/${entity.deviceId}.php"
							target="navTab" rel="proberList" title="控制器：${entity.deviceName}" >探测器</a>&nbsp;
						<a class="view"
							href="${ctx}/attachDevice/monitorList/${entity.deviceId}.php"
							target="navTab" rel="attachDeviceList" title="控制器：${entity.deviceName}" >外控器</a>&nbsp;
						<a class="view"
							href="${ctx}/device/view/${entity.deviceId}.php"
							target="dialog" rel="addDialog" title="查看控制器" mask="true"
							width="680" height="222">详细信息</a>
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
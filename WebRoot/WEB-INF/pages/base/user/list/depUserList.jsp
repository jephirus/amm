<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
<div class="panelBar">
	<ul class="toolBar">
		<li><a class="add" href="${ctx}/base/user/new/3.php" target="dialog" rel="addDialog" title="用户信息新增" mask="true" width="540" height="283"><span>添加单位管理员</span></a></li>
		<li class="line">line</li>
		<li><a class="delete" href="${ctx}/base/user/delete.php" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
	</ul>
	</div>
	<table class="table" width="100%" layoutH="80" rel="userBox">
		<thead>
			<tr>
				<th width="40" align="center"><input type="checkbox" class="checkboxCtrl" group="items" /></th>
				<th width="40" align="center">序号</th>
				<th width="200">帐户名称</th>
				<th width="200">用户姓名</th>
				<th>描述</th>
				<th width="100" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${users}" varStatus="statue">
			<tr>
				<td align="center"><input type="checkbox" name="items" value="${entity.userId}" /></td>
				<td align="center">${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}</td>
				<td>${entity.userName}</td>
				<td>${entity.realName}</td>
				<td>${entity.userDesc}</td>
				<td>
					<a class="btnEdit" href="${ctx}/base/user/edit/${entity.userId}.php" target="dialog" rel="modifyDialog" title="用户信息更新" mask="true" width="540" height="283">修改</a>
					&nbsp;<a href="${ctx}/base/user/initPw/${entity.userId}.php"  target="ajaxTodo" title="确定要初始密码吗?">初始密码</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
	</div>
</div>
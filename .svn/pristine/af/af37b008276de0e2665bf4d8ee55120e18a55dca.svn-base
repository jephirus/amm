<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent" width="100%" layoutH="0">
	<!-- 
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li><a class="add" href="javascript:void(0)"><span>系统基础信息设置</span> </a></li>
			<li class="line">line</li>
		</ul>
	</div>
	 -->
	<div class="panel" defH="100">
		<h1>系统基本信息配置</h1>
		<div>
			<table class="table" width="100%" rel="configBox">
				<thead>
					<tr>
						<th width="200">设置项目</th>
						<th>当前取值</th>
						<th width="50" align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>网站名称</td>
						<td>${configs['web']}</td>
						<td>&nbsp; <a class="btnEdit" href="${ctx}/base/config/edit/web.html" target="dialog" rel="modifyDialog" title="网站名称更新" mask="true" width="540" height="220">修改</a></td>
					</tr>
					<tr>
						<td>系统名称</td>
						<td>${configs['system']}</td>
						<td>&nbsp; <a class="btnEdit" href="${ctx}/base/config/edit/system.html" target="dialog" rel="modifyDialog" title="系统名称更新" mask="true" width="540" height="220">修改</a></td>
					</tr>
					<tr>
						<td>系统概述</td>
						<td>${configs['overview']}</td>
						<td>&nbsp; <a class="btnEdit" href="${ctx}/base/config/edit/overview.html" target="dialog" rel="modifyDialog" title="系统概述更新" mask="true" width="540" height="220">修改</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel">
		<h1>服务器参数信息</h1>
		<div>
			<table class="table" width="100%">
				<tbody>
					<tr>
						<td class="b">主机操作系统：</td>
						<td><%=System.getProperties().getProperty("os.name")%></td>
						<td class="b">主机操作系统架构:</td>
						<td><%=System.getProperties().getProperty("os.arch")%></td>
					</tr>
					<tr>
						<td class="b">主机操作版本：</td>
						<td><%=System.getProperties().getProperty("os.version")%></td>
						<td class="b">当前工作目录：</td>
						<td><%=System.getProperties().getProperty("user.dir")%></td>
					</tr>
					<tr>
						<td class="b">Java安装目录：</td>
						<td><%=System.getProperties().getProperty("java.home")%></td>
						<td class="b">Java API 版本：</td>
						<td><%=System.getProperties().getProperty("java.version")%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel">
		<h1>WEB服务器参数信息</h1>
		<div>
			<table class="table" width="100%">
				<tbody>
					<tr>
						<td class="b">web协议：</td>
						<td><%=request.getScheme()%></td>
						<td class="b">协议版本：</td>
						<td><%=request.getProtocol()%></td>
					</tr>
					<tr>
						<td class="b">服务器名:</td>
						<td><%=request.getServerName()%></td>
						<td class="b">主机信息:</td>
						<td><%=request.getHeader("Host")%></td>
					</tr>
					<tr>
						<td class="b">web 服务器：</td>
						<td><%=getServletConfig().getServletContext().getServerInfo()%></td>
						<td class="b">web 服务端口：</td>
						<td><%=request.getServerPort()%></td>
					</tr>
					<tr>
						<td class="b">字符编码：</td>
						<td><%=request.getCharacterEncoding()%></td>
						<td class="b">支持语言:</td>
						<td><%=request.getHeader("Accept-Language")%></td>
					</tr>
					<tr>
						<td class="b">网站路径:</td>
						<td colspan="3"><%=session.getServletContext().getRealPath("/")%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel">
		<h1>服务器存储信息</h1>
		<div>
			<table class="table" width="100%">
				<tbody>
					<tr>
					<%
						java.io.File[] roots = java.io.File.listRoots();
						for (java.io.File file : roots) 
						{
							if(file.getTotalSpace() != 0){
							out.println("<tr>");
							out.println("<td>盘符:" + file.getPath() +"</td>");
							out.println("<td>总空间:" + file.getTotalSpace() / 1024 / 1024 /1024 + "G</td>");//总空间
							out.println("<td>空闲空间:" + file.getFreeSpace() / 1024 / 1024  + "M</td>");//空闲空间
							long b = (file.getUsableSpace() * 100 ) / file.getTotalSpace();
							if(b < 10.0)
							{
								out.println("<td>空闲比率:<span style='color:red;font-size: 16px;'>" + b + "%</span></td>");
							}
							else
							{
								out.println("<td>空闲比率:<span style='color:green;font-size: 16px;'>" + b + "%</span></td>");
							}
							out.println("</tr>");
							}
						}
					%>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel">
		<h1>系统基本参数配置</h1>
		<div>
			<table class="table" width="100%" rel="configBox1">
				<thead>
					<tr>
						<th width="200">设置项目</th>
						<th>当前取值</th>
						<th width="50" align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>数据提交截止日期</td>
						<td>${configs['submit_time']}</td>
						<td>&nbsp; <a class="btnEdit" href="${ctx}/base/config/edit/submit_time.html" target="dialog" rel="modifyDialog" title="数据提交截止时间更新" mask="true" width="540" height="220">修改</a></td>
					</tr>
					<tr>
						<td>是否提交历史数据</td>
						<td>${configs['history']}</td>
						<td>&nbsp; <a class="btnEdit" href="${ctx}/base/config/edit/history.html" target="dialog" rel="modifyDialog" title="是否提交历史数据" mask="true" width="540" height="220">修改</a></td>
					</tr>
					<tr>
						<td>是否显示排名</td>
						<td>${configs['show_rank']}</td>
						<td>&nbsp; <a class="btnEdit" href="${ctx}/base/config/edit/show_rank.html" target="dialog" rel="modifyDialog" title="是否显示排名" mask="true" width="540" height="220">修改</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

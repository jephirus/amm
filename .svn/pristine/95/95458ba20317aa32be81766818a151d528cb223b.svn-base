<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- 判断用户类型 -->
<sec:authorize ifAllGranted="LABEL_SYS_DEPT">
	<!-- 单位用户 -->
	<%response.sendRedirect("amm/sysData/list.php");%>
</sec:authorize>

<sec:authorize ifAllGranted="LABEL_SYS_ASSESS">
	<!-- 考核管理员 -->
	<%response.sendRedirect("amm/score/assess.php");%>
</sec:authorize>

<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
	<!-- 副书记审核 -->
	<%response.sendRedirect("amm/score/audit.php");%>
</sec:authorize>

<sec:authorize ifAllGranted="LABEL_SYS_AUDIT">
	<!-- 书记审核 -->
	<%response.sendRedirect("amm/score/audit.php");%>
</sec:authorize>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link rel="shortcut icon" href="${ctx}/favicon.ico" />
<title>${webName} 加载ing</title>

<style type="text/css">
body {
	overflow: hidden
}

#loading {
	border: 1px solid #ccc;
	margin: 200px auto 0 auto;
	min-width: 300px;
	padding: 2px;
	z-index: 20001;
	height: auto;
	width: 400px;
	overflow: auto;
}

#loading a {
	color: #225588;
}

#loading .loadingIndicator {
	background: white;
	font: bold 13px tahoma, arial, helvetica;
	padding: 10px;
	margin: 0;
	height: auto;
	color: #444;
	text-align: left;
}

#loadingMsg {
	font: normal 10px arial, tahoma, sans-serif;
}
</style>

<link href="${ctx}/resource/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx}/resource/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx}/resource/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
<link href="${ctx}/resource/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if IE]>
<link href="${ctx}/resource/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<link href="${ctx}/resource/ztree/zTreeStyle.css" type="text/css"  rel="stylesheet" />

<!--[if lte IE 9]>
<script src="${ctx}/resource/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->

</head>

<body>
	<!--add loading indicator while the app is being loaded-->
	<div id="loadingWrapper">
		<div id="loading">
			<div class="loadingIndicator">
				${webName}初始化...<br /> <br /> <span id="loadingMsg">加载 样式 图片... </span>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		document.getElementById('loadingMsg').innerHTML = '加载 核心 API...';
	</script>

	<script src="${ctx}/resource/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
	<!-- <script src="${ctx}/resource/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script> -->
	<script src="${ctx}/resource/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
	
	<script src="${ctx}/resource/dwz/js/dwz.core.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.util.date.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.drag.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.tree.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.accordion.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.ui.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.theme.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.navTab.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.tab.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.resize.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.dialog.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.stable.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.ajax.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.pagination.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.database.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.effects.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.panel.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.history.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.combox.js" type="text/javascript"></script>
	<script src="${ctx}/resource/dwz/js/dwz.print.js" type="text/javascript"></script>
	<!--
	<script src="bin/dwz.min.js" type="text/javascript"></script>
	-->
	<script src="${ctx}/resource/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="${ctx}/resource/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/ztree/jquery.ztree.excheck-3.5.min.js"></script>

	<script type="text/javascript">
		document.getElementById('loadingMsg').innerHTML = '正在配置系统<br/>请稍等...';
	</script>

	<script type="text/javascript">
		document.getElementById('loadingMsg').innerHTML = '正在进入系统<br/>请稍等...';
	</script>

	<script type="text/javascript">
		window.location.href = '${ctx}/index.php';
	</script>

</body>
</html>
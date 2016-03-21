<div class="head">
	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="945" height="185" title="top">
        <param name="movie" value="${ctx}/html/css/img/bar.swf">
        <param name="quality" value="high">
        <param name="wmode" value="transparent">
        <embed src="${ctx}/html/css/img/bar.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="945" height="185"></embed>
    </object>
</div>

<div class="navbar nav-pills">
	<div class="navbar-inner">
		<ul class="nav menu">
			<li><a href="${ctx}/">首页</a></li>
			<li class="divider-vertical"></li>
			<#list columns as c>
			<li><a href="${ctx}/${c.columnType}/${c.columnCode}.html">${c.columnName}</a></li>
			<li class="divider-vertical"></li>
			</#list>
			<li><a href="#rank">考核排名</a></li>
			<li class="divider-vertical"></li>
		</ul>
		<form class="navbar-search form-search pull-right" action="${ctx}/search.html" method="get">
			<div class="input-append">
			<input type="text" name="keyword" class="span2 search-query" placeholder="输入关键字搜索"/>
			<button type="submit" class="btn"><i class="icon-search"></i> 搜索</button>
			</div>
		</form>
	</div>
</div>
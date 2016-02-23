<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.ui-multiselect { padding:2px 0 2px 4px; text-align:left }
.ui-multiselect span.ui-icon { float:right }
.ui-multiselect-single .ui-multiselect-checkboxes input { position:absolute !important; top: auto !important; left:-9999px; }
.ui-multiselect-single .ui-multiselect-checkboxes label { padding:5px !important }

.ui-multiselect-header { margin-bottom:3px; padding:3px 0 3px 4px }
.ui-multiselect-header ul { font-size:0.9em }
.ui-multiselect-header ul li { float:left; padding:0 10px 0 0 }
.ui-multiselect-header a { text-decoration:none }
.ui-multiselect-header a:hover { text-decoration:underline }
.ui-multiselect-header span.ui-icon { float:left }
.ui-multiselect-header li.ui-multiselect-close { float:right; text-align:right; padding-right:0 }

.ui-multiselect-menu { display:none; padding:3px; position:absolute; z-index:10000; text-align: left }
.ui-multiselect-checkboxes { position:relative /* fixes bug in IE6/7 */; overflow-y:auto }
.ui-multiselect-checkboxes label { cursor:default; display:block; border:1px solid transparent; padding:3px 1px }
.ui-multiselect-checkboxes label input { position:relative; top:1px }
.ui-multiselect-checkboxes li { clear:both; font-size:0.9em; padding-right:3px }
.ui-multiselect-checkboxes li.ui-multiselect-optgroup-label { text-align:center; font-weight:bold; border-bottom:1px solid }
.ui-multiselect-checkboxes li.ui-multiselect-optgroup-label a { display:block; padding:3px; margin:1px 0; text-decoration:none }

/* remove label borders in IE6 because IE6 does not support transparency */
* html .ui-multiselect-checkboxes label { border:none }

	.str { color: #080; }
	.kwd { color: #008; }
	.com { color: #800; }
	.typ { color: #606; }
	.lit { color: #066; }
	.pun { color: #660; }
	.pln { color: #000; }
	.tag { color: #008; }
	.atn { color: #606; }
	.atv { color: #080; }
	.dec { color: #606; }
	pre.prettyprint { padding: 5px; border:1px solid #d2d2d2; background:#f5f5f5 }
	
	@media print {
	  .str { color: #060; }
	  .kwd { color: #006; font-weight: bold; }
	  .com { color: #600; font-style: italic; }
	  .typ { color: #404; font-weight: bold; }
	  .lit { color: #044; }
	  .pun { color: #440; }
	  .pln { color: #000; }
	  .tag { color: #006; font-weight: bold; }
	  .atn { color: #404; }
	  .atv { color: #060; }
	}
	
	body { font:12px Helvetica, arial, sans-serif }
	h1, h2, p { margin:10px 0 }
	.hidden { visibility:hidden }
	
	/* Layout helpers
	----------------------------------*/
	.ui-helper-hidden { display: none; }
	.ui-helper-hidden-accessible { position: absolute !important; clip: rect(1px 1px 1px 1px); clip: rect(1px,1px,1px,1px); }
	.ui-helper-reset { margin: 0; padding: 0; border: 0; outline: 0; line-height: 1.3; text-decoration: none; font-size: 100%; list-style: none; }
	.ui-helper-clearfix:before, .ui-helper-clearfix:after { content: ""; display: table; }
	.ui-helper-clearfix:after { clear: both; }
	.ui-helper-clearfix { zoom: 1; }
	.ui-helper-zfix { width: 100%; height: 100%; top: 0; left: 0; position: absolute; opacity: 0; filter:Alpha(Opacity=0); }
	
	/* workarounds */
	* html .ui-autocomplete { width:1px; } /* without this, the menu expands to 100% in IE6 */
	
	.ui-button { display: inline-block; position: relative; padding: 0; margin-right: .1em; cursor: pointer; text-align: center; zoom: 1; overflow: visible; } /* the overflow property removes extra width in IE */
	.ui-button, .ui-button:link, .ui-button:visited, .ui-button:hover, .ui-button:active { text-decoration: none; }
	.ui-button-icon-only { width: 2.2em; } /* to make room for the icon, a width needs to be set here */
	button.ui-button-icon-only { width: 2.4em; } /* button elements seem to need a little more width */
	.ui-button-icons-only { width: 3.4em; } 
	button.ui-button-icons-only { width: 3.7em; } 
	
	/*button text element */
	.ui-button .ui-button-text { display: block; line-height: 1.4;  }
	.ui-button-text-only .ui-button-text { padding: .4em 1em; }
	.ui-button-icon-only .ui-button-text, .ui-button-icons-only .ui-button-text { padding: .4em; text-indent: -9999999px; }
	.ui-button-text-icon-primary .ui-button-text, .ui-button-text-icons .ui-button-text { padding: .4em 1em .4em 2.1em; }
	.ui-button-text-icon-secondary .ui-button-text, .ui-button-text-icons .ui-button-text { padding: .4em 2.1em .4em 1em; }
	.ui-button-text-icons .ui-button-text { padding-left: 2.1em; padding-right: 2.1em; }
	/* no icon support for input elements, provide padding by default */
	input.ui-button { padding: .4em 1em; }
	
	/*button icon element(s) */
	.ui-button-icon-only .ui-icon, .ui-button-text-icon-primary .ui-icon, .ui-button-text-icon-secondary .ui-icon, .ui-button-text-icons .ui-icon, .ui-button-icons-only .ui-icon { position: absolute; top: 50%; margin-top: -8px; }
	.ui-button-icon-only .ui-icon { left: 50%; margin-left: -8px; }
	.ui-button-text-icon-primary .ui-button-icon-primary, .ui-button-text-icons .ui-button-icon-primary, .ui-button-icons-only .ui-button-icon-primary { left: .5em; }
	.ui-button-text-icon-secondary .ui-button-icon-secondary, .ui-button-text-icons .ui-button-icon-secondary, .ui-button-icons-only .ui-button-icon-secondary { right: .5em; }
	.ui-button-text-icons .ui-button-icon-secondary, .ui-button-icons-only .ui-button-icon-secondary { right: .5em; }
	
	/* workarounds */
	button.ui-button::-moz-focus-inner { border: 0; padding: 0; } /* reset extra padding in Firefox */
	
	/* Fades and background-images don't work well together in IE6, drop the image */
	* html .ui-tooltip {
		background-image: none;
	}
	body .ui-tooltip { border-width: 2px; }
	
	/* Component containers
	----------------------------------*/
	.ui-widget { font-family: Verdana,Arial,sans-serif/*{ffDefault}*/; font-size: 1.1em/*{fsDefault}*/; }
	.ui-widget .ui-widget { font-size: 1em; }
	.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button { font-family: Verdana,Arial,sans-serif/*{ffDefault}*/; font-size: 1em; }
	.ui-widget-content { border: 1px solid #aaaaaa/*{borderColorContent}*/; background: #ffffff/*{bgColorContent}*/ url(resource/mutiselect/images/ui-bg_flat_75_ffffff_40x100.png)/*{bgImgUrlContent}*/ 50%/*{bgContentXPos}*/ 50%/*{bgContentYPos}*/ repeat-x/*{bgContentRepeat}*/; color: #222222/*{fcContent}*/; }
	.ui-widget-content a { color: #222222/*{fcContent}*/; }
	.ui-widget-header { border: 1px solid #aaaaaa/*{borderColorHeader}*/; background: #cccccc/*{bgColorHeader}*/ url(resource/mutiselect/images/ui-bg_highlight-soft_75_cccccc_1x100.png)/*{bgImgUrlHeader}*/ 50%/*{bgHeaderXPos}*/ 50%/*{bgHeaderYPos}*/ repeat-x/*{bgHeaderRepeat}*/; color: #222222/*{fcHeader}*/; font-weight: bold; }
	.ui-widget-header a { color: #222222/*{fcHeader}*/; }
</style>

<script type="text/javascript" src="${ctx}/resource/mutiselect/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="${ctx}/resource/mutiselect/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx}/resource/mutiselect/assets/prettify.js"></script>
<script type="text/javascript" src="${ctx}/resource/mutiselect/multiselectSrc/jquery.multiselect.js"></script>

<script type="text/javascript">
	$(function(){
	    $("#sela").multiselect({
	        noneSelectedText: "==请选择==",
	        checkAllText: "全选",
	        uncheckAllText: '全不选',
	        selectedList:4
	    });
	});
	
</script>
<div class="pageContent">
	<form method="post" action="${ctx}/device/save.php" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">控制器名称：</label>
				<input name="deviceName" class="required" type="text" size="30" value="${device.deviceName}" alt="请输入控制器名称"/>
				<input name="id" type="hidden" value="${device.deviceId}"/>
			</p>
			<p>
				<label style="text-align: right">控制器编号及地址：</label>
				<input name="deviceCode" class="required" type="text" size="30" value="${device.deviceCode}" alt="通信协议中信息模块ID+控制器地址"/>
			</p>
			<p>
				<label style="text-align: right">用户单位：</label>
					<select name="departmentId" id="department">
						<c:forEach var="dept" items="${departments}">
							<option value="${dept.departmentId}">${dept.departmentName}</option>
						</c:forEach>
					</select>
			</p>
			<p>
				<label style="text-align: right">区域：</label>
					<select id ="areas" name="areaId">
						<c:forEach var="area" items="${areas}">
							<option value="${area.areaId}">${area.areaName}</option>
						</c:forEach>
					</select>
			</p>
			<p>
				<label style="text-align: right">探测器数量：</label>
				<input name="proberCount" class="required" type="text" size="30" value="${device.proberCount}" alt="请输入探测器数量"/>
			</p>
			<p>
				<label style="text-align: right">外控器数量：</label>
				<input name="attachDeviceCount" class="required" type="text" size="30" value="${device.attachDeviceCount}" alt="请输入外控器数量"/>
			</p>
			<p>
				<label style="text-align: right">管理员:</label>
				<select id ="sela" multiple="multiple" name="userIds" class="uclas">
					<c:forEach var="user" items="${users}">
						<option value="${user.userId}">${user.userName}</option>
					</c:forEach>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
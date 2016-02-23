<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h2 class="contentTitle">
	设置参数
</h2>
<div class="pageContent" >
	<form method="post" action="${ctx}/alarmParameter/save.php"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" style="height: 165px;">
		<input name="id" type="hidden" value="${alarmParameter.id}" />
		<p>
			<label style="text-align: right">
				浓度：
			</label>

			<input name="standConcentration" type="text" style="width: 225px;" value="${alarmParameter.standConcentration}" alt="请输入浓度，百分数，不需要输入百分号" />
		</p>
		<p>
			<label style="text-align: right">
				单位：
			</label>
			<select name="unit" width="30">
				<option value="0">--请选择单位--</option>
				<option value="LEL">LEL</option>
				<option value="PPM">PPM</option>
				<option value="VOL">VOL</option>
				<option value="PPB">PPB</option>
			</select>
		</p>
		
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
	<script type="text/javascript">
	//自定义插件
	var editor;
	var allPlugin;
	$(pageInit);
	function pageInit() {
		allPlugin = {
			paipan : {
				c : 'paiban',
				t : '一键排版',
				e : function() {
					var text = editor.getSource();
					text = text.replaceAll("<p style=\"",
							"<p style=\"text-indent:2em;");
					editor.setSource(text);
				}
			}
		}
	}
	$(document)
			.ready(
					function() {
						// 初始化xhEditor编辑器插件
						editor = $('#content')
								.xheditor(
										{
											plugins : allPlugin,
											tools : 'Bold,Italic,Underline,|,FontSize,Align,List,Outdent,Indent,|,Table,Img,Link,|,paipan,Preview,Print,Fullscreen,Source',
											upMultiple : true,
											upImgUrl : "${ctx}/web/news/xhEditorUpload.html",
											upImgExt : "jpg,jpeg,gif,bmp,png",
											upLinkUrl : "${ctx}/web/news/xhEditorUpload.html",
											upLinkExt : "zip,rar,txt,doc,docx,xls,xlsx",
											onUpload : insertUpload,
											html5Upload : false
										});
						// xbhEditor编辑器图片上传回调函数
						function insertUpload(msg) {
							var _msg = msg.toString();
							var _picture_name = _msg.substring(_msg
									.lastIndexOf("/") + 1);
							var _picture_path = Substring(_msg);
							var _str = "<input type='radio' name='picture' value='"
						+ _picture_path
						+ "'/>"
									+ _picture_name + "<br/>";
							$("#content").append(_msg);
							$("#uploadList").append(_str);
						}
						// 处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
						function Substring(s) {
							return s.substring(s.substring(0,
									s.lastIndexOf("/")).lastIndexOf("/"),
									s.length);
						}
					});
</script>
</div>



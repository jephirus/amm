package cn.jxust.base.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.ShortMessage;
import cn.jxust.base.service.ShortMessageService;
import cn.jxust.common.action.BaseDwzAction;

@Controller
@RequestMapping("/base/shortMessage")
public class ShortMessageAction extends BaseDwzAction
{
	@Resource
	private ShortMessageService shortMessageService;
	
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(ShortMessage shortMessage)
	{
		/**
		 * 添加用户前先判断用户是否存在！
		 * 要初始化用户角色和部门
		 */
		if (shortMessage.getId() == null)
		{
			shortMessageService.save(shortMessage);
		} else
		{
			shortMessageService.update(shortMessage);
		}

		return closeCurrentAndRefresh("shortMessageList");
	}
	
	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(Integer[] items)
	{
		shortMessageService.delete(items);
		return refresh("shortMessageList");
	}
}

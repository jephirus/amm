package cn.jxust.base.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.Config;
import cn.jxust.base.service.ConfigService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.utils.ConfigUtils;

@Controller
@RequestMapping("/base/config")
public class ConfigAction extends BaseDwzAction
{
	@Resource
	private ConfigService configService;
	
	@RequestMapping(value="/list.php")
	public String list(HttpServletRequest request)
	{
		Map<String, String> configs = ConfigUtils.getInstance().getValues();
		request.setAttribute("configs", configs);
		return "/base/config/list";
	}
	
	@RequestMapping(value = "/edit/{key}.php")
	public String editForm(@PathVariable("key") String key, Model model)
	{
		Config config = configService.getFieldValue("configKey", key);
		model.addAttribute(config);
		return "/base/config/input";
	}
	
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Config config)
	{
		configService.update(config);
		//重新加载Config
		ConfigUtils.refreshConfigUtils();
		return closeCurrentAndRefresh("configList");
	}
}

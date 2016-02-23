package cn.jxust.base.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.Authority;
import cn.jxust.base.service.AuthorityService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.orm.PageData;

@Controller
@RequestMapping("/base/authority")
public class AuthorityAction extends BaseDwzAction
{
	@Resource
	private AuthorityService authorityService;

	@RequestMapping("/list.php")
	public ModelMap list(@RequestParam(defaultValue = "1") int pageNum, Authority entity)
	{
		PageData<Authority> pageData = new PageData<Authority>(pageNum);
		pageData = authorityService.findPage(pageData, entity);

		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		return "/authority/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		Authority authority = authorityService.find(id);
		model.addAttribute(authority);
		return "/authority/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Authority authority)
	{
		if (authority.getAuthId() == 0)
		{
			authorityService.save(authority);
		} else
		{
			authorityService.update(authority);
		}

		return closeCurrentAndRefresh("authorityList");
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") Integer[] id)
	{
		authorityService.delete(id);
		return refresh("authorityList");
	}
}

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

import cn.jxust.base.model.Role;
import cn.jxust.base.service.RoleService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.orm.PageData;

@Controller
@RequestMapping("/base/role")
public class RoleAction extends BaseDwzAction
{
	@Resource
	private RoleService roleService;

	@RequestMapping("/list.php")
	public ModelMap list(@RequestParam(defaultValue = "1") int pageNum, Role entity)
	{
		PageData<Role> pageData = new PageData<Role>(pageNum);
		pageData = roleService.findPage(pageData, entity);

		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		return "/role/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		Role role = roleService.find(id);
		model.addAttribute(role);
		return "/role/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Role role)
	{
		if (role.getRoleId() == 0)
		{
			roleService.save(role);
		} else
		{
			roleService.update(role);
		}

		return closeCurrentAndRefresh("roleList");
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") Integer[] id)
	{
		roleService.delete(id);
		return refresh("roleList");
	}
}

package cn.jxust.base.action;

import java.util.ArrayList;
import java.util.List;
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

import cn.jxust.base.model.Department;
import cn.jxust.base.model.Role;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.base.service.RoleService;
import cn.jxust.common.action.BaseDwzAction;

@Controller
@RequestMapping("/base/department")
public class DepartmentAction extends BaseDwzAction
{
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="/list.php")
	public ModelMap list(@RequestParam(defaultValue = "1") int pageNum, Department entity)
	{
		return new ModelMap(departmentService.getAllByPage(pageNum, entity));
	}
	
	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		return "/base/department/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		Department department = departmentService.find(id);
		model.addAttribute(department);
		return "/base/department/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Department department)
	{
		if (department.getDepartmentId() == null || "".equals(department.getDepartmentId()))
		{
			departmentService.save(department);
			// 通过单位/机构派生出单位/机构用户
			User user = new User();
			user.setUserName(department.getDepartmentName());
			user.setEnabled(true);
			user.setPassword("111111");
			user.setPhoneNumber(department.getPhoneNumber());
			user.setUserDep(department);
			user.setRealName(department.getDepartmentName());
			
			Role role = roleService.find(2);  // 创建单位/机构用户角色
			List<Role> lr = new ArrayList<Role>();
			lr.add(role);
			user.setUserRole(lr);
			userService.save(user);
		} else
		{
			Department d = departmentService.find(department.getDepartmentId());
			d.setDepartmentDesc(department.getDepartmentDesc());
			d.setDepartmentName(department.getDepartmentName());
			d.setPhoneNumber(department.getPhoneNumber());
			departmentService.update(d);
		}

		return closeCurrentAndRefresh("departmentList");
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(Integer[] items)
	{
		departmentService.delete(items);
		return refresh("departmentList");
	}
	
	@RequestMapping(value = "/details.php")
	public String detailsForm(Model model)
	{
		User user = getCurrentUser();
		model.addAttribute("username", user.getUserName());
		model.addAttribute("realName", user.getRealName());
		
		return "/base/department/details";
	}
	
}

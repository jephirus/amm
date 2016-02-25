package cn.jxust.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Department;
import cn.jxust.base.model.Role;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.base.service.RoleService;
import cn.jxust.base.service.UserService;
import cn.jxust.common.action.BaseDwzAction;

@Controller
@RequestMapping("/base/user")
public class UserAction extends BaseDwzAction {
	@Resource
	private UserService userService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private RoleService roleService;

	/**
	 * 查看用户列表
	 * @param pageNum
	 * @param type 超级游客，类型为1；如果是单位游客，类型为2；如果是单位管理员，类型为3
	 * @param entity
	 * @return
	 */
	@RequestMapping("/list/{type}.php")
	public ModelAndView list(@RequestParam(defaultValue = "1", required = false) int pageNum, @PathVariable("type") Integer type, User entity)
	{
		ModelAndView mv = new ModelAndView();
		User user = this.getCurrentUser();
		if (user.getUserName().equals("admin")) { // 如果用户是admin，可看所有用户
			if (type == 3)  // 系统管理员可看所单位管理员用户，单位管理员按单位以树型分类。
			{
				mv.addObject("userTree",userService.createUserTree(departmentService.getAll()));
				mv.setViewName("/base/user/list/3");
				return mv;
			}
			else
			{
				mv.addObject(userService.getAllByPage(pageNum, entity, type));
				mv.setViewName("/base/user/list/" + type);
				return mv;
			}
		} else { // 如果是单位用户，只能看其单位内的用户
			if (type == 3)
			{
				mv.addObject(userService.getAllByPage(pageNum, entity, type));
				mv.setViewName("/base/user/list/31");
				return mv;
			}
			else
			{
				mv.addObject(userService.getAllByPage(pageNum, entity, type));
				mv.setViewName("/base/user/list/" + type);
				return mv;
			}
		}
	}

	/**
	 * 查看用户列表
	 * @param pageNum
	 * @param type 超级游客，类型为1；如果是单位游客，类型为2；如果是单位管理员，类型为3
	 * @param entity
	 * @return
	 */
	@RequestMapping("/depUserList/{departmentId}.php")
	public ModelAndView depUserList(@PathVariable("departmentId") Integer departmentId)
	{
		ModelAndView mv = new ModelAndView();
		Department dep = departmentService.find(departmentId);
		List<User> users = userService.getUserByDepartment(dep);
		mv.addObject("users", users);
		mv.setViewName("/base/user/list/depUserList");
		return mv;
	}

	/**
	 * 添加用户窗口 admin只添加单位/机构用户、和两种游客，同时单位/机构用户不在此处添加，在添加单位时派生出单位/机构用户
	 * @param type 如果是添加超级游客，类型为1；如果是添加单位游客，类型为2；如果是添加单位管理员，类型为3
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new/{type}.php")
	public String addForm(@PathVariable("type") Integer type, Model model) {

		List<Role> roles = new ArrayList<Role>();
		if (type == 1) { // 超级游客没有单位
			roles.add(roleService.find(4));
			model.addAttribute("roles", roles);
			model.addAttribute("type", type);
		} else if (type == 2) {
			model.addAttribute("departments", departmentService.getAll());
			roles.add(roleService.find(5));
			model.addAttribute("roles", roles);
			model.addAttribute("type", type);
		} else if (type == 3) {
			if (getCurrentUser().getUserName().equals("admin")) {
				model.addAttribute("departments", departmentService.getAll());
			}else{
				List<Department> dl = new ArrayList<Department>();
				dl.add(getCurrentDepartment());
				model.addAttribute("departments", dl);
			}
			roles.add(roleService.find(3));
			model.addAttribute("roles", roles);
			model.addAttribute("type", type);
		}

		return "/base/user/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.find(id);
		model.addAttribute("user", user);
		for (Role r : user.getUserRole()) {
			if (null != r) {
				model.addAttribute("userRole", r);
				break;
			}
		}
		model.addAttribute("type", user.getType());
		model.addAttribute("departments", departmentService.getAll());
		model.addAttribute("roles", roleService.findAll());
		return "/base/user/input";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(User user, Integer departmentId, Integer roleId){
		/*
		 * 添加用户前先判断用户是否存在！ 要初始化用户角色和部门
		 */
		Role role = roleService.find(roleId);
		List<Role> lr = new ArrayList<Role>();
		lr.add(role);
		if (null != departmentId) {
			Department department = departmentService.find(departmentId);
			user.setUserDep(department);
		}
		user.setUserRole(lr);
		user.setPassword("111111"); // 初始化密码
		user.setRealName(user.getUserName()); // 将从前台传来的userName赋给realName

		if (user.getUserId() == null) {
			userService.save(user);
		} else {
			userService.update(user);
		}
		return closeCurrentAndRefresh("userList");
	}

	@RequestMapping(value = "/delete.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> delete(Integer[] items) {
		userService.delete(items);
		return refresh("userList");
	}

	@RequestMapping(value = "/changePw.php")
	public String changepwForm(Model model) {
		model.addAttribute("username", getCurrentUser().getUserName());
		return "/base/user/changePw";
	}

	@RequestMapping(value = "/updatePw.php")
	public @ResponseBody Map<String, String> changepw(Model model, String newPassword, String oldPassword) {
		User currentUser = getCurrentUser();
		boolean flag = userService.changePassword(currentUser, newPassword,
				oldPassword);
		model.addAttribute("username", currentUser.getUserName());
		if (flag) {
			model.addAttribute("tips", SUCCESS);
		} else {
			model.addAttribute("tips", "修改失败，原密码错误");
		}
		return closeCurrentAndRefresh("userList");
	}

	/**
	 * 初始化密码
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/initPw/{id}.php")
	public @ResponseBody Map<String, String> initPassword(
			@PathVariable("id") int userId) {
		userService.initPassword(userId);
		return refresh("userList");
	}

	@RequestMapping(value = "/isRegistered.php")
	public void isUserName(String userName, HttpServletResponse response) throws IOException {
	
		if(userService.getUserByPhoneNumber(userName) != null)
		{
			response.getWriter().write("false");
		}
		else
			response.getWriter().write("true");
	}
	
}

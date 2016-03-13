package cn.jxust.common.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.base.service.UserService;
import cn.jxust.device.service.DeviceService;
import cn.jxust.log.service.DeviceLogService;
import cn.jxust.utils.ConfigUtils;

/**
 * index
 * 
 * @author chenle 2013-5-5 4:48:19
 */

@Controller
@RequestMapping("/")
public class IndexController extends BaseDwzAction
{
	@Resource
	private UserService userService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private DeviceLogService deviceLogService;  // 控制器消息数据

	public String getWebName()
	{
		return ConfigUtils.getInstance().getValues().get("web");
	}
	
	public Cookie setCookieByCurrentUser()
	{
		//设置Cookie
		try
		{
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = userDetails.getUsername();
			username = URLEncoder.encode(username,"utf-8");
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60*60*24*7*2);
			return cookie;
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Cookie setCookieByCurrentUserType(String type)
	{
		//设置Cookie
		Cookie cookie = new Cookie("type", type);
		cookie.setMaxAge(60*60*24*7*2);
		return cookie;
	}

	/**
	 * 默认首页
	 * 设置Cookie
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model, @CookieValue(value="username", defaultValue="") String username, @CookieValue(value="type", defaultValue="") String type)
	{
		try
		{
			username = URLDecoder.decode(username,"utf-8");
			model.addAttribute("department", username);
			
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return "login";
	}
	
	/**
	 * 后台用户登录
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.php")
	public String loginPage(Model model)
	{
		model.addAttribute("webName", getWebName());
		return "login";
	}

	/**
	 * 后台加载页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/loading.php")
	public String loadingPage(Model model, HttpServletResponse response)
	{
		response.addCookie(setCookieByCurrentUser());
		
		model.addAttribute("webName", getWebName());
		return "loading";
	}

	/**
	 * 后台首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index.php")
	public String indexPage(Model model, HttpServletResponse response)
	{
		response.addCookie(setCookieByCurrentUser());
		
		Department department = this.getCurrentDepartment();
		
		String mt = deviceService.createMonitorTree(department);
		String dt = deviceService.createDeviceTree(department);
		String ut = userService.createUserTree(department);
		String dlt = deviceLogService.createDeviceLogTree(department);		// 日志记录查询
		model.addAttribute("monitorTree", mt);
		model.addAttribute("deviceTree", dt);
		model.addAttribute("userTree", ut);
		model.addAttribute("deviceLogTree", dlt);
		model.addAttribute("webName", getWebName());
		model.addAttribute("overview", ConfigUtils.getInstance().getValues().get("overview"));
		
		model.addAttribute("users",userService.findAll());
		return "index";
	}
	
	/**
	 * 返回定位信息，这里用城市定位,ajax调用
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPosition.php")
	public @ResponseBody Map<String,String> postion(int id)
	{
		Map<String,String> result = new HashMap<String, String>();
		String position = "江西理工大学";
		result.put("position", position);
		return result;
	}
	
	
	@RequestMapping(value = "/changePw.php")
	public String changepwForm(Model model)
	{
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", userDetails.getUsername());
		return "/changePw";
	}
	
	@RequestMapping(value = "/updatePw.php")
	public @ResponseBody Map<String, String> changepw(Model model, String newPassword, String oldPassword)
	{
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.getUserByName(userDetails.getUsername());
		boolean flag = userService.changePassword(currentUser, newPassword, oldPassword);
		Map<String, String> result = new HashMap<String, String>();
		if(flag)
		{
			result.put("statusCode", "200");
	        result.put("message", "修改成功");
		}
		else
		{
			result.put("statusCode", "300");
	        result.put("message", "修改失败，原密码错误");
		}
		result.put("navTabId", "changepwd");
		result.put("callbackType", "closeCurrent");
        return result;
	}
}

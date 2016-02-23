package cn.jxust.common.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.base.service.UserService;

public class BaseDwzAction
{
	@Resource
	public UserService userService;
	
	@Resource
	public DepartmentService departmentService;

	public static String SUCCESS = "操作成功";
	public static String ERROR = "操作失败";
	
	public Map<String, String> success(String message, String navTabId, String callbackType)
	{
		Map<String, String> result = new HashMap<String, String>();
		result.put("navTabId", navTabId);
		result.put("message", message);
		result.put("callbackType", callbackType);
		return result;
	}
	
	public static Map<String, String> success() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("statusCode", "200");
        result.put("message", SUCCESS);
        return result;
    }

    public static Map<String, String> success(String successMsg) {
    	Map<String, String> result = new HashMap<String, String>();
    	result.put("statusCode", "200");
    	result.put("message", successMsg);
        return result;
    }

    public static Map<String, String> error() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("statusCode", "300");
        result.put("message", ERROR);
        return result;
    }

    public static Map<String, String> error(String errorMsg) {
    	Map<String, String> result = new HashMap<String, String>();
    	 result.put("statusCode", "300");
         result.put("message", errorMsg);
        return result;
    }

    public static Map<String, String> refresh(String refreshNavTabId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("message", SUCCESS);
        result.put("statusCode", "200");
        result.put("navTabId", refreshNavTabId);
        return result;
    }

    public static Map<String, String> closeCurrentAndRefresh(String refreshNavTabId) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("navTabId", refreshNavTabId);
        result.put("message", SUCCESS);
        result.put("statusCode", "200");
        result.put("callbackType", "closeCurrent");
        return result;
    }
    
    public String getCurrentUsername()
    {
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
    }
    
    public String getCurrentUsername(HttpServletRequest request)
    {
		//获得当前用户和单位
    	SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
		return securityContextImpl.getAuthentication().getName();
    }
    
    public User getCurrentUser()
    {
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getUserByName(userDetails.getUsername());
    }
    
    public Department getCurrentDepartment()
    {
		//获得当前用户和单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByName(userDetails.getUsername());
		if (null != user.getUserDep()) {
			return departmentService.find(user.getUserDep().getDepartmentId());
		}else{
			return null;
		}
    }
}

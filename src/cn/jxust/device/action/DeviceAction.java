package cn.jxust.device.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.base.service.AreaService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.device.model.Device;
import cn.jxust.device.model.SysStateMessage;
import cn.jxust.device.service.DeviceService;
import cn.jxust.device.service.ProberService;
import cn.jxust.device.service.SysStateMessageService;
import cn.jxust.orm.PageData;

import com.dabizi.amm.receiver.AlarmMessageHandler;
import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

@Controller
@RequestMapping("/device")
public class DeviceAction  extends BaseDwzAction
{
	@Resource
	private SysStateMessageService sysStateMessageService;
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private ProberService proberService;
	
	@Resource
	private PointInfoService pointInfoService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private AlarmMessageHandler  alarmMessageHandler;
	
	
	/**
	 * 控制器信息管理列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/monitorList/{areaId}.php")
	public ModelAndView monitorList(@PathVariable("areaId") int areaId,HttpSession session)
	{
			ModelAndView mv = new ModelAndView();
			Set<Device> devices = areaService.find(areaId).getDevices();
			session.setAttribute("areaId", areaId);
			mv.addObject("devices", devices);
			mv.setViewName("/device/monitorList");
		return mv;
	}

	/**
	 * 控制器信息管理列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/monitor/{areaId}.php")
	public void monitorJsonList(@PathVariable("areaId") int areaId, HttpServletResponse response, HttpServletRequest request){
		JSONArray jsonArray = new JSONArray();
		HttpSession session = request.getSession();
		session.setAttribute("areaId", areaId);
		try {
			Set<Device> devices = areaService.find(areaId).getDevices();
			for (Device device : devices) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("deviceId", device.getDeviceId()+"");
				map.put("deviceCode", device.getDeviceCode());
				map.put("deviceName", device.getDeviceName());
				map.put("deviceDesc", device.getDeviceDesc());
				map.put("proberAlarmCount", device.getProberAlarmCount()+"");
				map.put("proberFaultCount", device.getProberFaultCount()+"");
				map.put("attachDeviceFaultCount", device.getAttachDeviceFaultCount()+"");
				map.put("attachDeviceCount", device.getAttachDeviceCount()+"");
				map.put("proberCount", device.getProberCount()+"");
				jsonArray.add(map);
			}
			response.getWriter().println(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 以区域为单位显示控制器信息列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/deviceList/{areaId}.php")
	public ModelAndView deviceList(@RequestParam(defaultValue = "1") int pageNum, @PathVariable("areaId") int areaId)
	{
		ModelAndView mv = new ModelAndView();
		
		PageData<Device> pageData = new PageData<Device>(pageNum);
		Area area = areaService.find(areaId);
		pageData = deviceService.getAll(pageData, area);
		mv.addObject(pageData);
		mv.setViewName("/device/deviceList");
		return mv;
	}
	
	/**
	 * 控制器日志信息列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/logList.php")
	public ModelAndView logList(@RequestParam(defaultValue = "1") int pageNum)
	{
		ModelAndView mv = new ModelAndView();
		
		PageData<SysStateMessage> pageData = new PageData<SysStateMessage>(pageNum);
		pageData = sysStateMessageService.getAll(pageData);
		mv.addObject(pageData);
		return mv;
	}
	
	/**
	 * 控制器下的探测器列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/proberList/{deviceId}.php")
	public ModelAndView proberList(@PathVariable("deviceId") Integer id)
	{
		ModelAndView mv = new ModelAndView();
		
		Device device = deviceService.find(id);
		mv.addObject("device", device);
		mv.setViewName("/device/proberList");
		return mv;
	}

	/**
	 * 添加控制器对话框
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		User user = this.getCurrentUser();
		
		Department d = user.getUserDep();
		if (null != user.getUserDep()) {
			List<Department> dl = new ArrayList<Department>();
			dl.add(d);
			model.addAttribute("departments", dl);
			model.addAttribute("users", d.getUsers());
			model.addAttribute("areas", d.getAreas());
			return "/device/depInput";
		}
		else
		{
			model.addAttribute("departments", departmentService.getAll());
			return "/device/adminInput";
		}
	}

	/**
	 * 实现控制器的添加。
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Device device, String userIds, Integer areaId)
	{
		Device serialDevice;
		if(device.getDeviceId() != null)
		{
			serialDevice = deviceService.find(device.getDeviceId());
			String[] usersId = userIds.split(",");
			Set<User> deviceManagers = new HashSet<User>();
			for(String id: usersId){   // 添加该控制器的管理员
				User u = userService.find(Integer.parseInt(id));
				deviceManagers.add(u);
			}
			serialDevice.setDeviceManagers(deviceManagers);
			serialDevice.setArea(areaService.find(areaId));
			serialDevice.setDeviceCode(device.getDeviceCode());
			serialDevice.setDeviceDesc(device.getDeviceDesc());
			serialDevice.setDeviceName(device.getDeviceName());
			deviceService.save(device.getProberCount(),device.getAttachDeviceCount(), serialDevice);  // 用于添加控制器中的探测器和外控制器
		}
		else
		{
			String[] usersId = userIds.split(",");
			Set<User> deviceManagers = new HashSet<User>();
			for(String id: usersId){   // 添加该控制器的管理员
				User u = userService.find(Integer.parseInt(id));
				deviceManagers.add(u);
			}
			device.setDeviceManagers(deviceManagers);
			device.setArea(areaService.find(areaId));
			deviceService.save(device.getProberCount(),device.getAttachDeviceCount(), device);  // 用于添加控制器中的探测器和外控制器
		}
		
		return closeCurrentAndRefresh("deviceList");
	}

	/**
	 * 查看控制器相关信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view/{deviceId}.php")
	public String viewForm(@PathVariable("deviceId") Integer id, Model model)
	{
		Device device = deviceService.find(id);
		Set<User> u = device.getDeviceManagers();
		model.addAttribute("device", device);
		model.addAttribute("u", u);
		
		return "/device/view";
	}

	/**
	 * 控制器信息修改
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{deviceId}.php")
	public String editForm(@PathVariable("deviceId") Integer id, Model model)
	{
		User user = this.getCurrentUser();
		Device device = deviceService.find(id);
		model.addAttribute("device", device);

		Set<User> us = device.getDeviceManagers();
		int i = 0;
		for (User u: us) {
			model.addAttribute("user" + ++i, u);
		}
		
		Department d = user.getUserDep();
		if (null != user.getUserDep())
		{
			List<Department> dl = new ArrayList<Department>();
			dl.add(d);
			model.addAttribute("departments", dl);
			model.addAttribute("users", d.getUsers());
			model.addAttribute("areas", d.getAreas());
			return "/device/depEdit";
		}
		else
		{
			model.addAttribute("areas", device.getArea().getDepartment().getAreas()); // 取该设备所有单位的所有区域
			model.addAttribute("users", device.getArea().getDepartment().getUsers()); // 取该设备所有单位的所有区域
			model.addAttribute("departments", departmentService.getAll());
			return "/device/adminEdit";
		}
	}

	@RequestMapping(value = "/delete.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> delete(Integer[] items)
	{
		deviceService.delete(items);
		return refresh("deviceList");
	}
	
	@RequestMapping(value = "/reset/{deviceId}.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> reset(@PathVariable("deviceId") Integer id)
	{
		Device device = deviceService.find(id);
		PointInfo pi = device.getPointInfo();
		pi.setStatus(0);
		pi.setStatusColor("green");
		
		alarmMessageHandler.resetDevice(device.getDeviceCode(), device);
		return success("已复位！");
	}
	
	@RequestMapping(value = "/getAreasAndUsersByDepartment.php")
	public @ResponseBody Map<String, String> getAreasAndUsersByDepartment(Integer departmentId){
		
		Department department = departmentService.find(departmentId);
		
        Map<String, String> result = new HashMap<String, String>();
		JsonConfig areaConfig = new JsonConfig();
		areaConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("department")) { // 过虑掉无法加载的属性
					return true;
				} else if (arg1.equals("devices")) {
					return true;
				} else {
					return false;
				}
			}
		});
		
		JsonConfig userConfig = new JsonConfig();
		userConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("userDep")) { // 过虑掉无法加载的属性
					return true;
				} else if (arg1.equals("userRole")) {
					return true;
				} else if (arg1.equals("device")) {
					return true;
				} else if (arg1.equals("serialVersionUID")) {
					return true;
				} else {
					return false;
				}
			}
		});
		// 将数据转换成Json数据
		List<Area> la = new ArrayList<Area>();
		la.addAll(department.getAreas());
		JSONArray areas = JSONArray.fromObject(la, areaConfig);
		
		List<User> lu = new ArrayList<User>();
		lu.addAll(department.getUsers());
		JSONArray users = JSONArray.fromObject(lu, userConfig);
		
	    result.put("areas", areas.toString());
	    result.put("users", users.toString());
        
        return result;
	}

}
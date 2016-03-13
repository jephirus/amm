package cn.jxust.log.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Department;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.device.model.Device;
import cn.jxust.device.model.Prober;
import cn.jxust.device.service.DeviceService;
import cn.jxust.log.model.DeviceLog;
import cn.jxust.log.model.ProberLog;
import cn.jxust.log.service.DeviceLogService;
import cn.jxust.orm.PageData;

/**
 * 控制器日志记录分析
 * 
 * @author Jephirus
 *
 */

@Controller
@RequestMapping("/log/device")
public class DeviceLogAction extends BaseDwzAction {

	@Resource
	private DeviceService deviceService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private DeviceLogService deviceLogService; // 控制器消息数据

	@RequestMapping("/list/{departmentId}.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum,
			DeviceLog entity, @PathVariable("departmentId") Integer departmentId)
	{
		PageData<DeviceLog> pageData = new PageData<DeviceLog>(pageNum);
		List<Device> devices = new ArrayList<>();
		Department department = departmentService.find(departmentId);

		pageData = deviceLogService.getAllDeviceLog(pageData, department);
		devices = deviceService.findAll(department);
		ModelAndView mv = new ModelAndView();
		mv.addObject("devices", devices);
		mv.addObject(pageData); // /----????
		mv.setViewName("/log/device/list");
		return mv;
	}

	/**
	 * 查询控制器、探测器等报警相关日志信息。
	 * @param pageNum
	 * @param deviceId
	 * @param proberNum
	 * @param beginDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getDeviceLogByQuery.html")
	public String getDeviceLogByQuery(@RequestParam(defaultValue = "1") int pageNum, Integer deviceCode,
			String proberNum, String beginDate, String endDate, Model model)
	{
		Department department = getCurrentDepartment();
		if (deviceCode == 0) {	// 查当前单位所有控制器信息。
			PageData<DeviceLog> pageData = new PageData<DeviceLog>(pageNum);
			PageData<DeviceLog> pd = deviceLogService.getDeviceLogByQuery(pageData, deviceCode, proberNum, beginDate,endDate, pageNum, department);
			model.addAttribute("pageData", pd);
			return "/log/device/list";
		}
		else if(!proberNum.equals(""))	// 查所稳定控制器的探测器信息或外控器信息。
		{
			PageData<ProberLog> pageData = new PageData<ProberLog>(pageNum);
			PageData<ProberLog> pd = deviceLogService.getProberLogByQuery(pageData, deviceCode, proberNum, beginDate, endDate, pageNum, department);
			model.addAttribute("pageData", pd);
			return "/log/prober/list";
		}
		else
			return "/log/device/list";
	}

	/**
	 * 用于级联查找控制器下的所有探测器
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/probers/{id}.php")
	public @ResponseBody String probersForCascade(@PathVariable("id") Integer deviceId)
	{
		Set<Prober> probers = deviceService.find(deviceId).getProbers();
		StringBuffer jsonProbers = new StringBuffer();
		jsonProbers.append("[");
		probers.size();
		for (Prober prober : probers) {
			jsonProbers.append("[\""); // json格式数据要加双引号“”
			jsonProbers.append(prober.getLocation());
			jsonProbers.append("\",\"");
			jsonProbers.append(prober.getLocation());
			jsonProbers.append("\"],");
		}
		jsonProbers.deleteCharAt(jsonProbers.length() - 1);
		jsonProbers.append("]");
		return jsonProbers.toString();
	}

}

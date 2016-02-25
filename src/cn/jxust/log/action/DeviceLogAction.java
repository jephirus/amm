package cn.jxust.log.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Department;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.device.model.Device;
import cn.jxust.device.service.DeviceService;
import cn.jxust.log.model.DeviceLog;
import cn.jxust.log.service.DeviceLogService;
import cn.jxust.orm.PageData;

/**
 * 控制器日志记录分析
 * @author Jephirus
 *
 */

@Controller
@RequestMapping("/log/device")
public class DeviceLogAction extends BaseDwzAction
{
	@Resource
	private DeviceLogService deptQuotaService;
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private DeviceLogService deviceLogService;  // 控制器消息数据

	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, DeviceLog entity)
	{
		PageData<DeviceLog> pageData = new PageData<DeviceLog>(pageNum);
		List<Device> devices = new ArrayList<>();
		Department department = this.getCurrentDepartment();
		if (null == department) {
			pageData = deviceLogService.getAllDeviceLog(pageData);
			devices = deviceService.findAll();
			
		}else{
			pageData = deviceLogService.getAllDeviceLog(pageData, department);
			devices = deviceService.findAll(department);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("devices", devices);
		mv.addObject(pageData); ///----????
		mv.setViewName("/log/device/list");
		return mv;
	}
	
	@RequestMapping(value = "/statQuery.html")
	public String statQuery(@RequestParam(defaultValue = "1") int pageNum,
			String deviceName, String proberLocation, String beginDate, String endDate, Model model) {
		PageData<DeviceLog> pageData = deviceLogService.getDeviceLogByQuery(deviceName, proberLocation,
				beginDate, endDate, pageNum);
		model.addAttribute("pageData", pageData);
		return "/log/device/list";
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(String[] items)
	{
		deptQuotaService.delete(items);
		return refresh("deptQuotaList");
	}
	
	/**
	 * 启用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enable/{id}.php")
	public @ResponseBody Map<String, String> enable(@PathVariable("id") String id)
	{
		//deptQuotaService.enable(id);
		return refresh("deptQuotaList");
	}
	
	/**
	 * 禁用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/disable/{id}.php")
	public @ResponseBody Map<String, String> disable(@PathVariable("id") String id)
	{
		//deptQuotaService.disable(id);
		return refresh("deptQuotaList");
	}
}

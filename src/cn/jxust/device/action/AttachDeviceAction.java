package cn.jxust.device.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.device.model.AttachDevice;
import cn.jxust.device.model.Device;
import cn.jxust.device.service.AttachDeviceService;
import cn.jxust.device.service.DeviceService;

@Controller
@RequestMapping("/attachDevice")
public class AttachDeviceAction extends BaseDwzAction
{
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private AttachDeviceService attachDeviceService;

	/**
	 * 通过控制器检索探测器
	 * @param deviceId
	 * @return
	 */
	@RequestMapping("/list/{deviceId}.php")
	public ModelAndView list(@PathVariable("deviceId") Integer deviceId)
	{
		ModelAndView mv = new ModelAndView();
		Device device = deviceService.find(deviceId);

		// 获取控制器下的所有探测器
		mv.addObject("attachDevices", device.getAttachDevices());
		mv.addObject("deviceId", device.getDeviceId());

		mv.setViewName("/device/attachDevice/list");
		return mv;
	}

	/**
	 * 添加外控器数量之视图
	 * @param deviceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAttachDeviceCount/{deviceId}.php")
	public String addAttachDeviceCountForm(@PathVariable("deviceId") Integer deviceId, Model model)
	{
		Device device = deviceService.find(deviceId);
		model.addAttribute("device", device);
		return "/device/addAttachDeviceCount";
	}

	/**
	 * 更新控制器，实现外控器的添加。
	 * @param id 控制器ID
	 * @param attachDeviceNum 后续增加的探测器数
	 * @return
	 */
 	@RequestMapping(value = "/update.php", method = RequestMethod.POST)
 	public @ResponseBody Map<String, String> update(Integer id, Integer additionalCount)
 	{
 		Device device = deviceService.find(id);
 		deviceService.save(0,additionalCount, device);  // 用于添加控制器中的探测器
 		
 		return closeCurrentAndRefresh("attachDeviceList");
 	}

	/**
	 * 查看控制下外控器的当前状态。
	 * @param deviceId
	 * @return
	 */
	@RequestMapping("/monitorList/{deviceId}.php")
	public ModelAndView view(@PathVariable("deviceId") Integer deviceId)
	{
		ModelAndView mv = new ModelAndView();
		Device device = deviceService.find(deviceId);

		// 获取控制器下的所有探测器
		mv.addObject("attachDevices", device.getAttachDevices());
		mv.addObject("deviceId", device.getDeviceId());

		mv.setViewName("/device/attachDevice/monitorList");
		return mv;
	}
	
	@RequestMapping(value = "/edit/{attachDeviceId}.php")
	public String editProberForm(@PathVariable("attachDeviceId") Integer attachDeviceId, Model model)
	{
		AttachDevice attachDevice = attachDeviceService.find(attachDeviceId);
		model.addAttribute("attachDevice", attachDevice);
		return "/device/attachDevice/edit";
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Integer id, String location)
	{
		AttachDevice attachDevice = attachDeviceService.find(id);
		attachDevice.setLocation(location);
		attachDeviceService.update(attachDevice);
		
		return closeCurrentAndRefresh("attachDeviceList");
	}

}

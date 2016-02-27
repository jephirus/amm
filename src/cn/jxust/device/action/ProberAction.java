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
import cn.jxust.device.model.Device;
import cn.jxust.device.model.Prober;
import cn.jxust.device.service.DeviceService;
import cn.jxust.device.service.ProberService;

@Controller
@RequestMapping("/prober")
public class ProberAction extends BaseDwzAction
{
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private ProberService proberService;

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
		mv.addObject("probers", device.getProbers());
		mv.addObject("deviceId", device.getDeviceId());

		mv.setViewName("/device/prober/list");
		return mv;
	}

	/**
	 * 添加探测器数量之视图
	 * @param deviceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addProberCount/{deviceId}.php")
	public String addProberCountForm(@PathVariable("deviceId") Integer deviceId, Model model)
	{
		Device device = deviceService.find(deviceId);
		model.addAttribute("device", device);
		return "/device/addProberCount";
	}

	/**
	 * 编辑探测器
	 * @param proberId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{proberId}.php")
	public String editProberForm(@PathVariable("proberId") Integer proberId, Model model)
	{
		Prober prober = proberService.find(proberId);
		model.addAttribute("prober", prober);
		return "/device/prober/edit";
	}

	/**
	 * 更新控制器，实现报警器的添加。
	 * @param id 控制器ID
	 * @param proberNum 后续增加的探测器数
	 * @return
	 */
	@RequestMapping(value = "/update.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> update(Integer id, Integer additionalCount)
	{
		Device device = deviceService.find(id);
		deviceService.save(additionalCount,0, device);  // 用于添加控制器中的探测器
		
		return closeCurrentAndRefresh("proberList");
	}

	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Integer id, String proberRange, String location, String alarmValue)
	{
		Prober prober = proberService.find(id);
		prober.setAlarmValue(alarmValue);
		prober.setLocation(location);
		prober.setProberRange(proberRange);
		proberService.update(prober);
		
		return closeCurrentAndRefresh("proberList");
	}

	/**
	 * 监控当前控制器下的探测器工作状态列表
	 * @param deviceId
	 * @return
	 */
	@RequestMapping("/monitorList/{deviceId}.php")
	public ModelAndView monitorList(@PathVariable("deviceId") Integer deviceId)
	{
		ModelAndView mv = new ModelAndView();
		Device device = deviceService.find(deviceId);

		// 获取控制器下的所有探测器
		mv.addObject("probers", device.getProbers());
		mv.addObject("deviceId", device.getDeviceId());

		mv.setViewName("/device/prober/monitorList");
		return mv;
	}
	
	@RequestMapping(value = "/delete.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> delete(Integer[] items)
	{
		
		Device device = proberService.find(items[0]).getDevice();
		
		proberService.delete(items);
		
		device.setProberCount(device.getProberCount() - items.length);	// 当删除探测器时，相应的控制器数量也要减去
		deviceService.update(device);

		return refresh("proberList");
	}
	
}

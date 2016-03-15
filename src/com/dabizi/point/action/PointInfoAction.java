package com.dabizi.point.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.device.model.Device;
import cn.jxust.device.service.DeviceService;
import cn.jxust.log.model.DeviceLog;
import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

@Controller
@RequestMapping("/index")
public class PointInfoAction extends BaseDwzAction {

	@Resource
	private PointInfoService pointInfoService;

	@Resource
	private DeviceService deviceService;

	/**
	 * 从前台ajax传来的数据，通过地图结点添加控制器，
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/PointInfo/savePointInfo.php", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> savePointInfo(PointInfo pointInfo, Integer deviceId) {

		if (pointInfo.getId() == null) {
			Device device = deviceService.find(deviceId);
			pointInfo.setDevice(device);
			pointInfoService.save(pointInfo);
			
			device.setFlag(true);
			deviceService.update(device);
		} else {
			pointInfoService.update(pointInfo);
		}
		return success("成功");
	}

	/**
	 * 当用户登录时，系统根据用户信息加载相关结点到电子地图。
	 * @return
	 */
	@RequestMapping(value = "/PointInfo/getPointInfo.php")
	public @ResponseBody String getPointInfo() {
		Department department = this.getCurrentUser().getUserDep();
		List<PointInfo> pointInfo;
		if (null != department) {
			Set<Area> areas = department.getAreas();
			pointInfo = new ArrayList<PointInfo>();
			for (Area area : areas) {
				for (Device d : area.getDevices()) {
					if (null != d.getPointInfo()) {
						pointInfo.add(d.getPointInfo());
					}
				}
			}
		}else{
			pointInfo = pointInfoService.findAll();
		}
		// 先过滤对set集合的拆解
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("device")) { // 过虑掉无法加载的属性
					return true;
				}else {
					return false;
				}
			}
		});
		// 将数据转换成Json数据
		JSONArray jsonObject = JSONArray.fromObject(pointInfo, config);
		return jsonObject.toString();
	}

	/**
	 * 从前台ajax传来的数据，用于加载 控制器 信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/PointInfo/getDevices.php")
	public void getDevices(HttpServletRequest request, HttpServletResponse response) {
		Department department = this.getCurrentUser().getUserDep();
		List<Device> devices;
		
		if (null != department) {
			devices = deviceService.getAll(department);
		}else{
			devices = deviceService.getAll();
		}
		// 先过滤对set集合的拆解
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("deviceManagers")) { // 过虑掉无法加载的属性
					return true;
				} else if (arg1.equals("probers")) {
					return true;
				} else if (arg1.equals("attachDevices")) {
					return true;
				}else if (arg1.equals("pointInfo")) {
					return true;
				}else if (arg1.equals("area")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.setCharacterEncoding("utf-8");
		// 将数据转换成Json数据
		JSONArray jsonObject = JSONArray.fromObject(devices, config);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 当用户在电子地图上点击某个结点时，系统获取当前结点信息。
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@RequestMapping(value = "/PointInfo/getPIByLL.php")
	public @ResponseBody
	Map<String, String> getPIByLL(String longitude, String latitude) {
		PointInfo pointInfo = pointInfoService.findPIByLL(longitude, latitude);
		Device device = pointInfo.getDevice();

		Map<String, String> result = new HashMap<String, String>();
		result.put("deviceCode", device.getDeviceCode());
		result.put("proberCount", device.getProberCount().toString());
		result.put("areaName", device.getArea().getAreaName());
		result.put("departmentName", device.getArea().getDepartment().getDepartmentName());
		result.put("status", device.getStatus());
		result.put("attachDeviceCount", device.getAttachDeviceCount().toString());

		return result;
	}

	@RequestMapping(value="/test.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, DeviceLog entity)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/test");
		return mv;
	}
	
}

package cn.jxust.log.action;

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
import cn.jxust.log.model.AttachDeviceLog;
import cn.jxust.log.service.AttachDeviceLogService;
import cn.jxust.orm.PageData;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/log/attachDevice")
public class AttachDeviceLogAction extends BaseDwzAction
{
	@Resource
	private AttachDeviceLogService attachDeviceLogService;
	
	@RequestMapping(value = "/my.php")
	public ModelAndView my(@RequestParam(defaultValue = "1") int pageNum)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", getCurrentDepartment().getDepartmentName());
		mv.addObject("years", DateUtils.getYearList(5));
		mv.addObject("currentYear", DateUtils.getCurrentYear());
		mv.setViewName("/khpj/reports/bonus_penalty_report/my");
		return mv;
	}
	
	@RequestMapping(value = "/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, AttachDeviceLog entity){
		PageData<AttachDeviceLog> pageData = new PageData<AttachDeviceLog>(pageNum);
		Department department = this.getCurrentDepartment();
		if (null == department) {
			pageData = attachDeviceLogService.getAllAttachDeviceLog(pageData);
		}else{
			pageData = attachDeviceLogService.getAllAttachDeviceLog(pageData, department);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.setViewName("/log/attachDevice/list");
		return mv;
	}

	@RequestMapping(value = "/view/{id}.php")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("data", attachDeviceLogService.find(id));
		return "/khpj/reports/bonus_penalty_report/view";
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") String[] id)
	{
		attachDeviceLogService.delete(id);
		return refresh("list");
	}
}

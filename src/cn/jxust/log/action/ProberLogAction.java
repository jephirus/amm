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
import cn.jxust.log.model.ProberLog;
import cn.jxust.log.service.ProberLogService;
import cn.jxust.orm.PageData;

@Controller
@RequestMapping("/log/prober")
public class ProberLogAction extends BaseDwzAction
{
	@Resource
	private ProberLogService proberLogService;

	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum, ProberLog entity)
	{
		PageData<ProberLog> pageData = new PageData<ProberLog>(pageNum);
		Department department = this.getCurrentDepartment();
		if (null == department) {
			pageData = proberLogService.getAllProberLog(pageData);
		}else{
			pageData = proberLogService.getAllProberLog(pageData, department);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject(pageData);
		mv.setViewName("/log/prober/list");
		return mv;
	}

	@RequestMapping(value = "/new/{type}.php")
	public String addForm(@PathVariable("type") String type, Model model)
	{
		model.addAttribute(type);
		return "/khpj/quota/input";
	}

	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") String id, Model model)
	{
		ProberLog quota = proberLogService.find(id);
		model.addAttribute(quota);
		return "/khpj/quota/input";
	}

	@RequestMapping(value = "/delete.php")
	public @ResponseBody Map<String, String> delete(@PathVariable("id") String[] id)
	{
		proberLogService.delete(id);
		return refresh("quotaList");
	}
}

package cn.jxust.device.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.device.model.AlarmParameter;
import cn.jxust.device.service.AlarmParameterService;
import cn.jxust.orm.PageData;

@Controller
@RequestMapping("/alarmParameter")
public class AlarmParameterAction extends BaseDwzAction {
	
	@Resource
	private AlarmParameterService alarmParameterService;
	
	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum)
	{
		ModelAndView mv = new ModelAndView();
		
		PageData<AlarmParameter> pageData = new PageData<AlarmParameter>(pageNum);
		pageData = alarmParameterService.getAll(pageData);
		mv.addObject(pageData);
		return mv;
	}
	
	/**
	 * 新建参数
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new.php")
	public String addForm(Model model)
	{
		return "/alarmParameter/input";
	}
	
	/**
	 * 修改参数
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}.php")
	public String editForm(@PathVariable("id") Integer id, Model model)
	{
		AlarmParameter alarmParameter = alarmParameterService.find(id);
		model.addAttribute(alarmParameter);
		return "/alarmParameter/input";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(AlarmParameter alarmParameter)
	{
		if (alarmParameter.getId() == null)
		{
			alarmParameterService.save(alarmParameter);
		} else
		{
			alarmParameterService.update(alarmParameter);
		}
		return closeCurrentAndRefresh("alarmParameterList");
	}
	
}

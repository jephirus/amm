package cn.jxust.sms.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jxust.base.model.User;
import cn.jxust.base.service.UserService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.orm.PageData;
import cn.jxust.sms.model.SMS;
import cn.jxust.sms.service.SMSService;

import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

@Controller
@RequestMapping("/shortMessage")
public class SMSAction extends BaseDwzAction {
	
	@Resource
	private SMSService smsService;
	@Resource
	private PointInfoService pointInfoService;
	@Resource
	private UserService userService;
	
	@RequestMapping("/list.php")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageNum)
	{
		ModelAndView mv = new ModelAndView();
		
		PageData<SMS> pageData = new PageData<SMS>(pageNum);
		pageData = smsService.getAll(pageData);
		mv.addObject(pageData);
		return mv;
	}
	
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(SMS sms)
	{
		if (sms.getId() == null)
		{
			smsService.save(sms);
		} else
		{
			smsService.update(sms);
		}
		return closeCurrentAndRefresh("smsList");
	}
	
	@RequestMapping("/new.php")
	public String addSms(Model model){
		List<PointInfo> pointInfos = pointInfoService.findAll();
		List<User> users = userService.findAll();
		model.addAttribute("pointInfos", pointInfos);
		model.addAttribute("users", users);
		return "/shortMessage/input";
	}
	
	@RequestMapping("/edit/{id}.php")
	public String edit(@RequestParam(defaultValue = "1") Integer id, Model model){
		SMS sms = smsService.find(id);
		model.addAttribute("sms", sms);
		List<PointInfo> pointInfos = pointInfoService.findAll();
		List<User> users = userService.findAll();
		model.addAttribute("pointInfos", pointInfos);
		model.addAttribute("users", users);
		return "/shortMessage/input";
	}
	@RequestMapping("/view/{id}.php")
	public String view(@RequestParam(defaultValue = "1") Integer id, Model model){
		SMS sms = smsService.find(id);
		model.addAttribute("sms", sms);
		return "/shortMessage/view";
	}
}

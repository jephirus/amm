package cn.jxust.base.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.base.service.AreaService;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.common.action.BaseDwzAction;
import cn.jxust.orm.PageData;
@Controller
@RequestMapping("/base/deparea")
public class AreaAction extends BaseDwzAction{

	@Resource
	private AreaService areaService;
	@Resource
	private DepartmentService departmentService;
	/**
	 * 
	 * @Description:查找所有
	 * @author:laiy
	 * @date:2015年10月18日下午4:30:27 
	 * @param: @param pageNum
	 * @param: @param entity
	 */
	@RequestMapping(value="/list.php")
	public ModelMap findArea(@RequestParam(defaultValue="1") int pageNum,Area entity){
		
		User user = this.getCurrentUser();
		Department department = this.getCurrentDepartment();
		PageData<Area> pageData;
		if (user.getUserName().equals("admin")) {    // 如果用户是管理员，可查看所有区域。
			pageData = areaService.getAllByPage(pageNum, entity);
		}else{
			pageData = areaService.getAllByPage(pageNum, entity, department);
		}
		return new ModelMap(pageData);
	}
	
	/**
	 * 
	 * @Description:进入添加区域页面
	 * @author:laiy
	 * @date:2015年10月18日下午7:40:05 
	 * @param: @param model
	 * @param: @return  
	 */
	@RequestMapping(value="/new.php")
	public String addArea(Model model){
		
		User user = this.getCurrentUser();
		if (user.getUserName().equals("admin")) {
			model.addAttribute("departments", departmentService.getAll());
		}else{
			List<Department> dl = new ArrayList<Department>();
			dl.add(getCurrentDepartment());
			model.addAttribute("departments", dl);
		}

		return "/base/deparea/input";
	}

	/**
	 * @Description:保存区域添加
	 * @date:2015年10月18日下午7:51:20 
	 * @param: @param area
	 */
	@RequestMapping(value = "/save.php", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> save(Area area, Integer departmentId){
		Department department = departmentService.find(departmentId);
		area.setDepartment(department);
		if(area.getAreaId()==null){
			areaService.save(area);
		}else{
			areaService.update(area);
		}
		
		return closeCurrentAndRefresh("areaList");
	}

	/**
	 * @Description: 批量删除区域
	 * @author:laiy
	 * @date:2015年10月19日下午7:31:44 
	 * @param: @param items
	 * @param: @return
	 */
	@RequestMapping(value="delete.php",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> delete(Integer[] items)
	{
		areaService.delete(items);
		return refresh("areaList");
	}
}

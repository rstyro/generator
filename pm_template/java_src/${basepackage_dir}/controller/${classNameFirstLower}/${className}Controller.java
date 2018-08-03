<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.controller.${classNameLower};


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${basepackage}.service.${classNameLower}.${className}Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.otcbi.admin.controller.base.BaseController;
import com.otcbi.admin.entity.ResponseModel;
import com.otcbi.admin.entity.ResultEnum;
import com.otcbi.admin.entity.system.Const;
import com.otcbi.admin.entity.system.User;
import com.otcbi.admin.util.Jurisdiction;
import com.otcbi.admin.util.ParameterMap;


/**
 * ${wzz_functionName}
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Controller
@RequestMapping("/${classNameLower}")
public class ${className}Controller extends BaseController{
	
	private String qxurl="${classNameLower}/list";
	
	@Autowired
	private ${className}Service ${classNameLower}Service;
	

	@GetMapping(path={"/list/{page}","/list"})
	public Object list(@PathVariable(value="page",required=false)Integer pageNo,Model model){
		try {
			if(pageNo == null){
				pageNo=1;
			}
			ParameterMap pm = this.getParameterMap();
			Page<ParameterMap> page = PageHelper.startPage(pageNo, Const.PAGE_SIZE).doSelectPage(() -> {
				try {
					${classNameLower}Service.getByConditionList(pm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			model.addAttribute("${classNameLower}", page.getResult());
			model.addAttribute("page", page);
			model.addAttribute("isDeleted", pm.getString("isDeleted"));
			model.addAttribute("keyword", pm.getString("keyword"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "page/${classNameLower}/list";
	}
	
	@GetMapping(path={"/detail","/detail/{id}"})
	@ResponseBody
	public Object getDetail(@PathVariable(value="id",required=false) String id){
		if(!Jurisdiction.buttonJurisdiction(qxurl, "query",this.getSession())){return ResponseModel.getNotAuthModel();} 
		try {
			ParameterMap pm = this.getParameterMap();
			ParameterMap detail = null;
			if(id == null){
				id = pm.getString("id");
			}
			detail = ${classNameLower}Service.selectByPrimaryKey(Long.valueOf(id));
			return ResponseModel.getModel(ResultEnum.SUCCESS, detail);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseModel.getModel(ResultEnum.ERROR, null);
		}
	}
	
	
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(){
		if(!Jurisdiction.buttonJurisdiction(qxurl, "edit",this.getSession())){return ResponseModel.getNotAuthModel();} 
		try {
			User user = (User) this.getSession().getAttribute(Const.SESSION_USER);
			ParameterMap pm = this.getParameterMap();
			pm.put("modifyTime", LocalDateTime.now());
			pm.put("modifyBy",user.getUserId());
			${classNameLower}Service.updateByPrimaryKeySelective(pm);
			return ResponseModel.getModel(ResultEnum.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseModel.getModel(ResultEnum.ERROR, null);
		}
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Object add(){
		if(!Jurisdiction.buttonJurisdiction(qxurl, "add",this.getSession())){return ResponseModel.getNotAuthModel();} 
		try {
			ParameterMap pm = this.getParameterMap();
			pm.put("createTime", LocalDateTime.now());
			pm.put("createBy", ((User) this.getSession().getAttribute(Const.SESSION_USER)).getUserId());
			${classNameLower}Service.insertSelective(pm);
			return ResponseModel.getModel(ResultEnum.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseModel.getModel(ResultEnum.ERROR, null);
		}
	}
	
	@PostMapping(path={"/del","/del/{id}"})
	@ResponseBody
	public Object del(@PathVariable(value="id",required=false) String id){
		if(!Jurisdiction.buttonJurisdiction(qxurl, "del",this.getSession())){return ResponseModel.getNotAuthModel();} 
		boolean isdel = false;
		try {
			String ids = id;
			if(id == null){
				ParameterMap pm = this.getParameterMap();
				ids = pm.getString("ids");
			}
			isdel = ${classNameLower}Service.delete${className}(ids);
			return ResponseModel.getModel(ResultEnum.SUCCESS, isdel);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseModel.getModel(ResultEnum.ERROR, isdel);
		}
	}
}
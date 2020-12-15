package com.nuaa.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuaa.bean.Msg;
import com.nuaa.bean.UserInfo;
import com.nuaa.service.IUserService;

@Controller
public class UserController {
	private final IUserService personService;   //注入service层
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(IUserService personService) {
		this.personService = personService;
	}
	
	//----首页跳转---
	@RequestMapping("/hello.do")
	public String printWelcome() {
		return "login";
	}
	
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Msg getPerson(String username,String password,String randCode,HttpServletRequest request){
		String rand = (String)request.getSession().getAttribute("rand");
		Msg msg = new Msg();
		UserInfo user = new UserInfo();
		user.setUsername(username);
	//System.out.println(randCode);
		List<UserInfo> users= personService.findUserSelective(user);
		if(randCode.equals(rand)) {
			if(users.size()>0 && password.equals(users.get(0).getPwd())) {
				msg.setSuccess(true);
				msg.setMsg("登录成功，正在跳转...");
				logger.info("*******************************");
				logger.info("登陆账户:"+username);   //输出日志
				UserInfo cu = users.get(0);
					//加入session
				request.getSession().setAttribute("loginUsers", cu);
				request.setAttribute("name", cu.getUsername());
			}else{
				msg.setSuccess(false);
				msg.setMsg("用户名不存在或密码错误");}
				
			} else {
				msg.setSuccess(false);
				msg.setMsg("验证码错误!");
		} 
		return msg ;
	}
	
	@RequestMapping("loginOut.do")
	@ResponseBody
	public Msg outSystem(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Msg msg = new Msg();
			request.getSession().removeAttribute("loginUsers");
			msg.setSuccess(true);
			msg.setMsg("退出成功！");
			logger.info("账户："+request.getAttribute("name")+"退出成功");
			logger.info("**********************************************");
			return msg;		
		}
	
	@RequestMapping("/getUser.do")
	@ResponseBody
	public Msg getMsg(String userInput,String selectId,String userModel,Integer start,Integer limit){
		
		UserInfo userInfo = new UserInfo();
		
		Msg result =new Msg();
				// 由于分页需要进行二次参数传递，不是通过query方法，所以form的值传不过来，
		        //若采用"name".equals(ID)会发生空指针异常，可以采用下面方法或者“常量”.equals(变量)即可解决！
				if(Objects.equals("num",selectId)){
						userInfo.setUsernumber(userInput);
						
					}else if(Objects.equals("name",selectId)){
						
						userInfo.setUsername(userInput);}else if(Objects.equals("shiro",selectId)){
							
									userInfo.setRole(userInput);
						}else {
							userInfo.setJob(userInput);
						}
		
				System.out.println(userInput);
				
		PageHelper.startPage((start/limit)+1, limit);
		
		if("wanquan".equals(userModel)) {
				List<UserInfo> persons0 = personService.findUserSelective(userInfo);
				for (UserInfo userInfo2 : persons0) {
					userInfo2.setPwd("");
				}
				PageInfo<UserInfo> page = new PageInfo<>(persons0);
				int total = (int) page.getTotal();
				result.setResultSize(total);
				result.setExtend(persons0);
	}else {
				List<UserInfo> person1 = personService.findUserMHSelective(userInfo);
				for (UserInfo userInfo2 : person1) {
						userInfo2.setPwd("");
				}
				PageInfo<UserInfo> page = new PageInfo<>(person1);
				int total = (int) page.getTotal();
				result.setResultSize(total);
				result.setExtend(person1);
	}
		return result;
	}
	
	@RequestMapping("/delUser.do")
	@ResponseBody
	public Msg delUser(int uid){
		Msg msg = new Msg();
		try {
			personService.deleteById(uid);
			msg.setSuccess(true);
			msg.setMsg("删除记录成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("服务器发生错误，请稍后再试！");
			logger.error("删除用户失败:"+e.getMessage());
		}
		
		return msg;	
	}
	
	@RequestMapping("updatePerson.do")
	@ResponseBody
	public Msg updataPerson(UserInfo record){
		Msg msg = new Msg();
		System.out.println(record.getUid());
		
		try {
			personService.updateUserByIdSelective(record);
			msg.setSuccess(true);
			msg.setMsg("编辑信息成功！");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg.setSuccess(false);
			msg.setMsg("数据不符合要求，修改失败！");
			logger.error("编辑用户失败:"+e.getMessage());
			
		}
		return msg;
	}
	
	@RequestMapping("addPerson.do")
	@ResponseBody
	public Msg addPerson(UserInfo record){
		Msg msg = new Msg();
	//	System.out.println(record.getUsername());
	//	System.out.println(record.getJob());
		
		try {
			record.setDecription(record.getRole());
			UserInfo info = new UserInfo();
			info.setUsernumber(record.getUsernumber());
			List<UserInfo> list = personService.findUserSelective(info);
			if(list.size()>0) {
				msg.setSuccess(false);
				msg.setMsg("工号重复，用户已存在！");
			}else {
				personService.insertUser(record);
				msg.setSuccess(true);
				msg.setMsg("添加新用户成功！");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg.setSuccess(false);
			msg.setMsg("添加失败，请稍后再试！");
			logger.error("添加用户失败:"+e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping("viewUser.do")
	@ResponseBody
	public Msg viewPerson(String loginName){
		Msg msg = new Msg();
		System.out.println(loginName);
		UserInfo user =new UserInfo();
		user.setUsername(loginName);
 		try {
			List<UserInfo> list = personService.findUserSelective(user);
			if(!list.isEmpty()) {
				msg.setSuccess(true);
				msg.setExtend(list);
				msg.setMsg("成功！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg.setSuccess(false);
			msg.setMsg("服务器发生错误，请稍后再试！");
			logger.error("查看用户"+loginName+"失败:"+e.getMessage());
		}
		return msg;
	}
}

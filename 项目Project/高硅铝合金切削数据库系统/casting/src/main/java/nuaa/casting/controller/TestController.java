package nuaa.casting.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import nuaa.casting.dao.EmpDao;
import nuaa.casting.service.BMpropara;
import nuaa.casting.service.CastingContent;
import nuaa.casting.service.JZpropara;
import nuaa.casting.service.LXpropara;
import nuaa.casting.service.MKpropara;
import nuaa.casting.service.MaterialProperties;
import nuaa.casting.service.User;

@Controller
public class TestController {
	@Autowired
	private EmpDao dao;
	
	
//用户信息/个人信息管理模块控制器---------------------------------------------------------------------	
	@RequestMapping("readall.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public Object Data_1() {					
		List<User> person= new ArrayList<User>();
		person=dao.findAll();    
        return person; //return json data 
	}
	
	@RequestMapping("readuser.do")  
    @ResponseBody
    public Object Data_2(@RequestBody String stuff_num ) {		
		User person=new User();
		//System.out.println(stuff_num);
		person=dao.findBystuff_num(stuff_num.substring(stuff_num.indexOf("=")+1));		
		return person;
	}
	
	@RequestMapping("updateuser.do") 
	@ResponseBody
	//参数与前端控件name一致才能传值
    public boolean Data_3(String username,String phonenumber,
    	String password,String remark,String userrole,String stuff_num) {	
		dao.updateUser(username,phonenumber,password,remark,userrole,stuff_num);
		System.out.println("updateuser.do");
		return true;
	}
	
	@RequestMapping("personupdateuser.do") 
	@ResponseBody
	//参数与前端控件name一致才能传值
    public boolean Data_3_1(String username,String phonenumber,
    	String password,String remark,String stuff_num) {	
		dao.updateUser_1(username,phonenumber,password,remark,stuff_num);
		System.out.println("personupdateuser.do");
		return true;
	}
	
	@RequestMapping("deluser.do") 
	@ResponseBody
    public String Data_4(@RequestBody String stuff_num){	
		System.out.println("deluser.do");
		String stuffnum=null;
		try {
			stuffnum=URLDecoder.decode(stuff_num.substring(stuff_num.indexOf("=")+1), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		dao.deleteUser("("+stuffnum+")");
		System.out.println(stuffnum);
		return "{success:true}";
	}
	
	@RequestMapping("saveuser.do") 
	@ResponseBody
    public String Data_5(String addpassword,String addusername,String addrole,
    		String addstuff_num,String addphonenumber,String addremark){	
		System.out.println("saveuser.do");
		dao.saveUser(addpassword,addusername,addrole,addstuff_num,addphonenumber,addremark);	
		System.out.println(addstuff_num);	
		return "{success:true}";
	}
//------------------------------------------------------------------------------------------	
	
	String partsdrawinggol="";
	@RequestMapping("castingsubmit.do") 
	@ResponseBody
    public String Data_6(String castingnumber,String partsnumber,String structuretype,
    		String castingname,String editor,String pouringweight,
    		String castingmethod,String castingprocessdesign,String riserdesign,String pouringtemperature,
    		String boxtime,String heatprocess,String smeltingprocess,String inoculationprocess,
    		String qualitytest,String materialnumber,String mainthickness,String castingweight,
    		String acceptcondition,String gatingsystemdesign,String chilldesign,String pouringtime,
    		String chemicalcomponent,String physicaltreatment,String physicalparameter,String defecttype){
		
		dao.saveCastingContent(castingnumber, partsnumber, structuretype, castingname, 
				editor, partsdrawinggol, pouringweight, castingmethod, castingprocessdesign, 
				riserdesign, pouringtemperature, boxtime, heatprocess, smeltingprocess, 
				inoculationprocess, qualitytest, materialnumber, mainthickness, castingweight, 
				acceptcondition, gatingsystemdesign, chilldesign, pouringtime, chemicalcomponent, 
				physicaltreatment, physicalparameter, defecttype);	
		System.out.println(partsdrawinggol);
		System.out.println("castingsubmit.do");
		partsdrawinggol="";
		return "{success:true}";
	}
	
	@RequestMapping("uploaddocument.do") 
	@ResponseBody
    public String Data_7(MultipartFile partsdrawing){
		//String rootPath=request.getServletContext().getRealPath("tomcat8.0");
		String rootPath="D:\\eclipse\\tomcat8.0\\upload";
		System.out.println(rootPath);
		System.out.println(partsdrawing);
		if(partsdrawing!=null)
		{
			String fileName=partsdrawing.getOriginalFilename();//获取上传文件的名称
			//String suffix=fileName.substring(fileName.lastIndexOf("."));
			String tempFileName=UUID.randomUUID().toString()+fileName;
			File fileTemp=new File(rootPath);
			if(!fileTemp.exists()) fileTemp.mkdir();
			File file=new File(rootPath+"\\"+tempFileName);
			System.out.println(rootPath+"\\"+tempFileName);
			partsdrawinggol="/file/"+tempFileName;//浏览器无法直接访问磁盘，需要设置相对路径(映射路径)
			try {
				partsdrawing.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				return "{success:false}";
			}
		}
		System.out.println("uploaddocument.do");
		return "{success:true}";
	}
	
	@RequestMapping("moddocument.do") 
	@ResponseBody
    public String Data_7_1(MultipartFile  partsdrawing2){
		String rootPath="D:\\eclipse\\tomcat8.0\\upload";
		System.out.println(rootPath);
		System.out.println(partsdrawing2);
		if(partsdrawing2!=null)
		{
			String fileName=partsdrawing2.getOriginalFilename();//获取上传文件的名称
			//String suffix=fileName.substring(fileName.lastIndexOf("."));
			String tempFileName=UUID.randomUUID().toString()+fileName;
			File fileTemp=new File(rootPath);
			if(!fileTemp.exists()) fileTemp.mkdir();
			File file=new File(rootPath+"\\"+tempFileName);
			System.out.println(rootPath+"\\"+tempFileName);
			partsdrawinggol="/file/"+tempFileName;
			try {
				partsdrawing2.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				return "{success:false}";
			}
		}
		System.out.println("moddocument.do");
		return "{success:true}";
	}
	
	@RequestMapping("castingcontentread.do") 
	@ResponseBody
    public CastingContent Data_8(String castingnumber){//参数加@RequestBody返回为‘castingnumber=？’形式，不加为原形。
		CastingContent content;
		content=dao.readCastingContentBycastingnumber(castingnumber);
		System.out.println("castingcontentread.do");
		return content;
	}
	
	@RequestMapping("readallcastingcontent.do") 
	//@ResponseBody  已有PrintWriter，可以不要此标签
    public void Data_9(HttpServletRequest req,HttpServletResponse response)
    		throws ServletException,IOException{
		List<CastingContent> content;
		int start = Integer.parseInt(req.getParameter("start"));//从前台传递的值
		int limit = Integer.parseInt(req.getParameter("limit"));
		limit=limit*(start/limit+1);
		content=dao.readallCastingContent(start+1,limit);				
		int count=dao.readallCastingContentcount();
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();//return a PrintWriter Object that can send character text to the client						
		//JSONObject json = JSONObject.fromObject(totalcount);//将java对象转换为json对象
		JSONArray jsonArr=JSONArray.fromObject(content);	//将java对象转换为json对象
		StringBuffer info = new StringBuffer();
		info.append("{totalcount:"+count+",data:");
		info.append(jsonArr);
		info.append("}");
		String jsonStr=info.toString();
		out.println(jsonStr);//输出
		System.out.println("readallcastingcontent.do");
	}
	
	@RequestMapping("readpartcastingcontent.do") 
    public void Data_10(String findtype,String findstring,HttpServletRequest req,HttpServletResponse response)
    		throws ServletException,IOException{
		List<CastingContent> content;
		int start = Integer.parseInt(req.getParameter("start"));//从前台传递的值
		int limit = Integer.parseInt(req.getParameter("limit"));
		limit=limit*(start/limit+1);
		content=dao.readpartCastingContent(start+1,limit,findtype,findstring);				
		int count=dao.readpartCastingContentcount(findtype,findstring);
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();//return a PrintWriter Object that can send character text to the client						
		JSONArray jsonArr=JSONArray.fromObject(content);	//将java对象转换为json对象
		StringBuffer info = new StringBuffer();
		info.append("{totalcount:"+count+",data:");
		info.append(jsonArr);
		info.append("}");
		String jsonStr=info.toString();
		out.println(jsonStr);//输出
		System.out.println("readpartcastingcontent.do");
	}
	
	@RequestMapping("castinginforgriddel.do") 
	@ResponseBody
    public String Data_11(String castinginforgriddel){				
		System.out.println(castinginforgriddel);
		dao.castinginforgriddel("("+castinginforgriddel+")");
		System.out.println("castinginforgriddel.do");
		return "{success:true}";
	}
	
	@RequestMapping("castingcontentmod.do") 
	@ResponseBody
    public String Data_12(String castingnumber2,String partsnumber2,String structuretype2,
    		String castingname2,String editor2,String pouringweight2,
    		String castingmethod2,String castingprocessdesign2,String riserdesign2,String pouringtemperature2,
    		String boxtime2,String heatprocess2,String smeltingprocess2,String inoculationprocess2,
    		String qualitytest2,String materialnumber2,String mainthickness2,String castingweight2,
    		String acceptcondition2,String gatingsystemdesign2,String chilldesign2,String pouringtime2,
    		String chemicalcomponent2,String physicaltreatment2,String physicalparameter2,String defecttype2){
		
		dao.modCastingContent(castingnumber2, partsnumber2, structuretype2, castingname2, 
				editor2, partsdrawinggol, pouringweight2, castingmethod2, castingprocessdesign2, 
				riserdesign2, pouringtemperature2, boxtime2, heatprocess2, smeltingprocess2, 
				inoculationprocess2, qualitytest2, materialnumber2, mainthickness2, castingweight2, 
				acceptcondition2, gatingsystemdesign2, chilldesign2, pouringtime2, chemicalcomponent2, 
				physicaltreatment2, physicalparameter2, defecttype2);	
		System.out.println(partsdrawinggol);
		System.out.println("castingcontentmod.do");
		partsdrawinggol="";
		return "{success:true}";
	}
	
	@RequestMapping("loginon.do")
	@ResponseBody
	public String Data_13(String stuff_numlogin,String passwordlogin,HttpServletRequest request){
		User user=dao.findBystuff_num(stuff_numlogin);
		if(null==user) {return "2";} 
		else{
			if(passwordlogin.equals(user.getPassword())){				
				HttpSession session=request.getSession();
				session.setAttribute("Userrole",user.getUserrole());
				session.setAttribute("Username",user.getUsername());
				session.setAttribute("Stuff_num",stuff_numlogin);
				System.out.println(session.getAttribute("Username"));
				return user.getUsername();
			}
			else{return "0";}
			}
		}
	
	@RequestMapping("loginout.do")
	@ResponseBody
	public String Data_14(HttpServletRequest request){
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("Username"));
		session.removeAttribute("Userrole");
		session.removeAttribute("Username");
		session.removeAttribute("Stuff_num");
		System.out.println("session.removeAttribute()");
		System.out.println(session.getAttribute("Username"));
		return "{success:true}";
	}

	@RequestMapping("readallmaterialproperties.do") 
	//@ResponseBody  已有PrintWriter，可以不要此标签
    public void Data_15(HttpServletRequest req,HttpServletResponse response)
    		throws ServletException,IOException{
		List<MaterialProperties> content;
		int start = Integer.parseInt(req.getParameter("start"));//从前台传递的值
		int limit = Integer.parseInt(req.getParameter("limit"));
		limit=limit*(start/limit+1);
		content=dao.readallMaterialPropertiesContent(start+1,limit);				
		int count=dao.readallMaterialPropertiesContentcount();
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();//return a PrintWriter Object that can send character text to the client						
		//JSONObject json = JSONObject.fromObject(totalcount);//将java对象转换为json对象
		JSONArray jsonArr=JSONArray.fromObject(content);	//将java对象转换为json对象
		StringBuffer info = new StringBuffer();
		info.append("{totalcount:"+count+",data:");
		info.append(jsonArr);
		info.append("}");
		String jsonStr=info.toString();
		out.println(jsonStr);//输出
		System.out.println("readallmaterialproperties.do");
	}
	
	@RequestMapping("readpartmpcontent.do") 
    public void Data_16(String findtype,String findstring,HttpServletRequest req,HttpServletResponse response)
    		throws ServletException,IOException{
		List<MaterialProperties> content;
		int start = Integer.parseInt(req.getParameter("start"));//从前台传递的值
		int limit = Integer.parseInt(req.getParameter("limit"));
		limit=limit*(start/limit+1);
		content=dao.readpartMaterialPropertiesContent(start+1,limit,findtype,findstring);				
		int count=dao.readpartMaterialPropertiesContentcount(findtype,findstring);
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();//return a PrintWriter Object that can send character text to the client						
		JSONArray jsonArr=JSONArray.fromObject(content);	//将java对象转换为json对象
		StringBuffer info = new StringBuffer();
		info.append("{totalcount:"+count+",data:");
		info.append(jsonArr);
		info.append("}");
		String jsonStr=info.toString();
		out.println(jsonStr);//输出
		System.out.println("readpartmpcontent.do");
	}
	
	
	@RequestMapping("mpdel.do") 
	@ResponseBody
    public String Data_17(String materialbrand){				
		System.out.println(materialbrand);
		dao.mpinforgriddel(materialbrand);
		System.out.println("mpdel.do");
		return "{success:true}";
	}
	
	@RequestMapping("modmaterialproperties.do") 
	@ResponseBody
    public String Data_18(String materialcategory,String materialbrand,String mainmatrixorganization,
    		String tensilestrength,String extensionstrength,String elongation,String hardness,
    		String distributionratio,String melting,String inoculation,String spheroidizing,String annealing 
    		){						
		dao.modMaterialPropertiesContent(materialcategory,materialbrand,tensilestrength,extensionstrength,
							elongation,hardness,mainmatrixorganization,distributionratio,
							 melting,inoculation,spheroidizing,annealing);
		System.out.println("mpdel.do");
		return "{success:true}";
	}
	
	@RequestMapping("mpcontentread.do") 
	@ResponseBody
    public MaterialProperties Data_19(String materialbrand){						
		MaterialProperties content;
		content=dao.readMaterialPropertiesContentBymaterialbrand(materialbrand);
		System.out.println("mpcontentread.do");
		System.out.println(materialbrand);
		return content;
	}
	
	@RequestMapping("addmaterialproperties.do") 
	@ResponseBody
    public String Data_20(String addmaterialcategory,String addmaterialbrand,String addmainmatrixorganization,
    		String addtensilestrength,String addextensionstrength,String addelongation,String addhardness,
    		String adddistributionratio,String addmelting,String addinoculation,String addspheroidizing,
    		String addannealing){		
		dao.saveMaterialProperties(addmaterialcategory,addmaterialbrand,addtensilestrength,addextensionstrength,
				addelongation,addhardness,addmainmatrixorganization,adddistributionratio,addmelting,addinoculation,addspheroidizing,
	    		 addannealing);	

		System.out.println("addmaterialproperties.do");
		return "{success:true}";
	}
	
	
	//------------------------------------------------------------
	@RequestMapping("readalldraftinfor.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public Object Data_21() {					
		List<BMpropara> bmpropara= new ArrayList<BMpropara>();
		bmpropara=dao.readAllDraftInfor(); 
		System.out.println(bmpropara);
        return bmpropara; //return json data 
	}
	
	@RequestMapping("readpartdraftinfor.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public Object Data_22(String bmfindstring) {					
		List<BMpropara> bmpropara= new ArrayList<BMpropara>();
		bmpropara=dao.readPartDraftInfor(bmfindstring); 
		System.out.println("readpartdraftinfor.do");
        return bmpropara; //return json data 
	}
	
	@RequestMapping("bmproparadel.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public String Data_23(String castingmodeling,String sizecondition,String draftremarks) {					
		dao.deletebmpropara(castingmodeling,sizecondition,draftremarks); 
		System.out.println("bmproparadel.do");
		return "{success:true}";
	}
	
	@RequestMapping("addbmpropara.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public String Data_24(String castingmodeling,String sizecondition,String draftangle,
    		String draftlength,String draftremarks) {
		dao.addbmpropara(castingmodeling,sizecondition,draftangle,draftlength,draftremarks); 
		System.out.println("addbmpropara.do");
		return "{success:true}";
	}
	
	@RequestMapping("readallpouringinfor.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public Object Data_25() {					
		List<JZpropara> jzpropara= new ArrayList<JZpropara>();
		jzpropara=dao.readAllPouringInfor();
		System.out.println("readallpouringinfor.do");
        return jzpropara; //return json data 
	}
	
	@RequestMapping("jzproparadel.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public String Data_26(String pouringweight,String njdnum) {					
		dao.deletejzpropara(pouringweight,njdnum); 
		System.out.println("jzproparadel.do");
		return "{success:true}";
	}
	
	@RequestMapping("addjzpropara.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public String Data_27(String pouringweight,String njdnum,String njda,String njdb,
    		String njdc,String hjda,String hjdb,String hjdc,String zjdd,String pouringremarks) {
		dao.addjzpropara(pouringweight,njdnum,njda,njdb,njdc,hjda,hjdb,hjdc,zjdd,pouringremarks); 
		System.out.println("addjzpropara.do");
		return "{success:true}";
	}
	
	
	@RequestMapping("readallriserinfor.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public Object Data_28() {					
		List<MKpropara> mkpropara= new ArrayList<MKpropara>();
		mkpropara=dao.readAllRiserInfor();
		System.out.println("readallriserinfor.do");
        return mkpropara; //return json data 
	}
	
	@RequestMapping("mkproparadel.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public String Data_29(String castingmodulus) {					
		dao.deletemkpropara(castingmodulus); 
		System.out.println("mkproparadel.do");
		return "{success:true}";
	}
	
	@RequestMapping("addmkpropara.do")//匹配前端url  
    @ResponseBody//此标签用于输出json格式到前端
    public String Data_30(String castingmodulus,String slowpourd,String quickpourd,String squaremk,
    		String rectanglemk,String mkremarks) {
		dao.addmkpropara(castingmodulus,slowpourd,quickpourd,squaremk,rectanglemk,mkremarks); 
		System.out.println("addmkpropara.do");
		return "{success:true}";
	}
	
	
	
	
//离心铸造模块控制器-------------------------------------------------------------------------------------------	
	@RequestMapping("readalllxinfor.do") 
	@ResponseBody
    public Object Data_31(){
		List<LXpropara> content=new ArrayList<LXpropara>();
		content=dao.readAllLXInfor();
		System.out.println("readalllxinfor.do");
		return content;
	}
	
	@RequestMapping("readpartlxinfor.do")
	@ResponseBody
    public Object Data_32(String findtype,String findstring){
		List<LXpropara> content=new ArrayList<LXpropara>();
		content=dao.readpartlxinfor(findtype,findstring);
		System.out.println("readpartlxinfor.do");
		return content;
	}
	
	@RequestMapping("lxproparadel.do") 
	@ResponseBody
    public String Data_33(String bypartnumber){				
		System.out.println(bypartnumber);
		dao.lxinforgriddel(bypartnumber);
		System.out.println("lxproparadel.do");
		return "{success:true}";
	}
	
	
	@RequestMapping("modlxpropara.do") 
	@ResponseBody
    public String modData_34(String modparttype,String modmaterial,String modpartnumber,
    		String modpartouterdiam,String modoutersurfmachallow,String modinnersurfmachallow,String modendmachallow,
    		String modrotatspeed,String modcoating,String modoperattemper,String modpourtemper,String modreleasetemper,String modmolddryingpro
    		){						
		dao.modlxpropara(modparttype,modmaterial,modpartnumber,modpartouterdiam,modoutersurfmachallow,
				modinnersurfmachallow,modendmachallow,modrotatspeed,modcoating,modoperattemper,
				modpourtemper,modreleasetemper,modmolddryingpro);
		System.out.println("modlxpropara.do");
		return "{success:true}";
	}
	
	
	@RequestMapping("lxreadbypartnumber.do") 
	@ResponseBody
    public LXpropara Data_35(String partnumber){						
		LXpropara content;
		content=dao.lxreadbypartnumber(partnumber);
		System.out.println("lxreadbypartnumber.do");
		return content;
	}
	
	@RequestMapping("addlxpropara.do") 
	@ResponseBody
    public String Data_36(String addparttype,String addmaterial,String addpartnumber,
    		String addpartouterdiam,String addoutersurfmachallow,String addinnersurfmachallow,String addendmachallow,
    		String addrotatspeed,String addcoating,String addoperattemper,String addpourtemper,
    		String addreleasetemper,String addmolddryingpro){		
		dao.addlxpropara(addparttype,addmaterial,addpartnumber,addpartouterdiam,addoutersurfmachallow,
	    		addinnersurfmachallow,addendmachallow,addrotatspeed,addcoating,addoperattemper,addpourtemper,
	    		addreleasetemper,addmolddryingpro);	
		System.out.println("addmaterialproperties.do");
		return "{success:true}";
	}
//--------------------------------------------------------------------------------------------------	
}

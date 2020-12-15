package nuaa.casting.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import nuaa.casting.dao.MyBatisRepository;
import nuaa.casting.service.BMpropara;
import nuaa.casting.service.CastingContent;
import nuaa.casting.service.JZpropara;
import nuaa.casting.service.LXpropara;
import nuaa.casting.service.MKpropara;
import nuaa.casting.service.MaterialProperties;
import nuaa.casting.service.User;

@MyBatisRepository
@Component
public interface EmpDao {
	public List<User> findAll();
	
	public User findBystuff_num(String stuff_num);
	
	public void updateUser(@Param("username")String username,
						   @Param("phonenumber")String phonenumber,
						   @Param("password")String password,
						   @Param("remark")String remark,
						   @Param("userrole")String userrole,
						   @Param("stuff_num")String stuff_num);
	
	public void updateUser_1(@Param("username")String username,
							   @Param("phonenumber")String phonenumber,
							   @Param("password")String password,
							   @Param("remark")String remark,
							   @Param("stuff_num")String stuff_num);
	
	public void deleteUser(@Param("stuff_num")String stuff_num);
	
	public void saveUser(@Param("password")String password,
						 @Param("username")String username,
						 @Param("userrole")String userrole,
						 @Param("stuff_num")String stuff_num,
						 @Param("phonenumber")String phonenumber,							
						 @Param("remark")String remark);
	
	public void saveCastingContent(
			@Param("castingnumber")String castingnumber,     	  @Param("partsnumber")String partsnumber,            @Param("structuretype")String structuretype,
			@Param("castingname")String castingname,        	  @Param("editor")String editor,                      @Param("partsdrawing")String partsdrawing,
			@Param("pouringweight")String pouringweight,     	  @Param("castingmethod")String castingmethod,        @Param("castingprocessdesign")String castingprocessdesign,
			@Param("riserdesign")String riserdesign,       		  @Param("pouringtemperature")String pouringtemperature,
			@Param("boxtime")String boxtime,	        	      @Param("heatprocess")String heatprocess,  	      @Param("smeltingprocess")String smeltingprocess,
			@Param("inoculationprocess")String inoculationprocess,@Param("qualitytest")String qualitytest,            @Param("materialnumber")String materialnumber,
			@Param("mainthickness")String mainthickness,          @Param("castingweight")String castingweight,        @Param("acceptcondition")String acceptcondition,
			@Param("gatingsystemdesign")String gatingsystemdesign,@Param("chilldesign")String chilldesign,      	  @Param("pouringtime")String pouringtime,
			@Param("chemicalcomponent")String chemicalcomponent,  @Param("physicaltreatment")String physicaltreatment,@Param("physicalparameter")String physicalparameter,
			@Param("defecttype")String defecttype);
	
	public CastingContent readCastingContentBycastingnumber(String castingnumber);
		
	public List<CastingContent> readallCastingContent(@Param("start")int start,@Param("limit")int limit);
	
	public int readallCastingContentcount();
	
	public List<CastingContent> readpartCastingContent(@Param("start")int start,
														@Param("limit")int limit,
														@Param("findtype")String findtype,
														@Param("findstring")String findstring
														);
	
	public int readpartCastingContentcount(@Param("findtype")String findtype,
											@Param("findstring")String findstring);
	
	public void castinginforgriddel(@Param("castinginforgriddel")String castinginforgriddel);
	
	public void modCastingContent(
			@Param("castingnumber")String castingnumber,     	  @Param("partsnumber")String partsnumber,            @Param("structuretype")String structuretype,
			@Param("castingname")String castingname,        	  @Param("editor")String editor,                      @Param("partsdrawing")String partsdrawing,
			@Param("pouringweight")String pouringweight,     	  @Param("castingmethod")String castingmethod,        @Param("castingprocessdesign")String castingprocessdesign,
			@Param("riserdesign")String riserdesign,       		  @Param("pouringtemperature")String pouringtemperature,
			@Param("boxtime")String boxtime,	        	  	  @Param("heatprocess")String heatprocess,  	      @Param("smeltingprocess")String smeltingprocess,
			@Param("inoculationprocess")String inoculationprocess,@Param("qualitytest")String qualitytest,            @Param("materialnumber")String materialnumber,
			@Param("mainthickness")String mainthickness,          @Param("castingweight")String castingweight,        @Param("acceptcondition")String acceptcondition,
			@Param("gatingsystemdesign")String gatingsystemdesign,@Param("chilldesign")String chilldesign,      	  @Param("pouringtime")String pouringtime,
			@Param("chemicalcomponent")String chemicalcomponent,  @Param("physicaltreatment")String physicaltreatment,@Param("physicalparameter")String physicalparameter,
			@Param("defecttype")String defecttype);
	
	public List<MaterialProperties> readallMaterialPropertiesContent(@Param("start")int start,@Param("limit")int limit);
	
	public int readallMaterialPropertiesContentcount();
	
	public List<MaterialProperties> readpartMaterialPropertiesContent(@Param("start")int start,
								@Param("limit")int limit,
								@Param("findtype")String findtype,
								@Param("findstring")String findstring
								);

	public int readpartMaterialPropertiesContentcount(@Param("findtype")String findtype,
								@Param("findstring")String findstring);
	
	public void mpinforgriddel(String materialbrand);
	
	
	public void modMaterialPropertiesContent(@Param("materialcategory")String materialcategory,
												@Param("materialbrand")String materialbrand,
												@Param("tensilestrength")String tensilestrength,
												@Param("extensionstrength")String extensionstrength,
												@Param("elongation")String elongation,
												@Param("hardness")String hardness,
												@Param("mainmatrixorganization")String mainmatrixorganization,
												
												@Param("distributionratio")String distributionratio,
												@Param("melting")String melting,
												@Param("inoculation")String inoculation,
												@Param("spheroidizing")String spheroidizing,
												@Param("annealing")String annealing
			);
	
	public MaterialProperties readMaterialPropertiesContentBymaterialbrand(String materialbrand);
	
	public void saveMaterialProperties(@Param("materialcategory")String materialcategory,
										@Param("materialbrand")String materialbrand,
										@Param("tensilestrength")String tensilestrength,
										@Param("extensionstrength")String extensionstrength,
										@Param("elongation")String elongation,
										@Param("hardness")String hardness,
										@Param("mainmatrixorganization")String mainmatrixorganization,
										
										@Param("adddistributionratio")String adddistributionratio,
										@Param("addmelting")String addmelting,
										@Param("addinoculation")String addinoculation,
										@Param("addspheroidizing")String addspheroidizing,
										@Param("addannealing")String addannealing
										);
	
	public List<BMpropara> readAllDraftInfor();
	
	public List<BMpropara> readPartDraftInfor(@Param("bmfindstring")String bmfindstring);
	
	public void deletebmpropara(@Param("castingmodeling")String castingmodeling,
								@Param("sizecondition")String sizecondition,
								@Param("draftremarks")String draftremarks);
	
	public void addbmpropara(@Param("castingmodeling")String castingmodeling,
							@Param("sizecondition")String sizecondition,
							@Param("draftangle")String draftangle,
							@Param("draftlength")String draftlength,
							@Param("draftremarks")String draftremarks);
	
	public List<JZpropara> readAllPouringInfor();
	
	public void deletejzpropara(@Param("pouringweight")String pouringweight,
								@Param("njdnum")String njdnum);
	
	public void addjzpropara(@Param("pouringweight")String pouringweight,
								@Param("njdnum")String njdnum,
								@Param("njda")String njda,
								@Param("njdb")String njdb,
								@Param("njdc")String njdc,
								@Param("hjda")String hjda,
								@Param("hjdb")String hjdb,
								@Param("hjdc")String hjdc,
								@Param("zjdd")String zjdd,
								@Param("pouringremarks")String pouringremarks);
	
	public List<MKpropara> readAllRiserInfor();
	
	public void deletemkpropara(@Param("castingmodulus")String castingmodulus);
	
	public void addmkpropara(@Param("castingmodulus")String castingmodulus,
								@Param("slowpourd")String slowpourd,
								@Param("quickpourd")String quickpourd,
								@Param("squaremk")String squaremk,
								@Param("rectanglemk")String rectanglemk,
								@Param("mkremarks")String mkremarks);
	
	public List<LXpropara> readAllLXInfor();
	
	public void lxinforgriddel(String partnumber);
	
	public List<LXpropara> readpartlxinfor(@Param("findtype")String findtype,
											@Param("findstring")String findstring); 
	
	public void modlxpropara(@Param("parttype")String parttype,
								@Param("material")String material,
								@Param("partnumber")String partnumber,
								@Param("partouterdiam")String partouterdiam,
								@Param("outersurfmachallow")String outersurfmachallow,
								@Param("innersurfmachallow")String innersurfmachallow,
								@Param("endmachallow")String endmachallow,
								@Param("rotatspeed")String rotatspeed,
								@Param("coating")String coating,
								@Param("operattemper")String operattemper,
								@Param("pourtemper")String pourtemper,
								@Param("releasetemper")String releasetemper,
								@Param("molddryingpro")String molddryingpro
);
	
	public LXpropara lxreadbypartnumber(String partnumber);
	
	public void addlxpropara(@Param("parttype")String parttype,
								@Param("material")String material,
								@Param("partnumber")String partnumber,
								@Param("partouterdiam")String partouterdiam,
								@Param("outersurfmachallow")String outersurfmachallow,
								@Param("innersurfmachallow")String innersurfmachallow,
								@Param("endmachallow")String endmachallow,
								@Param("rotatspeed")String rotatspeed,
								@Param("coating")String coating,
								@Param("operattemper")String operattemper,
								@Param("pourtemper")String pourtemper,
								@Param("releasetemper")String releasetemper,
								@Param("molddryingpro")String molddryingpro);
	

	
	
	
}

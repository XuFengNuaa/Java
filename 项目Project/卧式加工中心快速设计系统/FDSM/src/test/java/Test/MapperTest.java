package Test;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nuaa.bean.Person;
import com.nuaa.bean.Workpiece;
import com.nuaa.service.IPartsMgService;
import com.nuaa.service.IUserService;

/*
 * 测试Dao层
 * 
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
	public class MapperTest { 
	@Autowired
	private IUserService personService;
	private IPartsMgService partMg;
	
	@Autowired
	SqlSession sqlSession;  // 批量的插入
	@Test
	public void test1() {
			Person person = new Person();
			person.setAge("21");
			person.setGender("女");
			person.setPwd("wang2");
			person.setName("wang2");
	//		personService.insert(person);
			System.out.println(person.getDid());
	}
	@Test
	public void test2(){
		System.out.println(personService);
		Person person1 = new Person();
		person1.setName("admin");
		//System.out.println(person1.getDid());  不是null,是 0
	//    List<Person> persons= personService.findSelective(person1);
		//for(int i=0;i<persons.size();i++) {
	//		System.out.println(persons.get(0).getDid()+"--"+persons.get(0).getName());
		//}
	}
	
	@Test
	public void testwp() {
		System.out.println(personService);
		System.out.println(partMg);
		Workpiece workpiece = new Workpiece();
		workpiece.setWorkid(1);
		workpiece.setCourse("sad");
		workpiece.setFendu("asd");
		partMg.updateWpByIdSelective(workpiece);
}
	@Test
	public void test2wp(){
		System.out.println(personService);
		Person person1 = new Person();
		person1.setName("admin");
		//System.out.println(person1.getDid());  不是null,是 0
	//    List<Person> persons= personService.findSelective(person1);
		//for(int i=0;i<persons.size();i++) {
	//		System.out.println(persons.get(0).getDid()+"--"+persons.get(0).getName());
		//}
	}
	@Test
	public void testSd() {
		double[] weightb = new double[12];
		weightb[0] = 0.20;  //L
		weightb[1] = 0.16;  //w
		weightb[2] = 0.13;  //H
		weightb[3] = 0.09;  //C_d
		weightb[4] = 0.08;  //X_L
		weightb[5] = 0.08;  //X_distance
		weightb[6] = 0.08;  //Z_L
		weightb[7] = 0.07;  //Z_distance
		weightb[8] = 0.04;  //wp_h
		weightb[9] = 0.02;  //ribThick
		weightb[10] = 0.02;  //jhole
		weightb[11] = 0.03;  //structure
		
	}
	
	@Test
	public void testLog(){
		Logger logger =Logger.getLogger(MapperTest.class);
		logger.debug("debug");
	}	
	
}

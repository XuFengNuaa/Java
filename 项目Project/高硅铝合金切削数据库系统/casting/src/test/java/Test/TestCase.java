package Test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import nuaa.casting.dao.EmpDao;
import nuaa.casting.service.CastingContent;
import nuaa.casting.service.User;

public class TestCase {

	private ApplicationContext ac;
	private EmpDao dao;
@Before//”≈œ»÷¥––	
	public void init(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		dao=ac.getBean("empDao",EmpDao.class);
	}	
	
@Test
	public void test1() {		
	List<User> person;
	person=dao.findAll();
	for(User user:person) {
		System.out.println(user);
	}
	
	}

@Test
public void test2() {
	
}





}

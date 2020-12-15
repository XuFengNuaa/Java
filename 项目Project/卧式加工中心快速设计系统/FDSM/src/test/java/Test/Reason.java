package Test;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nuaa.bean.Atc;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.Workpiece;
import com.nuaa.service.IReasonFtService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Reason {
	@Autowired
	private IReasonFtService reason;
	
	@Test
	public void reasonWp() {
			System.out.println(reason);
			Workpiece workpiece = new Workpiece();
			workpiece.setGongjianMaxd(630);
			workpiece.setGongjianH(630);
			workpiece.setZaihe(630);
		/*	List<Workpiece> reasonWp = reason.findReasonWp(workpiece);
		for (Workpiece workpiece2 : reasonWp) {
		//		System.out.println(workpiece2.getType());
			}*/
	}
	
	@Test
	public void spiltl() {
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			String[] arry = str.split("/");
			System.out.println(Integer.parseInt(arry[0]));
	}
	
	@Test
	public void testSd() {
			System.out.println(reason);
			List<Spindle> sd = reason.findReasonSd("Al", "", 800);
			for (Spindle spindle : sd) {
				System.out.println(spindle.getSdType());
			}
	}
	
	@Test
	public void testAtc() {
			System.out.println(reason);
			Atc atc = new Atc();
			List<Atc> atc2 = reason.findReasonAtc("2", "MAS403", 800);
			for (Atc atc3 : atc2) {
				System.out.println(atc3.getTooldaihao());
			}
			
	}
	
	@Test
	public void testSw() {
			System.out.println(reason);
	/*		List<Screw> sw = reason.findReasonSw(50.0, 57100, 1000, "C2","1");
			System.out.println(sw.size());
			for (Screw screw : sw) {
				System.out.println(screw.getDaochen() +"--"+ screw.getGcd());
			} */
			
	}
	
	@Test
	public void testSway() {
			System.out.println(reason);
	//		List<Slideway> s = reason.findReasonSdWay(1000, 850, 1000, 900, 580, 50, 0.01, 630);
	//		for (Slideway way : s) {
		//		System.out.println(way.getXinghao());
	//		}
	}
	
	
}

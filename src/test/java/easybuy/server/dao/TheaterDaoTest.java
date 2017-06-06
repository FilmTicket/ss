package easybuy.server.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import easybuy.server.model.Theater;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class TheaterDaoTest {
    
	@Autowired
	private TheaterDao theaterdao;
	
	//@Test
	public void addTheaterTest() {
		
		String theaterName = "新天地电影院";
		String theaterAddr = "新天地"; 
		String theaterDis = "省略";
		String theaterLowest = "shit";
		String tag = "Imax";
		
		String message = theaterdao.addTheater(theaterName, theaterAddr, theaterDis, theaterLowest, tag);
		System.out.println("\nlog in test:");
	
		if (message != null) {
			System.out.println("Theater has existed\n");
		} else {
			System.out.println("Theater add success\n");
		}
	}
	
	@Test
	public void getTheatersByTagTest() {
		String theaterName = "万达影院";
		String theaterAddr = "新天地"; 
		String theaterDis = "省略";
		String theaterLowest = "shit";
		String tag = "Imax";
		
		theaterdao.addTheater(theaterName, theaterAddr, theaterDis, theaterLowest, tag);
		
		List<Theater> theaters1 = theaterdao.getTheatersByTag(tag);
		System.out.println("\ngetTheatersByTag test1:");
		if (theaters1 == null) {
			System.out.println("Theater does not existed\n");
		} else {
			for (int i = 0; i < theaters1.size(); i++) {
				System.out.println(theaters1.get(i).getTheaterId() + "has this tag\n");
			}
		}
		
		List<Theater> theaters2 = theaterdao.getTheatersByTag("shit");
		System.out.println("\ngetTheatersByTag test2:");
		if (theaters2 == null) {
			System.out.println("Theater does not existed\n");
		} else {
			for (int i = 0; i < theaters2.size(); i++) {
				System.out.println(theaters2.get(i).getTheaterId() + "has this tag\n");
			}
		}
	}
	
}

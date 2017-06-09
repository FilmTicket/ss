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
	
	@Test
	public void addTheaterTest() {
		String theaterName = "Wanda";
		String theaterAddr = "11111"; 
		String theaterDis = "222222";
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
		String theaterName = "死神影院";
		String theaterAddr = "22222"; 
		String theaterDis = "333333";
		String theaterLowest = "shit";
		String tag = "Imax|popcorn";
		
		theaterdao.addTheater(theaterName, theaterAddr, theaterDis, theaterLowest, tag);
		List<Theater> theaters1 = theaterdao.getTheatersByTag("Imax");
		System.out.println("\ngetTheatersByTag test1:");
		
		if (theaters1 == null) {
			System.out.println("Theater does not existed\n");
		} else {
			for (int i = 0; i < theaters1.size(); i++) {
				System.out.println(theaters1.get(i).getTheaterId() + "has this tag\n");
			}
		}
		
		List<Theater> theaters2 = theaterdao.getTheatersByTag("全部");
		System.out.println("\ngetTheatersByTag test2:");
		
		if (theaters2.isEmpty()) {
			System.out.println("Theater does not existed\n");
		} else {
			for (int i = 0; i < theaters2.size(); i++) {
				System.out.println(theaters2.get(i).getTheaterId() + "has this tag\n");
			}
		}
	}
	
	@Test
	public void searchTheatersTest() {
	    String keyword = "死神";
		
		List<Theater> theaters1 = theaterdao.searchTheater(keyword);
		System.out.println("\nsearchTheater test1:");
		
		if (theaters1.isEmpty()) {
			System.out.println("Theater does not existed\n");
		} else {
			for (int i = 0; i < theaters1.size(); i++) {
				System.out.println(theaters1.get(i).getTheaterId() + "find\n");
			}
		}
		
		List<Theater> theaters2 = theaterdao.searchTheater("HAHa");
		System.out.println("\nsearchTheater test2:");
		
		if (theaters2.isEmpty()) {
			System.out.println("Theater does not existed\n");
		} else {
			for (int i = 0; i < theaters2.size(); i++) {
				System.out.println(theaters2.get(i).getTheaterId() + "find\n");
			}
		}
	} 
	
	/*
	@Test
	public void getTheaterTagTest() {
		String _id = "10";
		List<String> tags = theaterdao.getTheaterTag(_id);
		System.out.println("\ngetTheaterTag test1:");
		if (tags == null||tags.isEmpty()) {
			System.out.println("This Theater does not existed\n");		
		} else {
			for (int i = 0 ; i < tags.size(); ++i)
				System.out.println(tags.get(i) + " find\n");
		}
		
	}
	*/
}

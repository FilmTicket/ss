package easybuy.server.networkdata.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import easybuy.server.model.Theater;
import easybuy.server.networkdata.beans.TheaterBean;
import easybuy.server.networkdata.beans.TheaterData;
import easybuy.server.networkdata.beans.TheaterVO;
import easybuy.server.networkdata.retrofit.RetrofitFactory;
import easybuy.server.service.TheaterService;
import rx.functions.Action1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class TheaterProcess {
	
	@Autowired
	private TheaterService theaterService;
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterProcess.class);
	
	@Test
	public void addTheaterProcess() {
		final List<Theater> theaters = new ArrayList<Theater>();
		String message = null;
		
        RetrofitFactory.getInstance().getMovieService()
        .getAllTheaters()
        .subscribe(new Action1<TheaterVO>() {
            @Override
            public void call(TheaterVO theaterVO) {
                System.out.println(theaterVO.getStatus());
                TheaterData theaterData = theaterVO.getData();
                ArrayList<TheaterBean> theaterBeans = theaterData.getAllTheaterBeans();
                
                Random random = new Random();
                for (TheaterBean bean : theaterBeans) {
                	String tag = null;
                	
                	switch (random.nextInt(4)) {
                		case 0:
                			tag = "";
                			break;
                		case 1:
                			tag = "imax";
                			break;
                		case 2:
                			tag = "popcorn";
                			break;
                		case 3:
                			tag = "imax|popcorn";
                			break;
                		default:
                			tag = "popcorn";
                			break;
                	}
                	
                    Theater theater = new Theater(bean.getNm(), bean.getAddr(), "", bean.getSellPrice().toString(), tag);
                    theaters.add(theater);
                }
            }
        });
        
        message = theaterService.addTheaters(theaters);
        if (message != null) {
        	logger.info(message);
        }
	}
	
//	@Test
	public void deleteTheaterProcess() {
		String message = null;
		
		message = theaterService.deleteAllTheaters();
		if (message != null) {
        	logger.info(message);
        }
	}
}

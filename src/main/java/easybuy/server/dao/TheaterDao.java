package easybuy.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easybuy.server.model.Theater;

@Component
public class TheaterDao {
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//根据tag查询Theater
	public List<Theater> getTheatersByTag (String tag) {
		List<Theater> theaters = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from Theater where tag = ?";
			Query<Theater> query = sess.createQuery(hql, Theater.class);
			theaters = query.setParameter(0, tag).setCacheable(true).getResultList();
			tx.commit();
		} catch (Exception e) {
			logger.error("TheaterDao::getTheatersByTag" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return theaters;
	}
	
	public String addTheater(String theaterName, String theaterAddr, String theaterDis, String theaterLowest, String tag) {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			Theater theater = new Theater(theaterName, theaterAddr, theaterDis, theaterLowest, tag);
			sess.save(theater);
			
			tx.commit();
		} catch (Exception e) {
			message = "插入影院失败， 影院已经存在";
			logger.error("TheaterDao::addTheater" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}


}

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
	
	// 根据tag查询影院
	public List<Theater> getTheatersByTag(String tag) {
		List<Theater> theaters = null;
		
		Session sess = null;
		Transaction tx = null;
		if (tag.equals("全部")) {
			tag = "";
		}
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from Theater where tag like :name";
			Query<Theater> query = sess.createQuery(hql, Theater.class);
			theaters = query.setParameter("name", "%"+tag+"%").setCacheable(true).getResultList();
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
			message = "影院插入失败";
			logger.error("TheaterDao::addTheater" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
    
	// 根据关键字查询影院
    public List<Theater> searchTheater(String keyword) {
        List<Theater> theaters = null;
		
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from Theater where theaterName like :name";
			Query<Theater> query = sess.createQuery(hql, Theater.class);
			theaters = query.setParameter("name", "%"+keyword+"%").setCacheable(true).getResultList();
			tx.commit();
		} catch (Exception e) {
			logger.error("TheaterDao::searchTheater" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return theaters;
    }
    
    // 查询影院的tag
    /*
    public List<String> getTheaterTag(String theaterId) {
        
    	List<Theater> theaters = null;
    	List<String> tags = null;
		Session sess = null;
		Transaction tx = null;
		String tag = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			Integer _id = Integer.parseInt(theaterId);
			
			String hql = "from Theater where theaterId = ?";
			Query<Theater> query = sess.createQuery(hql, Theater.class);
			theaters = query.setParameter(0, _id).setCacheable(true).getResultList();
			
			if (theaters.isEmpty()||theaters == null) {
				
			} else {				 
				 tag = theaters.get(0).getTag();
				 String[] temp = tag.split("\\u007C");
				 tags = new ArrayList<String>();
				 for (String s : temp) tags.add(s);
			}
			tx.commit();
		} catch (Exception e) {
			logger.error("TheaterDao::getTheaterTag" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return tags;
    }
    */
    
    public String addTheaters (List<Theater> theaters) {
    	String message = null;
    	for (int i = 0; i < theaters.size(); ++i) {
    		Theater temp = theaters.get(i);
    		message = addTheater(temp.getTheaterName(), temp.getTheaterAddr(), temp.getTheaterDis(), temp.getTheaterLowest(), temp.getTag());
    		if (message != null) break;	   		
    	}
    	return message;
    }
}

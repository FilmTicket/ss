package easybuy.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easybuy.server.comm.Util;
import easybuy.server.dao.TheaterDao;
import easybuy.server.model.Theater;

@Service
public class TheaterService {

	@Autowired
	private TheaterDao  theaterDao;
	
	public List<Theater> getTheatersByTag(String tag) {
		if (Util.isBlank(tag)) {
			return null;
		}
		
		return theaterDao.getTheatersByTag(tag);
	}
	
	public List<Theater> searchTheater(String keyword) {
		if (Util.isBlank(keyword)) {
			return null;
		}
		
		return theaterDao.searchTheater(keyword);
	}
	
	public String addTheaters(List<Theater> theaters) {
		String message = null;
		
		if (theaters == null || theaters.isEmpty()) {
			message = "影院列表为空";
		}
		
		if (message == null) {
			return theaterDao.addTheaters(theaters);
		}
		
		return message;
		
	}
	
	public String deleteAllTheaters() {
		return theaterDao.deleteAllTheaters();
	}
	
//	public List<String> getTheaterTag(String theaterId) {
//		if (Util.isBlank(theaterId)) {
//			return null;
//		}
//		
//		return theaterDao.getTheaterTag(theaterId);
//	}
	
}

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
	private TheaterDao  theaterdao;
	
	public List<Theater> getTheatersByTag (String tag) {
		if (Util.isBlank(tag)) {
			return null;
		}
		return theaterdao.getTheatersByTag(tag);
	}
	
	public List<Theater> searchTheater(String keyword) {
		if (Util.isBlank(keyword)) {
			return null;
		}
		return theaterdao.searchTheater(keyword);
	}
		
	public List<String> getTheaterTag(String theaterId) {
		if (Util.isBlank(theaterId)) {
			return null;
		}
		return theaterdao.getTheaterTag(theaterId);
	}
}

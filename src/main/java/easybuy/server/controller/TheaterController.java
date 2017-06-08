package easybuy.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.server.comm.Util;
import easybuy.server.model.HttpResult;
import easybuy.server.model.Theater;
import easybuy.server.service.TheaterService;

@Controller
@RequestMapping("theater")
public class TheaterController {
    
	@Autowired
	private TheaterService theaterService;
	
	@ResponseBody
	@RequestMapping(value = "getTheaterByTag", method = RequestMethod.POST) 
	public Object getTheaterByTag(String tag) {
		List<Theater> theaters = null;
		String message = null;
		
		if (Util.isBlank(tag)) {
			message = "tag为空";
		}
						
		if (message == null) {
			theaters = theaterService.getTheatersByTag(tag);			
			if (theaters == null||theaters.isEmpty()) {
				message = "影院不存在";
			}
		}
		
		HttpResult<List<Theater>> result = null;
		if (message == null) {
			result = new HttpResult<List<Theater> >(1, "", theaters);
		} else {
			result = new HttpResult<List<Theater> >(0, message, null);
		}
		
		return result;	
	}
    
	@ResponseBody
	@RequestMapping(value = "searchTheater", method = RequestMethod.POST) 
	public Object searchTheater(String keyword) {
		List<Theater> theaters = null;
		String message = null;
		
		if (Util.isBlank(keyword)) {
			message = "keyword为空";
		}
			
		if (message == null) {
			theaters = theaterService.searchTheater(keyword);			
			if (theaters == null||theaters.isEmpty()) {
				message = "影院不存在";
			}
		}
		
		HttpResult<List<Theater>> result = null;
		if (message == null) {
			result = new HttpResult<List<Theater> >(1, "", theaters);
		} else {
			result = new HttpResult<List<Theater> >(0, message, null);
		}
		
		return result;	
	}
	
	@ResponseBody
	@RequestMapping(value = "getTheaterTag", method = RequestMethod.POST)
	public Object getTheaterTag(String theaterId) {
		List<String> tags = null;
		String message = null;
		
		if (Util.isBlank(theaterId)) {
			message = "theaterIdÎª¿Õ";
		}
		
		if (message == null) {
			tags = theaterService.getTheaterTag(theaterId);
			if (tags == null||tags.isEmpty()) {
				message = "theaterIdÎª¿Õ";
			}
		}
		
		HttpResult<List<String>> result = null;
		if (message == null) {
			result = new HttpResult<List<String> >(1, "", tags);
		} else {
			result = new HttpResult<List<String> >(0, message, null);
		}
		
		return result;	
	}
}

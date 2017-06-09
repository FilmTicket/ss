package easybuy.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterController.class);
	
	@ResponseBody
	@RequestMapping(value = "getTheaterByTag", method = RequestMethod.POST)
	public Object getTheaterByTag(String tag, HttpSession session) {
		List<Theater> theaters = null;
		String message = null;
		
		logger.info("Request to get theater by tag, session id:" + session.getId());
		
		if (Util.isBlank(tag)) {
			message = "tag为空";
		}
						
		if (message == null) {
			theaters = theaterService.getTheatersByTag(tag);			
			if (theaters == null || theaters.isEmpty()) {
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
	public Object searchTheater(String keyword, HttpSession session) {
		List<Theater> theaters = null;
		String message = null;
		
		logger.info("Request to search theater, session id:" + session.getId());
		
		if (Util.isBlank(keyword)) {
			message = "keyword为空";
		}
			
		if (message == null) {
			theaters = theaterService.searchTheater(keyword);			
			if (theaters == null || theaters.isEmpty()) {
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
	@RequestMapping(value = "getTheaterTag", method = RequestMethod.GET)
	public Object getTheaterTag(HttpSession session) {
		List<String> tags = new ArrayList<String>();
		
		logger.info("Request to get theater tag, session id:" + session.getId());
		
		tags.add("imax");
		tags.add("popcorn");
		
		HttpResult<List<String>> result = null;
		result = new HttpResult<List<String> >(1, "", tags);
		
		return result;	
	}
}

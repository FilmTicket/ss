package easybuy.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import easybuy.server.controller.UserController;

public class NetWorkDataListener implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Server start");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// 服务器关闭，暂无任务
	}
}

package net.slipp;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class WebserverLauncher {
	public static void main(String[] args) throws Exception {
		String webappDirLocation = "WebContent/";
		Tomcat tomcat = new Tomcat();
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		
		tomcat.setPort(Integer.parseInt(webPort));
		Connector connector = tomcat.getConnector();
		connector.setURIEncoding("UTF-8");
		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir : " + new File(webappDirLocation).getAbsolutePath());
		tomcat.start();
		tomcat.getServer().await();
	}
}

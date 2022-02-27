package es.codeurjc.wallypop.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@SpringBootApplication
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
 
	
	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {FrontController.class};
	}
 
}


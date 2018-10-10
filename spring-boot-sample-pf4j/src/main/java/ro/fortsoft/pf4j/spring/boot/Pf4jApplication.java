package ro.fortsoft.pf4j.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration 				// 配置控制
@EnableScheduling
@EnableAutoConfiguration 	// 启用自动配置
@SpringBootApplication
public class Pf4jApplication implements ApplicationRunner, CommandLineRunner {
/*
	@Autowired
	private PluginManager pluginManager;*/
	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) throws Exception {

		SpringApplication.run(Pf4jApplication.class, args);

	}
 

	@Override
	public void run(ApplicationArguments args) throws Exception {
			/*
		Map<String,AuthcExtensionPoint> map = context.getBeansOfType(AuthcExtensionPoint.class);
		for (String key : map.keySet()) {
			System.err.println(map.get(key));
		}*/
		
	    /*List<PluginWrapper> list = pluginManager.getPlugins();
	    for (PluginWrapper pluginWrapper : list) {
			System.out.println(pluginWrapper.getPluginId());
			
			List<?> extensions = pluginManager.getExtensions(pluginWrapper.getPluginId());
		    for (Object extension : extensions) {
		    	
		    	System.out.println(extension);
		    	
			}
		    
		}*/
	    
		// pluginManager.stopPlugins();
	    
		// System.out.println("=============");
	    
	}

	@Override
	public void run(String... args) throws Exception {
 
	}


}

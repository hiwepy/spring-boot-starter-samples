/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package pf4j2.plugin.test;

import java.nio.file.Paths;
import java.util.List;

import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

import pf4j2.plugin.api.AuthcExtensionPoint;
import pf4j2.plugin.api.annotation.ExtensionMapping;

public class AuthcPluginTest {

	public static void main(String[] args) {
		
		
		
		System.setProperty("pf4j.mode", RuntimeMode.DEPLOYMENT.toString());
		
//		if(RuntimeMode.DEPLOYMENT.compareTo(RuntimeMode.DEPLOYMENT) == 0) {
//			System.setProperty("pf4j.pluginsDir", System.getProperty("app.home","e:/root") + "/plugins");
//		} else {
//			System.setProperty("pf4j.pluginsDir", "plugins");
//		}
		
		
//		final PluginManager pluginManager = new DefaultPluginManager(new File("E:/root/").toPath());
		//final PluginManager pluginManager = new JarPluginManager();
		PluginManager pluginManager = new Pf4jPluginManager(Paths.get("E:/root/"));

	    pluginManager.loadPlugins();

	    pluginManager.startPlugins();
	    
	    List<PluginWrapper> list = pluginManager.getPlugins();
	    for (PluginWrapper pluginWrapper : list) {
			System.out.println(pluginWrapper.getPluginId());
		}
	    
	    
	    List<AuthcExtensionPoint> extensions = pluginManager.getExtensions(AuthcExtensionPoint.class);
	    
	    for (AuthcExtensionPoint point : extensions) {
	    	
	    	ExtensionMapping m = point.getClass().getAnnotation(ExtensionMapping.class);
	    	System.out.println(m.title());
	    	
		}
	    
	    
	    pluginManager.stopPlugins();
	    
	    System.out.println("=============");
		
	}
	
}

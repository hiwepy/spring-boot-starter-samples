/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package pf4j.plugin.test;

import java.util.List;

import pf4j.plugin.api.AuthcExtensionPoint;
import pf4j.plugin.api.annotation.ExtensionMapping;
import ro.fortsoft.pf4j.JarPluginManager;
import ro.fortsoft.pf4j.PluginManager;
import ro.fortsoft.pf4j.PluginWrapper;
import ro.fortsoft.pf4j.RuntimeMode;

public class AuthcPluginTest {

	public static void main(String[] args) {
		
		System.setProperty("pf4j.mode", RuntimeMode.DEPLOYMENT.toString());
		System.setProperty("pf4j.pluginsDir", "plugins");
		
		if(RuntimeMode.DEPLOYMENT.compareTo(RuntimeMode.DEPLOYMENT) == 0) {
			//System.setProperty("pf4j.pluginsDir", System.getProperty("app.home","e:/root") + "/plugins");
		}
		
		/**
		 * 创建PluginManager对象,此处根据生产环境选择合适的实现，或者自定义实现
		 */
		// PluginManager pluginManager = new DefaultPluginManager(new File("E:/root/").toPath());
		PluginManager pluginManager = new JarPluginManager();
		// PluginManager pluginManager = new Pf4jJarPluginManager();
		// PluginManager pluginManager = new Pf4jJarPluginWhitSpringManager();
		// PluginManager pluginManager = new Pf4jPluginManager();
	
		/**
		 * 加载插件到JVM
		 */
	    pluginManager.loadPlugins();

	    /**
	     * 调用Plugin实现类的start()方法: 
	     */
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
	    
	    /**
	     * 调用Plugin实现类的stop()方法
	     */
	    pluginManager.stopPlugins();
	    
	    System.out.println("=============");
		
	}
	
}

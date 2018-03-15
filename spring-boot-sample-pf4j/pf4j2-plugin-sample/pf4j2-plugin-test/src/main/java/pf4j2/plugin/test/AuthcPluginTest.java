/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package pf4j2.plugin.test;

import java.util.List;

import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

import pf4j2.plugin.api.AuthcExtensionPoint;
import pf4j2.plugin.api.annotation.ExtensionMapping;

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
		PluginManager pluginManager = new DefaultPluginManager();

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

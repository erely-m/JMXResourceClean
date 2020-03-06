package cn.erely.jmx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.remote.JMXConnector;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * JMX工具类
 *
 * @author maqh
 * @data 2018年10月15日
 * @time 上午09:11:48
 */
public class JMXUtil {

    private static Logger log = LoggerFactory.getLogger(JMXUtil.class);

    /**
     * 新增以下方法关闭JMXConnector 解决JMX连接释放问题
     *
     * @param bean 对应的Mbean
     */
    public static void closeJMXConnector(Object bean) {
        //获取bean对应的处理器. get bean handler
        MBeanServerInvocationHandler handler = (MBeanServerInvocationHandler) Proxy.getInvocationHandler(bean);
        //通过bean获取serverconnection.get ServerConnection for this bean
        MBeanServerConnection mbsc = handler.getMBeanServerConnection();
        //获取serverconnection中的属性其中第二个属性为JMXConnector. JMXConnector comes from the second attribute of ServerConnection
        Field[] fields = mbsc.getClass().getDeclaredFields();
        //Set the accessible flag for this object to the indicated boolean value.
        fields[1].setAccessible(true);

        try {
            ((JMXConnector) fields[1].get(mbsc)).close();
        } catch (Exception e) {
            log.error("close jmx connector fail" + e);
        }
    }
}


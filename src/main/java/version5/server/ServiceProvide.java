package version5.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import version5.register.NacosRegister;
import version5.register.ServerRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:41
 */
@Data
public class ServiceProvide {
    /**
     * 一个实现类可能实现多个接口
     */
    private Map<String, Object> interfaceProvider;
    private ServerRegister register;
    private String host;
    private int port;

    public ServiceProvide(String host,int port){
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        this.register = new NacosRegister();
    }

    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for(Class clazz : interfaces){
            //当前的接口和实现对象映射
            interfaceProvider.put(clazz.getName(),service);
            //将服务注册到注册中心
            register.register(clazz.getName(),new InetSocketAddress(host,port));
        }

    }

    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}

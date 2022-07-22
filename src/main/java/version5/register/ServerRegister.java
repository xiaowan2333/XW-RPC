package version5.register;

import java.net.InetSocketAddress;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 11:11
 */

/**
 * 定义注册功能接口，后续不管使用哪个注册中心都可以
 */
public interface ServerRegister {
    void register(String serviceName, InetSocketAddress address);
    InetSocketAddress serviceDiscovery(String serviceName);
}

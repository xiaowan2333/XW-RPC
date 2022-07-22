package version5.register;

import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 13:30
 */
public class TestRegister {
    @Test
    public void testInstance(){
        ServerRegister register = new NacosRegister();
        InetSocketAddress nuSuchServer = register.serviceDiscovery("NuSuchServer");
        System.out.println(nuSuchServer==null);
    }
}

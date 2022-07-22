package version5.register;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import version5.loadbalance.LoadBalance;
import version5.loadbalance.RoundLoadBalance;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 11:13
 */
public class NacosRegister implements ServerRegister{
    private static final String SERVER_ADDRESS = "127.0.0.1:8848";
    private static final NamingService namingService;
    //默认使用轮询
    private  LoadBalance balance = new RoundLoadBalance();
    static {
        namingService = getNamingService();
        System.out.println("已连接到注册中心...");
    }
    private static NamingService getNamingService() {
        try {
            return NacosFactory.createNamingService(SERVER_ADDRESS);
        } catch (NacosException e) {
            System.out.println("连接注册中心失败！"+e);
            throw new RuntimeException("连接注册中心失败！");
        }
    }

    public LoadBalance getBalance() {
        return balance;
    }

    public void setBalance(LoadBalance balance) {
        this.balance = balance;
    }

    @Override
    public void register(String serviceName, InetSocketAddress address) {
        try {
            namingService.registerInstance(serviceName, address.getHostName(), address.getPort());
            System.out.println("服务："+serviceName+"注册成功");
        } catch (NacosException e) {
            System.out.println("服务注册失败！");
            e.printStackTrace();
        }
    }

    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        List<Instance> allInstances = null;
        try {
            allInstances = namingService.getAllInstances(serviceName);
        } catch (NacosException e) {
            System.out.println("拉取服务列表失败");
            e.printStackTrace();
        }
        //加入负载均衡
        if (allInstances.isEmpty()){
            //当可用服务>1时用负载均衡
            if (allInstances.size()>1){
                Instance balance = this.balance.balance(allInstances);

            }
            //这里直接拿第一个实例，封装成socket
            Instance instance = allInstances.get(0);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        }
        return null;
    }
}

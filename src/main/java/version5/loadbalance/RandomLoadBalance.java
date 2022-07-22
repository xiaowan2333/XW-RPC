package version5.loadbalance;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Random;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 15:39
 */
public class RandomLoadBalance implements LoadBalance{
    @Override
    public Instance balance(List<Instance> sList) {
        Random random = new Random();
        int i = random.nextInt(sList.size());
        System.out.println("随机负载均衡选择了第"+i+"个服务器的服务");
        return sList.get(i);
    }
}

package version5.loadbalance;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 15:32
 */
public interface LoadBalance {
    Instance balance(List<Instance> sList);
}

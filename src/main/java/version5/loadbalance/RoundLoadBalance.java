package version5.loadbalance;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 16:15
 */
public class RoundLoadBalance implements LoadBalance{
    private int choose = -1;
    @Override
    public Instance balance(List<Instance> sList) {
        choose++;
        choose = choose%sList.size();
        return sList.get(choose);
    }
}

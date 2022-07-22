package version4.client;

import version4.common.RpcRequest;
import version4.common.RpcResponse;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:14
 */
public interface RpcClient {
    RpcResponse sentRequest(RpcRequest request);
}

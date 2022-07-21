package version3.client;

import version3.common.RpcRequest;
import version3.common.RpcResponse;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:14
 */
public interface RpcClient {
    RpcResponse sentRequest(RpcRequest request);
}

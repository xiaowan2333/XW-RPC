package version5.client;

import version5.common.RpcRequest;
import version5.common.RpcResponse;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:14
 */
public interface RpcClient {
    RpcResponse sentRequest(RpcRequest request);
}

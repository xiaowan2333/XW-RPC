package version2.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:20
 */
@Builder
@Data
public class RpcResponse implements Serializable {
    // 状态信息
    private int code;
    private String message;
    // 具体数据
    private Object data;

    public static RpcResponse success(Object data) {
        return RpcResponse.builder().code(200).data(data).build();
    }
    public static RpcResponse fail() {
        return RpcResponse.builder().code(500).message("服务器发生错误").build();
    }
}

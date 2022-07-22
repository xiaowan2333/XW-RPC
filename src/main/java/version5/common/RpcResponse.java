package version5.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:20
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {
    // 状态信息
    private int code;
    private String message;
    // 具体数据
    private Object data;
    //data的类型，json反序列化时需要用到
    private Class<?> dataType;


    public static RpcResponse success(Object data) {
        return RpcResponse.builder().code(200).data(data).dataType(data.getClass()).build();
    }
    public static RpcResponse fail() {
        return RpcResponse.builder().code(500).message("服务器发生错误").build();
    }
}

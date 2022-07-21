package version3.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:17
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramTypes;
}

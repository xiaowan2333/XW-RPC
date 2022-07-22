package version5.codec;

import lombok.AllArgsConstructor;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 9:53
 */
@AllArgsConstructor
public enum MessageType {
    Request(0),Response(1);
    private int code;
    public int getCode(){
        return this.code;
    }
}

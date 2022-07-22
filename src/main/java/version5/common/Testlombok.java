package version5.common;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 10:33
 */
public class Testlombok {
    @Test
    public void testrequest(){
        RpcRequest request = new RpcRequest("suib", "sss", new Object[3], new Class[2]);
        Arrays.stream(request.getParams()).sorted();
        System.out.println(request.getInterfaceName());
    }
    @Test
    public void test1(){
        new RpcResponse().fail();
    }
}

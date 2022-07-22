package version4.client;

import lombok.AllArgsConstructor;
import version4.common.RpcRequest;
import version4.common.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:20
 */
@AllArgsConstructor
public class RpcClientProxy implements InvocationHandler {

    private RpcClient rpcClient;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = RpcRequest.builder().
                interfaceName(method.getDeclaringClass().getName()).
                methodName(method.getName()).
                params(args).
                paramTypes(method.getParameterTypes()).build();
        System.out.println("[start]代理发送请求对象"+request);
        System.out.println("发送方式："+rpcClient.getClass().getName());
        RpcResponse response = rpcClient.sentRequest(request);
        return response.getData();
    }

    <T> T getProxy(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) o;
    }
}

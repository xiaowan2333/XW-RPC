package version2.client;

import lombok.AllArgsConstructor;
import version2.common.RpcRequest;
import version2.common.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:57
 */

/**
 * 根据接口使用jdk动态代理
 */
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    //传入参数
    private String host;
    private int port;


    // jdk 动态代理,每一次代理对象调用方法，会经过此方法增强（反射获取request对象，socket发送至客户端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest request = RpcRequest.builder().
                interfaceName(method.getDeclaringClass().getName()).
                methodName(method.getName()).
                params(args).
                paramTypes(method.getParameterTypes()).build();
        System.out.println("[start]代理发送请求对象"+request);
        RpcResponse response = IOClient.sentRequest(host, port, request);
        return response.getData();
    }

    <T> T getProxy(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T) o;
    }
}

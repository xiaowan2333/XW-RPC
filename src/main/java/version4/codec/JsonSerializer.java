package version4.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import version4.common.RpcRequest;
import version4.common.RpcResponse;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 16:59
 */
public class JsonSerializer implements Serializer{
    @Override
    public byte[] serialize(Object obj) {
        byte[] bytes = JSONObject.toJSONBytes(obj);
        return bytes;
    }

    /**
     * json序列化过程丢失了类型信息，所以反序列化过程要制定接口是request还是response
     * @param bytes
     * @param messageType
     * @return
     */
    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object obj = null;
        switch (messageType){
            case 0:
                RpcRequest request = JSON.parseObject(bytes, RpcRequest.class);
                System.out.println("接收到的byte[]初步转化为对象是："+request);
                Object[] objects = new Object[request.getParams().length];
                //把json对象转化成相应的对象
                for (int i = 0; i < objects.length; i++) {
                    Class<?> paramsType = request.getParamTypes()[i];
                    //判断参数和参数类型是否对的上
                    if (!paramsType.isAssignableFrom(request.getParams()[i].getClass())){
                        objects[i] = JSONObject.toJavaObject((JSONObject) request.getParams()[i],request.getParamTypes()[i]);
                    }else{
                        objects[i] = request.getParams()[i];
                    }
                }
                request.setParams(objects);
                obj  = request;
                System.out.println("转化之后的请求对象："+request);
                break;
            case 1:
                RpcResponse response = JSON.parseObject(bytes,RpcResponse.class);
                System.out.println("接受到的byte[]初步转化对象是:"+response);
                Class<?> dataType = response.getDataType();
                if (!dataType.isAssignableFrom(response.getData().getClass())){
                    //不对劲,防止后续空指针吧
                    response.setData(JSONObject.toJavaObject((JSONObject)response.getData(),dataType));
                }
                obj = response;
                break;
            default:
                System.out.println("不支持的消息类型！");
                throw new RuntimeException("kfc crazy thursday v 50$");
        }
        return obj;
    }

    @Override
    public int getType() {
        return 1;
    }
}

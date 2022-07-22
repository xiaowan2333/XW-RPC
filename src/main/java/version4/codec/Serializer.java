package version4.codec;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 16:43
 */
public interface Serializer {
    //将对象序列化
    byte[] serialize(Object obj);
    //反序列化
    Object deserialize(byte[] bytes,int messageType);

    int getType();
    //0表示Java原生序列化，1表示fastjson的json序列化
    //根据序号取出序列化器，暂时有两种实现方式，需要其它方式，实现这个接口即可
    static Serializer getSerializerByCode(int code){
        switch (code){
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return new ObjectSerializer();
        }
    }
}

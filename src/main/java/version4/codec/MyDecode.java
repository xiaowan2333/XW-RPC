package version4.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 10:00
 */
public class MyDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取消息类型
        short messageType = in.readShort();
        if (messageType != MessageType.Request.getCode()&&
        messageType != MessageType.Response.getCode()){
            System.out.println("出错了，不支持的数据类型");
            return;
        }
        //获取序列化方式得到反序列化器
        short serializeType =  in.readShort();
        Serializer serializer = Serializer.getSerializerByCode(serializeType);
        if (serializer == null){
            System.out.println("不支持的序列化方式！！");
        }
        //获取之前写入的数据长度
        int length = in.readInt();
        //根据长度读取byte[]
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        //反序列化获取对象
        Object deserialize = serializer.deserialize(bytes, messageType);
        out.add(deserialize);
    }
}

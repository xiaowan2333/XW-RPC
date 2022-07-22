package version4.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import version3.client.RpcClientProxy;
import version4.common.RpcRequest;
import version4.common.RpcResponse;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/22 9:46
 */

/**
 * 继承抽象类，之后注入到netty
 */
@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder{
    private Serializer serializer;
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        System.out.println("写入消息类型："+msg.getClass());
        if (msg instanceof RpcRequest){
            //先写入消息类型
            out.writeShort(MessageType.Request.getCode());
        }else if (msg instanceof RpcResponse){
            out.writeShort(MessageType.Response.getCode());
        }
        //写入序列化方式
        out.writeShort(serializer.getType());
        //得到序列化后的byte[]
        byte[] serialize = serializer.serialize(msg);
        //写入序列化的长度，避免粘包
        out.writeInt(serialize.length);
        //最后写入数据
        out.writeBytes(serialize);
    }
}

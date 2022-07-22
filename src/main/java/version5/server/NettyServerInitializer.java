package version5.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;
import version5.codec.MyDecode;
import version5.codec.MyEncode;
import version5.codec.ObjectSerializer;


/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:38
 */

/**
 * netty初始化，主要是序列化的编码解码
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvide serviceProvide;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        // 消息格式 [长度][消息体], 解决粘包问题
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
//        // 计算当前待发送消息的长度，写入到前4个字节中
//        pipeline.addLast(new LengthFieldPrepender(4));
//
//        // 这里使用的还是java 序列化方式， netty的自带的解码编码支持传输这种结构
//        pipeline.addLast(new ObjectEncoder());
//        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
//            @Override
//            public Class<?> resolve(String className) throws ClassNotFoundException {
//                return Class.forName(className);
//            }
//        }));
        //使用自己定义的 编码和解码器
        pipeline.addLast(new MyDecode());
        //服务端使用Java自带的Object编码
        pipeline.addLast(new MyEncode(new ObjectSerializer()));

        pipeline.addLast(new NettyRPCServerHandler(serviceProvide));
    }
}

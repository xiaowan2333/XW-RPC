package version5.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import version5.register.NacosRegister;
import version5.register.ServerRegister;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:36
 */
public class NettyRpcServer implements RpcServer {
    private ServiceProvide serviceProvider;
    private ServerRegister register;

    public NettyRpcServer(ServiceProvide serviceProvider) {
        this.serviceProvider = serviceProvider;
        register = new NacosRegister();
    }

    @Override
    public void start(int port) {
        // netty 服务线程组boss负责建立连接， work负责具体的请求
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        System.out.printf("Netty服务端启动了...");
        try {
            // 启动netty服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 初始化
            serverBootstrap.group(bossGroup,workGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new NettyServerInitializer(serviceProvider));
            // 同步阻塞，绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 死循环监听,端口连接不上就关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    @Override
    public void top() {

    }
}

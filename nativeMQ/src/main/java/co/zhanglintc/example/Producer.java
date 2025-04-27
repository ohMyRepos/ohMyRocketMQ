package co.zhanglintc.example;

import co.zhanglintc.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Classname Producer
 * @Description
 * @Date 2025/4/25 16:41
 * @Created by zhanglintc
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception {
        // 实例化一个生产者，指定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer(Config.PRODUCER_GROUP);
        // 设置NameServer地址
        producer.setNamesrvAddr(Config.NAME_SERVER);
        // 启动生产者
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建消息，指定Topic、Tag和消息体
            Message msg = new Message(Config.TOPIC, Config.TAGA, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息并获取发送结果
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }

        // 关闭生产者
        producer.shutdown();
    }
}


package co.zhanglintc.example;

import co.zhanglintc.common.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Classname Consumer
 * @Description
 * @Date 2025/4/25 16:42
 * @Created by zhanglintc
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 实例化一个消费者，指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group");
        // 设置NameServer地址
        consumer.setNamesrvAddr(Config.NAME_SERVER);
        // 订阅Topic和Tag
        consumer.subscribe(Config.TOPIC, "*");

        // 注册回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("Received message: %s%n", new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者
        consumer.start();

        System.out.println("Consumer Started.");
    }
}

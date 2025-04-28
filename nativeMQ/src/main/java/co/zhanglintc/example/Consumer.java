package co.zhanglintc.example;

import co.zhanglintc.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

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
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Config.CONSUMER_GROUP_NATIVE);
        consumer.setNamesrvAddr(Config.NAME_SERVER);
        consumer.subscribe(Config.TOPIC, Config.TAG_NATIVE);
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 注册回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String tag = msg.getTags();
                    String body = new String(msg.getBody());
                    log.info("Received message, tag: {}, body: {}", tag, body);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者
        consumer.start();

        log.info("Consumer Started.");
    }
}

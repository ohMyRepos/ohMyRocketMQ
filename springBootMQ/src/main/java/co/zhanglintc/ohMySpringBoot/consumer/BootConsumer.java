package co.zhanglintc.ohMySpringBoot.consumer;

import co.zhanglintc.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * @Classname BootConsumer
 * @Description
 * @Date 2025/4/27 16:43
 * @Created by zhanglintc
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = Config.TOPIC, consumerGroup = Config.CONSUMER_GROUP)
public class BootConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(MessageExt msg) {
        String topic = msg.getTopic();
        String tags = msg.getTags();
        String body = new String(msg.getBody());
        log.info("Received message, topic: {}, tags: {}, body: {}", topic, tags, body);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setNamesrvAddr(Config.NAME_SERVER);
    }
}

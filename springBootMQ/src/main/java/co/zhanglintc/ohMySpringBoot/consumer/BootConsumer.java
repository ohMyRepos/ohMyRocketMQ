package co.zhanglintc.ohMySpringBoot.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Classname BootConsumer
 * @Description
 * @Date 2025/4/27 16:43
 * @Created by zhanglintc
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "consumer_group")
public class BootConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        log.info("Received message: {}", new String(message.getBody()));
    }
}

package co.zhanglintc.ohMySpringBoot.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname BootProducer
 * @Description
 * @Date 2025/4/27 17:27
 * @Created by zhanglintc
 */
@Slf4j
@Component
public class BootProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessageWithTag(String topic, String tag, String body) {
        String destination = topic + ":" + tag; // 格式：topic:tag
        rocketMQTemplate.convertAndSend(destination, body);
        log.info("Sent message, topic: {}, tag: {}, body: {}", topic, tag, body);
    }
}

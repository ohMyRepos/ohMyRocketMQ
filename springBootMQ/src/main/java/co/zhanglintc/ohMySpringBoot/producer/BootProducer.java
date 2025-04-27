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

    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
        log.info("Message sent: {}", message);
    }

    public void sendMessageWithTag(String topic, String tag, String message) {
        String destination = topic + ":" + tag; // 格式：topic:tag
        rocketMQTemplate.convertAndSend(destination, message);
        log.info("Message sent: {}", message);
    }
}

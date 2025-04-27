package co.zhanglintc.ohMySpringBoot.config;


import co.zhanglintc.util.Config;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RocketMQConfig
 * @Description
 * @Date 2025/4/27 17:41
 * @Created by zhanglintc
 */
@Configuration
public class RocketMQConfig {

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        DefaultMQProducer producer = new DefaultMQProducer(Config.PRODUCER_GROUP);
        producer.setNamesrvAddr(Config.NAME_SERVER);

        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(producer);

        return rocketMQTemplate;
    }
}

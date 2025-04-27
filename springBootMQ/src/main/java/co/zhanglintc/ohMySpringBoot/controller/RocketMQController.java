package co.zhanglintc.ohMySpringBoot.controller;

import cn.hutool.core.util.RandomUtil;
import co.zhanglintc.ohMySpringBoot.producer.BootProducer;
import co.zhanglintc.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname RocketMQController
 * @Description
 * @Date 2025/4/27 17:31
 * @Created by zhanglintc
 */
@RestController
public class RocketMQController {

    @Autowired
    private BootProducer bootProducer;

    @GetMapping("/send")
    public String sendMessage() {
        String message = RandomUtil.randomString(20);
        bootProducer.sendMessage(Config.TOPIC, message);
        return message;
    }
}
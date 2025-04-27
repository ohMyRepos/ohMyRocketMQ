package co.zhanglintc;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 */
@Slf4j
public class ohMyRocketMQ {
    public static void main(String[] args) {
        log.info("start: Hello World!");
        System.out.println(ohMyRocketMQ.class.getClassLoader().getResource(""));
        System.out.println(ohMyRocketMQ.class.getResource(""));
        log.info("end: Hello World!");
    }
}
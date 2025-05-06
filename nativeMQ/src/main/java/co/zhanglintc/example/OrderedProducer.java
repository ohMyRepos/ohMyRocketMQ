package co.zhanglintc.example;

import co.zhanglintc.util.Config;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @Classname OrderedProducer
 * @Description
 * @Date 2025/4/28 17:34
 * @Created by zhanglintc
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        // 创建生产者实例
        DefaultMQProducer producer = new DefaultMQProducer(Config.PRODUCER_GROUP);
        producer.setNamesrvAddr(Config.NAME_SERVER); // 设置 NameServer 地址
        producer.start();

        // 发送顺序消息
        for (int i = 0; i < 10; i++) {
            // 创建消息，指定 Topic 和消息体
            Message msg = new Message(Config.TOPIC, Config.TAG_NATIVE, ("Ordered Message " + i).getBytes());

            // 发送消息，使用 MessageQueueSelector 选择队列
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    // 根据业务逻辑选择队列，这里使用订单 ID 作为选择依据
                    long orderId = (long) arg;
                    int index = (int) (orderId % mqs.size());
                    return mqs.get(index);
                }
            }, 12345L); // 12345L 是订单 ID，用于选择队列

            System.out.println("Send result: " + sendResult);
        }

        // 关闭生产者
        producer.shutdown();
    }
}
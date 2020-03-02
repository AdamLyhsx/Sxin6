package com.adamly.xin6.listener;


import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/12/10 19:39
 */
//redis消息监听器
@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern ){
        String body = new String(message.getBody());
        String topic=new String(pattern);
        System.out.println(body+"-redisMessage-"+topic);
    }

}

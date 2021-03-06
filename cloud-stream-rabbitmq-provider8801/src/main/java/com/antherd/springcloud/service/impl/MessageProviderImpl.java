package com.antherd.springcloud.service.impl;

import com.antherd.springcloud.service.IMessageProvider;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class) // 定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

  @Resource
  private MessageChannel output; // 消息发送管道，对应yml配置中binding下的通道名称

  @Override
  public String send() {
    String serial = UUID.randomUUID().toString();
    output.send(MessageBuilder.withPayload(serial).build());
    log.info("***** serial：" + serial);
    return null;
  }
}

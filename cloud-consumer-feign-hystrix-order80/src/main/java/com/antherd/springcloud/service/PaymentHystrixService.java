package com.antherd.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentHystrixService {

  @GetMapping("/payment/hystrix/ok/{id}")
  String paymentInfoOk(@PathVariable("id") Integer id);

  @GetMapping("/payment/hystrix/timeout/{id}")
  String paymentInfoTimeout(@PathVariable("id") Integer id);
}

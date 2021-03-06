package com.antherd.springcloud.alibaba.controller;

import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.antherd.springcloud.alibaba.handler.CustomerBlockHandler;
import com.antherd.springcloud.entities.CommonResult;
import com.antherd.springcloud.entities.Payment;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class RateLimitController {

  @GetMapping("/byResource")
  @SentinelResource(value = "byResource", blockHandler = "handleException")
  public CommonResult byResource() {
    return new CommonResult(HttpStatus.HTTP_OK, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
  }

  public CommonResult handleException(BlockException exception) {
    return new CommonResult(444, exception.getClass().getCanonicalName() + " 服务不可用");
  }

  @GetMapping("/rateLimit/byUrl")
  @SentinelResource(value = "byUrl")
  public CommonResult byUrl() {
    return new CommonResult(HttpStatus.HTTP_OK, "按url限流测试OK", new Payment(2020L, "serial002"));
  }

  @GetMapping("/rateLimit/customerBlockHandler")
  @SentinelResource(value = "customerBlockHandler",
      blockHandlerClass = CustomerBlockHandler.class,
      blockHandler = "handlerException2")
  public CommonResult customerBlockHandler() {
    return new CommonResult(HttpStatus.HTTP_OK, "按客户自定义", new Payment(2020L, "serial003"));
  }
}

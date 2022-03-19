package com.example.springboot.actuator.config;

//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
//import org.springframework.stereotype.Component;
//import reactor.core.CoreSubscriber;
//import reactor.core.publisher.Mono;

//ReactiveHealthIndicator提供了一个非阻塞的合同来获取应用程序运行状况。与传统的HealthIndicator类似，
// 健康信息是从a的内容中收集的 （默认情况下，在ApplicationContext中定义的所有 和 实例。
// 不检查反应API的常规HealthIndicator是在弹性上执行的调度。
// ReactiveHealthIndicatorRegistryHealthIndicator ReactiveHealthIndicator
//    在响应式应用程序中，ReactiveHealthIndicatorRegistry可用于在运行时注册和取消注册运行状况指示器。
//    @Override
//@Component
//public class MyReactiveHealthIndicator implements ReactiveHealthIndicator {
//
//
//
//    public Mono<Health> health() {
//        return doHealthCheck() //perform some specific health check that returns a Mono<Health>
//                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()));
//    }
//
//    private Mono<Health> doHealthCheck() {
//        Mono<Health> mono = new Mono<Health>() {
//            @Override
//            public void subscribe(CoreSubscriber coreSubscriber) {
//
//            }
//        };
//        return mono;
//    }
//}

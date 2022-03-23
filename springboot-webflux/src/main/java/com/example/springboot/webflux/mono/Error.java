package com.example.springboot.webflux.mono;

import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.concurrent.CompletableFuture;

/**
 * @author want
 * @createTime 2021.03.24.22:10
 */
public class Error {

    public static void main(String[] args) throws InterruptedException {
        // 我想创建一个错误序列：
        Mono.error(new IllegalArgumentException());
        // 我想创建一个错误序列​替换一个完成的
        Mono.fromSupplier(() -> "str")
                .then(Mono.error(new IllegalArgumentException()));
        // lazy
        Mono.error(IllegalArgumentException::new);
        // 我想要类似 try/catch 的表达方式：然后返回缺省值：
        Mono.error(IllegalArgumentException::new)
                .onErrorReturn("error")
                .subscribe(System.out::println);
        // 我想要类似 try/catch 的表达方式： 然后返回一个 Mono
        Mono.error(IllegalArgumentException::new)
                .onErrorResume(ex -> Mono.just("然后返回一个Mono"))
                .subscribe(System.out::println);
        // 我想要类似 try/catch 的表达方式：finally 代码块：
        Mono.error(IllegalArgumentException::new)
                .doFinally(signalType -> {
                    if(signalType == SignalType.ON_ERROR){
                        System.out.println("SignalType on error!");
                    }
                });

        //我想从错误中恢复…​默认值
        Mono.error(IllegalArgumentException::new)
                .onErrorReturn("error")
                .subscribe(System.out::println);

        // 我想从错误中恢复…​ 另一个Mono
        Mono.error(IllegalArgumentException::new)
                .onErrorResume(ex -> Mono.just("我想从错误中恢复…\u200B 另一个Mono"))
                .subscribe(System.out::println);

        // 我想从错误中恢复…​ 重试
//        Mono.error(IllegalArgumentException::new)
//                .retry();
        Mono.error(IllegalArgumentException::new)
                .retry(2);
//        Mono.error(IllegalArgumentException::new)
//                .retry(2,ex -> ex instanceof IllegalArgumentException);

        Mono.fromFuture(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "v1";
        }))
                .map(str -> {
                    if(true){
                        throw new RuntimeException("excption");
                    }
                    return str;
                }).doOnNext(System.out::println)
                .onErrorMap(RuntimeException::new)
                .zipWith(Mono.fromFuture(CompletableFuture.supplyAsync(() ->{
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "v2";
                })).doOnNext(System.out::println).onErrorMap(RuntimeException::new)).subscribe(tuples -> System.out.println("tuples.toString() = " + tuples.toString()));

        Thread.sleep(3000);
    }

}

package com.example.SpringWebFlux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class HelloReactorEx {
    public static void main(String[] args) throws InterruptedException {
        Flux // Reactor Sequence의 시작점 Flux로 시작한다는 것은 Reactor Sequence가 여러 건의 데이터를 처리함을 의미
                .just("Hello", "Reactor") // 원본 데이터 소스로부터 데이터를 emit하는 Publisher
                .map(message -> message.toUpperCase()) // Publisher로부터 전달받은 데이터를 가공하는 Operator
                .publishOn(Schedulers.parallel()) // 스레드 관리자 역할을 하는 Scheduler를 지정하는 Operator
                .subscribe(System.out::println, // 세 개의 파라미터를 가진다. 첫 번째는 Publisher가 emit한 데이터를 전달받아 처리한다.
                        error -> System.out.println(error.getMessage()), // 두 번째는 Reactor Sequence 상에서 에러 발생할 경우 에러 처리, 세 번째는 Reactor Sequence가 종료된 후 어떤 후처리를 한다.
                        () -> System.out.println("# onComplete"));
        Thread.sleep(100L);

    }
}

package com.example.SpringWebFlux.schedulers;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulersEx01 {
    public static void main(String[] args) throws InterruptedException {
        // Scheduler를 적용하지 않은 경우
        // main 스레드에서 실행된다.
        Flux
                .range(1, 10)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .subscribe(data -> log.info("# onNext: {}", data));

        // subdscribeOn()을 사용해 Scheduler를 추가한 경우
        Flux
                .range(1, 10)
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe")) // 여기서부터 boundedElastic 스레드에서 실행된다.
                .subscribeOn(Schedulers.boundedElastic()) // 구독 직후 실행되는 Operator 체인의 실행 스레드를 Scheduler에서 지정한 스레드로 변경한다.
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
    }



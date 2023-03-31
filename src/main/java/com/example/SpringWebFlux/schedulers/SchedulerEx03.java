package com.example.SpringWebFlux.schedulers;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerEx03 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(1, 10)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))

                // publishOn을 추가할 때마다 추가한 PublishOn()을 기준으로 DownStream쪽으로 스레드가 PublishOn에서 Scheduler로 지정한 스레드로 변경된다.
                .publishOn(Schedulers.parallel())
                .filter(n -> n % 2 == 0)
                // doOnNext()는 doOnNext()바로 앞에 위치한 Operator가 실행될 때 트리거 되는 Operator
                .doOnNext(data -> log.info("# filter doOnNext")) // 2

                .publishOn(Schedulers.parallel())
                .map(n -> n * 2)
                .doOnNext(data -> log.info("# map doOnNext"))

                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(100L);

    }
}

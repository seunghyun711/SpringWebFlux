package com.example.SpringWebFlux;

import reactor.core.publisher.Flux;

public class MarbleDiagramEx {
    public static void main(String[] args) {
        Flux
                .just("Purple-car", "Black-car", "Blue-car") // 세 개의 문자열을 emit
                .map(figure -> figure.replace("car", "color")) // map() Operator 내부에서 car를 color로 변경
                .subscribe(System.out::println);
    }
}

package main.java;

public class BuildTool {
    /*
    === 자바 빌드 도구 (Gradle VS Maven) ===

    1) Gradle ( 빌드 스크립트 형식: build.gradle || build.gradle.kts )
    - 2007년 출시
    - Groovy / Kotlin DSL 기반 언어
    : 선언 + 명령형 방식
    : 매우 유연 (스크립트 작성 가능)

    2) Maven ( 빌드 스크립트 형식: pom.xml )
    - 2004년 출시
    - XML 기반 언어
    : 완전 선언형 방식
    : 상대적으로 제한적

    cf) 성능 (속도) 비교
    1) Gradle
        : 초기 빌드 속도가 느림 (캐시가 없을 경우)
        - 재빌드 속도가 빠름 (빌드 캐시 지원)
    2) Maven
        : 초기 빌드 속도가 일반적인 수준
        - 항상 전체 빌드 위주

    >> Gradle은 변경된 부분만 빌드하는 증분 빌드 및 캐시 기능 덕분에
        '지속적인 개발 환경에서 매우 빠름'

    cf) 생태계 및 커뮤니티
    - Spring Boot에서의 사용
        : 공식 가이드가 Gradle 중심
        : Maven 사용량은 오랜 기간 사용으로 높고, 안정적인 자료가 많음

    cf) 결론 요약
    - 빠른 빌드 속도가 중요: Gradle
    - 표준화된 빌드 프로세스 필요: Maven
    - 자유로운 스크림팅 필요: Gradle
    - 보수적 기업 환경: Maven (안정성, 예측 가능성)


    +) Groovy VS Kotlin DSL
    Groovy
    : 동적 타입 기반 스크립트 언어 (build.gradle)
    - 타입 안전 부족, 자동완성 어려움

    Kotlin DSL
    : 정적 타입 기반 정식 언어
    - 학습 곡선 있음, 문서가 비교적 적음

     */
}

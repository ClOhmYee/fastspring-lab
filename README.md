# fastspring-lab

## 프로젝트 목표
    
    1. 단일 책임 원칙을 최대한 준수하며 Spring Boot 서버와 FastAPI 서버를 구축한다.
    
    2. Spring Boot 서버와 FastAPI 서버 간 API 통신을 구현한다.

    3. 단순 구현만이 아닌 REST API 개념을 이해하고 적용해본다.

## 구현 기능

    Spring Boot 서버에서 메세지를 FastAPI 서버로 메세지와 함께 POST 요청을 전송하고,
    FastAPI 서버에서는 전달받은 메세지를 별도의 prefix와 함께 다시 Spring Boot 서버로 전송

## 본 테스트 진행 방법

    (본 프로젝트는 WINDOWS 환경에서 진행되었음)
    편의상 FastAPI 서버를 구동하기 위한 가상 환경 파일이 포함되어 있으므로,
    test.bat 스크립트를 실행하여 자동 테스트 진행

## 진행 환경

    Spring Boot : 3.5.7
    Gradle : 8.14.3
    JDK : 21
    Python : 3.10.11

## 프로젝트 구조

```
fastspring-lab/
├── docs/
│   ├── fastapi-design.md
│   └── spring-boot-design.md
├── servers/
│   ├── fastapi/
│   │   ├── app/
│   │   │   ├── __init__.py
│   │   │   ├── config.py
│   │   │   ├── main.py
│   │   │   └── routers/
│   │   │       ├── __init__.py
│   │   │       └── receive.py
│   │   ├── tests/
│   │   │   ├── __init__.py
│   │   │   └── test_main.py
│   │   ├── pytest.ini
│   │   ├── requirements.txt
│   │   └── venv/
│   └── spring-boot/
│       └── spring/
│           ├── src/
│           │   ├── main/
│           │   │   ├── java/
│           │   │   │   └── com/domain/spring/
│           │   │   │       ├── Application.java
│           │   │   │       ├── dto/
│           │   │   │       │   └── MessageDto.java
│           │   │   │       ├── fastapi/
│           │   │   │       │   ├── controller/
│           │   │   │       │   │   └── FastApiController.java
│           │   │   │       │   └── service/
│           │   │   │       │       └── FastApiService.java
│           │   │   │       └── main/
│           │   │   │           └── controller/
│           │   │   │               └── MainController.java
│           │   │   └── resources/
│           │   │       └── application.properties
│           │   └── test/
│           │       └── java/
│           │           └── com/domain/spring/
│           │               └── ApplicationTests.java
│           ├── build.gradle
│           ├── settings.gradle
│           ├── gradlew
│           ├── gradlew.bat
│           └── gradle/
│               └── wrapper/
│                   ├── gradle-wrapper.jar
│                   └── gradle-wrapper.properties
├── test.bat
├── LICENSE
└── README.md
```

## 학습 내용

* 단일 책임 원칙 적용

    최대한 각 클래스가 하나의 책임만을 가질 수 있도록 구조를 구성

* 계층별 구조 채택

    책임을 더 명확하게 나타낼 수 있도록 기능별 구조가 아닌 계층별 구조를 채택해 구성

    간단한 메세지를 구현하는 기능만 포함되어 있기에 구조 간 큰 차이는 없으나, 단일 책임 원칙에 도움이 되는 구조라고 판단

* REST API 이해 및 구현

    REST API 원칙을 이해하고 두 서버 간 간단한 통신을 구현

    - GET : 리소스 조회
    - POST : 리소스 전송

* Spring Boot 및 FastAPI 서버 구성

    개념을 활용하여 직접 서버를 구현하며 세부적인 동작 방식을 이해
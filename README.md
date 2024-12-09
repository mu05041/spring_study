# spring_study
## 목차
- [Spring에서의 Configuration과 Bean](#1)
- [java bean과 spring bean의 차이점](#2)
- [Bean Factory와 어플리케이션 컨텍스트 차이](#3)
- [Spring에서의 의존성 주입 방식](#4)
- [전체적인 Spring의 작동방식](#5)
- [Lazy Initializition vs Eager Initialization](#6)
- [스프링 싱글톤](#7)
- [PostConstruct vs PreDestroy](#8)
- [스프링의 어노테이션](#9)



<a id="1"></a>

## Spring에서의 Configuration과 Bean
  Django 비유:

  @Configuration = settings.py/apps.py 같은 설정파일 역할

  @Bean = INSTALLED_APPS에 앱을 등록하는 것과 비슷

  Django: settings.py에 앱 등록
  Spring: @Bean으로 객체 등록
  차이점:

  Django는 설정파일에 앱 이름만 등록
  Spring은 @Bean으로 객체 생성 로직까지 정의


<a id="2"></a>

## java bean과 spring bean의 차이점
Java Bean:

모든 필드가 private (캡슐화)
기본 생성자 필수
getter/setter 메소드로 접근
단순한 데이터 객체

Spring Bean:

스프링 컨테이너가 생성/관리하는 객체
기본 생성자 필수 아님
getter/setter 필수 아님
비즈니스 로직 포함 가능
@Bean, @Component 등으로 등록



<a id="3"></a>

## Bean Factory와 어플리케이션 컨텍스트 차이

BeanFactory:

단순한 DI 컨테이너
Bean 객체를 요청할 때 생성 (Lazy loading)
기본적인 Bean 생성/관리 기능만 제공

ApplicationContext:

BeanFactory의 확장 버전
REST API, 마이크로 서비스 같은 웹 서비스에선 애플리케이션 컨텍스트가 권장됨

실무에서는 대부분 ApplicationContext 사용

<a id="4"></a>

## Spring에서의 의존성 주입 방식
스프링에서 의존성을 주입하는 주요 방식은 3가지가 있습니다:

필드 주입 (Field Injection)

```java
javaCopy@Autowired
private Dependency1 dependency1;
```
생성자 주입 (Constructor Injection)

``` java
javaCopyprivate final Dependency1 dependency1;

@Autowired
public YourBusinessClass(Dependency1 dependency1) {
    this.dependency1 = dependency1;
}
```

setter 주입 (Setter Injection)

``` java
javaCopy@Autowired
public void setDependency1(Dependency1 dependency1) {
    this.dependency1 = dependency1;
}
```

이 코드를 실행하면 "setterInjection1"과 "setterInjection2"가 출력되는 것을 볼 수 있는데, 이는 스프링이 setter 메서드를 통해 의존성을 주입하는 과정을 보여줍니다.
참고로, 최근의 스프링 개발에서는 생성자 주입 방식을 가장 권장한다. 그 이유는:

필수적인 의존성을 명확히 표현할 수 있음
불변성(immutability) 보장
단위 테스트가 용이함


<a id="5"></a>

## 전체적인 Spring의 작동방식
```java
// 커피 제조 인터페이스
public interface CoffeeMaker {
    void makeCoffee();
}

// 아메리카노 제조 클래스
public class AmericanoCoffeeMaker implements CoffeeMaker {
    @Override
    public void makeCoffee() {
        System.out.println("아메리카노를 만듭니다.");
    }
}

// 라떼 제조 클래스
public class LatteCoffeeMaker implements CoffeeMaker {
    @Override
    public void makeCoffee() {
        System.out.println("라떼를 만듭니다.");
    }
}

// 카페 클래스 (의존성 주입을 받는 클래스)
public class Cafe {
    private final CoffeeMaker coffeeMaker;
    
    // 생성자 주입
    @Autowired
    public Cafe(CoffeeMaker coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }
    
    public void orderCoffee() {
        coffeeMaker.makeCoffee();
    }
}

// 스프링 설정 클래스
@Configuration
public class CafeConfig {
    
    @Bean
    public CoffeeMaker americanoCoffeeMaker() {
        return new AmericanoCoffeeMaker();
    }
    
    @Bean
    public CoffeeMaker latteCoffeeMaker() {
        return new LatteCoffeeMaker();
    }
    
    @Bean
    public Cafe cafe(CoffeeMaker americanoCoffeeMaker) {
        return new Cafe(americanoCoffeeMaker);
    }
}
```
###느슨한 결합(Loose Coupling)

Cafe 클래스는 구체적인 커피 제조 클래스(AmericanoCoffeeMaker, LatteCoffeeMaker)가 아닌 CoffeeMaker 인터페이스에 의존합니다.
이렇게 하면 나중에 새로운 커피 종류를 추가하거나 변경할 때 Cafe 클래스를 수정할 필요가 없습니다.


###스프링 빈(Spring Bean)

@Bean 어노테이션이 붙은 메소드들이 스프링 빈을 생성합니다.
americanoCoffeeMaker(), latteCoffeeMaker(), cafe() 메소드가 각각 스프링 빈을 생성합니다.
스프링 컨테이너가 이러한 빈들을 관리하고 필요한 곳에 주입해줍니다.


###의존성 주입(Dependency Injection)

Cafe 클래스는 생성자를 통해 CoffeeMaker를 주입받습니다.
스프링이 자동으로 설정된 빈을 주입해주므로, 직접 객체를 생성하고 관리할 필요가 없습니다.



이렇게 설계하면 다음과 같은 장점이 있습니다:

코드 변경이 쉽습니다 (새로운 커피 종류 추가가 용이함)
테스트하기 쉽습니다 (Mock 객체를 주입하기 쉬움)
코드 재사용성이 높아집니다

<a id="6"></a>

## Lazy 초기화 vs Eager(즉시) 초기화
Bean을 필요할때만 불러서 쓰는거-Lazy 초기화
거의 모든 Bean에 관장되는 사용방식- Eager


<a id="7"></a>

## 스프링 싱글톤
### 싱글톤이란?

스프링 컨테이너에서 생성되는 빈(Bean)의 기본 스코프입니다
애플리케이션이 구동되는 동안 단 하나의 객체 인스턴스만 생성되어 공유됩니다

### 주요 특징:

인스턴스 생성 시점


스프링 컨테이너가 초기화될 때 싱글톤 빈이 생성됩니다
애플리케이션 구동 중에는 항상 같은 인스턴스가 반환됩니다


### 장점
메모리 사용을 효율적으로 관리할 수 있습니다
여러 요청에서 동일한 객체를 공유하므로 성능상 이점이 있습니다
전역적인 상태를 가질 수 있습니다

<a id="8"></a>

## PostCOnstruct vs PreDestroy

### @PostConstruct:

빈(Bean) 생성자가 실행된 후, 의존성 주입이 완료된 직후에 실행됩니다
빈이 초기화되는 시점에 필요한 설정을 수행할 때 사용합니다
예를 들어 데이터베이스 연결, 초기 데이터 로드, 캐시 워밍업 등의 작업에 활용됩니다
```java
@Component
public class MyService {
    @PostConstruct
    public void init() {
        // 빈 초기화 후 실행될 로직
        System.out.println("서비스 초기화 완료");
    }
}
```

### @PreDestroy:

빈이 소멸되기 직전에 실행됩니다
주로 애플리케이션 종료 시 필요한 정리 작업을 수행할 때 사용합니다
리소스 해제, 연결 종료, 임시 파일 삭제 등의 작업에 활용됩니다

```java
@Component
public class MyService {
    @PreDestroy
    public void cleanup() {
        // 빈 소멸 전 실행될 로직
        System.out.println("서비스 종료 전 정리 작업");
    }
}
```

<a id="9"></a>

## 스프링의 어노테이션

- @Component 어노테이션한 클래스가 컴포넌트임을 알림
- @Service 보통 클래스에 비즈니스 논리가 있음을 나타냄
- @Controller 웹 컨트롤러와 같이 컨트롤러클래스인 경우에 사용
- @Repository Bean이 데이터베이스와 통신하는경우, 데이터를 저장하거나 검색하거나 조작하는경우
- @Configuration Java의 설정파일을 만드는것 @Bean어노테이션을 추가할 수 있다. 스프링이
메서드에 반환되는 모든 값에 Bean을 자동으로 생성한다.
- @ComponentScan 특정패키지를 스캔함
- @Primary Bean이 단일 값 의존성에 자동 연결될 후보일때 Bean에 우선순위를 지정해줌
- @Qualifier 특별한상황에 Bean을 자동연결할수있게해줌. 아주 구체적.
- @Lazy Bean을 사용할때만 초기화하게 하려고 씀
- Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE) 특정 컴포넌트에 프로토타입 스코프를 정의할 수 있음
빈을 프로토타입으로 정의함. 여기서 프로토타입이란 Bean을 참조할때마다 인스턴스가 새로 만들어진다는 뜻. 
- @PostConstruce 의존성주입이 수행될 이후 초기화를 위해 실행될 메서드를 나타냄
- @PreDestroy 컨테이너에서 인스턴스를 삭제하는 과정을 거치고 있음을 알려줌
- @Name Jakart Context & Dependency Injection 에서 @Component와 비슷하게 씀


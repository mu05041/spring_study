# spring_study
## 목차
- [Spring에서의 Configuration과 Bean](#1)
- [java bean과 spring bean의 차이점](#2)
- [Bean Factory와 어플리케이션 컨텍스트 차이](#3)
- [Spring에서의 의존성 주입 방식](#4)


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

`javaCopy@Autowired
private Dependency1 dependency1;
`
생성자 주입 (Constructor Injection)

`javaCopyprivate final Dependency1 dependency1;

@Autowired
public YourBusinessClass(Dependency1 dependency1) {
    this.dependency1 = dependency1;
}`

setter 주입 (Setter Injection)

`javaCopy@Autowired
public void setDependency1(Dependency1 dependency1) {
    this.dependency1 = dependency1;
}`

이 코드를 실행하면 "setterInjection1"과 "setterInjection2"가 출력되는 것을 볼 수 있는데, 이는 스프링이 setter 메서드를 통해 의존성을 주입하는 과정을 보여줍니다.
참고로, 최근의 스프링 개발에서는 생성자 주입 방식을 가장 권장합니다. 그 이유는:

필수적인 의존성을 명확히 표현할 수 있음
불변성(immutability) 보장
단위 테스트가 용이함
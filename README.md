# spring_study
## 목차
- [Spring에서의 Configuration과 Bean](#1)
- [java bean과 spring bean의 차이점](#2)
- [Bean Factory와 어플리케이션 컨텍스트 차이](#usage)


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
시작 시점에 모든 Bean 생성 (Eager loading)
추가 기능:

국제화(i18n)
이벤트 발행/구독
AOP 기능
웹 애플리케이션 컨텍스트


실무에서는 대부분 ApplicationContext 사용
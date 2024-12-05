# spring_study

## Spring에서의 Configuration과 Bean
  Django 비유:

  @Configuration = settings.py/apps.py 같은 설정파일 역할

  @Bean = INSTALLED_APPS에 앱을 등록하는 것과 비슷

  Django: settings.py에 앱 등록
  Spring: @Bean으로 객체 등록
  차이점:

  Django는 설정파일에 앱 이름만 등록
  Spring은 @Bean으로 객체 생성 로직까지 정의

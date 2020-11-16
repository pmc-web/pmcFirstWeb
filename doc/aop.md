# Spring AOP Logger

해당 문서는 Spring 프로젝트 팀원들에게 AOP를 사용하는 방법과 AOP를 이용하여 추가한 Request Log를 어떻게 개발하는지 설명하기 위한 가이드 문서입니다.

---

## AOP란

AOP는 Aspect Oriented Programming의 약자로, 여러 객체에 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍입니다. 

Spring에서는 주로 Logging, Caching, Auditing, Perfomance monitoring 등에 주로 사용되며, 런타임 시에 프록시 객체를 생성하여 공통 기능을 삽입하는 방식으로 동작합니다.

## AOP 주요 용어와 어노테이션

- Aspect: 여러 객체에 공통으로 적용되는 기능. 트랜젝션, 보안 등
- Advice: 언제 공통로직을 핵심 로직에 적용할지를 정의하는 것.
- JointPoint: Advice를 적용 가능한 지점으로, 스프링은 프록시를 이용하여 AOP를 구현하기 때문에 메서드 호출에 대한 JointPoint를 지원합니다.
- PointCut: JoinPoint의 부분 집합으로, 실제 Advice가 적용되는 JoinPoint를 나타냅니다. 스프링은 정규식이나 Aspect 문법을 이용하여 Pointcut에 정의할 수 있습니다.

---

## Spring AOP 구현하기

1. Aspect로 사용할 클래스에 @Aspect 어노테이션을 붙인다.
2. @Pointcut 어노테이션으로 공통 기능을 적용할 PointCut을 정의한다.
3. 공통 기능을 구현한 메서드에 @Around 어노테이션을 사용한다.

`Around: Around Advice를 줄여 @Around 어노테이션을 붙입니다. 대상 객체(Controller나 Service 등)의 메서드 실행 전, 후 또는 익셉션 발생 시점에 공통 기능을 실행하는데 사용 가능합니다. (그냥 모든 부분에 사용 가능해서 스프링에서는 일반적으로 @Around 어노테이션을 사용합니다.)`

---

## Spring에서 AOP Logger 추가하기

1. Application 혹은 Config파일에 @EnableAspectJAutoProxy 어노테이션 추가하기

```java
@EnableAspectJAutoProxy
@SpringBootApplication
public class PmcwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmcwebApplication.class, args);
	}
}
```

2. Aspect 파일 만들기 (@PointCut과 @Around 포함)

```java
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.bootproj.pmcweb.Controller..*)")
    public void onRequest() {
    }

    @Around("com.bootproj.pmcweb.Network.Aspect.LoggingAspect.onRequest()")
    public Object requestLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        long start = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            logger.info("Request: {} {}: {} ({}ms)", request.getMethod(), request.getRequestURL(), paramMapToString(request.getParameterMap()), end - start);
        }
    }

    private String paramMapToString(Map<String, String[]> paraStringMap) {
        return paraStringMap.entrySet().stream()
                .map(entry -> String.format("%s : %s",
                        entry.getKey(), Arrays.toString(entry.getValue())))
                .collect(Collectors.joining(", "));
    }
}
```

`@PointCut("within(com.bootproj.pmcweb.Controller..*)")`
  - onRequest메서드에 Controller 패키지 내의 모든 파일에 대해 PointCut으로 정의하겠다는 설정

`@Around("com.bootproj.pmcweb.Network.Aspect.LoggingAspect.onRequest()")`
  - 위에서 PointCut으로 정의한 onRequest 파일들에 대해 @Around시점(메서드 호출 시점)에 requestLogging 메서드를 실행하겠다는 내용

`proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs()`
  - 실제 객체 (Controller의 메서드)를 호출하는 부분입니다. 공통 로직에서 하고 싶은 행동은 proceed 전후에 정의합니다. 위 requestLogging 메서드에서는 메서드 전후에 시간을 기록하는 것과 request 값에 대해 로그를 남기는 작업을 수행했습니다.


---

## 참고자료

https://www.javaguides.net/2019/05/spring-boot-spring-aop-logging-example-tutorial.html

https://www.javaguides.net/2019/05/spring-boot-spring-aop-logging-example-tutorial.html

스프링5 프로그래밍 입문 책 (저자: 최범균)



추가로 궁금하신 점이나 수정해야 할 점 있으면 언제든 말씀해 주세요!
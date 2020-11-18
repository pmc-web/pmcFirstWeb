# Spring Boot - Swagger 사용하기

해당 문서는 Spring Boot 프로젝트에 Swagger를 연동하는 방법에 대한 가이드 입니다.

---

## Swagger란

간단한 설정을 통해 프로젝트에서 지정한 URL들을 HTML 화면으로 보여주는 툴 입니다.

API Spec 문서를 자동화 하여 개발자간 협업 및 사용자에게 공유하기 위해 사용합니다.

---

## Swagger 연동하기

1. Gradle에 Swagger Depenncy 추가

```java
compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
```

2. Swagger Config 작성

```java
@Configuration
@EnableSwagger2
public class SwaggerConfiguraton {

    @Autowired
    private Environment env;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bootproj.pmcweb.Controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build();

    }

    private Set<String> getConsumeContentTypes(){
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes(){
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    private ApiInfo getApiInfo(){
        Properties properties = new Properties();
        return new ApiInfoBuilder()
                .title("API")
                .description("[PMC]API")
                .contact(new Contact("PMC Swagger", "https://github.com/kymkyj/pmcFirstWeb", env.getProperty("email.admin.account")))
                .version("2.0")
                .build();
    }
}
```

3. http://localhost:8080/swagger-ui.html 접속하여 확인

---

## 참고자료

https://victorydntmd.tistory.com/341
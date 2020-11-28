# Junit 5 테스트 코드 작성 가이드

해당 문서는 Junit 5 테스트 코드 작성 가이드에 관한 문서입니다. Junit 4와 Junit 5의 차이점을 알아보고 Junit5 유저 가이드를 공부하며 제대로 테스트코드를 작성하기 위해 만들어진 문서입니다.


---

## Junit 5 특징

`JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage`

Junit4가 단일 jar였던 것에 반해, Junit5는 Junit Platform, Junit Jupiter, Junit Vintage 모듈 세 가지로 구성되어 있습니다.

 - `Junit Platform`
     - JVM에서 동작하는 테스트 프레임워크입니다. 테스트를 발견하고 계획을 생성하고 결과를 보고하는 TestEngine 인터페이스를 정의합니다.
 - `Junit Jupiter`
     - Junit5 TestEngine의 실제 구현체입니다. Junit5 기반의 테스트를 실행시키기 위한 TestEngine을 Platform에 제공합니다.
 - `Junit Vintage`
     - TestEngine에서 Junit3 및 Junit4 기반 테스트를 실행하기 위한 기능을 제공합니다. 

Junit5는 Java 8 이상의 버전을 요구합니다.

---

## Junit 5 기본 테스트 케이스 형식 예시
  ```
  import static org.junit.jupiter.api.Assertions.assertEquals;

  import example.util.Calculator;

  import org.junit.jupiter.api.Test;

  class MyFirstJUnitJupiterTests {

      private final Calculator calculator = new Calculator();

      @Test
      void addition() {
          assertEquals(2, calculator.add(1, 1));
      }

  }
  ```

## Junit 5 Annotation
Junit5에서 제공하는 org.junit.jupiter.api 패키지 내의 어노테이션을 설명합니다.

  - `@Test`
    - 해당 어노테이션을 달아둔 메서드가 테스트 메서드임을 나타냅니다.

  - `@BeforeEach`
    - 각각의 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory 전에 실행됩니다.
  - `@AfterEach`
    - 각각의 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory 후에 실행됩니다.
  - `@BeforeAll`
    - 모든 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory 전에 실행됩니다.
  - `@AfterAll`
    - 모든 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory 후에 실행됩니다.
  - `@ExtendWith`
    - 확장을 선언적으로 등록하는데 사용됩니다. Extendtion 뒤에 인자로 확장할 Extension을 추가하여 사용합니다. 
    - Spring을 사용할 경우 @ExtendWith(SpringExtension.class)와 같이 사용합니다.
  - `@Disabled`
    - 테스트 클래스 또는 테스트 메서드를 비활성화 하는데 사용됩니다.
  - `@ParameterizedTest`
    - 메서드가 매개변수가 있는 테스트임을 나타냅니다.
  - `@RepeatedTest`
    - 메서드가 반복 테스트를 위한 테스트 템플릿임을 나타냅니다.
  - `@TestFactory`
    - 메서드가 동적 테스트를 위한 테스트 팩토리임을 나타냅니다.
  - `@TestMethodOrder`
    - 테스트 메서드 실행 순서를 구성하는데 사용됩니다.
  - `@DisplayName`
    - 테스트 클래스 또는 테스트 메서드에 대한 사용자 지정 표시 이름을 정해줄 때 사용됩니다.

---

## Junit 4 vs Junit 5 (Migration)
Junit5와 Junit4 는 일무 어노테이션에서도 차이가 있습니다. Junit5로 넘어가며 어떤 것이 차이가 있는지 알아보려 합니다. 

이전에 Junit 4 기반으로 작성된 파일들은 위에서 설명했던 것과 같이 junit-vintage-engine이 있다면 자동으로 Junit Platform 런처에서 Junit 3, 4기반 테스트 코드를 선택합니다. 이미 만들어진 Junit 4 파일을 5버전으로 수정할 필요는 없습니다.

  - `Assertion` 의 위치가 Junit5에서는 `org.junit.jupiter.api.Assertions`으로 변경되었습니다. AssertJ, Hamcrest, Trust에서 제공하는 org.junit.Assert는 그대로 사용할 수 있습니다.
  - `@Before`과 `@After` 어노테이션이 사라지고, 각각 `@BeforeEach`와 `@AfterEach`로 변경되었습니다.
  - `@BeforeClass`와 `@AfterClass` 어노테이션이 사라지고, `@BeforeAll`과 `@AfterAll`로 변경되었습니다.
  - `@Ignore`이 사라지고, `@Disabled`로 변경되었습니다.
    - 단, 이 경우 `@EnableJUnit4MigrationSupport` 어노테이션을 붙여줍니다.
  - `@Category`가 사라지고, `@Tag`로 변경되었습니다.
  - `@RunWith`이 사라지고 `@ExtendWith`으로 변경되었습니다.
  - `@Rule`과 `@ClassRule`이 사라지고, `@ExtendWith`과 `@RegisterExtention`으로 대체되었습니다.



---

## Test에 순서 추가하기
  - `@TestMethodOrder(OrderAnnotation.class)`
  - `@Order`

## Junit5 설정하기
1. Junit5 의존성 추가하기 (gradle)
    ```
    dependencies {
      testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.7.0")
      testImplementation('org.junit.jupiter:junit-jupiter:5.7.0')
      testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.0")
    }
    ```
  
    Junit4에 대한 의존성이 필요하지 않다면, junit-vintage-engine 종속성은 제외하고 추가합니다. 만약 spring-boot-starter-test를 사용한다면, 아래와 같이 vintage를 제외합니다.

    ```
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
      exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
    ```

2. test파일에 jupiter 추가해주고 assert 사용하려면 static으로 import 해주기
    ```
    import org.junit.jupiter.api.*;
    import static org.junit.jupiter.api.Assertions.*;
    ```
    junit에서 제공하는 Assertion말고 AssertJ를 사용할 경우 아래와 같이 정의해줍니다. 

    ```
    import org.junit.jupiter.api.*;
    import static org.assertj.core.api.Assertions.*;
    ```

## Mapper Test Code 예시

```
...
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
@SpringBootTest(classes = PmcwebApplication.class) // Junit5 기준 Application Context사용할 때 사용
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order를 붙일 때 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 데이터베이스에 테스트
public class UserMapperTest {
    @Autowired
    private AccountMapper userMapper;
    private static final String testEmail = "test@naver.com";
    private static final String testName = "test";

    @Test
    @Order(1)
    void createUser() {
        Account account = Account.builder()
                .name(testName)
                .email(testEmail)
                .password("1234")
                .status(UserStatus.REGISTERED.getTitle())
                .role(UserRole.NORMAL.getTitle())
                .instTime(new Date(System.currentTimeMillis()))
                .build();

        userMapper.createUser(account);
        Account createdUser = userMapper.getUserByEmail(testEmail);
        assertThat(createdUser.getEmail().equals(account.getEmail()));
    }

    @Test
    @Order(2)
    void getUserByEmail() {
        Account getUser = userMapper.getUserByEmail(testEmail);
        assertThat(testName.equals(getUser.getName()));
    }

    @Test
    @Order(3)
    void getUserList() {
        List<Account> userList = userMapper.getUserList();
        assertThat(userList.size() > 0);
    }

    @Test
    @Order(4)
    void deleteUser() {
        Account getUser = userMapper.getUserByEmail(testEmail);
        assertThat(getUser!=null);
        userMapper.deleteUser(getUser.getId());
        assertThat(userMapper.getUserById(getUser.getId())==null);
    }

}
```

이후 Optional로 변경하면, get하는 곳이 변경될 예정입니다.


---

## Service Test Code 예시
```
...
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private AccountServiceImpl userServiceImpl;
    private static final String testEmail = "test@naver.com";
    private static final String testName = "test";

    @Test
    @Order(1)
    void createUser() {
        Account account = Account.builder()
                .name(testName)
                .email(testEmail)
                .password("1234")
                .status(UserStatus.REGISTERED.getTitle())
                .role(UserRole.NORMAL.getTitle())
                .instTime(new Date(System.currentTimeMillis()))
                .build();
        userServiceImpl.createUser(account);
        Account findUser = userServiceImpl.getUserByEmail(account.getEmail());
        assertThat(account.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    @Order(2)
    void getUser() {
        Account getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(testEmail.equals(getUser.getEmail()));
        assertThat(testName.equals(getUser.getName()));
    }

    @Test
    @Order(3)
    void getUsers() {
        List<Account> userList = userServiceImpl.getUsers();
        assertThat(userList.size() > 0);
    }

    @Test
    @Order(4)
    void deleteUser() {
        Account getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(userServiceImpl.getUserByEmail(testEmail)!=null);
        userServiceImpl.deleteUser(getUser.getId());
        assertThat(userServiceImpl.getUserByEmail(testEmail)==null);
    }

```

## Controller Test Code 예시
```
...
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountSecurityService accountSecurityService;

    @Test
    public void getSignup() throws Exception {
        this.mockMvc.perform(get("/user/signup"))
                .andDo(print())
                .andExpect(result -> "user/register".equals(result))
                .andExpect(status().isOk());
    }
...

```

UserController 테스트 코드는 아직 완성되지 않았다. 일단 시큐리티와 연동되지 않은 것들은 위와 같은 방식으로 테스트가 가능하다.

현재 로그인한 유저 정보(@AuthenticationPrincipal 로 받아온 유저)를 얻어와서 테스트를 해야 하는데 MockUser를 얻어오는 방법을 모르겠다. 그냥 static하게 WithMockUser로 항상 생성되어 있는 유저로 사용해야 하나?

다음에는 시큐리티와 연동하여 Mock 테스트를 하는 것을 알아보자.

---

## 참고 자료
  - Junit 5 유저 가이드
    - https://junit.org/junit5/docs/current/user-guide/
  - Junit 4 -> Junit 5로 마이그레이션 하기
    - https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4
  - Mock으로 Junit5 테스트 코드 작성하기
    - https://spring.io/guides/gs/testing-web/
    
---

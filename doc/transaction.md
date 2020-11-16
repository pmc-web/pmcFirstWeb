# Spring에서 트랜잭션 사용하기

해당 문서는 Spring Boot 프로젝트에서 트랜젝션을 설정하여 롤백하는 방법에 대해 가이드한 문서입니다.

---

## 트랜잭션이란

트랜잭션이란 두 개 이상의 쿼리를 한 작업으로 실행해야 할 때 사용하는 것으로 어떤 쿼리를 논리적으로 하나의 작업으로 묶어줄 필요가 있을 때 사용합니다. 한 트랜잭션으로 묶인 쿼리 중, 하나라도 실패하면 전체 쿼리를 실패로 간주하고 실패 이전에 실행한 쿼리를 취소합니다. 이렇게 쿼리 실행 결과를 취소하고 DB를 기존 상태로 되돌리는 것을 롤백(rollback)이라고 부릅니다. 

아래 예시에서는 유저가 회원가입을 할 때, 유저 정보를 DB에 넣고 나서 이메일을 전송하는 과정에서 Exception이 발생했을 경우, Transaction을 통해 자동으로 롤백해주는 과정을 보여주고 있습니다.

---

## Spring에서 트랜젝션 사용하기

1. DatabaseConfig에 @EnableTransactionManagement 어노테이션 달아서 트랜젝션 허용하기

```java
@EnableTransactionManagement // 트랜젝션 처리를 허용해주는 어노테이션
public class DatabaseConfiguration {
    ...
}
```

2. DatabaseConfig에 transactionManager 빈 설정하기

```java
@Bean
public DataSourceTransactionManager transactionManager(){
    DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
    return manager;
}
```

3. 트랜젝션 사용하려는 곳에 @Transaction 붙여주기
(Exception 발생 시 해당 Exception이 RuntimeException을 상속받지 않았다면, 직접 rollbackFor로 아래와 같이 언급해 주어야 합니다.) 
```java
    @Override
    @Transactional(rollbackFor = SendEmailException.class)
    public User sendSignUpEmail(User user) throws SendEmailException, DuplicateEmailException {
        User insertUser = new User(user.getEmail(), user.getPassword(), user.getName());;

        try {
            // DB에 정보 insert
            createUser(insertUser);
        } catch (Exception e){
            throw new DuplicateEmailException(e.getMessage());
        }

        try {
            // 임의의 authKey 생성 & 이메일 발송
            String authKey = mailSendService.sendAuthMail(user.getEmail());
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", user.getEmail());
            map.put("authKey", authKey);
            updateUserAuthKey(map);
        } catch (Exception e){
            // 메일 전송 실패 시 데이터 롤백
            // 트랜잭션 어노테이션으로 자동 처리
//            deleteUser(insertUser.getId());
            throw new SendEmailException(e.getMessage());
        }
        return null;
    }
```

혹은 아래와 같이 SendEmailException을 Exception이 아닌 RuntimeException을 상속받도록 바꾸어 주면 됩니다.

```java
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "이메일 발송 실패")
public class SendEmailException extends RuntimeException {
    public SendEmailException(String message) {
        super(message);
    }
}
```

위와 같이 트랜젝션을 추가해주면, 위 예시에서는 만약 유저를 생성하여 DB에 넣고 나서 이메일을 보내다가 에러가 발생하게 되면, sendSignUpEmail 서비스가 트랜젝션으로 하나로 묶여있기 때문에 자동으로 롤백하여 생성한 유저를 삭제해줍니다. 따라서, 기존에 try-catch에서 생성한 유저를 삭제하는 로직을 삭제해도 자동으로 처리해줄 수 있게 됩니다.

---

추가 문의 혹은 수정이 필요한 사항이 있으면 연락주세요.
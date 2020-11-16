# Spring 프로젝트 ERD 설계하기

해당 문서는 PMC 스프링 프로젝트에서 ERD를 설계하며 진행했던 내용을 정리한 문서입니다.

---

##  1. 기획에서 필요한 테이블과 컬럼 정리하기

우선 UX 설계한 내용과 기능 리스트를 보며 어떤 Entity가 필요할 지 생각해본다. 스터디 전에 각자 필요한 테이블과 컬럼들이 어떤 것이 있을지 생각해보고, 회의에서는 [ERD Cloud](https://www.erdcloud.com/)라는 툴을 이용하여 온라인으로 바로 화면을 공유하며 테이블을 함께 설계했다. (집단지성의 힘)

-   Study
    -   제일 먼저 필요한 테이블은 Study 테이블. 어떤 스터디들이 있는지 리스트와 상세 목록을 볼 수 있는 페이지가 있고, 연결된 테이블들이 가장 많을 테이블이다.
    -   필요한 컬럼에는 스터디 ID값과 제목, 정렬과 관리를 위한 데이터가 생성된 시간과 업데이트 된 시간, 그리고 스터디 모집 상태를 의미하는 상태 값과 설명, 스터디의 시작 날짜와 종료 날짜, 그리고 스터디에 대한 평가와 유료/무료를 구분하는 타입이 있을 것이다.
-   User
    -   다음으로는 거의 모든 프로젝트를 할 때 필수적으로 들어가는 유저 테이블이다. 유저 테이블에서는 가입한 계정 정보가 들어간다.
    -   필요한 컬럼에는 User를 구분하는 ID값과 (지금 생각해보면 email이 유니크값이니 ID는 필요 없었을 듯) User의 실제 계정으로 쓰이는 이메일, 패스워드, 그리고 유저에 대한 평점과 생성 시간, 업데이트 시간, 유저의 상태 (이메일 인증 전, 인증 후, Disable상태 등)를 나타내는 값과 유저의 권한 (어드민, 일반 유저)와 유저의 이름이 필요할 것이다. (이후 이메일 인증을 위해 인증용 난수 코드를 저장하는 값이 추가되었다.)
-   Subject
    -   스터디의 주제(카테고리)를 저장하는 테이블이다.
    -   primary키인 id값과 subject depth가 2개 정도가 될 것으로 예상하여 depth1, depth2 컬럼을 만들었다. 예를 들면, depth1에 프로그래밍이 들어간다면 depth2에는 백엔드가 들어가는 식의 깊이를 생각했다.
-   Region
    -   서울시 - 강남구 - ㅇㅇ동과 같이 지역을 depth로 저장하고 있는 지역 테이블이다.
    -   id값과 depth1, depth2, depth3이 있다.
-   Attachment
    -   프로젝트에서 사용되는 각종 자료의 위치를 저장할 테이블이다. 예를 들면 유저의 프로필 사진이나 스터디에서 사용되는 각종 파일들을 저장하기 위해 만든 테이블이다.
    -   ID값과 저장된 파일의 위치와 파일의 이름, 생성된 시간과 업데이트 된 시간을 저장한다.
-   Date
    -   일정 테이블이다. 특정 날짜에 어떤 스터디가 있는지 저장하고 있다.
    -   primary key인 id값과 일정, 일정에 대한 설명이 있다.
-   Alarm
    -   오른쪽 상단에 뜨는 알람을 저장할 테이블이다.
    -   id값과 알람이 울리는 시간과 확인했는지 안했는지를 저장할 상태값, 어떤 종류의 알람인지를 저장할 type값이 있다.
-   Message
    -   유저와 유저 사이의 메세지를 전송할 수 있는 기능이 있기 때문에 해당 메세지를 저장할 테이블이다.
    -   id값과 메세지의 내용, 메세지를 보낸 시간, 메세지 확인 상태, 보낸 사람과 받은 사람을 저장한다.
-   Favorate
    -   유저의 즐겨찾기(좋아요)를 저장하고 있을 테이블이다. 장바구니와 같이 관심있는 스터디의 리스트를 저장해둔다. 유저와 스터디의 N:N 매핑 테이블이다.
    -   id값과 어떤 유저가 어떤 스터디를 즐겨찾기 했는지에 대한 데이터를 저장한다.
-   Study Member
    -   특정 스터디에 어떤 유저들이 포함되어 있는지를 담고 있는 스터디와 유저의 매핑 테이블이다.
    -   id값과 역할 (스터디 장인지 스터디원인지 등), 어떤 유저와 스터디인지에 대한 id값을 저장한다. 
-   Study Material
    -   특정 스터디에 어떤 자료들이 저장되어 있는지를 저장한다. 스터디와 Attachment의 N:N 매핑 테이블이다.
    -   id값과 어떤 스터디인지와 어떤 자료인지에 대한 정보를 저장한다.

---

##  2. 각 테이블의 관계 설정하기

위에서 필요하다고 생각했던 테이블을 모두 그리고, 각 테이블간에 관계를 생각한다. 온라인으로 회의를 하는 동시에 테이블을 그리고, 각 테이블간의 관계가 어떨지 논의했다.

우선 쉬운 것부터 시작하도록 하자. 

-   Subject : Study의 관계
    -   1:N 관계이다. 하나의 Subject는 여러개의 Study 값을 가질 수 있다.
-   Region : Study의 관계
    -   1:N 관계이다. 하나의 Region은 여러개의 Study 값을 가질 수 있다.
-   Date : Study
    -   N:1 관계이다. 하나의 Study는 여러개의 Date 값(스터디 하는 날)을 가질 수 있다.
-    Study : Attachment
    -   N:N 관계이다. 각각의 스터디는 여러개의 Attachment(자료)를 가질 수 있다.
    -   N:N 관계이기 때문에 중간에 Mapping table인 Study Material 테이블을 두도록 한다.
-   Study : User
    -   N:N 관계이다. 각각의 스터디는 여러 USer를 가질 수 있다.
    -   N:N 관계이기 때문에 중간에 Study Member 테이블이라는 매핑 테이블을 두도록 한다.
-   User : Message
    -   1:N 관계이다. 한명의 유저는 여러개의 메세지를 가질 수 있다.
-   User : Alarm
    -   1:N 관계이다. 한명의 유저는 여러개의 알람을 가질 수 있다.
-   User : Region
    -   N:1 관계이다. 하나의 지역값은 여러명의 유저를 가질 수 있다.
-   User : Attachment
    -   1:1 관계이다. 한명의 유저는 하나의 자료(프로필 사진)를 가질 수 있다.

이와 같은 관계를 생각해보며, 각 데이터들의 타입과 이름을 생각하여 아래와 같이 테이블을 그려보았다.

![ERD Cloud에서 회의하면서 작성한 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FoVzXq%2FbtqNeITKKYw%2FqhvUrmze5okhb1Wxm4nP5K%2Fimg.png)

---

##  3. MySQL Workbench로 ERD 설계하기

회의에서 논의한 내용을 바탕으로 MySQL에서 테이블을 만든다. 직접 테이블을 만드는 SQL을 작성해도 좋고, Reverse Engineer를 사용하여 테이블을 그리고, 각 관계에 대한 설정과 추가적인 제약조건 등을 설정해도 좋다.

아래는 회의를 통해 나온 테이블을 MySLQ Workbench의 Reverse Engineer를 통해 그린 그림과 SQL이다. 

![ERD 설계 결과물](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbDajXp%2FbtqNeJkSffm%2FUhczW7UVdxE98Y1oauAWA1%2Fimg.png)

SQL문 (create.sql)

```sql
-- -----------------------------------------------------
-- Schema study
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

-- -----------------------------------------------------
-- Table `study`.`attchment`study
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`attachment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(50) NOT NULL COMMENT '자료 위치',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '자료 이름',
  `inst_time` DATETIME NULL DEFAULT NULL COMMENT '생성 시간',
  `updt_time` DATETIME NULL DEFAULT NULL COMMENT '업데이트된 시간',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '자료 테이블';


-- -----------------------------------------------------
-- Table `study`.`subject`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`subject` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `subject_depth1` VARCHAR(50) NULL DEFAULT NULL,
  `subject_depth2` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '주제 테이블';


-- -----------------------------------------------------
-- Table `study`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `study`.`region` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `region_depth1` VARCHAR(50) NULL DEFAULT NULL COMMENT '시',
  `region_depth2` VARCHAR(50) NULL DEFAULT NULL COMMENT '군',
  `region_depth3` VARCHAR(50) NULL DEFAULT NULL COMMENT '구',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '지역 테이블';


-- -----------------------------------------------------
-- Table `study`.`study`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`study` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL COMMENT '제목',
  `inst_time` DATETIME NOT NULL COMMENT '생성 날짜',
  `updt_time` DATETIME NULL DEFAULT NULL COMMENT '업데이트 시간',
  `status` VARCHAR(50) NOT NULL COMMENT '스터디 모집 현항',
  `description` VARCHAR(1000) NULL DEFAULT NULL COMMENT '스터디 설명',
  `start_date` DATE NULL DEFAULT NULL COMMENT '스터디 시작 시간',
  `end_date` DATE NULL DEFAULT NULL COMMENT '스터디 종료 시간',
  `evaluation` INT NULL DEFAULT NULL COMMENT '평가',
  `type` VARCHAR(50) NULL DEFAULT NULL COMMENT '스터디 타입',
  `subject_id` BIGINT NULL,
  `region_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`subject_id`) REFERENCES subject(id),
  FOREIGN KEY (`region_id`) REFERENCES region(id)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '스터디 테이블';

-- -----------------------------------------------------
-- Table `study`.`study_material`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`study_material` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `attachment_id` BIGINT NOT NULL,
  `study_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`attachment_id`) REFERENCES attachment(id),
  FOREIGN KEY (`study_id`) REFERENCES study(id)
  )
ENGINE = InnoDB
COMMENT = '스터디 자료 매핑 테이블';

-- -----------------------------------------------------
-- Table `study`.`user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL COMMENT '유저 이메일 (아이디)',
  `password` VARCHAR(100) NOT NULL COMMENT '비밀번호',
  `grade` INT NULL DEFAULT NULL COMMENT '개인 평점',
  `inst_time` DATETIME NOT NULL COMMENT '생성 시간',
  `updt_time` DATETIME NULL DEFAULT NULL COMMENT '수정 시간',
  `status` VARCHAR(50) NOT NULL COMMENT '상태',
  `name` VARCHAR(50) NOT NULL COMMENT '이름',
  `role` VARCHAR(50) NOT NULL COMMENT '권한 (admin, normal)',
  `region_id` BIGINT NULL,
  `attchment_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`region_id`) REFERENCES region(id),
  FOREIGN KEY (`attchment_id`) REFERENCES attachment(id)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `study`.`date`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`date` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL COMMENT '일정',
  `description` VARCHAR(1000) NULL DEFAULT NULL COMMENT '일정 설명',
  `study_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`study_id`) REFERENCES study(id)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '일정 테이블';


-- -----------------------------------------------------
-- Table `study`.`alarm`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`alarm` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL COMMENT '알람의 타입 (Study 관련 일정 알람 등)',
  `alarm_time` DATETIME NOT NULL COMMENT '알람이 울리는 시간',
  `status` VARCHAR(50) NOT NULL COMMENT '알람 확인 상태',
  `user_id` BIGINT NOT NULL,
  `date_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES user(id),
  FOREIGN KEY (`date_id`) REFERENCES date(id)
  )

ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '알람 테이블 (오른쪽 상단에 뜨는 알람을 저장하는 테이블)';


-- -----------------------------------------------------
-- Table `study`.`favorite`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`favorite` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `study_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES user(id),
  FOREIGN KEY (`study_id`) REFERENCES study(id)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '즐겨찾기 테이블';


-- -----------------------------------------------------
-- Table `study`.`message`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`message` (
  `id` BIGINT NOT NULL COMMENT '아이디' AUTO_INCREMENT,
  `contents` VARCHAR(1000) NULL DEFAULT NULL COMMENT '내용',
  `time` DATETIME NOT NULL COMMENT '메세지 보낸 시간',
  `status` VARCHAR(50) NOT NULL COMMENT '메세지 확인 상태',
  `sendor` BIGINT NOT NULL COMMENT '보낸 사람',
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES user(id)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `study`.`study_member`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`study_member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(50) NOT NULL COMMENT '스터디 역할 (스터디장, 스터디원 등)',
  `user_id` BIGINT NOT NULL,
  `study_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES user(id),
  FOREIGN KEY (`study_id`) REFERENCES study(id)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '스터디와 유저의 매핑 테이블';
```

이후 추가된 sql

```sql
-- -----------------------------------------------------
-- 2020.10.29
-- -----------------------------------------------------

ALTER TABLE `study`.`user`
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE;
;

-- -----------------------------------------------------
-- 2020.11.03
-- -----------------------------------------------------

ALTER TABLE `study`.`user`
ADD COLUMN `auth_key` VARCHAR(50) NULL COMMENT '이메일 인증용 난수' AFTER `attchment_id`;


```

## 관련글
  
[자세한 내용 보기](https://hirlawldo.tistory.com/36)
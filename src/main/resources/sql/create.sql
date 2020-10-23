-- -----------------------------------------------------
-- Schema study
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

-- -----------------------------------------------------
-- Table `study`.`attchment`study
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`attachment` (
  `id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL,
  `subject_depth1` VARCHAR(50) NULL DEFAULT NULL,
  `subject_depth2` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '주제 테이블';


-- -----------------------------------------------------
-- Table `study`.`study`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`study` (
  `id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL,
  `attachment_id` BIGINT NOT NULL,
  `study_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`attachment_id`) REFERENCES attachment(id),
  FOREIGN KEY (`study_id`) REFERENCES study(id)
  )
ENGINE = InnoDB
COMMENT = '스터디 자료 매핑 테이블';


-- -----------------------------------------------------
-- Table `study`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `study`.`region` (
  `id` BIGINT NOT NULL,
  `region_depth1` VARCHAR(50) NULL DEFAULT NULL COMMENT '시',
  `region_depth2` VARCHAR(50) NULL DEFAULT NULL COMMENT '군',
  `region_depth3` VARCHAR(50) NULL DEFAULT NULL COMMENT '구',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '지역 테이블';


-- -----------------------------------------------------
-- Table `study`.`user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `study`.`user` (
  `id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL COMMENT '아이디',
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
  `id` BIGINT NOT NULL,
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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
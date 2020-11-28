/*----------------2020.10.29--------------------*/
ALTER TABLE `study`.`user`
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE;
;

/*--------------2020.11.03---------------------*/
ALTER TABLE `study`.`user`
ADD COLUMN `auth_key` VARCHAR(50) NULL COMMENT '이메일 인증용 난수' AFTER `attchment_id`;



----------------2020.11.27------------------------
ALTER TABLE `study`.`user`
CHANGE COLUMN `attchment_id` `attachment_id` BIGINT NULL DEFAULT NULL ;

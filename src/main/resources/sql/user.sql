CREATE USER 'study'@'localhost'
IDENTIFIED BY 'study';

GRANT ALL ON study.* to 'study'@'localhost';
FLUSH privileges;
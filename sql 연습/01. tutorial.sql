-- 함수, 리터럴, 상수, 연산식
select version(), current_date(), now(), "hello", 1 + 2 from dual;

-- 수학함수, 문자열 함수, 날짜함수
select sin(pi()/4), upper("seoul"), curdate() from dual;

-- 대소문자 구문이 없다.
select version(), current_Date from Dual;

-- table 생성: DDL
create table pet (
	name varchar(100),
    owner varchar(50),
    species varchar(50),
    gender char(1),
    birth date,
    death date
);

-- scheam 확인
describe pet;
desc pet;

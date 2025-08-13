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

-- scheam 확인 중
describe pet;
desc pet;

-- insert: DML(c)
insert
	into pet
values('까꿍이', '권승욱', 'dog', 'm', '2020-12-12', null);

-- select: DML(R)
select * from pet;

-- update: DML(u)
update pet set name='상원이' where name = '까꿍이'; 

-- delete: DML(D)
delete from pet where name='까꿍이';

-- load data: mysql(cli) 전용 
load data local infile '/root/pet.txt' into table pet;
update pet set death = null where name != 'boeser';

-- select 연습
select name, owner 
from pet
where name='bowser'; 

-- 1998 이후에 태어난 애들의 이름, 종, 생일을 출력하세요.
--
select name, species, birth
from pet
where bitrh >= '1998-01-01';

--
-- 개들 중에 암컷만 이름과 종과 성별을 출력하세요.
--
select name, species, gender
from pet
where species = 'dog' and gender = 'f';

--
-- 새와 뱀들만 이름과 종을 출력하세요
--
select name, species
from pet
where species = 'bird' or 'snake';

--
-- 애완동물들의 이름과 생일을 제일 어린 순서대로 출력하세요
--
select name, birth
from pet
order by birth desc;

--
-- 애완동물들의 이름과 생일을 나이 많은 순서대로 출력하세요
--
select name, birth
from pet
order by birth asc;

--
-- 애완동물들의 이름과 생일을 제일 어린 순서대로 출력하세요
--

--
-- 애완동물들 중에 살아있는 애들만 이름과 생일 사망일을 출력하세요
--
select name, birth, death
from pet
where death = null;

--
-- 애완동물들 중에 이름dl 'b'로 시작하는 아이들의 이름만 출력하세요
--
select name
from pet
where name like 'b%';

--
-- 애완동물들 중에 이름이 'fy'로 끝나는 아이들의 이름만 출력하세요
--
select name
from pet
where name like '%p';

--
-- 애완동물들 중에 이름에 'w'가 들어있는 아이들의 이름만 출력하세요
--
select name
from pet
where name like '%w%';

--
-- 애완동물들 중에 이름이 5문자인 아이들의 이름만 출력하세요
--
select name
from pet
where name like '_____';

--
-- 애완동물들 중에 이름이 b로 시작하고 6문자인 아이들의 이름만 출력하세요
--
select name
from pet
where name like 'b_____';

--
-- 집계
--
select count(*), max(birth) from pet;
















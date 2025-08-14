--
-- 문자열 함수
--

-- upper, ucase
select upper('seoul'), ucase('Seoul') from dual;
select upper(first_name) from employees;

-- lower
select lower('seoul'), lcase('Seoul') from dual;
select lower(first_name) from employees;

-- substring(문자열, index, length)
select substring('Hello World', 3, 2) from dual;

-- 예제: 1989년에 입사한 직원들의 이름, 입사일 출력
select first_name, hire_date
from employees
where substring(hire_date, 1, 4) = '1989';

-- lpad(오른쪽 정렬), rpad(왼쪽 정렬)
select lpad('1234', 10, '-'), rpad('1234', 10, '-') from dual; 
select rpad('1234', 10, '-') from dual;

-- 예제) 직원들의 월급을 오른쪽 정렬로(빈 공간은 *)
select lpad(salary, 10, '*')
from salaries;

-- trim, ltrim, rtrim: 스페이스 공간 없애기
select concat('---', ltrim('       hello       '), '---'),
 concat('---', rtrim('    hello      '), '---'),
  concat('---', trim(leading ' ' from '    hello      '), '---'),
  concat('---', trim(trailing ' ' from '    hello      '), '---'),
  concat('---', trim(both 'x' from 'xxxhelloxxx'), '---')
  from dual;
  
  -- length
  select length('Hello World') from dual;
  




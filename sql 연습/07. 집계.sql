-- 1) 집계 쿼리: select 절에 통계 함수(count, max, min, sum, avg, variance, stdev,...)
select avg(salary) 
from salaries;

-- 2) select 절에 집계함수(그룹함수)가 있는 경우, 어떤 컬럼도 select 절에 올 수 없다.
-- 아래의 emp_no 는 의미가 없음,
-- 오류
select emp_no, avg(salary)
from salaries;

-- 3) query 순서
-- -- 1) from: 접근 테이블 확인
-- -- 2) where: 조건에 맞는 row를 선택한다. 임시테이블
-- -- 3) 집계(테이블)
-- -- 4) projection
-- 예제) salaries 테이블에서 사번이 10060인 직원의 급여 평균과 총합계를 출력;
select emp_no, 
avg(salary), sum(salary)
from salaries
where emp_no = 10060;

-- 4) group by에 참여 하고 있는 컬럼은 projection이 가능하다. select 절에 올 수 있다.
-- 예제) 사원별 평균 급여
select emp_no, avg(salary)
from salaries
group by emp_no;

-- 5) having
-- 집계 결과(걸과 테이블) 에서 row를 선택 해야 하는 경우만 - 그룹화 후 집계 결과를 필터링
-- 이미 where(그룹화, 집계 전에만 필터링 가능) 절은 실행 되었기 때문에 having 절에 이 조건을 주어야 한다.
-- 예제) 평균 급여가 60000 달러 이상인 사원의 사번과 평균 급여를 출력하세요.
select emp_no, avg(salary)
from salaries
group by emp_no
having avg(salary) >= 6000;

-- 6) order by
-- order by 는 항상 맨 마지막 출력(projection, 
select emp_no, avg(salary)
from salaries
group by emp_no
having avg(salary) >= 6000
order by(salary) asc;

-- 주의) 사번이 10060 번인 직원의 사번, 평균 월급, 급여총합을 출력하세요.
select emp_no, avg(salary), sum(salary)
from salaries
where emp_no = 100670;

-- 문법적으로 오류(의미적으로는 맞다)
select emp_no, avg(salary), sum(salary)
from salaries
where emp_no = '10060';
-- 문법적으로 맞음

select emp_no, avg(salary), sum(salary)
from salaries
having emp_no = '10060';

-- 예제) dept_emp  테이블에서  d008에 근무하는 인원수는?
select count(*)
from dept_emp
where dept_no = 'd008'; 
 
 



select
    m.scoresum as score,
    e.emp_no,
    e.emp_name,
    e.position,
    e.email
from
    hr_employees e join hr_department d on e.dept_id = d.dept_id
    join (
        select emp_no, sum(score) as scoresum
        from hr_grade
        where year = 2022
        group by emp_no
    ) m on e.emp_no = m.emp_no
order by
    m.scoresum desc
limit 1
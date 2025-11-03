select
    d.dept_id,
    d.dept_name_en,
    round(avg(e.sal), 0) as avg_sal
from
    hr_employees e join hr_department d on e.dept_id = d.dept_id
group by
    dept_id
order by
    round(avg(e.sal), 0) desc;
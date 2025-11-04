select
    year(s.sales_date) as year,
    month(s.sales_date) as month,
    u.gender,
    count(distinct s.user_id) as users
from
    online_sale s join user_info u on s.user_id = u.user_id
where
    u.gender is not null
group by
    year(s.sales_date), month(s.sales_date), u.gender
order by
    year, month, gender
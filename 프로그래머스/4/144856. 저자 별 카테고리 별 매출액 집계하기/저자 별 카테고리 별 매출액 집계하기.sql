select
    b.author_id, 
    a.author_name,
    b.category,
    sum(s.sales * b.price) as total_sales
from
    book_sales s join book b on s.book_id = b.book_id
    join author a on b.author_id = a.author_id
where
    s.sales_date >= '2022-01-01' and s.sales_date < '2022-02-01'
group by
    b.author_id, b.category
order by
    b.author_id,
    b.category desc
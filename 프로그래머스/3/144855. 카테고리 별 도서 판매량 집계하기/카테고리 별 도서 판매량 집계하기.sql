select
    b.category as category,
    sum(s.sales) as total_sales
from
    book_sales s join book b on s.book_id = b.book_id
where
    s.sales_date >= '2022-01-01' and s.sales_date < '2022-02-01'
group by
    b.category
order by
    category;
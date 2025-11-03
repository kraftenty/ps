select
    o.product_id as product_id,
    p.product_name as product_name,
    sum(p.price * o.amount) as total_sales
from
    food_order o join food_product p on o.product_id = p.product_id
where
    o.produce_date >= '2022-05-01' and o.produce_date < '2022-06-01'
group by
    o.product_id
order by
    sum(p.price * o.amount) desc,
    o.product_id
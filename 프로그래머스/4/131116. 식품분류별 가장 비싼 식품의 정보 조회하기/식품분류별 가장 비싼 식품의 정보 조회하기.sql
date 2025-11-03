select
    f.category,
    price,
    product_name
from
    food_product f inner join
    (
        select
            category,
            max(price) as mp
        from
            food_product
        where
            category in ('과자', '국', '김치', '식용유')
        group by
            category
    ) t on f.category = t.category and f.price = t.mp
order by
    price desc;
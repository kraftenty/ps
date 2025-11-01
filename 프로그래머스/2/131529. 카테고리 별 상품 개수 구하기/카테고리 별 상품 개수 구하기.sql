-- 코드를 입력하세요
select
    substring(PRODUCT_CODE, 1, 2) as CATEGORY,
    count(*) as PRODUCTS
from 
    PRODUCT
group by
    substring(PRODUCT_CODE, 1, 2)
order by
    category
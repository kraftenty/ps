select
    r.rest_id,
    i.rest_name,
    i.food_type,
    i.favorites,
    i.address,
    round(avg(r.review_score), 2) as score
from
    rest_review r join rest_info i on r.rest_id = i.rest_id
where
    i.address like '서울%'
group by
    r.rest_id
order by
    round(avg(r.review_score), 2) desc,
    i.favorites desc
    
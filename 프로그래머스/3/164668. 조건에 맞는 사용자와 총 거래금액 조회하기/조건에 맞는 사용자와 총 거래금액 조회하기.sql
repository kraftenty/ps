select
    u.user_id as user_id,
    u.nickname as nickname,
    m.pricesum as total_sales
from
    used_goods_user u 
    join (
        select writer_id, sum(price) as pricesum
        from used_goods_board
        where status = 'DONE'
        group by writer_id
        having sum(price) >= 700000
    ) m on u.user_id = m.writer_id
order by
    m.pricesum
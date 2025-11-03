select
    b.title as title,
    b.board_id as board_id,
    r.reply_id as reply_id,
    r.writer_id as writer_id,
    r.contents as contents,
    date_format(r.created_date, '%Y-%m-%d') as created_date
from
    used_goods_board b join used_goods_reply r on b.board_id = r.board_id
where
    b.created_date >= '2022-10-01' and b.created_date < '2022-11-01'
order by
    r.created_date,
    b.title
select
    concat('/home/grep/src/', b.board_id, '/', f.file_id, f.file_name, f.file_ext) as file_path
from
    used_goods_board b join used_goods_file f on b.board_id = f.board_id
where
    b.views = (select max(views) from used_goods_board)
order by
    f.file_id desc
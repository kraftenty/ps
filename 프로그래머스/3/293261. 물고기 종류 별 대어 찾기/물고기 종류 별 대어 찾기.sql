with tbl as (
    select 
        fi.id as id,
        fi.fish_type as fish_type,
        fi.length as length,
        fni.fish_name as fish_name,
        max(fi.length) over (partition by fi.fish_type) as max_length
    from
        fish_info fi join fish_name_info fni on fi.fish_type = fni.fish_type
)

select
    id,
    fish_name,
    length
from 
    tbl
where
    length = max_length
order by
    id;
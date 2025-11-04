select
    p.id,
    p.name,
    p.host_id
from
    places p join (
        select
            host_id,
            count(*) as cnt
        from
            places
        group by
            host_id
    ) j on p.host_id = j.host_id
where
    cnt > 1
order by
    id
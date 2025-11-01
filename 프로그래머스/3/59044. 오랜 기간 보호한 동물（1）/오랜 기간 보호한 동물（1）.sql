select
    ai.name as NAME,
    ai.datetime as DATETIME
from
    animal_ins ai left join animal_outs ao on ai.animal_id = ao.animal_id
where
    ao.datetime is null
order by
    datetime
limit 3;
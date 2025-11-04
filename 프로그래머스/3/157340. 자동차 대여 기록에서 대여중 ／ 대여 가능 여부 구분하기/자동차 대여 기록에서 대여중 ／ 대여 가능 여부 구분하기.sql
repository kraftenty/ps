select
    h.car_id,
    case
        when max(m.flag) = 1 then '대여중'
        else '대여 가능'
    end as availability
from
    car_rental_company_rental_history h
    join
    (
        select
            history_id,
            case
                when start_date <= '2022-10-16' and end_date >= '2022-10-16' then 1
                else 0
            end as flag
        from
            car_rental_company_rental_history
        
    ) m on h.history_id = m.history_id
group by
    h.car_id
order by
    h.car_id desc;
select 
    c.CAR_ID, 
    c.CAR_TYPE, 
    floor(c.daily_fee*(1-dp.discount_rate/100)*30) as FEE
from
    car_rental_company_car c 
    join car_rental_company_discount_plan dp on c.car_type = dp.car_type
where
    (c.car_type = '세단' or c.car_type = 'SUV')
    and
    dp.duration_type = '30일 이상'
    and
    not exists (
        select 1
        from 
            car_rental_company_rental_history h
        where 
            h.car_id = c.car_id
            and
            h.start_date <= '2022-11-30' and h.end_date >= '2022-11-01'
    )
    and
    floor(c.daily_fee*(1-dp.discount_rate/100)*30) between 500000 and 1999999
order by
    fee desc,
    c.car_type,
    c.car_id desc
select
    distinct(h.car_id) as car_id
from
    car_rental_company_rental_history h join car_rental_company_car c on h.car_id = c.car_id
where
    c.car_type = '세단'
    and
    month(h.start_date) = '10'
order by
    h.car_id desc
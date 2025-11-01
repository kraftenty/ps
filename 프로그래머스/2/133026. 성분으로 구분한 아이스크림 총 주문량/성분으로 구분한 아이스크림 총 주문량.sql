select
    ii.ingredient_type as INGREDIENT_TYPE,
    sum(fh.total_order) as TOTAL_ORDER
from
    icecream_info ii join first_half fh on ii.flavor = fh.flavor
group by
    ingredient_type
order by
    total_order
select
    year(d.differentiation_date) as year,
    (m.maxsize - d.size_of_colony) as year_dev,
    d.id as id
from
    ecoli_data d join (
        select
            year(differentiation_date) as year,
            max(size_of_colony) as maxsize
        from
            ecoli_data
        group by
            year(differentiation_date)
    ) m on year(d.differentiation_date) = m.year
order by
    year,
    year_dev
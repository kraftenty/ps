select
    a.apnt_no as apnt_no,
    p.pt_name as pt_name,
    a.pt_no as pt_no,
    a.mcdp_cd as mcdp_cd,
    d.dr_name as dr_name,
    a.apnt_ymd as apnt_ymd
from
    appointment a join patient p on a.pt_no = p.pt_no
    join doctor d on a.mddr_id = d.dr_id
where
    a.mcdp_cd = 'cs'
    and
    a.apnt_ymd >= '2022-04-13' and a.apnt_ymd < '2022-04-14'
    and
    a.apnt_cncl_yn = 'N'
order by
    a.apnt_ymd
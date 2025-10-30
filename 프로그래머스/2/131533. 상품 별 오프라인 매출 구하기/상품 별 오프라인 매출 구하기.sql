SELECT
    p.product_code as PRODUCT_CODE,
    SUM(os.sales_amount)*p.price as SALES
FROM 
    offline_sale os join product p on os.product_id = p.product_id
GROUP BY
    os.product_id
ORDER BY 
    sales desc,
    PRODUCT_CODE asc;

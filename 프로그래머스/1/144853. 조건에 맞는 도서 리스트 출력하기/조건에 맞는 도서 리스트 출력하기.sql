-- 코드를 입력하세요
SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM book
WHERE category='인문' AND published_date BETWEEN '2021-01-01' AND '2021-12-31'
ORDER BY published_date;
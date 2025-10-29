-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, DATE_FORMAT(datetime, '%Y-%m-%d') as '날짜'
FROM animal_ins
order by animal_id;
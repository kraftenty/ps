-- 코드를 입력하세요
SELECT animal_id, name
FROM animal_ins
WHERE animal_type='Dog' AND LOWER(name) like '%el%'
ORDER BY name;
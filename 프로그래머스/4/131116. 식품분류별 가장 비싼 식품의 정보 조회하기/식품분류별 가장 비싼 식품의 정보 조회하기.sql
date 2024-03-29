SELECT P.CATEGORY, SQ.MAX_PRICE, P.PRODUCT_NAME
FROM FOOD_PRODUCT P JOIN (
    SELECT CATEGORY, MAX(PRICE) MAX_PRICE
    FROM FOOD_PRODUCT
    WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
    GROUP BY CATEGORY
) AS SQ ON P.CATEGORY = SQ.CATEGORY AND P.PRICE = SQ.MAX_PRICE
ORDER BY MAX_PRICE DESC
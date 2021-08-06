INSERT INTO ingredients_in_stock
(ingredient_cost, ingredient_selling_price, name, number_of_shots, quantity_per_shot, unit_of_measurement)
VALUES (3.0, 1.5, 'MILK', 20, 10, "ml"),
    (3.0, 1.5, 'HONEY', 20, 10, "g"),
    (4.0, 2.0, 'SYRUP', 20, 10, "ml"),
    (5.0, 2.5, 'STEAMED_MILK', 20, 15, "g"),
    (3.0, 1.5, 'MILK_FOAM', 20, 10, "g"),
    (5.0, 2.5, 'SWEETENED_CONDENSED_MILK', 20, 10, "g"),
    (7.0, 3.5, 'ICE_CREAM', 20, 70, "g"),
    (5.0, 2.5, 'WHIPPED_CREAM', 20, 10, "g"),
    (5.0, 2.5, 'CINNAMON', 20, 3, "g"),
    (2.0, 1.0, 'HOT_WATER', 20, 20, "ml"),
    (2.0, 1.0, 'ICE_CUBES', 20, 4, "pieces"),
    (7.0, 3.5, 'ESPRESSO_SHOT', 20, 20, "ml"),
    (8.0, 4.0, 'BLACK_COFFEE', 20, 20, "ml");

INSERT INTO standard_recipe_in_stock (standard_recipe)
VALUES ('ESPRESSO'),('MACHIATTO'),('CAFFEE_LATTE'),('CAPPUCCINO'),
       ('CAFFEE_MIEL'),('CUSTOM');

INSERT INTO standard_recipe_ingredient_in_stock (number_of_shots, id_ingredient_in_stock, id_standard_recipe_in_stock)
VALUES (2,12,1), (2,12,2), (1,5,2), (2,12,3), (2,4,3), (1,5,3), (1,12,4), (1,4,4),
(2,5,4), (2,13,5), (1,2,5), (1,9,5), (1,4,5)
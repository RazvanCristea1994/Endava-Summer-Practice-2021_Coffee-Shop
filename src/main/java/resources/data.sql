INSERT INTO ingredients
(ingredient_cost, ingredient_selling_price, name, number_of_shots, quantity_per_shot, unit_of_measurement)
VALUES (1.5, 3.0, 'MILK', 20, 10, "ml"),
    (1.5, 3.0, 'HONEY', 20, 10, "g"),
    (2.0, 4.0, 'SYRUP', 20, 10, "ml"),
    (2.5, 5.0, 'STEAMED_MILK', 20, 15, "g"),
    (1.5, 3.0, 'MILK_FOAM', 20, 10, "g"),
    (2.5, 5.0, 'SWEETENED_CONDENSED_MILK', 20, 10, "g"),
    (3.5, 7.0, 'ICE_CREAM', 20, 70, "g"),
    (2.5, 5.0, 'WHIPPED_CREAM', 20, 10, "g"),
    (2.5, 5.0, 'CINNAMON', 20, 3, "g"),
    (1.0, 2.0, 'HOT_WATER', 20, 20, "ml"),
    (1.0, 2.0, 'ICE_CUBES', 20, 4, "pieces"),
    (3.5, 7.0, 'ESPRESSO_SHOT', 20, 20, "ml"),
    (4.0, 8.0, 'BLACK_COFFEE', 20, 20, "ml");

INSERT INTO standard_recipe (standard_recipe)
VALUES ('ESPRESSO'),('MACHIATTO'),('CAFFEE_LATTE'),('CAPPUCCINO'),
       ('CAFFEE_MIEL'),('CUSTOM');

INSERT INTO standard_recipe_ingredient (number_of_shots, ingredient_id, standard_recipe_id)
VALUES (2,12,1), (2,12,2), (1,5,2), (2,12,3), (2,4,3), (1,5,3), (1,12,4), (1,4,4),
(2,5,4), (2,13,5), (1,2,5), (1,9,5), (1,4,5)
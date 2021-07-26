package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;
import org.fantasticcoffee.shop.repository.DefaultExtraIngredientRepository;
import org.fantasticcoffee.shop.service.ExtraIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("extraIngredientService")
public class DefaultExtraIngredientService implements ExtraIngredientService {

    private static Integer id = 0;

    @Autowired
    DefaultExtraIngredientRepository extraIngredientRepository;

    @Override
    public void seedStock() {

        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.MILK, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.HONEY, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.SYRUP, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.STEAMED_MILK, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.MILK_FOAM, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.SWEETENED_CONDENSED_MILK, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.ICE_CREAM, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.WHIPPED_CREAM, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.CINNAMON, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.HOT_WATER, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultExtraIngredientService.id, ExtraIngredient.ICE_CUBES, 20).build());
    }

    public List<ExtraIngredientInStock> getAllExtraIngredientsInStock() {
        return this.extraIngredientRepository.findAll();
    }

    public void decrementExtraIngredient(List<ExtraIngredientInStock> extraIngredientToChange) {

        extraIngredientToChange.forEach(extraIngredientInStock -> {
            ExtraIngredientInStock ingredient = this.extraIngredientRepository.find(extraIngredientInStock.getExtraIngredient());
            ingredient.setQuantity(ingredient.getQuantity() - extraIngredientInStock.getQuantity());
            this.extraIngredientRepository.update(ingredient.getId(), ingredient);
        });
    }

    @Override
    public List<ExtraIngredient> findAll() {
        return new ArrayList<>();
    }

    @Override
    public ExtraIngredient find(Integer id) {
        return null;
    }

    @Override
    public ExtraIngredient update(ExtraIngredient eIngredient) {
        return null;
    }

    @Override
    public ExtraIngredient delete(Integer id) {
        return null;
    }
}
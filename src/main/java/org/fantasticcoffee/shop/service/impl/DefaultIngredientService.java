package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;
import org.fantasticcoffee.shop.repository.DefaultBaseIngredientRepository;
import org.fantasticcoffee.shop.repository.DefaultExtraIngredientRepository;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ingredientService")
public class DefaultIngredientService implements IngredientService {

    private static Integer idExtraIngredient = 0;
    private static Integer idBaseIngredient = 0;

    @Autowired
    DefaultExtraIngredientRepository extraIngredientRepository;

    @Autowired
    DefaultBaseIngredientRepository baseIngredientRepository;

    @Override
    public void seedStock() {

        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.MILK, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.HONEY, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.SYRUP, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.STEAMED_MILK, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.MILK_FOAM, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.SWEETENED_CONDENSED_MILK, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.ICE_CREAM, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.WHIPPED_CREAM, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.CINNAMON, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.HOT_WATER, 20).build());
        this.extraIngredientRepository.save(new ExtraIngredientInStock.Builder(++DefaultIngredientService.idExtraIngredient, ExtraIngredient.ICE_CUBES, 20).build());

        this.baseIngredientRepository.save(new BaseIngredientInStock.Builder(++DefaultIngredientService.idBaseIngredient, BaseIngredient.ESPRESSO_SHOT, 20).build());
        this.baseIngredientRepository.save(new BaseIngredientInStock.Builder(++DefaultIngredientService.idBaseIngredient, BaseIngredient.BLACK_COFFEE, 20).build());
    }

    public List<ExtraIngredientInStock> getAllExtraIngredientsInStock() {
        return this.extraIngredientRepository.findAll();
    }

    public List<BaseIngredientInStock> getAllBaseIngredientsInStock() {
        return this.baseIngredientRepository.findAll();
    }

    @Override
    public void decrementExtraIngredient(List<ExtraIngredientOnRecipe> extraIngredientToChange) {

        extraIngredientToChange.forEach(extraIngredientOnRecipe -> {
            ExtraIngredientInStock ingredient = this.extraIngredientRepository.find(extraIngredientOnRecipe.getExtraIngredient());
            ingredient.setQuantity(ingredient.getQuantity() - extraIngredientOnRecipe.getQuantity());
        });
    }

    @Override
    public void decrementBaseIngredient(List<BaseIngredientOnRecipe> baseIngredientToChange) {

        baseIngredientToChange.forEach(baseIngredientOnRecipe -> {
            BaseIngredientInStock ingredient = this.baseIngredientRepository.find(baseIngredientOnRecipe.getBaseIngredient());
            ingredient.setQuantity(ingredient.getQuantity() - baseIngredientOnRecipe.getQuantity());
        });
    }
}

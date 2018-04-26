/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.MealMaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author utilizador
 */
public class SearchBatchUsageController {

    private final MealMaterialRepository mealRepo = PersistenceContext.repositories().mealMaterial();
    private final BatchRepository batchRepo = PersistenceContext.repositories().batch();

    public List<Batch> getAllBatches() {
        List<Batch> b = this.batchRepo.findAll();
        return b;
    }

    public Batch getBatchById(int id) {
        return batchRepo.findByPk(id);
    }

    public List<Meal> getMealsFromBatch(Batch b) {
        return mealRepo.getMealsByBatchID(b);
    }

}

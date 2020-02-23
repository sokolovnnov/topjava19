package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.List;

@Service
public class MealService {//todo add checkNotFoundWithId
    private static final Logger log = LoggerFactory.getLogger(MealService.class);


    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository){this.repository = repository;}

    public Meal create(int authUserId, Meal meal) {
        log.debug("in create, userId: " + meal.getUserId() + "  - " + authUserId);
        return repository.save(meal, authUserId); //no need check
    }

    public Meal update(Integer id, Meal meal, Integer autUserId){
        return ValidationUtil.checkNotFoundWithId(repository.save(meal, autUserId), id);
    }

    public void delete(int id, Integer autUserId){
        ValidationUtil.checkNotFoundWithId(repository.delete(id, autUserId), id);
    }

    public Meal get(int id, Integer autUserId){
        return ValidationUtil.checkNotFoundWithId(repository.get(id, autUserId), id);
    }

    public List<MealTo> getAll(Integer autUserId, int caloriesPerDay) {
        return MealsUtil.getTos(repository.getAll(autUserId), caloriesPerDay);
    }
}
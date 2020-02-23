package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {

    private final MealService service;
    @Autowired
    public MealRestController(MealService mealService){
        this.service = mealService;
    }

    private Integer autUserId = SecurityUtil.authUserId();

    public Meal save(Meal meal) {
        return service.create(autUserId, meal);
    }

    public Meal update(int id, Meal meal) {
        ValidationUtil.assureIdConsistent(meal, id);
        return service.update(id, meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        service.delete(id, autUserId);
    }

    public Meal get(int id) {
        return service.get(id, autUserId);
    }

    public List<MealTo> getAll() {
        return service.getAll(autUserId, SecurityUtil.authUserCaloriesPerDay());
    }

}
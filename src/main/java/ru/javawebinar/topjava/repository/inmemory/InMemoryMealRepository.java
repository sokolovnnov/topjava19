package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(meal, 1));
    }


    @Override
    public Meal save(Meal meal, int autUserId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(autUserId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage

        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int autUserId) {
        if (isWrongUserId(id, autUserId)) return false;
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int autUserId) {
        if (isWrongUserId(id, autUserId)) return null;
        return repository.get(id);
    }

    @Override//todo добавить сортировку
    public List<Meal> getAll(int autUserId) {
        return repository.values()
                .stream()
                .filter(meal -> meal.getUserId().equals(autUserId))
                .collect(Collectors.toList());
    }

    @Override//todo реализовать
    public List<Meal> getWithFilter(int authUserId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    private boolean isWrongUserId(int mealId, int autUserId){
        if (repository.get(mealId) == null) return true;
        return repository.get(mealId).getUserId() != autUserId;
    }
}


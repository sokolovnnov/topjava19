package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MealReporitory {
    Meal create(Meal meal);
    Meal get(int id);
    Meal update(Meal meal);
    void delete(int id);
    Collection<Meal> getAll();
    public int getSize();
//    Collection<Meal> getFilter(LocalDate startDate, LocalDate endDate);
}

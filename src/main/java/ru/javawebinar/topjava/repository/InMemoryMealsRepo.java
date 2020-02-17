package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealsRepo {
    // public List meals = new ArrayList<Meal>();
    static AtomicInteger id = new AtomicInteger(0);

    public ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();

    public InMemoryMealsRepo() {
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                "Завтрак", 500));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                "Завтрак", 500));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0),
                "Обед", 1000));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0),
                "Ужин", 500));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0),
                "Еда на граничное значение", 100));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0),
                "Завтрак", 1000));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0),
                "Обед", 500));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0),
                "Ужин", 410));
    }
}

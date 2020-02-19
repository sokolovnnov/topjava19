package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.UserServlet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class InMemoryMealsRepo implements MealReporitory {
    private static final Logger log = getLogger(InMemoryMealsRepo.class);
    // public List meals = new ArrayList<Meal>();
    static AtomicInteger id = new AtomicInteger(0);

    public ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();

    public InMemoryMealsRepo() {
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                "Завтрак", 500));
        meals.put(id.incrementAndGet(), new Meal(id.get(),
                LocalDateTime.of(2020, Month.JANUARY, 30, 10, 1),
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

    public Meal get(int id){
        return meals.get(id);
    }

    public Collection<Meal>getAll(){
        return meals.values();
    }

    /*public List<Meal> getFilter(LocalDate startDate, LocalDate endDate, int caloriesPerDay){
        //todo complete
        MealsUtil.filteredByStreams(meals, startDate, endDate, caloriesPerDay)
        return null;
    }*/

    public Meal create(Meal meal){
        Meal newMeal = new Meal(id.incrementAndGet(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        return meals.put(newMeal.getId(), newMeal);
    }

    public void delete(int id){
        log.debug("removing...");
        meals.remove(id);
    }

    public Meal update(Meal meal){
        log.debug("updating... " + meal.getId());
        log.debug("updating... " + meal.getCalories());
        Meal m1 = meals.computeIfPresent(meal.getId(), (key, value) -> {
            log.debug(key + " - id");
            log.debug(value.getCalories() + " - calorires");
            return meal;});
        //return meals.computeIfPresent(meal.getId(), (key, value) -> value);
        log.debug(m1.getCalories() + "");
        return m1;
    }

    public int getSize(){
        return meals.size();
    }
}

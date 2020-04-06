package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal MEAL01 = new Meal(START_SEQ + 2,
            LocalDateTime.of(2020,12,8,4,55,6),
            "ужин1", 100);
    public static final Meal MEAL02 = new Meal(START_SEQ + 3,
            LocalDateTime.of(2020,11,11,4,30,6),
            "ужин2", 200);
    public static final Meal MEAL03 = new Meal(START_SEQ + 4,
            LocalDateTime.of(2020,10,9,4,5,6),
            "ужин3", 300);
    public static final Meal MEAL04 = new Meal(START_SEQ + 5,
            LocalDateTime.of(2020,9,12,6,5,6),
            "ужин4", 400);
    public static final Meal MEAL05 = new Meal(START_SEQ + 6,
            LocalDateTime.of(2020,8,2,4,11,6),
            "ужин5", 500);
    public static final Meal MEAL06 = new Meal(START_SEQ + 7,
            LocalDateTime.of(2020,4,5,23,45,6),
            "ужин6", 600);
    public static final Meal MEAL07 = new Meal(START_SEQ + 8,
            LocalDateTime.of(2020,3,11,8,30,6),
            "ужин7", 700);
    public static final Meal MEAL08 = new Meal(START_SEQ + 9,
            LocalDateTime.of(2020,1,10,14,4,6),
            "ужин8", 800);
    public static final Meal MEAL09 = new Meal(START_SEQ + 10,
            LocalDateTime.of(2020,1,9,14,5,6),
            "ужин9", 900);

    public static List<Meal> getMeals(){
        return new ArrayList<>(Arrays.asList(MEAL01, MEAL02, MEAL03, MEAL04, MEAL05, MEAL06, MEAL07, MEAL08, MEAL09));
    }



}

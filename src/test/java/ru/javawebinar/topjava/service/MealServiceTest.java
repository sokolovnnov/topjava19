package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(START_SEQ + 3, USER_ID);
        assertThat(MEAL02).isEqualToComparingFieldByField(meal);
    }

    @Test(expected = NotFoundException.class)
    public void get_others_meal() {
        mealService.get(START_SEQ + 6, 6);
    }

    @Test
    public void delete() {
        mealService.delete(100003, USER_ID);
        List<Meal> actualList = mealService.getAll(USER_ID);
        List<Meal> expectedList = Arrays.asList(MEAL01, MEAL03, MEAL04, MEAL05, MEAL06, MEAL07, MEAL08, MEAL09);
        assertThat(expectedList).usingFieldByFieldElementComparator().isEqualTo(actualList);
    }

    @Test(expected = NotFoundException.class)
    public void deleteOthersMeal() {
        mealService.delete(100003, ADMIN_ID);
    }

    @Test
    public void getAll() {
        List<Meal> actual = mealService.getAll(USER_ID);
        List<Meal> expectedList = Arrays.asList(MEAL01, MEAL02, MEAL03, MEAL04, MEAL05, MEAL06, MEAL07, MEAL08, MEAL09);
        assertThat(expectedList).usingFieldByFieldElementComparator().isEqualTo(actual);
    }

    @Test
    public void update() {
        Meal updateMeal = new Meal(START_SEQ + 4,
                LocalDateTime.of(2021,10,9,4,5,6),
                "ужин3", 300);
        mealService.update(updateMeal, USER_ID);
        Meal afterUpd = mealService.get(START_SEQ + 4, USER_ID);
        assertThat(updateMeal).isEqualToComparingFieldByField(afterUpd);
    }

    @Test(expected = NotFoundException.class)
    public void updateOthersMeal() {
        Meal updateMeal = new Meal(START_SEQ + 4,
                LocalDateTime.of(2021,10,9,4,5,6),
                "ужин3", 300);
        mealService.update(updateMeal, 12);
    }

    @Test
    public void create() {
    }
}
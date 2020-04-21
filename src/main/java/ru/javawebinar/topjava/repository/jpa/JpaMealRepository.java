package ru.javawebinar.topjava.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaMealRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional()
    public Meal save(Meal meal, int userId) {
        User user = em.getReference(User.class, userId);

        if (meal.isNew()) {
            meal.setUser(user);
            em.persist(meal);
            return meal;
        } else {
            Meal meal1 = em.find(Meal.class, meal.getId());
            if (meal1.getUser().getId().equals(user.getId())) {
                meal.setUser(user);
                return em.merge(meal);
            }else
                return null;
        }
    }

    @Override//ok
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userid", userId)
                .executeUpdate() != 0;
    }

    @Override//ok
    public Meal get(int id, int userId) {
        try {
            return em.createNamedQuery(Meal.GET, Meal.class)
                    .setParameter("id", id)
                    .setParameter("userid", userId)
                    .getSingleResult();
        } catch (NoResultException n){
            log.error("javax.persistence.NoResultException throw in 'get' meal {} for user {} return ", id, userId);
            return null;
        }

/*
        Meal meal = em.find(Meal.class, id);
        if (meal.getUser().getId() == userId) {
            return meal;
        } else
            return null;
*/
    }

    @Override//ok
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("userid", userId)
                .getResultList();
    }

    @Override//не проходит тест
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN_SORTED, Meal.class)
                .setParameter("userid", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
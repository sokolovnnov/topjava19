package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealsRepo;
import ru.javawebinar.topjava.repository.MealReporitory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealReporitory repo;
    private List<MealTo> mealsTo;

    @Override
    public void init() throws ServletException {
        repo = new InMemoryMealsRepo();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("in doPost()");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if (id == null || id.equals("")){
            log.debug("in id == null");
            Meal meal = new Meal(null, LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            repo.create(meal);

        }
        else {
            log.debug("in id != null, id = " + id );

            Meal meal = new Meal(Integer.parseInt(request.getParameter("id")),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            repo.update(meal);
        }

       // request.setAttribute("meals", mealsTo);
   //     request.getRequestDispatcher("meals.jsp").forward(request, response);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        log.debug("before switch :" + action);
        switch (action == null ? "all" : action){
            case ("delete"):{
                log.debug("in switch delete");
                int idToDel = Integer.parseInt(req.getParameter("id"));
                log.debug("to Delete: " + idToDel);
                repo.delete(idToDel);
                log.debug("map size after deleting: " + repo.getSize());
                resp.sendRedirect("meals");
                break;
            }
            case ("new"):{
                log.debug("redirect to createmeal.jsp");

                //todo
                Meal meal = new Meal(null, LocalDateTime.now(), "", 0);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/createmeal.jsp").forward(req, resp);
                break;
            }
            case ("update"):{
                Meal meal = repo.get(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/createmeal.jsp").forward(req, resp);
                break;
            }
            case ("save"):{
                break;
            }
            case ("all"):{
                log.debug("redirect to meals");

                mealsTo = MealsUtil.filteredByStreams(
                        new ArrayList<>(repo.getAll()), LocalTime.MIN, LocalTime.MAX, 2000);

                req.setAttribute("meals", mealsTo);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                break;
            }
        }

    }
}

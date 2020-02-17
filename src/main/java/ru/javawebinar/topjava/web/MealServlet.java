package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealsRepo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private InMemoryMealsRepo repo;
    private List<MealTo> mealsTo;

    @Override
    public void init() throws ServletException {
         mealsTo = MealsUtil.filteredByStreams(new ArrayList<>(new InMemoryMealsRepo().meals.values()), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");


        request.setAttribute("meals", mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    //    response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        log.debug(action);
        switch (action == null ? "all" : action){
            case ("delete"):{
                int idToDel = Integer.parseInt(req.getParameter("id"));
                repo.meals.remove(idToDel);
                log.debug("Delete: " + idToDel);
                resp.sendRedirect("/meals.jsp");
                break;
            }
            case ("new"):{
                log.debug("redirect to createmeal.jsp");
                req.getRequestDispatcher("/createmeal.jsp").forward(req, resp);
                break;
            }
            case ("edit"):{
                break;
            }
            case ("save"):{
                break;
            }
            case ("all"):{
                log.debug("redirect to meals");


                req.setAttribute("meals", mealsTo);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                break;
            }
        }

    }
}

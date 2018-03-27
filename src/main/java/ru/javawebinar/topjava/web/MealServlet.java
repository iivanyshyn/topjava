package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author iivanyshyn
 * @since by 3/27/2018
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LOG.debug("redirect to meals");
        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.of(7, 0), LocalTime.of(21, 0), 2000);
        request.setAttribute("meals", mealsWithExceeded);
        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        view.forward(request, response);

//        response.sendRedirect("meals.jsp");
    }
}

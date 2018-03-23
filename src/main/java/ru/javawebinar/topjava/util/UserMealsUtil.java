package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
            filteredWithExceeded.forEach(System.out::println);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Long timeStart = System.currentTimeMillis();
//        Map<LocalDate, Integer> caloriesPerDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(um -> um.getCalories())));
//        List<UserMealWithExceed> userMealWithExceeds = mealList.stream()
//                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
//                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), caloriesPerDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
//                .collect(Collectors.toList());
        Map<LocalDate, Integer> caloriesPerDate = new HashMap<>();
        for (UserMeal userMeal : mealList){
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            if (caloriesPerDate.get(localDate)== null)
                caloriesPerDate.put(localDate, userMeal.getCalories());
            else
                caloriesPerDate.put(localDate, (caloriesPerDate.get(localDate) + userMeal.getCalories()));
        }
        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        for (UserMeal userMeal : mealList){
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)){
                userMealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), caloriesPerDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        Long timeEnd = System.currentTimeMillis();
        System.out.println(""+(timeEnd-timeStart) + " ms.");
        return userMealWithExceeds;
    }
}

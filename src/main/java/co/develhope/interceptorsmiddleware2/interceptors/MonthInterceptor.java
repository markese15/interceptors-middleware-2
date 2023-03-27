package co.develhope.interceptorsmiddleware2.interceptors;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1, "Gennaio", "January", "Januar"),
            new Month(2, "Febbraio", "February", "Februar"),
            new Month(3, "Marzo", "March", "Marsh"),
            new Month(4, "Aprile", "April", "April"),
            new Month(5, "Maggio", "May", "Kann"),
            new Month(6, "Giugno", "June", "Juni")

    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURL().toString().contains("/months")) {
            if (request.getHeader("monthNumber") == null || request.getHeader("monthNumber").isEmpty()) {
                response.setStatus(400);
                return true;
            }
        }
        Integer monthNumber = request.getIntHeader("monthNumber");
        Optional<Month> foundedMonth = monthList.stream().filter(singleMonth -> singleMonth.getMonthNumber() == monthNumber).findFirst();
        if (foundedMonth.isPresent()) {
            request.setAttribute("month", foundedMonth.get());
        } else {
            Month newMonth = new Month(monthNumber, "nope", "nope", "nope");
            request.setAttribute("month", newMonth);
        }
        return true;

    }



}

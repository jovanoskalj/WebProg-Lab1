package mk.finki.ukim.web.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

import static mk.finki.ukim.web.lab.bootstrap.DataHolder.events;

@WebServlet(name="eventlist-servlet",urlPatterns = "/*")
public class EventListServlet extends HttpServlet {
    private final  EventService eventService;

//    process thymeleaf templates
//    This enables server-side generation of HTML with data injected into the view.
    private final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        List<Event> events;
//        events=DataHolder.events;

        String searchName = req.getParameter("searchName");
        String minRatingParam = req.getParameter("minRating");

        if((searchName==null||searchName.isEmpty())&&(minRatingParam==null || minRatingParam.isEmpty()) ){
            events=eventService.listAll();
        }
        else{
            double minRating = minRatingParam != null && !minRatingParam.isEmpty() ? Double.parseDouble(minRatingParam) : 0.0;
            events=eventService.searchEvents(searchName,minRating);
        }

        WebContext context = new WebContext(webExchange, req.getLocale());
        //for search
        context.setVariable("searchName", searchName);
        context.setVariable("minRating", minRatingParam);
        context.setVariable("events", events);
        //for listing
        String clientIpAddress = req.getRemoteAddr();
        context.setVariable("clientIpAddress", clientIpAddress);
        context.setVariable("events", events);
        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

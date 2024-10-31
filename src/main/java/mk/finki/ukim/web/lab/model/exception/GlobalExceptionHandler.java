package mk.finki.ukim.web.lab.model.exception;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBookingException.class)
    public String handleTicketAvailabilityException(InvalidBookingException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "redirect:/";
    }
}
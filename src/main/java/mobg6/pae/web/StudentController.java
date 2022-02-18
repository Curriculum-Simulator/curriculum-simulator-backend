package mobg6.pae.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    
    @GetMapping("/student")
    public String index() {
        return "student";
    }
}

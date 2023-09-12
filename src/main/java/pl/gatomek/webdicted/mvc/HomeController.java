package pl.gatomek.webdicted.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value= {"/", "/home", "/about", "/dict" })
    public String index() {
        return "index";
    }
}

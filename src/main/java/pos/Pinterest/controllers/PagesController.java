package pos.Pinterest.controllers;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pos.Pinterest.model.User;
import pos.Pinterest.services.UserService;


@Controller
public class PagesController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefaultPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByUsername(authentication.getName());


        System.out.println(authentication.isAuthenticated() +
                ", " + authentication.getPrincipal() +
                ", " + authentication.getAuthorities()+
                ", " + authentication.getCredentials()+
                ", " + authentication.getDetails() +
                ", " + authentication.getName());

        model.addAttribute("firstname", "Costel");

        model.addAttribute("img_src", "images/animals/animal-hybrids-fb__700.jpg");

        return "/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(){
        System.out.println("/main");

        return "/main";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(Model model){
        System.out.println("/index");

        return "/index";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String returnAdminPage(){
        System.out.println("/admin");

        return "/admin";
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String returnPublicPage(){
        System.out.println("/admin");

        return "/public";
    }
}

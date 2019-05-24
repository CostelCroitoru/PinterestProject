package pos.Pinterest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pos.Pinterest.model.User;
import pos.Pinterest.services.CustomUserDetailsService;
import pos.Pinterest.services.UserService;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userDetailsService")
    CustomUserDetailsService userDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        System.out.println("/login");
        return "/login";
    }


    @RequestMapping(value = "/perform_login", method = RequestMethod.POST)
    public String performLogin(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               Model model
    ) {
        System.out.println("/perform_login");

        User user = userService.findByUsername(username);


        if ((user != null) &&   /*  if user exist in database   */
                ((user.getPassword() != null))) {


            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {

                List<GrantedAuthority> authorities = userDetailsService.buildUserAuthority(user.getRoles());

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword(),
                                authorities
                        )
                );

                model.addAttribute("firstname", user.getFirstname());

                return "redirect:/";
            }
        }

        return "redirect:/login?error";
    }


}

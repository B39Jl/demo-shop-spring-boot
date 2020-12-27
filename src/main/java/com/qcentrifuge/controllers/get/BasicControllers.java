package com.qcentrifuge.controllers.get;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class BasicControllers {



    @GetMapping
    public String getStart(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(o -> {
            System.out.println(o);
        });
        return "index";
    }


    @GetMapping("lofi")
    public String getNy(){
        return "iot";
    }

    @Controller
    class CustomErrorController implements ErrorController {

        @RequestMapping("/error")
        public String handleError(HttpServletRequest request, Model model) {
            int code = Integer.valueOf(String.valueOf(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)));
            model.addAttribute("code", code);
            String message = "Error";
            code = code / 100;
            if (code == 4){
                message = "Page not found";
            }else if (code == 5){
                message = "Server Exception";
            }
            model.addAttribute("message", message);

            return "error";

        }

        @Override
        public String getErrorPath() {
            return null;
        }
    }

}

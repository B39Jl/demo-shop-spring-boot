package com.qcentrifuge.controllers.post;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qcentrifuge.db.users.Users;
import com.qcentrifuge.db.users.UsersRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Set;

@Controller
public class UsersController {

    @Value("${secret-capcha}")
    private String secretCapcha;

    @Autowired
    private UsersRep rep;

    @Autowired
    private RestTemplate template;

    @GetMapping("/reg")
    public String reg(){
        return"reg";
    }

    @PostMapping("/reg")
    public String reg(Users users, String password2, @RequestParam("g-recaptcha-response") String gResponse, Model model){
        CaptchaResponse captchaResponse = template.postForObject("https://www.google.com/recaptcha/api/siteverify?secret=" + secretCapcha + "&response=" + gResponse, Collections.emptyList(), CaptchaResponse.class);

        if (captchaResponse.isSuccess()) {
            String password = users.getPassword();
            boolean hasUser = rep.findByLogin(users.getLogin()) != null;
            if (!hasUser) {
                if (password.equals(password2)) {
                    users.setPassword(new BCryptPasswordEncoder(8).encode(users.getPassword()));
                    rep.save(users);
                    return "redirect:/";
                }
            }
        }else{
            model.addAttribute("error", "Captcha Error");
        }
        return "reg";
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    static class CaptchaResponse{

        private boolean success;

        @JsonAlias("error-codes")
        private Set<String> errorCodes;


        public Set<String> getErrorCodes() {
            return errorCodes;
        }

        public void setErrorCodes(Set<String> errorCodes) {
            this.errorCodes = errorCodes;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

}

package com.example.demo;

import com.example.demo.Hibernate.AccountEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class LoginController {

    String local = "localhost:8080";

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        return "redirect:/login";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model, HttpSession httpSession) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+local+"/api/accounts/"+username+"/"+password+"").build();
        AccountEntity accountResponse=null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            String StringResponseAccount = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            accountResponse = objectMapper.readValue(StringResponseAccount,AccountEntity.class);
        } catch (IOException e) {
            accountResponse = null;
            e.printStackTrace();
        }

        if (accountResponse!=null) {
            if(accountResponse.getType().replace(" ","").compareToIgnoreCase("admin") == 0){
                httpSession.setAttribute("username", username);
                return "redirect:admin/index";
            }
            else {
                model.addAttribute("username", username);
                model.addAttribute("err", "Loại tài khoản không hợp lệ.");
                return "login";
            }
        } else {
            model.addAttribute("username", username);
            model.addAttribute("err", "Sai tài khoản hoặc mật khẩu.");
            return "login";
        }
    }

    @GetMapping("/admin/index")
    public String indexAdmin(HttpSession httpSession) {
            return "admin/index";
    }

}


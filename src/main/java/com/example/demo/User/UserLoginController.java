package com.example.demo.User;

import com.example.demo.Hibernate.AccountEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class UserLoginController {
    private Session session;

    public Session openSession() {
        return SellPhonesDBContext.getSession();
    }

    @PostMapping("/login/index")
    public String login(String username, String password, Model model, HttpSession httpSession, HttpServletRequest httpServletRequest) {
        AccountEntity account = new AccountEntity();
        if (username != null && password != null) {
            try {
                account = (AccountEntity) openSession().createQuery("from AccountEntity where username='" + username + "' and  password='" + password + "'").getSingleResult();
                httpSession.setAttribute("user", account.getUsername());
                httpSession.setAttribute("maDH", 19);
                return "redirect:/index";
            } catch (NoResultException e) {
                model.addAttribute("alertMessage", "sai tài khoản hoặc mật khẩu.");
                model.addAttribute("username", username);
                return "/user/Login/login";
            }
        } else {
            model.addAttribute("alertLogin", httpServletRequest.getAttribute("alertLogin").toString());
            return "/user/Login/login";
        }
    }

    @GetMapping("/login/index")
    public String login() {
        return "/user/Login/login";
    }

    @GetMapping("/login/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/register/index")
    public String register() {
        return "/user/Register/register";
    }

    @PostMapping("/register/index")
    public String register(String email, String hoten, String username, String password, String passwordauth, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("hoten", hoten);
        model.addAttribute("username", username);
        if (checkExists(email)) {
            model.addAttribute("alertMessage", "Email đã có người sử dụng.");
            return "user/Register/register";
        } else if (checkExists(username)) {
            model.addAttribute("alertMessage", "Tên người dùng đã tồn tại.");
            return "user/Register/register";
        } else {
            if (password.equals(passwordauth)) { //nếu 2 mật khẩu nhâp vào giống nhau
                AccountEntity account = new AccountEntity();
                account.setUsername(username);
                account.setPassword(password);
                account.setHoten(hoten);
                account.setNgaysinh(Date.valueOf("1999-07-10"));
                account.setEmail(email);
                account.setPhonenumber("");
                account.setAddress("");
                account.setAvatar("");
                account.setType("customer");
                SellPhonesDBContext.addNewObject(account);
                return "redirect:/login/index";
            } else {
                model.addAttribute("alertMessage", "Mật khẩu nhập lai jkhông khớp.");
                return "user/Register/register";
            }
        }
    }

    public boolean checkExists(String s) {
        List<AccountEntity> entities = new ArrayList<>();
        entities = openSession().createQuery("from AccountEntity where username='" + s + "' or email='" + s + "'").list();
        if (entities.size() > 0) {
            return true;
        } else return false;
    }
}

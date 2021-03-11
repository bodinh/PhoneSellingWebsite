package com.example.demo.User;

import com.example.demo.Hibernate.AccountEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    private Session session;

    public Session openSession() {
        return SellPhonesDBContext.getSession();
    }

    @GetMapping("/account/password")
    public String changePassword(){
        return "/user/ChangePassword/changePassword";
    }

    @PostMapping("/account/password")
    public String changePassword(String oldPassword,String newPassword,String confirmPassword,HttpSession httpSession,Model model){
        if(oldPassword.contains(" ") || newPassword.contains(" ") || confirmPassword.contains(" ")){
            model.addAttribute("alertMessage","Mật khẩu không chứa dấu cách.");
            return "/user/ChangePassword/changePassword";
        }else {
            AccountEntity accountEntity =(AccountEntity) openSession().createQuery("from AccountEntity where idAccount="+UID.getUID(httpSession)).getSingleResult();
            if(!newPassword.equals(confirmPassword)){
                model.addAttribute("alertMessage","Mật khẩu nhập lại không khớp.");
                return "/user/ChangePassword/changePassword";
            }else if(accountEntity.getPassword().replace(" ","").equals(oldPassword)){
                accountEntity.setPassword(newPassword);
                SellPhonesDBContext.updateObject(accountEntity);
                model.addAttribute("successMessage","Đổi mật khẩu thành công.");
                return "/user/ChangePassword/changePassword";
            }
            return "/user/ChangePassword/changePassword";
        }
    }
}

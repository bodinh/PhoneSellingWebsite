package com.example.demo.User;

import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private Session session ;

    private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }
    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String home(Model model){
        List<SanphamEntity> listSanPhams=new ArrayList<>();
        listSanPhams = openSession().createQuery("from SanphamEntity ").list();
        model.addAttribute("listSanPham",listSanPhams.subList(0,8));
        return "index";
    }
}

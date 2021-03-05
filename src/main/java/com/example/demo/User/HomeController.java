package com.example.demo.User;

import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private Session session ;

    private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }
    @GetMapping("/index")
    public String home(Model model){
        List<SanphamEntity> listSanPhams=new ArrayList<>();
        listSanPhams = openSession().createQuery("from SanphamEntity ").list();
        model.addAttribute("listSanPham",listSanPhams);
        return "index";
    }
}

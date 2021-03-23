package com.example.demo.User;

import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.ListSanPham;
import com.example.demo.Model.SanPham;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private Session session;

    private Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    private ListSanPham listSanPham;
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home(Model model) {
        listSanPham = new ListSanPham();
        List<SanPham> listSanPhamsHot = listSanPham.getAllHot();
        List<SanPham> listSanPhamsNew = listSanPham.getAllNew();
        List<SanPham> listSanPhamsSale = listSanPham.getAllSale();
        model.addAttribute("listSanPhamsHot", listSanPhamsHot.subList(0, 8));
        model.addAttribute("listSanPhamsNew", listSanPhamsNew.subList(0, 8));
        if(listSanPhamsHot.size() != 0){
            model.addAttribute("listSanPhamsSale", listSanPhamsSale.subList(0, 8));
        }else {
            model.addAttribute("listSanPhamsSale", listSanPhamsSale);
        }
        return "index";
    }
}

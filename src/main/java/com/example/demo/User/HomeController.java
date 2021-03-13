package com.example.demo.User;

import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.ProductDiscount;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
    private Session session;

    private Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home(Model model) {
        List<SanphamEntity> listSanPhamsHot = new ArrayList<>();
        List<SanphamEntity> listSanPhamsNew = new ArrayList<>();
        List<SanphamEntity> listSanPhamsSale = new ArrayList<>();
        listSanPhamsHot = openSession().createQuery("from SanphamEntity where ishot=1").list();
        listSanPhamsNew = openSession().createQuery("from SanphamEntity where isnew=1").list();
//        listSanPhamsSale = openSession().createSQLQuery("SELECT s.MaSP ,s.TenSP ,s.LoaiSP ,s.HangSX ,s.Anh ,s.Isnew ,s.Ishot ,s.Gia ,sk.Giamgia FROM Sanpham s LEFT JOIN SPkhuyenmai sk ON sk.MaSP = s.MaSP  LEFT JOIN Khuyenmai k ON k.MaKM = sk.MaSPKM").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        listSanPhamsSale = openSession().createQuery("from SanphamEntity ").list();
        model.addAttribute("listSanPhamsHot", listSanPhamsHot.subList(0, 8));
        model.addAttribute("listSanPhamsNew", listSanPhamsNew.subList(0, 8));
        model.addAttribute("listSanPhamsSale", listSanPhamsSale.subList(0, 8));
        return "index";
    }
}

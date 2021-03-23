package com.example.demo.User;

import com.example.demo.Hibernate.CartEntity;
import com.example.demo.Hibernate.DonhangKhEntity;
import com.example.demo.Model.DonHangKH;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/layout")
public class Cart_Layout {

    DonHangKH donHangKH = new DonHangKH();

    @GetMapping("/get-data-layout")
        public String DataLayout(HttpSession httpSession) {
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        long tongSoSP = 0;
        for (CartEntity item: listInCart) {
            tongSoSP += item.getSoluong();
        }
        return JSONObject.quote("{\"tongTien\" :"+ tongTien+", \"sl\" : "+tongSoSP+" }");
    }

}

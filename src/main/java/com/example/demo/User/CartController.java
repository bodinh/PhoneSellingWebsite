package com.example.demo.User;

import com.example.demo.Hibernate.AccountEntity;
import com.example.demo.Hibernate.CartEntity;
import com.example.demo.Hibernate.DonhangKhEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.DonHangKH;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    DonHangKH donHangKH = new DonHangKH();
    @PostMapping("/cart")
    public String Cart(int maSP, HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.addToCart(maSP,donhangKhEntity);
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        return "/user/Cart/cart";
    }

    @GetMapping("/cart")
    public String Cart(HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        return "/user/Cart/cart";
    }
    @GetMapping("/cart/delete")
    public String Cart(int maSP,HttpSession httpSession){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.deleteFromCart(maSP,donhangKhEntity);
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        return "redirect:/cart";
    }
}

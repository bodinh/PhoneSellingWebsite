package com.example.demo.User;

import com.example.demo.Hibernate.AccountEntity;
import com.example.demo.Hibernate.CartEntity;
import com.example.demo.Hibernate.DonhangKhEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.DonHangKH;
import org.hibernate.Session;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;
import org.thymeleaf.context.IWebContext;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    DonHangKH donHangKH = new DonHangKH();
    @PostMapping("/cart")
    public String CartAdd(@RequestParam(name = "maSP")int maSP, HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.addToCart(maSP,donhangKhEntity);
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        // get tổng giá của giỏ hàng
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        
        model.addAttribute("tongTienCart", tongTien);
        long tongSoSP = 0;
        for (CartEntity item: listInCart) {
            tongSoSP += item.getSoluong();
        }
        model.addAttribute("tongsosp", tongSoSP);
        return "/user/Cart/cart";
    }

    @GetMapping("/cart")
    public String CartGet(HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        long tongSoSP = 0;
        for (CartEntity item: listInCart) {
            tongSoSP += item.getSoluong();
        }
        model.addAttribute("tongsosp", tongSoSP);
        return "/user/Cart/cart";
    }
    @GetMapping("/cart/delete")
    public String Cart(int maSP,HttpSession httpSession){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.deleteFromCart(maSP,donhangKhEntity);
        return "redirect:/cart";
    }

    @GetMapping("/cart/deleteAll")
    public String CartDelete(HttpSession httpSession){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        for (CartEntity item: listInCart) {
            donHangKH.deleteFromCart(item.getSanpham().getMaSp(), donhangKhEntity);
        }
        return "redirect:/index";
    }

    @GetMapping("/cart/deleteInBasket")
    public String CartDelete(int maSP,HttpSession httpSession){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.deleteFromCart(maSP,donhangKhEntity);
        return "redirect:/";
    }

    @PostMapping("/cart/check-out")
    public String CheckOut(int maSP, HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.addToCart(maSP,donhangKhEntity);
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        return "user/Cart/CheckOut";
    }
    @GetMapping("/cart/check-out")
    public String CheckOut(HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        return "user/Cart/CheckOut";
    }
    @PostMapping("/fragments")
    public String Fragments(int maSP, HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.addToCart(maSP,donhangKhEntity);
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart1",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        // get tổng giá của giỏ hàng
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        long tongSoSP = 0;
        for (CartEntity item: listInCart) {
            tongSoSP += item.getSoluong();
        }
        model.addAttribute("tongsosp", tongSoSP);
        return "/user/fragments";
    }

    @GetMapping("/fragments")
    public String Fragment(HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart1",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        long tongSoSP = 0;
        for (CartEntity item: listInCart) {
            tongSoSP += item.getSoluong();
        }
        model.addAttribute("tongsosp", tongSoSP);
        return "/user/fragments";
    }

    /*@PostMapping("/check-out")
    public String Check_Out(int maSP, HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        donHangKH.addToCart(maSP,donhangKhEntity);
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart1",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        // get tổng giá của giỏ hàng
        long tongTien = 0;
        for (CartEntity item: listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        long tongSoSP = 0;
        for (CartEntity item: listInCart) {
            tongSoSP += item.getSoluong();
        }
        model.addAttribute("tongsosp", tongSoSP);
        return "/user/checkout";
    }

    @GetMapping("/check-out")
    public String Check_Out( HttpSession httpSession, Model model) {
        DonhangKhEntity donhangKhEntity = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute("listProductInCart2", listInCart);
        model.addAttribute("maDH", donhangKhEntity.getMaDh());
        // get tổng giá của giỏ hàng
        long tongTien = 0;
        for (CartEntity item : listInCart) {
            tongTien += item.getTonggia();
        }
        model.addAttribute("tongTienCart", tongTien);
        long tongSoSP = 0;
        for (CartEntity item : listInCart) {
            tongSoSP += item.getSoluong();
        }
        model.addAttribute("tongsosp", tongSoSP);
        return "/user/checkout";
    }*/
}

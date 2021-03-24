package com.example.demo.User;

import com.example.demo.Hibernate.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.IWebContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    private Session session;
    public Session openSession() {
        return SellPhonesDBContext.getSession();
    }

    @PostMapping("/cart/checkout")
    public String CheckOut(String hoTen, String email, String soDienThoai, String diaChi, String ghiChu, String deliver, Model model, RedirectAttributes redirectAttributes,HttpSession httpSession){
        if(deliver != null){
            //cập nhật tình trạng đơn hàng
            DonhangKhEntity donhangKhEntity = donHangKH.createCart(UID.getUID(httpSession));
            donhangKhEntity.setGhichu(ghiChu);
            donhangKhEntity.setPhuongthucTt(deliver);
            donhangKhEntity.setTinhtrangdonhang(1);
            donhangKhEntity.setNgaydatmua(Timestamp.valueOf(LocalDateTime.now()));

            List<CartEntity> cartEntities = donHangKH.getAllInCart(donhangKhEntity);
            donhangKhEntity.setTongTien(getTongTien(cartEntities));

            SellPhonesDBContext.updateObject(donhangKhEntity);
            httpSession.removeAttribute("maDH");
            //============================

            //cập nhật lại số lượng trong kho
            cartEntities.forEach(cartEntity -> {
                SanphamEntity sanphamEntity = cartEntity.getSanpham();
                sanphamEntity.setSoLuong(sanphamEntity.getSoLuong() - cartEntity.getSoluong());
                SellPhonesDBContext.updateObject(sanphamEntity);
            });
            //===============================

            //cập nhật thông tin khách hàng
            AccountEntity accountEntity = new AccountEntity();
            accountEntity =(AccountEntity) openSession().createQuery("from AccountEntity where username='"+httpSession.getAttribute("user").toString()+"'").getSingleResult();
            accountEntity.setHoten(hoTen);
            accountEntity.setEmail(email);
            accountEntity.setPhonenumber(soDienThoai);
            accountEntity.setAddress(diaChi);
            SellPhonesDBContext.updateObject(accountEntity);
            //=============================
            return "redirect:/";
        }else {
            redirectAttributes.addFlashAttribute("alertMessage","bạn chưa chọn phương thức thanh toán.");
            return "redirect:/cart/checkout";
        }
    }

    public long getTongTien(List<CartEntity> cartEntities){
        long tongTien = 0;
        for (CartEntity item: cartEntities) {
            tongTien += item.getTonggia();
        }
        return tongTien;
    }

    @GetMapping("/cart/checkout")
    public String CheckOut(HttpSession httpSession, Model model){
        DonhangKhEntity donhangKhEntity  = donHangKH.createCart(UID.getUID(httpSession));
        List<CartEntity> listInCart = donHangKH.getAllInCart(donhangKhEntity);
        model.addAttribute( "listProductInCart",listInCart);
        model.addAttribute("maDH",donhangKhEntity.getMaDh());
        long tongTien = getTongTien(listInCart);
        model.addAttribute("tongTienCart", tongTien);

        //get user information
        AccountEntity accountEntity = new AccountEntity();
        accountEntity =(AccountEntity) openSession().createQuery("from AccountEntity where username='"+httpSession.getAttribute("user").toString()+"'").getSingleResult();
        model.addAttribute("userInformation",accountEntity);
        return "user/Cart/checkout";
    }

}

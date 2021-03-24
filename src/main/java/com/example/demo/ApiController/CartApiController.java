package com.example.demo.ApiController;

import com.example.demo.Hibernate.*;
import com.example.demo.Model.DonHangKH;
import com.example.demo.User.UID;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {
    private Session session;

    private Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    @GetMapping("/list/{maDH}")
    public List<CartEntity> getAllInCart(@PathVariable(name = "maDH") int maDH) {
        List<CartEntity> list = new ArrayList<>();
        List<ChitietDhEntity> dhEntities = new ArrayList<>();
        dhEntities = openSession().createQuery("from ChitietDhEntity where maDh=" + maDH).list();
        dhEntities.forEach(chitietDhEntity -> {
            SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp=" + chitietDhEntity.getMaSp()).getSingleResult();
            list.add(new CartEntity(chitietDhEntity.getSoluong(), chitietDhEntity.getThanhtien(), sanphamEntity));
        });
        return list;
    }

    //Thêm mới 1 sản phẩm vào giỏ
    @GetMapping("/addnew/{maSP}/{maDH}")
    public List<CartEntity> addToCart(@PathVariable(name = "maSP") int maSP, @PathVariable(name = "maDH") int maDH) {
        ChitietDhEntity chitietDhEntity = new ChitietDhEntity();
        try {
            chitietDhEntity = (ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh=" + maDH + " and maSp=" + maSP).getSingleResult();
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong() + 1);
            chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            SellPhonesDBContext.updateObject(chitietDhEntity);
        } catch (NoResultException e) {
            chitietDhEntity.setMaDh(maDH);
            chitietDhEntity.setMaSp(maSP);
            chitietDhEntity.setSoluong(1);
            chitietDhEntity.setThanhtien(getPrice(maSP));
            SellPhonesDBContext.addNewObject(chitietDhEntity);
        }
        return getAllInCart(maDH);
    }

    //Thêm mới 1 sản phẩm vào giỏ
    @GetMapping("/addnew/{maSP}")
    public List<CartEntity> addToCart(@PathVariable(name = "maSP") int maSP, HttpSession httpSession) {
        ChitietDhEntity chitietDhEntity = new ChitietDhEntity();
        DonHangKH donHangKH = new DonHangKH();
        DonhangKhEntity donhangKhEntity = donHangKH.createCart(UID.getUID(httpSession));
        try {
            chitietDhEntity = (ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh=" + donhangKhEntity.getMaDh() + " and maSp=" + maSP).getSingleResult();
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong() + 1);
            chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            SellPhonesDBContext.updateObject(chitietDhEntity);
        } catch (NoResultException e) {
            chitietDhEntity.setMaDh(donhangKhEntity.getMaDh());
            chitietDhEntity.setMaSp(maSP);
            chitietDhEntity.setSoluong(1);
            chitietDhEntity.setThanhtien(getPrice(maSP));
            SellPhonesDBContext.addNewObject(chitietDhEntity);
        }
        return getAllInCart(donhangKhEntity.getMaDh());
    }

    @GetMapping("/reduce/{maSP}/{maDH}")
    public List<CartEntity> reduceProduct(@PathVariable(name = "maSP") int maSP, @PathVariable(name = "maDH") int maDH) {
        ChitietDhEntity chitietDhEntity = new ChitietDhEntity();
        chitietDhEntity = (ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh=" + maDH + " and maSp=" + maSP).getSingleResult();
        if (chitietDhEntity.getSoluong() == 0) {
            SellPhonesDBContext.deleteObject(chitietDhEntity);
        } else {
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong() - 1);
            chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            SellPhonesDBContext.updateObject(chitietDhEntity);
        }
        return getAllInCart(maDH);
    }

    @GetMapping("/add/{maSP}/{maDH}")
    public List<CartEntity> addProduct(@PathVariable(name = "maSP") int maSP, @PathVariable(name = "maDH") int maDH) {
        ChitietDhEntity chitietDhEntity = new ChitietDhEntity();
        chitietDhEntity = (ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh=" + maDH + " and maSp=" + maSP).getSingleResult();
        if (chitietDhEntity.getSoluong() == getNumberInWareHouse(maSP)) {
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong());
            SellPhonesDBContext.updateObject(chitietDhEntity);
        } else {
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong() + 1);
            chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            SellPhonesDBContext.updateObject(chitietDhEntity);
        }
        return getAllInCart(maDH);
    }

    public long getPrice(int maSP) {
        SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp=" + maSP).getSingleResult();
        return sanphamEntity.getGia();
    }

    public int getNumberInWareHouse(int maSP) {
        SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp=" + maSP).getSingleResult();
        return sanphamEntity.getSoLuong();
    }
}

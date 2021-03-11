package com.example.demo.ApiController;

import com.example.demo.Hibernate.*;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/reduce/{maSP}/{maDH}")
    public List<CartEntity> reduceProduct(@PathVariable(name = "maSP") int maSP, @PathVariable(name = "maDH") int maDH) {
        ChitietDhEntity chitietDhEntity = new ChitietDhEntity();
        chitietDhEntity = (ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh=" + maDH + " and maSp=" + maSP).getSingleResult();
        if(chitietDhEntity.getSoluong() == 0){
            SellPhonesDBContext.deleteObject(chitietDhEntity);
        }else {
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
        if(chitietDhEntity.getSoluong() == getNumberInWareHouse(maSP)){
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong());
            SellPhonesDBContext.updateObject(chitietDhEntity);
        }else {
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong() + 1);
            chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            SellPhonesDBContext.updateObject(chitietDhEntity);
        }
        return getAllInCart(maDH);
    }

    public long getPrice(int maSP){
        SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp="+maSP).getSingleResult();
        return sanphamEntity.getGia();
    }

    public int getNumberInWareHouse(int maSP){
        SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp="+maSP).getSingleResult();
        return sanphamEntity.getSoLuong();
    }
}

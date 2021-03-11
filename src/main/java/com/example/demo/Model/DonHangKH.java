package com.example.demo.Model;

import com.example.demo.Hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

public class DonHangKH {
    public Session session;

    public Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    public DonhangKhEntity createCart(int idAccount) {
        DonhangKhEntity donhangKh = new DonhangKhEntity();
        try {
            donhangKh = (DonhangKhEntity) openSession().createQuery("from DonhangKhEntity where tinhtrangdonhang=0 and maKh=" + idAccount + "").getSingleResult();
            return donhangKh;
        } catch (NoResultException e) {
            DonhangKhEntity donhangKhEntity = new DonhangKhEntity();
            donhangKhEntity.setMaKh(idAccount);
            donhangKhEntity.setPhivanchuyen((double) 0);
            donhangKhEntity.setPhuongthucTt("null");
            donhangKhEntity.setNgaydatmua(Timestamp.valueOf(LocalDateTime.now()));
            donhangKhEntity.setTongTien((double) 0);
            donhangKhEntity.setTinhtrangdonhang(0);
            donhangKhEntity.setGhichu("null");

            SellPhonesDBContext.addNewObject(donhangKhEntity);

            donhangKh = (DonhangKhEntity) openSession().createQuery("from DonhangKhEntity where tinhtrangdonhang=0 and maKh=" + idAccount + "").getSingleResult();

            return donhangKh;
        }
    }

    public void addToCart(int maSP, DonhangKhEntity donhangKh) {
        ChitietDhEntity chitietDhEntity = new ChitietDhEntity();
        try {
            chitietDhEntity = (ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh=" + donhangKh.getMaDh() + " and maSp=" + maSP).getSingleResult();
            chitietDhEntity.setSoluong(chitietDhEntity.getSoluong() + 1);
            chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            SellPhonesDBContext.updateObject(chitietDhEntity);
        } catch (NoResultException e) {
            chitietDhEntity.setMaDh(donhangKh.getMaDh());
            chitietDhEntity.setMaSp(maSP);
            chitietDhEntity.setSoluong(1);
            chitietDhEntity.setThanhtien(getPrice(maSP));
            SellPhonesDBContext.addNewObject(chitietDhEntity);
        }
    }

    public long getPrice(int maSP){
        SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp="+maSP).getSingleResult();
        return sanphamEntity.getGia();
    }

    public void deleteFromCart(int maSP,DonhangKhEntity donhangKh) {
        ChitietDhEntity chitietDhEntity =(ChitietDhEntity) openSession().createQuery("from ChitietDhEntity where maDh="+donhangKh.getMaDh()+" and  maSp="+maSP).getSingleResult();
        SellPhonesDBContext.deleteObject(chitietDhEntity);
    }

    public List<CartEntity> getAllInCart(DonhangKhEntity donhangKh){
        List<CartEntity> list = new ArrayList<>();
        List<ChitietDhEntity> dhEntities = new ArrayList<>();
        dhEntities = openSession().createQuery("from ChitietDhEntity where maDh="+donhangKh.getMaDh()).list();
        dhEntities.forEach(chitietDhEntity -> {
            SanphamEntity sanphamEntity =(SanphamEntity) openSession().createQuery("from SanphamEntity where maSp="+chitietDhEntity.getMaSp()).getSingleResult();
            list.add(new CartEntity(chitietDhEntity.getSoluong(),chitietDhEntity.getThanhtien(),sanphamEntity));
        });
        return list;
    }
}

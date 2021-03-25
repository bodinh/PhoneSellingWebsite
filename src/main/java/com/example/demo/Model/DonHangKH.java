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
            donhangKhEntity.setTongTien(0);
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
            //kiểm tra sale của sản phẩm
            if(checkSPKM(maSP) > 0){
                chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP));
            }else
                chitietDhEntity.setThanhtien(chitietDhEntity.getSoluong() * getPrice(maSP) * (100 - checkSPKM(maSP) ) / 100);
            //==========================
            SellPhonesDBContext.updateObject(chitietDhEntity);
        } catch (NoResultException e) {
            chitietDhEntity.setMaDh(donhangKh.getMaDh());
            chitietDhEntity.setMaSp(maSP);
            chitietDhEntity.setSoluong(1);

            //kiểm tra sale của sản phẩm
            if(checkSPKM(maSP) > 0){
                chitietDhEntity.setThanhtien(getPrice(maSP) * (100 - checkSPKM(maSP) )/ 100 );
            }else
            chitietDhEntity.setThanhtien(getPrice(maSP));
            //==========================
            SellPhonesDBContext.addNewObject(chitietDhEntity);
        }
    }

    public int checkSPKM(int maSP){
        try {
            SPkhuyenmaiEntity sPkhuyenmaiEntity = (SPkhuyenmaiEntity) openSession().createQuery("from SPkhuyenmaiEntity where maSp="+maSP).getSingleResult();
            return sPkhuyenmaiEntity.getGiamgia();
        }catch (NoResultException e){
            return -1;
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
            SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp=" + chitietDhEntity.getMaSp()).getSingleResult();
            SPkhuyenmaiEntity sPkhuyenmaiEntity = new SPkhuyenmaiEntity();
            HangSxEntity hangSX = new HangSxEntity();
            try {
                hangSX = (HangSxEntity) openSession().createQuery("from HangSxEntity where maHangSx="+sanphamEntity.getHangSx()).getSingleResult();
                sPkhuyenmaiEntity =   (SPkhuyenmaiEntity) openSession().createQuery("from SPkhuyenmaiEntity where maSp=" + sanphamEntity.getMaSp()).getSingleResult();
            }catch (NoResultException e){
                sPkhuyenmaiEntity = new SPkhuyenmaiEntity();
                sPkhuyenmaiEntity.setGiamgia(0);
            }
            SanPham sanPham = new SanPham(sanphamEntity,sPkhuyenmaiEntity,hangSX.getTenhang());
            list.add(new CartEntity(chitietDhEntity.getSoluong(), chitietDhEntity.getThanhtien(), sanPham));
        });
        return list;
    }
}

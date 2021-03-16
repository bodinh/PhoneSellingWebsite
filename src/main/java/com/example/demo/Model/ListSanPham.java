package com.example.demo.Model;

import com.example.demo.Hibernate.SPkhuyenmaiEntity;
import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ListSanPham {
    private List<SanPham> sanPhamList;

    private Session session;

    private Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    public ListSanPham() {
        sanPhamList = new ArrayList<>();
    }

    public ListSanPham(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

    public List<SanPham> getAllHot(){
        List<SanPham> listitems = new ArrayList<>();
        List<SanphamEntity> sanphamEntityList = openSession().createQuery("from SanphamEntity where ishot=1").list();
        for (SanphamEntity sp:sanphamEntityList
        ) {
            try{
                SPkhuyenmaiEntity sPkhuyenmaiEntity = (SPkhuyenmaiEntity) openSession().createQuery("from SPkhuyenmaiEntity where maSp="+sp.getMaSp()).getSingleResult();
                listitems.add(new SanPham(sp,sPkhuyenmaiEntity));
            }catch (NoResultException e){
                listitems.add(new SanPham(sp,new SPkhuyenmaiEntity()));
            }
        }
        return listitems;
    }
    public List<SanPham> getAllNew(){
        List<SanPham> listitems = new ArrayList<>();
        List<SanphamEntity> sanphamEntityList = openSession().createQuery("from SanphamEntity where isnew=1").list();
        for (SanphamEntity sp:sanphamEntityList
        ) {
            try{
                SPkhuyenmaiEntity sPkhuyenmaiEntity = (SPkhuyenmaiEntity) openSession().createQuery("from SPkhuyenmaiEntity where maSp="+sp.getMaSp()).getSingleResult();
                listitems.add(new SanPham(sp,sPkhuyenmaiEntity));
            }catch (NoResultException e){
                listitems.add(new SanPham(sp,new SPkhuyenmaiEntity()));
            }
        }
        return listitems;
    }
    public List<SanPham> getAllSale(){
        List<SanPham> listitems = new ArrayList<>();
        List<SanphamEntity> sanphamEntityList = openSession().createQuery("from SanphamEntity").list();
        for (SanphamEntity sp:sanphamEntityList
        ) {
            try{
                SPkhuyenmaiEntity sPkhuyenmaiEntity = (SPkhuyenmaiEntity) openSession().createQuery("from SPkhuyenmaiEntity where maSp="+sp.getMaSp()).getSingleResult();
                listitems.add(new SanPham(sp,sPkhuyenmaiEntity));
            }catch (NoResultException e){
                e.printStackTrace();
            }
        }
        return listitems;
    }
}

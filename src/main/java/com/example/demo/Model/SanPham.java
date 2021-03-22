package com.example.demo.Model;

import com.example.demo.Hibernate.SPkhuyenmaiEntity;
import com.example.demo.Hibernate.SanphamEntity;

import java.util.List;

public class SanPham {
    private SanphamEntity sanphamEntity;
    private SPkhuyenmaiEntity sPkhuyenmaiEntity;
    private String brand;

    public SanPham() {

    }

    public SanPham(SanphamEntity sanphamEntity, SPkhuyenmaiEntity sPkhuyenmaiEntity, String brand) {
        this.sanphamEntity = sanphamEntity;
        this.sPkhuyenmaiEntity = sPkhuyenmaiEntity;
        this.brand = brand;
    }

    public SanphamEntity getSanphamEntity() {
        return sanphamEntity;
    }

    public void setSanphamEntity(SanphamEntity sanphamEntity) {
        this.sanphamEntity = sanphamEntity;
    }

    public SPkhuyenmaiEntity getsPkhuyenmaiEntity() {
        return sPkhuyenmaiEntity;
    }

    public void setsPkhuyenmaiEntity(SPkhuyenmaiEntity sPkhuyenmaiEntity) {
        this.sPkhuyenmaiEntity = sPkhuyenmaiEntity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

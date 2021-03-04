package com.example.demo.Model;

import com.example.demo.Hibernate.HangSxEntity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;


public class LoaiSpHangSp implements Serializable {
    private int LoaiSP;
    private String TenLoai;

    List<HangSxEntity> hangSxEntityList;

    public LoaiSpHangSp() {
        super();
    }

    public LoaiSpHangSp(int loaiSP, String tenLoai, List<HangSxEntity> hangSxEntityList) {
        LoaiSP = loaiSP;
        TenLoai = tenLoai;
        this.hangSxEntityList = hangSxEntityList;
    }

    public int getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(int loaiSP) {
        LoaiSP = loaiSP;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public List<HangSxEntity> getHangSxEntityList() {
        return hangSxEntityList;
    }

    public void setHangSxEntityList(List<HangSxEntity> hangSxEntityList) {
        this.hangSxEntityList = hangSxEntityList;
    }
}

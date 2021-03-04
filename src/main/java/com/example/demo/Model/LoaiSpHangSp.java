package com.example.demo.Model;

import com.example.demo.Hibernate.HangSxEntity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;


public class LoaiSpHangSp implements Serializable {
    private int LoaiSP;
    private String TenLoai;

    List<HangSX> hangSXES;

    public LoaiSpHangSp() {
        super();
    }

    public LoaiSpHangSp(int loaiSP, String tenLoai, List<HangSX> hangSXES) {
        LoaiSP = loaiSP;
        TenLoai = tenLoai;
        this.hangSXES = hangSXES;
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

    public List<HangSX> getHangSXES() {
        return hangSXES;
    }

    public void setHangSXES(List<HangSX> hangSXES) {
        this.hangSXES = hangSXES;
    }
}

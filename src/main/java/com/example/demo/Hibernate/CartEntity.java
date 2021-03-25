package com.example.demo.Hibernate;

import com.example.demo.Model.SanPham;

public class CartEntity {
    private int soluong;
    private long tonggia;
    private SanPham sanPham;

    public CartEntity() {

    }

    public CartEntity(int soluong, long tonggia, SanPham sanPham) {
        this.soluong = soluong;
        this.tonggia = tonggia;
        this.sanPham = sanPham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getTonggia() {
        return tonggia;
    }

    public void setTonggia(long tonggia) {
        this.tonggia = tonggia;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
}

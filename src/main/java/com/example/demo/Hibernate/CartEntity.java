package com.example.demo.Hibernate;

public class CartEntity {
    private int soluong;
    private long tonggia;
    private SanphamEntity sanpham;

    public CartEntity() {

    }

    public CartEntity(int soluong, long tonggia, SanphamEntity sanpham) {
        this.soluong = soluong;
        this.tonggia = tonggia;
        this.sanpham = sanpham;
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

    public SanphamEntity getSanpham() {
        return sanpham;
    }

    public void setSanpham(SanphamEntity sanpham) {
        this.sanpham = sanpham;
    }
}

package com.example.demo.Model;

import org.hibernate.annotations.Entity;

@Entity
public class ProductDiscount {
    private int maSp;
    private String tenSp;
    private int loaiSp;
    private String tenLoai;
    private int hangSx;
    private String anh;
    private Integer isnew;
    private Integer ishot;
    private String tenhang;
    private long gia;
    private int giamgia;
    private long giamoi;

    public ProductDiscount() {
        super();
    }

    public ProductDiscount(int maSp, String tenSp, int loaiSp, String tenLoai, int hangSx, String anh, Integer isnew, Integer ishot, String tenhang, long gia, int giamgia, long giamoi) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.loaiSp = loaiSp;
        this.tenLoai = tenLoai;
        this.hangSx = hangSx;
        this.anh = anh;
        this.isnew = isnew;
        this.ishot = ishot;
        this.tenhang = tenhang;
        this.gia = gia;
        this.giamgia = giamgia;
        this.giamoi = giamoi;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getLoaiSp() {
        return loaiSp;
    }

    public void setLoaiSp(int loaiSp) {
        this.loaiSp = loaiSp;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getHangSx() {
        return hangSx;
    }

    public void setHangSx(int hangSx) {
        this.hangSx = hangSx;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public long getGiamoi() {
        return giamoi;
    }

    public void setGiamoi(long giamoi) {
        this.giamoi = giamoi;
    }
}

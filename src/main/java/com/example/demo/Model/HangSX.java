package com.example.demo.Model;

public class HangSX {
    private int maHangSx;
    private String trusochinh;
    private String quocgia;
    private String tenhang;

    public HangSX() {
        super();
    }

    public HangSX(int maHangSx, String trusochinh, String quocgia, String tenhang) {
        this.maHangSx = maHangSx;
        this.trusochinh = trusochinh;
        this.quocgia = quocgia;
        this.tenhang = tenhang;
    }

    public int getMaHangSx() {
        return maHangSx;
    }

    public void setMaHangSx(int maHangSx) {
        this.maHangSx = maHangSx;
    }

    public String getTrusochinh() {
        return trusochinh;
    }

    public void setTrusochinh(String trusochinh) {
        this.trusochinh = trusochinh;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }
}

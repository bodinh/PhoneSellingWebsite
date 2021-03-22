package com.example.demo.Model;

public class DoanhThuThang {
    private int thang;
    private long doanhThuThang;

    public DoanhThuThang() {

    }

    public DoanhThuThang(int thang, long doanhThuThang) {
        this.thang = thang;
        this.doanhThuThang = doanhThuThang;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public long getDoanhThuThang() {
        return doanhThuThang;
    }

    public void setDoanhThuThang(long doanhThuThang) {
        this.doanhThuThang = doanhThuThang;
    }
}

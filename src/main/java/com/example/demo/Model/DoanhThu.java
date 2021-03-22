package com.example.demo.Model;

import java.util.List;

public class DoanhThu {
    private int nam;
    private List<DoanhThuThang> doanhthu;
    public DoanhThu() {

    }

    public DoanhThu(int nam, List<DoanhThuThang> doanhthu) {
        this.nam = nam;
        this.doanhthu = doanhthu;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public List<DoanhThuThang> getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(List<DoanhThuThang> doanhthu) {
        this.doanhthu = doanhthu;
    }
}

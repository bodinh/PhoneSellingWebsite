package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DonhangKH", schema = "dbo", catalog = "Sellphones")
public class DonhangKhEntity {
    private int maDh;
    private int maKh;
    private Double phivanchuyen;
    private String phuongthucTt;
    private Timestamp ngaydatmua;
    private long tongTien;
    private Integer tinhtrangdonhang;
    private String ghichu;

    @Id
    @Column(name = "MaDH", nullable = false)
    public int getMaDh() {
        return maDh;
    }

    public void setMaDh(int maDh) {
        this.maDh = maDh;
    }

    @Basic
    @Column(name = "MaKH", nullable = false)
    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    @Basic
    @Column(name = "Phivanchuyen", nullable = true, precision = 0)
    public Double getPhivanchuyen() {
        return phivanchuyen;
    }

    public void setPhivanchuyen(Double phivanchuyen) {
        this.phivanchuyen = phivanchuyen;
    }

    @Basic
    @Column(name = "PhuongthucTT", nullable = false, length = 50)
    public String getPhuongthucTt() {
        return phuongthucTt;
    }

    public void setPhuongthucTt(String phuongthucTt) {
        this.phuongthucTt = phuongthucTt;
    }

    @Basic
    @Column(name = "Ngaydatmua", nullable = false)
    public Timestamp getNgaydatmua() {
        return ngaydatmua;
    }

    public void setNgaydatmua(Timestamp ngaydatmua) {
        this.ngaydatmua = ngaydatmua;
    }

    @Basic
    @Column(name = "TongTien", nullable = true, precision = 0)
    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    @Basic
    @Column(name = "Tinhtrangdonhang", nullable = true)
    public Integer getTinhtrangdonhang() {
        return tinhtrangdonhang;
    }

    public void setTinhtrangdonhang(Integer tinhtrangdonhang) {
        this.tinhtrangdonhang = tinhtrangdonhang;
    }

    @Basic
    @Column(name = "ghichu", nullable = true, length = 100)
    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonhangKhEntity that = (DonhangKhEntity) o;
        return maDh == that.maDh &&
                maKh == that.maKh &&
                Objects.equals(phivanchuyen, that.phivanchuyen) &&
                Objects.equals(phuongthucTt, that.phuongthucTt) &&
                Objects.equals(ngaydatmua, that.ngaydatmua) &&
                Objects.equals(tongTien, that.tongTien) &&
                Objects.equals(tinhtrangdonhang, that.tinhtrangdonhang) &&
                Objects.equals(ghichu, that.ghichu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDh, maKh, phivanchuyen, phuongthucTt, ngaydatmua, tongTien, tinhtrangdonhang, ghichu);
    }
}

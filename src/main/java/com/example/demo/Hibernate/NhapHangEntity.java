package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "NhapHang", schema = "dbo", catalog = "Sellphones")
public class NhapHangEntity {
    private int maNhapHang;
    private Integer soluongNhap;
    private Double donGia;
    private Date ngayNhap;

    @Id
    @Column(name = "MaNhapHang", nullable = false)
    public int getMaNhapHang() {
        return maNhapHang;
    }

    public void setMaNhapHang(int maNhapHang) {
        this.maNhapHang = maNhapHang;
    }

    @Basic
    @Column(name = "SoluongNhap", nullable = true)
    public Integer getSoluongNhap() {
        return soluongNhap;
    }

    public void setSoluongNhap(Integer soluongNhap) {
        this.soluongNhap = soluongNhap;
    }

    @Basic
    @Column(name = "DonGia", nullable = true, precision = 0)
    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    @Basic
    @Column(name = "NgayNhap", nullable = true)
    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NhapHangEntity that = (NhapHangEntity) o;
        return maNhapHang == that.maNhapHang &&
                Objects.equals(soluongNhap, that.soluongNhap) &&
                Objects.equals(donGia, that.donGia) &&
                Objects.equals(ngayNhap, that.ngayNhap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhapHang, soluongNhap, donGia, ngayNhap);
    }
}

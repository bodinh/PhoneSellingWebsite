package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Khuyenmai", schema = "dbo", catalog = "Sellphones")
public class KhuyenmaiEntity {
    private int maKm;
    private Timestamp ngaybatdau;
    private Timestamp ngayketthuc;
    private String noidung;
    private String ten;

    @Id
    @Column(name = "MaKM", nullable = false)
    public int getMaKm() {
        return maKm;
    }

    public void setMaKm(int maKm) {
        this.maKm = maKm;
    }

    @Basic
    @Column(name = "Ngaybatdau", nullable = false)
    public Timestamp getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Timestamp ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    @Basic
    @Column(name = "Ngayketthuc", nullable = false)
    public Timestamp getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Timestamp ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    @Basic
    @Column(name = "noidung", nullable = true, length = 400)
    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    @Basic
    @Column(name = "ten", nullable = true, length = 100)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenmaiEntity that = (KhuyenmaiEntity) o;
        return maKm == that.maKm &&
                Objects.equals(ngaybatdau, that.ngaybatdau) &&
                Objects.equals(ngayketthuc, that.ngayketthuc) &&
                Objects.equals(noidung, that.noidung) &&
                Objects.equals(ten, that.ten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKm, ngaybatdau, ngayketthuc, noidung, ten);
    }
}

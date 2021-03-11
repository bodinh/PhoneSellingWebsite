package com.example.demo.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ChitietDH", schema = "dbo", catalog = "Sellphones")
public class ChitietDhEntity {
    private int maChitietDh;
    private Integer maDh;
    private Integer maSp;
    private Integer soluong;
    private long thanhtien;

    @Id
    @Column(name = "MaChitietDH", nullable = false)
    public int getMaChitietDh() {
        return maChitietDh;
    }

    public void setMaChitietDh(int maChitietDh) {
        this.maChitietDh = maChitietDh;
    }

    @Basic
    @Column(name = "MaDH", nullable = true)
    public Integer getMaDh() {
        return maDh;
    }

    public void setMaDh(Integer maDh) {
        this.maDh = maDh;
    }

    @Basic
    @Column(name = "MaSP", nullable = true)
    public Integer getMaSp() {
        return maSp;
    }

    public void setMaSp(Integer maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "Soluong", nullable = true)
    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    @Basic
    @Column(name = "Thanhtien", nullable = true, precision = 0)
    public long getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(long thanhtien) {
        this.thanhtien = thanhtien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChitietDhEntity that = (ChitietDhEntity) o;
        return maChitietDh == that.maChitietDh &&
                Objects.equals(maDh, that.maDh) &&
                Objects.equals(maSp, that.maSp) &&
                Objects.equals(soluong, that.soluong) &&
                Objects.equals(thanhtien, that.thanhtien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChitietDh, maDh, maSp, soluong, thanhtien);
    }
}

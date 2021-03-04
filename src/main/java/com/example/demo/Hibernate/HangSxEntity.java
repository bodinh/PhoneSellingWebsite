package com.example.demo.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HangSX", schema = "dbo", catalog = "Sellphones")
public class HangSxEntity {
    private int maHangSx;
    private String trusochinh;
    private String quocgia;
    private String tenhang;

    @Id
    @Column(name = "MaHangSX", nullable = false)
    public int getMaHangSx() {
        return maHangSx;
    }

    public void setMaHangSx(int maHangSx) {
        this.maHangSx = maHangSx;
    }

    @Basic
    @Column(name = "Trusochinh", nullable = false, length = 50)
    public String getTrusochinh() {
        return trusochinh;
    }

    public void setTrusochinh(String trusochinh) {
        this.trusochinh = trusochinh;
    }

    @Basic
    @Column(name = "Quocgia", nullable = true, length = 50)
    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    @Basic
    @Column(name = "tenhang", nullable = true, length = 50)
    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HangSxEntity that = (HangSxEntity) o;
        return maHangSx == that.maHangSx &&
                Objects.equals(trusochinh, that.trusochinh) &&
                Objects.equals(quocgia, that.quocgia) &&
                Objects.equals(tenhang, that.tenhang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHangSx, trusochinh, quocgia, tenhang);
    }
}

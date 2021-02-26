package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Binhluan", schema = "dbo", catalog = "Sellphones")
public class BinhluanEntity {
    private int maBl;
    private int maSp;
    private Integer maKh;
    private String noidung;
    private Timestamp ngaydang;
    private String hoten;
    private String email;

    @Id
    @Column(name = "MaBL", nullable = false)
    public int getMaBl() {
        return maBl;
    }

    public void setMaBl(int maBl) {
        this.maBl = maBl;
    }

    @Basic
    @Column(name = "MaSP", nullable = false)
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "MaKH", nullable = true)
    public Integer getMaKh() {
        return maKh;
    }

    public void setMaKh(Integer maKh) {
        this.maKh = maKh;
    }

    @Basic
    @Column(name = "Noidung", nullable = true, length = 500)
    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    @Basic
    @Column(name = "Ngaydang", nullable = false)
    public Timestamp getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(Timestamp ngaydang) {
        this.ngaydang = ngaydang;
    }

    @Basic
    @Column(name = "Hoten", nullable = false, length = 50)
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinhluanEntity that = (BinhluanEntity) o;
        return maBl == that.maBl &&
                maSp == that.maSp &&
                Objects.equals(maKh, that.maKh) &&
                Objects.equals(noidung, that.noidung) &&
                Objects.equals(ngaydang, that.ngaydang) &&
                Objects.equals(hoten, that.hoten) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maBl, maSp, maKh, noidung, ngaydang, hoten, email);
    }
}

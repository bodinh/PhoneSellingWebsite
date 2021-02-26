package com.example.demo.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SPkhuyenmai", schema = "dbo", catalog = "Sellphones")
@IdClass(SPkhuyenmaiEntityPK.class)
public class SPkhuyenmaiEntity {
    private int maSpkm;
    private int maSp;
    private Integer giamgia;

    @Id
    @Column(name = "MaSPKM", nullable = false)
    public int getMaSpkm() {
        return maSpkm;
    }

    public void setMaSpkm(int maSpkm) {
        this.maSpkm = maSpkm;
    }

    @Id
    @Column(name = "MaSP", nullable = false)
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "Giamgia", nullable = true)
    public Integer getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(Integer giamgia) {
        this.giamgia = giamgia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SPkhuyenmaiEntity that = (SPkhuyenmaiEntity) o;
        return maSpkm == that.maSpkm &&
                maSp == that.maSp &&
                Objects.equals(giamgia, that.giamgia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSpkm, maSp, giamgia);
    }
}

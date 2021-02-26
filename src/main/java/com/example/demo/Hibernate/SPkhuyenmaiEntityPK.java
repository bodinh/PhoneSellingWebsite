package com.example.demo.Hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SPkhuyenmaiEntityPK implements Serializable {
    private int maSpkm;
    private int maSp;

    @Column(name = "MaSPKM", nullable = false)
    @Id
    public int getMaSpkm() {
        return maSpkm;
    }

    public void setMaSpkm(int maSpkm) {
        this.maSpkm = maSpkm;
    }

    @Column(name = "MaSP", nullable = false)
    @Id
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SPkhuyenmaiEntityPK that = (SPkhuyenmaiEntityPK) o;
        return maSpkm == that.maSpkm &&
                maSp == that.maSp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSpkm, maSp);
    }
}

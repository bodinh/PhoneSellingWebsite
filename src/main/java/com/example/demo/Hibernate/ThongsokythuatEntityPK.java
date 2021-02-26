package com.example.demo.Hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ThongsokythuatEntityPK implements Serializable {
    private int maSp;
    private String thuoctinh;
    private String giatri;

    @Column(name = "MaSP", nullable = false)
    @Id
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Column(name = "Thuoctinh", nullable = false, length = 100)
    @Id
    public String getThuoctinh() {
        return thuoctinh;
    }

    public void setThuoctinh(String thuoctinh) {
        this.thuoctinh = thuoctinh;
    }

    @Column(name = "Giatri", nullable = false, length = 150)
    @Id
    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThongsokythuatEntityPK that = (ThongsokythuatEntityPK) o;
        return maSp == that.maSp &&
                Objects.equals(thuoctinh, that.thuoctinh) &&
                Objects.equals(giatri, that.giatri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSp, thuoctinh, giatri);
    }
}

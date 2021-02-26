package com.example.demo.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Thongsokythuat", schema = "dbo", catalog = "Sellphones")
@IdClass(ThongsokythuatEntityPK.class)
public class ThongsokythuatEntity {
    private int maSp;
    private String thuoctinh;
    private String giatri;

    @Id
    @Column(name = "MaSP", nullable = false)
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Id
    @Column(name = "Thuoctinh", nullable = false, length = 100)
    public String getThuoctinh() {
        return thuoctinh;
    }

    public void setThuoctinh(String thuoctinh) {
        this.thuoctinh = thuoctinh;
    }

    @Id
    @Column(name = "Giatri", nullable = false, length = 150)
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
        ThongsokythuatEntity that = (ThongsokythuatEntity) o;
        return maSp == that.maSp &&
                Objects.equals(thuoctinh, that.thuoctinh) &&
                Objects.equals(giatri, that.giatri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSp, thuoctinh, giatri);
    }
}

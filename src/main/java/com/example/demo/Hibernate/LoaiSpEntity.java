package com.example.demo.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "LoaiSP", schema = "dbo", catalog = "Sellphones")
public class LoaiSpEntity {
    private int maLoai;
    private String tenLoai;

    @Id
    @Column(name = "MaLoai", nullable = false)
    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    @Basic
    @Column(name = "TenLoai", nullable = false, length = 100)
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoaiSpEntity that = (LoaiSpEntity) o;
        return maLoai == that.maLoai &&
                Objects.equals(tenLoai, that.tenLoai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLoai, tenLoai);
    }
}

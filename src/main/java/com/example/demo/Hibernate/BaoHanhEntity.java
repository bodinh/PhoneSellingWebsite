package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "BaoHanh", schema = "dbo", catalog = "Sellphones")
public class BaoHanhEntity {
    private int mabaohanh;
    private Integer maSp;
    private String thoigianbaohanh;
    private Integer status;
    private Timestamp ngayhetbaohanh;

    @Id
    @Column(name = "Mabaohanh", nullable = false)
    public int getMabaohanh() {
        return mabaohanh;
    }

    public void setMabaohanh(int mabaohanh) {
        this.mabaohanh = mabaohanh;
    }

    @Basic
    @Column(name = "MaSp", nullable = true)
    public Integer getMaSp() {
        return maSp;
    }

    public void setMaSp(Integer maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "thoigianbaohanh", nullable = true, length = 50)
    public String getThoigianbaohanh() {
        return thoigianbaohanh;
    }

    public void setThoigianbaohanh(String thoigianbaohanh) {
        this.thoigianbaohanh = thoigianbaohanh;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "Ngayhetbaohanh", nullable = true)
    public Timestamp getNgayhetbaohanh() {
        return ngayhetbaohanh;
    }

    public void setNgayhetbaohanh(Timestamp ngayhetbaohanh) {
        this.ngayhetbaohanh = ngayhetbaohanh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaoHanhEntity that = (BaoHanhEntity) o;
        return mabaohanh == that.mabaohanh &&
                Objects.equals(maSp, that.maSp) &&
                Objects.equals(thoigianbaohanh, that.thoigianbaohanh) &&
                Objects.equals(status, that.status) &&
                Objects.equals(ngayhetbaohanh, that.ngayhetbaohanh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mabaohanh, maSp, thoigianbaohanh, status, ngayhetbaohanh);
    }
}

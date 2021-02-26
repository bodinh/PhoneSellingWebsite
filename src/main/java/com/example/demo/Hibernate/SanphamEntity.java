package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Sanpham", schema = "dbo", catalog = "Sellphones")
public class SanphamEntity {
    private int maSp;
    private String tenSp;
    private int loaiSp;
    private int hangSx;
    private String xuatxu;
    private long gia;
    private String mota;
    private String anh;
    private Integer isnew;
    private Integer ishot;
    private Integer soLuong;
    private Timestamp thoiDiemNhapHang;

    @Id
    @Column(name = "MaSP", nullable = false)
    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    @Basic
    @Column(name = "TenSP", nullable = false, length = 500)
    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    @Basic
    @Column(name = "LoaiSP", nullable = false)
    public int getLoaiSp() {
        return loaiSp;
    }

    public void setLoaiSp(int loaiSp) {
        this.loaiSp = loaiSp;
    }

    @Basic
    @Column(name = "HangSX", nullable = false)
    public int getHangSx() {
        return hangSx;
    }

    public void setHangSx(int hangSx) {
        this.hangSx = hangSx;
    }

    @Basic
    @Column(name = "Xuatxu", nullable = false, length = 100)
    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    @Basic
    @Column(name = "Gia", nullable = false, precision = 0)
    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    @Basic
    @Column(name = "Mota", nullable = true, length = 500)
    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Basic
    @Column(name = "Anh", nullable = false, length = 500)
    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    @Basic
    @Column(name = "Isnew", nullable = true)
    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    @Basic
    @Column(name = "Ishot", nullable = true)
    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    @Basic
    @Column(name = "SoLuong", nullable = true)
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Basic
    @Column(name = "ThoiDiemNhapHang", nullable = true)
    public Timestamp getThoiDiemNhapHang() {
        return thoiDiemNhapHang;
    }

    public void setThoiDiemNhapHang(Timestamp thoiDiemNhapHang) {
        this.thoiDiemNhapHang = thoiDiemNhapHang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanphamEntity that = (SanphamEntity) o;
        return maSp == that.maSp &&
                loaiSp == that.loaiSp &&
                hangSx == that.hangSx &&
                Double.compare(that.gia, gia) == 0 &&
                Objects.equals(tenSp, that.tenSp) &&
                Objects.equals(xuatxu, that.xuatxu) &&
                Objects.equals(mota, that.mota) &&
                Objects.equals(anh, that.anh) &&
                Objects.equals(isnew, that.isnew) &&
                Objects.equals(ishot, that.ishot) &&
                Objects.equals(soLuong, that.soLuong) &&
                Objects.equals(thoiDiemNhapHang, that.thoiDiemNhapHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSp, tenSp, loaiSp, hangSx, xuatxu, gia, mota, anh, isnew, ishot, soLuong, thoiDiemNhapHang);
    }
}

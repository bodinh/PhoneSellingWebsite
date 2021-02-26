package com.example.demo.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Hopdong", schema = "dbo", catalog = "Sellphones")
public class HopdongEntity {
    private int idHopdong;
    private int idsp;
    private int idNhacungcap;
    private Timestamp ngayky;
    private Integer soluong;
    private Double tongtien;

    @Id
    @Column(name = "IDHopdong", nullable = false)
    public int getIdHopdong() {
        return idHopdong;
    }

    public void setIdHopdong(int idHopdong) {
        this.idHopdong = idHopdong;
    }

    @Basic
    @Column(name = "IDSP", nullable = false)
    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    @Basic
    @Column(name = "IDNhacungcap", nullable = false)
    public int getIdNhacungcap() {
        return idNhacungcap;
    }

    public void setIdNhacungcap(int idNhacungcap) {
        this.idNhacungcap = idNhacungcap;
    }

    @Basic
    @Column(name = "Ngayky", nullable = true)
    public Timestamp getNgayky() {
        return ngayky;
    }

    public void setNgayky(Timestamp ngayky) {
        this.ngayky = ngayky;
    }

    @Basic
    @Column(name = "soluong", nullable = true)
    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    @Basic
    @Column(name = "tongtien", nullable = true, precision = 0)
    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HopdongEntity that = (HopdongEntity) o;
        return idHopdong == that.idHopdong &&
                idsp == that.idsp &&
                idNhacungcap == that.idNhacungcap &&
                Objects.equals(ngayky, that.ngayky) &&
                Objects.equals(soluong, that.soluong) &&
                Objects.equals(tongtien, that.tongtien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHopdong, idsp, idNhacungcap, ngayky, soluong, tongtien);
    }
}

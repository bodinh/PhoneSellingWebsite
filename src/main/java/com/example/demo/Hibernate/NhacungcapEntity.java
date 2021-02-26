package com.example.demo.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Nhacungcap", schema = "dbo", catalog = "Sellphones")
public class NhacungcapEntity {
    private int idNhacungcap;
    private String tenNhaungcap;
    private String phonenumber;
    private String email;

    @Id
    @Column(name = "IDNhacungcap", nullable = false)
    public int getIdNhacungcap() {
        return idNhacungcap;
    }

    public void setIdNhacungcap(int idNhacungcap) {
        this.idNhacungcap = idNhacungcap;
    }

    @Basic
    @Column(name = "TenNhaungcap", nullable = false, length = 100)
    public String getTenNhaungcap() {
        return tenNhaungcap;
    }

    public void setTenNhaungcap(String tenNhaungcap) {
        this.tenNhaungcap = tenNhaungcap;
    }

    @Basic
    @Column(name = "phonenumber", nullable = false, length = 15)
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
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
        NhacungcapEntity that = (NhacungcapEntity) o;
        return idNhacungcap == that.idNhacungcap &&
                Objects.equals(tenNhaungcap, that.tenNhaungcap) &&
                Objects.equals(phonenumber, that.phonenumber) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNhacungcap, tenNhaungcap, phonenumber, email);
    }
}

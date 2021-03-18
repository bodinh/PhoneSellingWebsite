package com.example.demo.ApiController;

import com.example.demo.Hibernate.DonhangKhEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.DoanhThu;
import com.example.demo.Model.DoanhThuThang;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/doanhthu")
public class DoanhThuApiController {
    private Session session;

    private Session openSession() {
        return SellPhonesDBContext.getSession();
    }

    @GetMapping("/")
    public List<DoanhThu> getDoanhThu() {
        Session ss = openSession();
        List<DonhangKhEntity> khEntities = ss.createQuery("from DonhangKhEntity where tinhtrangdonhang=1 order by ngaydatmua desc ").list();
        List<DoanhThu> doanhThuNam = new ArrayList<>();
        List<DoanhThuThang> doanhThuThang = initdDoanhThuThang();
        for (int i = 0; i < khEntities.size(); i++) {
            String[] sa = String.valueOf(khEntities.get(i).getNgaydatmua()).split("-");
            if (i > 0) {
                String[] sap = String.valueOf(khEntities.get(i - 1).getNgaydatmua()).split("-");
                //trường hợp sang năm mới
                if (!sa[0].equals(sap[0])) {
                    doanhThuNam.add(new DoanhThu(Integer.valueOf(sap[0]), doanhThuThang));
                    doanhThuThang = initdDoanhThuThang();
                } else
                    //nếu một tháng có nhiều đơn hàng
                    if (sa[1].equals(sap[1])) {
                        DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sap[1]) - 1);
                        dtt.setDoanhThuThang(dtt.getDoanhThuThang() + khEntities.get(i).getTongTien());
                    }
                if (i == khEntities.size() - 1) {
                    DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sa[1]) - 1);
                    dtt.setDoanhThuThang(khEntities.get(i).getTongTien());
                    doanhThuNam.add(new DoanhThu(Integer.valueOf(sa[0]), doanhThuThang));
                }
            }
            DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sa[1]) - 1);
            dtt.setDoanhThuThang(khEntities.get(i).getTongTien());
            if (khEntities.size() == 1) {
                doanhThuNam.add(new DoanhThu(Integer.valueOf(sa[0]), doanhThuThang));
            }
        }
        return doanhThuNam;
    }

    public List<DoanhThuThang> initdDoanhThuThang() {
        List<DoanhThuThang> doanhThuThang = new ArrayList<>();
        doanhThuThang.add(new DoanhThuThang(1, 0));
        doanhThuThang.add(new DoanhThuThang(2, 0));
        doanhThuThang.add(new DoanhThuThang(3, 0));
        doanhThuThang.add(new DoanhThuThang(4, 0));
        doanhThuThang.add(new DoanhThuThang(5, 0));
        doanhThuThang.add(new DoanhThuThang(6, 0));
        doanhThuThang.add(new DoanhThuThang(7, 0));
        doanhThuThang.add(new DoanhThuThang(8, 0));
        doanhThuThang.add(new DoanhThuThang(9, 0));
        doanhThuThang.add(new DoanhThuThang(10, 0));
        doanhThuThang.add(new DoanhThuThang(11, 0));
        doanhThuThang.add(new DoanhThuThang(12, 0));
        return doanhThuThang;
    }
}

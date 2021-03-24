package com.example.demo.ApiController;

import com.example.demo.Hibernate.DonhangKhEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.DoanhThu;
import com.example.demo.Model.DoanhThuThang;
import org.hibernate.Criteria;
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
            //dùng split tách chuỗi lấy năm và tháng
            String[] sa = String.valueOf(khEntities.get(i).getNgaydatmua()).split("-");
            if (i > 0) { //nếu i > 0, xét xem năm sau có bị trùng với năm trước không, tháng sau có trùng với tháng trước không
                String[] sap = String.valueOf(khEntities.get(i - 1).getNgaydatmua()).split("-");
                //trường hợp năm hiện tại khác năm trước, thì thêm mới doanh thu của năm cũ
                if (!sa[0].equals(sap[0])) {
                    doanhThuNam.add(new DoanhThu(Integer.valueOf(sap[0]), doanhThuThang));
                    doanhThuThang = initdDoanhThuThang();
                } else
                    //nếu một tháng có nhiều đơn hàng thì cộng dồn vào doanh thu tháng đó
                    if (sa[1].equals(sap[1])) {
                        DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sap[1]) - 1);
                        dtt.setDoanhThuThang(dtt.getDoanhThuThang() + khEntities.get(i).getTongTien());
                    }
                    //cuối cùng xét xem phần tủ có phải cuối cùng danh sách không, nếu đúng thì thêm mới doanh thu của năm đó và kết thúc.
                if (i == khEntities.size() - 1) {
                    DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sa[1]) - 1);
                    dtt.setDoanhThuThang(khEntities.get(i).getTongTien());
                    doanhThuNam.add(new DoanhThu(Integer.valueOf(sa[0]), doanhThuThang));
                }
                continue;
            }
            DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sa[1]) - 1);
            dtt.setDoanhThuThang(khEntities.get(i).getTongTien());
            if (khEntities.size() == 1) {
                doanhThuNam.add(new DoanhThu(Integer.valueOf(sa[0]), doanhThuThang));
            }
        }
        return doanhThuNam;
    }

    @GetMapping("/lai")
    public List<DoanhThu> getLaiHangThang(){
        Session ss = openSession();
        List<DoanhThu> doanhThuNam = new ArrayList<>();
        List<DoanhThuThang> doanhThuThang = initdDoanhThuThang();
        List<Object> khEntities = ss.createSQLQuery("SELECT dh.Ngaydatmua,ctdh.Thanhtien,ctdh.Soluong,nh.SoluongNhap,nh.DonGia FROM DonhangKH dh INNER JOIN ChitietDH ctdh ON ctdh.MaDH = dh.MaDH INNER JOIN Sanpham sp ON sp.MaSP = ctdh.MaSP INNER JOIN NhapHang nh ON nh.MaSanPham = sp.MaSP WHERE dh.Tinhtrangdonhang=1 ORDER BY dh.Ngaydatmua DESC\n").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        for(int i=0;i<khEntities.size();i++){
            HashMap<String,Object> hashMap = (HashMap<String, Object>) khEntities.get(i);
            String Ngaydatmua = String.valueOf(hashMap.get("Ngaydatmua"));
            long Thanhtien = new Double(String.valueOf(hashMap.get("Thanhtien"))).longValue();
            int SoLuong = (int) hashMap.get("Soluong");
            int SoluongNhap = (int) hashMap.get("SoluongNhap");
            long DonGia = new Double(String.valueOf(hashMap.get("DonGia"))).longValue();
            long laiThang = Thanhtien - (DonGia * SoLuong) ;
            //dùng split tách chuỗi lấy năm và tháng
            String[] sa = Ngaydatmua.split("-");
            if (i > 0) { //nếu i > 0, xét xem năm sau có bị trùng với năm trước không, tháng sau có trùng với tháng trước không
                HashMap<String,Object> hashMapl = (HashMap<String, Object>) khEntities.get(i-1);
                String Ngaydatmual = String.valueOf(hashMapl.get("Ngaydatmua"));
                long Thanhtienl = new Double(String.valueOf(hashMapl.get("Thanhtien"))).longValue();
                int SoLuongl = (int) hashMapl.get("Soluong");
                int SoluongNhapl = (int) hashMapl.get("SoluongNhap");
                long DonGial = new Double(String.valueOf(hashMapl.get("DonGia"))).longValue();
                long laiThangl = Thanhtienl -( DonGial * SoLuongl );

                String[] sap = Ngaydatmual.split("-");
                //trường hợp năm hiện tại khác năm trước, thì thêm mới doanh thu của năm cũ
                if (!sa[0].equals(sap[0])) {
                    doanhThuNam.add(new DoanhThu(Integer.valueOf(sap[0]), doanhThuThang));
                    doanhThuThang = initdDoanhThuThang();
                } else
                    //nếu một tháng có nhiều đơn hàng thì cộng dồn vào doanh thu tháng đó
                    if (sa[1].equals(sap[1])) {
                        DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sap[1]) - 1);
                        dtt.setDoanhThuThang(dtt.getDoanhThuThang() + laiThang);
                    }
                //cuối cùng xét xem phần tủ có phải cuối cùng danh sách không, nếu đúng thì thêm mới doanh thu của năm đó và kết thúc.
                if (i == khEntities.size() - 1) {
                    DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sa[1]) - 1);
                    dtt.setDoanhThuThang(laiThang);
                    doanhThuNam.add(new DoanhThu(Integer.valueOf(sa[0]), doanhThuThang));
                }
                continue;
            }
            DoanhThuThang dtt = doanhThuThang.get(Integer.valueOf(sa[1]) - 1);
            dtt.setDoanhThuThang(laiThang);
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

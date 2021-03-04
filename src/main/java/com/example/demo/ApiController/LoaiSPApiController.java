package com.example.demo.ApiController;

import com.example.demo.Hibernate.HangSxEntity;
import com.example.demo.Hibernate.LoaiSpEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;

import com.example.demo.Model.HangSX;
import com.example.demo.Model.LoaiSpHangSp;
import org.hibernate.*;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loaisps")
public class LoaiSPApiController {

    private Session session;

    private Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    @RequestMapping("/list")
    public List<LoaiSpEntity> GetAllLoaiSPs() {
        List<LoaiSpEntity> loaiSPS = new ArrayList<>();
        loaiSPS = openSession().createQuery("from LoaiSpEntity ").list();
        return loaiSPS;
    }

    @PostMapping("/post")
    public String PostLoaiSP(@RequestBody LoaiSpEntity loaiSP) {
        String result;

        try {
            SellPhonesDBContext.addNewObject(loaiSP);
            result = "successful";
        } catch (HibernateException e) {
            result = "Mã loại sản phẩm đã tồn tại trong hệ thống.";
        } catch (Exception e) {
            result = "Mã loại sản phẩm đã tồn tại trong hệ thống.";
        }
        return result;
    }

    @GetMapping("/getLoaiSpHangSp")
    public List<LoaiSpHangSp> getLoaiSpHangSp() {
        List<LoaiSpHangSp> loaiSpHangSps = new ArrayList<>();
        List<Object[]> listObject = openSession().createQuery(" SELECT DISTINCT  s.loaiSp,l.tenLoai,s.hangSx,h.tenhang,h.quocgia,h.trusochinh FROM SanphamEntity s INNER JOIN HangSxEntity h ON h.maHangSx = s.hangSx INNER JOIN LoaiSpEntity l ON l.maLoai = s.loaiSp").list();
        List<HangSX> hangSXES = new ArrayList<>();
        for (int i = 0; i < listObject.size(); i++
        ) {
            int loaiSp = Integer.valueOf(listObject.get(i)[0].toString());
            String tenloai = listObject.get(i)[1].toString();
            int hangSx = Integer.valueOf(listObject.get(i)[2].toString());
            String tenhang = listObject.get(i)[3].toString();
            String quocgia = listObject.get(i)[4].toString();
            String trusochinh = listObject.get(i)[5].toString();
            hangSXES.add(new HangSX(hangSx, trusochinh, quocgia, tenhang));
            if (i < listObject.size() -1) {
                int loaiSpNext= Integer.valueOf(listObject.get(i)[0].toString());
                if (Integer.valueOf(listObject.get(i+1)[0].toString()) != loaiSpNext) {
                    loaiSpHangSps.add(new LoaiSpHangSp(loaiSp, tenloai, hangSXES));
                    hangSXES = new ArrayList<>();
                }
            }else {
                loaiSpHangSps.add(new LoaiSpHangSp(loaiSp, tenloai, hangSXES));
                hangSXES = new ArrayList<>();
            }
        }
        return loaiSpHangSps;
    }
}

package com.example.demo.ApiController;

import com.example.demo.Hibernate.*;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.hibernate.Session;

import java.util.List;


@RestController
public class DetailProductController {
//    @GetMapping("/product/detail")
//    public String TestController(){
//        return "Hello";
//    }
private Session session ;

private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }
@GetMapping("/product/detail/{MaSP}")
public ThongSoKT_SamPhamPK GetSanPham(@PathVariable(name = "MaSP") int MaSP) {
    SanphamEntity sanPham = null;
    List<ThongsokythuatEntity> lsThongSoKT = null;
    try {
        sanPham =(SanphamEntity) openSession().createQuery("from SanphamEntity where maSp='"+MaSP+"'").getSingleResult();
        lsThongSoKT = openSession().createQuery("From ThongsokythuatEntity where maSp ='"+MaSP+"'").getResultList();
    } catch (Exception throwables) {
        throwables.printStackTrace();
    }
    // Create JSon từ 2 dữ liueje samPham và ls
    ThongSoKT_SamPhamPK re = new ThongSoKT_SamPhamPK();
    re.sp = sanPham;
    re.lsThongSo = lsThongSoKT;
    return re;

}
//@GetMapping("/product/detail/{MaSP}")
//public SanphamEntity GetSanPham(@PathVariable(name = "MaSP") int MaSP) {
//    SanphamEntity sanPham = null;
//    try {
//        sanPham = (SanphamEntity) openSession().createQuery("select * from SanphamEntity where maSp='" + MaSP + "'").getSingleResult();
//    } catch (Exception throwables) {
//        throwables.printStackTrace();
//    }
//    return sanPham;
//
//}
}

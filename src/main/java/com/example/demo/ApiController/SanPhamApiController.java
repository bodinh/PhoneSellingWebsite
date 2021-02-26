package com.example.demo.ApiController;

import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sanphams")
public class SanPhamApiController {

    private Session session ;

    private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }

    //single item
    @GetMapping("/single/{MaSP}")
    public SanphamEntity GetSanPham(@PathVariable(name = "MaSP") int MaSP){
        SanphamEntity sanPham = null;
        try {
            sanPham =(SanphamEntity) openSession().createQuery("from SanphamEntity where maSp='"+MaSP+"'").getSingleResult();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return sanPham;
    }

    //list item
    @GetMapping("/list")
    public List<SanphamEntity> GetAllSanPham(){
        List<SanphamEntity> sanPhams = new ArrayList<>();
        try {
            sanPhams = openSession().createQuery("from SanphamEntity ORDER BY thoiDiemNhapHang DESC ").list();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return sanPhams;
    }

    @DeleteMapping("/delete/{MaSP}")
    public String DeleteSanPham(@PathVariable(name = "MaSP") int MaSP){
        String result = null;

        try {
            SellPhonesDBContext.deleteObject(MaSP);
            result="successful";
        }catch (Exception e){
            result="Không thể xoá sản phẩm này.";
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/post")
    public String PostSanPham(@RequestBody SanphamEntity sanPham){
        String  result = "";
        try {
            SellPhonesDBContext.addNewObject(sanPham);
            result = "successful";
        }catch (Exception e){
            result = "Mã sản sẩn đã tồn tại trong hệ thống.";
            e.printStackTrace();
        }
        return result;
    }

    @PutMapping("/put")
    public String PutSanPham(@RequestBody SanphamEntity sanPham){
        String result = "";
        try {
            SellPhonesDBContext.updateObject(sanPham);
            result = "successful";
        } catch (Exception throwables) {
            result="Cập nhật thất bại.Vui lòng kiểm tra lại.";
            throwables.printStackTrace();
        }
        return result;
    }

}

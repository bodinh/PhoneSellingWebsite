package com.example.demo.ApiController;

import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    //6 item random
    @GetMapping("/random")
    public List<SanphamEntity> GetRandomSanPham(){
        List<SanphamEntity> sanphamEntityList = null;
        try {
            sanphamEntityList = (List<SanphamEntity>) openSession().createQuery("from SanphamEntity").list();
            Collections.shuffle(sanphamEntityList);
            sanphamEntityList = sanphamEntityList.subList(0,6);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return sanphamEntityList;
    }
    //loadMore 4 item Hot
    @GetMapping("/hot/{start}/{number}")
    public List<SanphamEntity> Get4Hot(@PathVariable(name = "start") int start,@PathVariable(name = "number") int number){
        List<SanphamEntity> listitems=new ArrayList<>();
        listitems = openSession().createQuery("from SanphamEntity where ishot=1").list();
        try{
            if(start+ number > listitems.size() ){
                listitems=listitems.subList(start,listitems.size());
            }else listitems=listitems.subList(start,start+number);
        }catch (IllegalArgumentException e){
            return null;
        }
        return listitems;
    }
    //loadMore 4 item New
    @GetMapping("/new/{start}/{number}")
    public List<SanphamEntity> Get4New(@PathVariable(name = "start") int start,@PathVariable(name = "number") int number){
        List<SanphamEntity> listitems=new ArrayList<>();
        listitems = openSession().createQuery("from SanphamEntity where isnew=1").list();
        try{
            if(start+ number > listitems.size() ){
                listitems=listitems.subList(start,listitems.size());
            }else listitems=listitems.subList(start,start+number);
        }catch (IllegalArgumentException e){
            return null;
        }
        return listitems;
    }
    //loadMore 4 item Hot
    @GetMapping("/sale/{start}/{number}")
    public List<SanphamEntity> Get4Sale(@PathVariable(name = "start") int start,@PathVariable(name = "number") int number){
        List<SanphamEntity> listitems=new ArrayList<>();
        listitems = openSession().createQuery("from SanphamEntity").list();
        try{
            if(start+ number > listitems.size() ){
                listitems=listitems.subList(start,listitems.size());
            }else listitems=listitems.subList(start,start+number);
        }catch (IllegalArgumentException e){
            return null;
        }
        return listitems;
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
            SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp="+MaSP).getSingleResult();
            SellPhonesDBContext.deleteObject(sanphamEntity);
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

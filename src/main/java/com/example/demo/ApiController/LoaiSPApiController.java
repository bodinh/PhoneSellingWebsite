package com.example.demo.ApiController;

import com.example.demo.Hibernate.LoaiSpEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/loaisps")
public class LoaiSPApiController {

    private Session session ;

    private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }

    @RequestMapping("/list")
    public List<LoaiSpEntity> GetAllLoaiSPs(){
        List<LoaiSpEntity> loaiSPS = new ArrayList<>();
        loaiSPS = openSession().createQuery("from LoaiSpEntity ").list();
        return loaiSPS;
    }

    @PostMapping("/post")
    public String PostLoaiSP(@RequestBody LoaiSpEntity loaiSP){
        String result;

        try{
            SellPhonesDBContext.addNewObject(loaiSP);
            result = "successful";
        }catch (HibernateException e){
            result = "Mã loại sản phẩm đã tồn tại trong hệ thống.";
        }catch (Exception e){
            result = "Mã loại sản phẩm đã tồn tại trong hệ thống.";
        }
        return result;
    }
}

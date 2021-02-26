package com.example.demo.ApiController;

import com.example.demo.Hibernate.HangSxEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hangsxs")
public class HangSXApiController {

    private Session session ;

    private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }

    @RequestMapping("/list")
    public List<HangSxEntity> GetAllHangSXs(){
        List<HangSxEntity> hangSXES = new ArrayList<>();
        hangSXES = openSession().createQuery("from HangSxEntity ").list();
        return hangSXES;
    }

    @PostMapping("/post")
    public String PostHangSX(@RequestBody HangSxEntity hangSX){
        String result;
        try{
            SellPhonesDBContext.addNewObject(hangSX);
            result = "successful";
        }catch (HibernateException e){
            result = "Mã hãng sản xuất đã tồn tại trong hệ thống.";
        }catch (Exception e){
            result = "Mã hãng sản xuất đã tồn tại trong hệ thống.";
        }
        return result;
    }
}

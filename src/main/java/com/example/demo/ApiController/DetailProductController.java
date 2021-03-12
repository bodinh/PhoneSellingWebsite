package com.example.demo.ApiController;

import com.example.demo.Hibernate.SanphamEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Hibernate.SellPhonesDBContext;

import org.hibernate.Session;



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
public SanphamEntity GetSanPham(@PathVariable(name = "MaSP") int MaSP){
    SanphamEntity sanPham = null;
    try {
        sanPham =(SanphamEntity) openSession().createQuery("from SanphamEntity where maSp='"+MaSP+"'").getSingleResult();
    } catch (Exception throwables) {
        throwables.printStackTrace();
    }
    return sanPham;
}
}

package com.example.demo.User;

import com.example.demo.Hibernate.AccountEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import com.example.demo.Model.DonHangKH;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;

public class UID {
    private static Session session ;

    private static Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }

    public static int getUID(HttpSession httpSession){
        String username =httpSession.getAttribute("user").toString();
        AccountEntity accountEntity =(AccountEntity) openSession().createQuery("from AccountEntity where username='"+username+"'").getSingleResult();
        return accountEntity.getIdAccount();
    }
}

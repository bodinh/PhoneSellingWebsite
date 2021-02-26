package com.example.demo.ApiController;

import com.example.demo.Hibernate.AccountEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountApiController {

    private Session session ;

    private Session openSession(){
        return session = SellPhonesDBContext.getSession();
    }

    @GetMapping("/list")
    public List<AccountEntity> GetAllAccounts() throws SQLException {
        List<AccountEntity> accounts = new ArrayList<>();
        accounts = openSession().createQuery("from AccountEntity").list();
        return accounts;
    }

    @GetMapping("/{username}/{password}")
    public AccountEntity DangNhap(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password) throws SQLException {
        AccountEntity account = null;
        List<AccountEntity> accountList = GetAllAccounts();
        for (AccountEntity ac : accountList
        ){
            if (ac.getUsername().replace(" ", "").compareTo(username) == 0 && ac.getPassword().replace(" ", "").compareTo(password) == 0) {
                account = ac;
                return account;
            }
        }
        return account;
    }

}

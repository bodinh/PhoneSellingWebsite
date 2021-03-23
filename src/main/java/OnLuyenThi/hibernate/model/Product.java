package OnLuyenThi.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Sanpham", schema = "dbo", catalog = "Sellphones")
public class Product {
    private int maSp;
    private String tenSp;

}

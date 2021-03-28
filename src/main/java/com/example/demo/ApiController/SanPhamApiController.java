package com.example.demo.ApiController;

import com.example.demo.Hibernate.SPkhuyenmaiEntity;
import com.example.demo.Hibernate.SanphamEntity;
import com.example.demo.Hibernate.LoaiSpEntity;
import com.example.demo.Hibernate.SellPhonesDBContext;

import com.example.demo.Model.ListSanPham;
import com.example.demo.Model.SanPham;
import com.example.demo.constant.ProductConstant;
import org.hibernate.Session;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/sanphams")
public class SanPhamApiController {

    private Session session;
    private ListSanPham listSanPham;
    private Session openSession() {
        return session = SellPhonesDBContext.getSession();
    }

    //single item
    @GetMapping("/single/{MaSP}")
    public SanphamEntity GetSanPham(@PathVariable(name = "MaSP") int MaSP) {
        SanphamEntity sanPham = null;
        try {
            sanPham = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp='" + MaSP + "'").getSingleResult();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return sanPham;
    }

    //6 item random
    @GetMapping("/random")
    public List<SanphamEntity> GetRandomSanPham() {
        List<SanphamEntity> sanphamEntityList = null;
        try {
            sanphamEntityList = (List<SanphamEntity>) openSession().createQuery("from SanphamEntity").list();
            Collections.shuffle(sanphamEntityList);
            sanphamEntityList = sanphamEntityList.subList(0, 6);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return sanphamEntityList;
    }

    //loadMore 4 item Hot
    @GetMapping("/hot/{start}/{number}")
    public List<SanPham> Get4Hot(@PathVariable(name = "start") int start, @PathVariable(name = "number") int number) {
        listSanPham = new ListSanPham();
        List<SanPham> listitems = listSanPham.getAllHot();
        try {
            if (start + number > listitems.size()) {
                listitems = listitems.subList(start, listitems.size());
            } else listitems = listitems.subList(start, start + number);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return listitems;
    }

    //loadMore 4 item New
    @GetMapping("/new/{start}/{number}")
    public List<SanPham> Get4New(@PathVariable(name = "start") int start, @PathVariable(name = "number") int number) {
        listSanPham = new ListSanPham();
        List<SanPham> listitems = listSanPham.getAllNew();
        try {
            if (start + number > listitems.size()) {
                listitems = listitems.subList(start, listitems.size());
            } else listitems = listitems.subList(start, start + number);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return listitems;
    }

    //loadMore 4 item Sale
    @GetMapping("/sale/{start}/{number}")
    public List<SanPham> Get4Sale(@PathVariable(name = "start") int start, @PathVariable(name = "number") int number) {
        listSanPham = new ListSanPham();
        List<SanPham> listitems = listSanPham.getAllSale();
        try {
            if (start + number > listitems.size()) {
                listitems = listitems.subList(start, listitems.size());
            } else listitems = listitems.subList(start, start + number);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return listitems;
    }

    //list item
    @GetMapping("/list")
    public List<SanphamEntity> GetAllSanPham() {
        List<SanphamEntity> sanPhams = new ArrayList<>();
        try {
            sanPhams = openSession().createQuery("from SanphamEntity ORDER BY thoiDiemNhapHang DESC ").list();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return sanPhams;
    }

    @DeleteMapping("/delete/{MaSP}")
    public String DeleteSanPham(@PathVariable(name = "MaSP") int MaSP) {
        String result = null;

        try {
            SanphamEntity sanphamEntity = (SanphamEntity) openSession().createQuery("from SanphamEntity where maSp=" + MaSP).getSingleResult();
            SellPhonesDBContext.deleteObject(sanphamEntity);
            result = "successful";
        } catch (Exception e) {
            result = "Không thể xoá sản phẩm này.";
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/post")
    public String PostSanPham(@RequestBody SanphamEntity sanPham) {
        String result = "";
        try {
            SellPhonesDBContext.addNewObject(sanPham);
            result = "successful";
        } catch (Exception e) {
            result = "Mã sản sẩn đã tồn tại trong hệ thống.";
            e.printStackTrace();
        }
        return result;
    }

    @PutMapping("/put")
    public String PutSanPham(@RequestBody SanphamEntity sanPham) {
        String result = "";
        try {
            SellPhonesDBContext.updateObject(sanPham);
            result = "successful";
        } catch (Exception throwables) {
            result = "Cập nhật thất bại.Vui lòng kiểm tra lại.";
            throwables.printStackTrace();
        }
        return result;
    }

    @GetMapping("/search")
    public ModelAndView findByKeyword(@RequestParam(name = "key") String key,
                                      @RequestParam(name = "category",  required = false, defaultValue = "0") int category,
                                      @RequestParam(name = "minPrice", required = false, defaultValue = "0") double minPrice,
                                      @RequestParam(name = "maxPrice", required = false, defaultValue = "0") String maxPrice,
                                      @RequestParam(name = "brand", required = false) List<Integer> brands,
                                      @RequestParam(name = "sort", required = false) String sortType) {
        ModelAndView mv = new ModelAndView("search-and-filter.html");
        List<SanphamEntity> products;
        try {
            //get list
            List<SanphamEntity> topSellerProducts = openSession().createQuery("from SanphamEntity sp where sp.tenSp like '%" + key + "%'").list();
            mv.addObject("topSellerProducts", topSellerProducts);
            List<SanphamEntity> lowPriceProducts=openSession().createQuery("from SanphamEntity sp where sp.tenSp like '%" + key + "%' order by sp.gia asc ").list();
            mv.addObject("lowPriceProducts", lowPriceProducts);
            List<SanphamEntity> highPriceProducts=openSession().createQuery("from SanphamEntity sp where sp.tenSp like '%" + key + "%' order by sp.gia desc ").list();
            mv.addObject("highPriceProducts", highPriceProducts);
//
//            mv.addObject("totalResult", totalResult);
//
//            //page distribution
//            int totalPageCount = totalResult/ProductConstant.LOAD_LIMIT;
//            mv.addObject("totalPageCount", totalPageCount);
//            mv.addObject("currentIndex", pageNumber-1);
//            mv.addObject("beginIndex", beginIndex);
//            mv.addObject("endIndex", endIndex);
//            mv.addObject("baseUrl", "/api/sanphams/");
//            mv.addObject("key", key);

//            List<SanphamEntity> products = openSession().createQuery("from SanphamEntity sp where sp.tenSp like '%" + key + "%'").list();
//            FilterChain filterChain=new FilterChain();
//            filterChain.addFilter(new FilterBrand(brands));
//            List<SanphamEntity> filterList = filterChain.doFilter(products);
//            mv.addObject("products", filterList);

            List<LoaiSpEntity> categories = openSession().createQuery("from LoaiSpEntity").list();
            mv.addObject("categories", categories);

            brands = openSession().createQuery("from HangSxEntity").list();
            mv.addObject("brands", brands);

            mv.addObject("key", key);
            mv.addObject("totalResult", topSellerProducts.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}

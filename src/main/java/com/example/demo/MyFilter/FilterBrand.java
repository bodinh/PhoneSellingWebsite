package com.example.demo.MyFilter;

import com.example.demo.Hibernate.SanphamEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Tran Tuan Anh
 * @Created: Tue, 16/03/2021 10:35 PM
 **/

public class FilterBrand implements Filter{
    List<Integer> brandIds;
    public FilterBrand(List<Integer> brandIds){
        if (brandIds==null){
            brandIds=new ArrayList<>();
        }
        this.brandIds=brandIds;
    }
    public List<SanphamEntity> doFilter(List<SanphamEntity> products){
        if (brandIds==null||brandIds.size()==0){
            return products;
        }
        List<SanphamEntity> result=new ArrayList<>();
        for (SanphamEntity product:products){
            if (brandIds.contains(product.getHangSx())){
                result.add(product);
            }
        }
        return result;
    }
}

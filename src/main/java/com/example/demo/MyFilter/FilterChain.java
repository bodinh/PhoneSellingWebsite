package com.example.demo.MyFilter;

import com.example.demo.Hibernate.SanphamEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Tran Tuan Anh
 * @Created: Tue, 16/03/2021 10:28 PM
 **/

public class FilterChain {
    List<Filter> filters;
    public void addFilter(Filter filter){
        if (filters==null){
            filters=new ArrayList<>();
        }
        filters.add(filter);
    }
    public List<SanphamEntity> doFilter(List<SanphamEntity> products){
        List<SanphamEntity> filteredList=products;
        for (Filter filter:filters){
            filteredList = filter.doFilter(filteredList);
        }
        return filteredList;
    }
}

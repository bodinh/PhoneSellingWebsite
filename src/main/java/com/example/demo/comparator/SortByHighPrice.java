package com.example.demo.comparator;

import com.example.demo.Hibernate.SanphamEntity;

import java.util.Comparator;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 15/03/2021 9:46 AM
 **/

public class SortByHighPrice implements Comparator<SanphamEntity> {
    @Override
    public int compare(SanphamEntity o1, SanphamEntity o2) {
        return (int) (o1.getGia()-o2.getGia());
    }
}

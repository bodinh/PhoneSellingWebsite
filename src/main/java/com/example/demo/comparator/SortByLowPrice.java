package com.example.demo.comparator;

import com.example.demo.Hibernate.SanphamEntity;

import java.util.Comparator;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 15/03/2021 9:43 AM
 **/

public class SortByLowPrice implements Comparator<SanphamEntity> {
    @Override
    public int compare(SanphamEntity o1, SanphamEntity o2) {
        return (int)(o2.getGia() - o1.getGia());
    }
}

package com.example.demo.MyFilter;

import com.example.demo.Hibernate.SanphamEntity;

import java.util.List;

public interface Filter {
    public List<SanphamEntity> doFilter(List<SanphamEntity> products);
}

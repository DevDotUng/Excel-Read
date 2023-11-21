package com.excel.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelImageDAO {
    List<String> getImages();
    void saveImages(List<String> images);
}

package com.excel.dao;

import com.excel.domain.excel.dto.DogDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelDAO {
    List<DogDTO> getView();
    void clearDogList();
    void saveDogList(List<DogDTO> dogList);
}

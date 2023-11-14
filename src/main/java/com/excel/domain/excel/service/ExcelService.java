package com.excel.domain.excel.service;

import com.excel.domain.excel.dto.DogDTO;

import java.util.List;

public interface ExcelService {
    List<DogDTO> getView();
    void saveDogList(List<DogDTO> dogList);
}

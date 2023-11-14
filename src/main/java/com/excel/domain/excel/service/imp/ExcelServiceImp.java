package com.excel.domain.excel.service.imp;

import com.excel.dao.ExcelDAO;
import com.excel.domain.excel.dto.DogDTO;
import com.excel.domain.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelServiceImp implements ExcelService {

    @Autowired
    ExcelDAO excelDAO;

    @Override
    public List<DogDTO> getView() {
        return excelDAO.getView();
    }

    @Override
    public void saveDogList(List<DogDTO> dogList) {
        excelDAO.clearDogList();
        excelDAO.saveDogList(dogList);
    }
}

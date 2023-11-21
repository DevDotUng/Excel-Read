package com.excel.domain.image.service.imp;

import com.excel.dao.ExcelImageDAO;
import com.excel.domain.image.service.ExcelImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelImageServiceImp implements ExcelImageService {

    @Autowired
    ExcelImageDAO excelImageDAO;

    @Override
    public List<String> getImages() {
        return excelImageDAO.getImages();
    }

    @Override
    public void saveImages(List<String> images) {
        excelImageDAO.saveImages(images);
    }
}

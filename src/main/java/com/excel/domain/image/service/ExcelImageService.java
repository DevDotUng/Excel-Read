package com.excel.domain.image.service;

import java.util.List;

public interface ExcelImageService {
    List<String> getImages();
    void saveImages(List<String> images);
}

package com.excel.domain.image.controller;

import com.excel.domain.image.service.ExcelImageService;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/image")
public class ExcelImageController {

    @Autowired
    ServletContext ctx;

    @Autowired
    ExcelImageService excelImageService;

    @GetMapping("")
    public String getImages(Model model) {
        List<String> images = excelImageService.getImages();

        model.addAttribute("images", images);

        return "images";
    }

    @PostMapping("")
    public String readExcelImages(@RequestParam("excelFile") MultipartFile excelFile, Model model) throws IOException {

        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());

        List pictures = workbook.getAllPictures();

        List<String> images = new ArrayList<>();

        for (int i = 0; i < pictures.size(); i++) {
            PictureData picture = (PictureData) pictures.get(i);

            String ext = picture.suggestFileExtension();
            byte[] data = picture.getData();

            images.add(saveImage(ext, data));
        }

        excelImageService.saveImages(images);

        model.addAttribute("images", excelImageService.getImages());

        return "images";
    }

    String saveImage(String extension, byte[] data) throws IOException {
        String uuid = UUID.randomUUID().toString();

        String fileName = uuid + "." + extension;

        String webPath = "/upload/dog";
        String realPath = ctx.getRealPath(webPath);

        File savePath = new File(realPath);
        if (!savePath.exists())
            savePath.mkdirs();

        realPath += File.separator + fileName;
        File saveFile = new File(realPath);

        FileOutputStream out = new FileOutputStream(saveFile);
        out.write(data);
        out.close();

        return fileName;
    }
}
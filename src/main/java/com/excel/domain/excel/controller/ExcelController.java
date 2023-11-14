package com.excel.domain.excel.controller;

import com.excel.domain.excel.dto.DogDTO;
import com.excel.domain.excel.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @GetMapping("")
    public String getView(Model model) {
        List<DogDTO> dogDTOList = excelService.getView();

        model.addAttribute("dogs", dogDTOList);

        return "dogList";
    }

    @PostMapping("")
    public String readExcel(@RequestParam("excelFile") MultipartFile excelFile, Model model) throws IOException {
        List<DogDTO> dogList = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            DogDTO dog = new DogDTO(
                    row.getCell(0).getStringCellValue(),
                    row.getCell(1).getStringCellValue(),
                    (int)row.getCell(2).getNumericCellValue()
                    );

            dogList.add(dog);
        }

        excelService.saveDogList(dogList);

        List<DogDTO> dogDTOList = excelService.getView();

        model.addAttribute("dogs", dogDTOList);

        return "dogList";
    }
}

package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static by.tms.onlinerclonec30onl.Constants.PRODUCT_TYPES_CONTROLLER;

@Controller
@RequestMapping(PRODUCT_TYPES_CONTROLLER)
public class ProductsTypesController {

    @Autowired
    private ProductTypeDAO productTypeDAO;

    @GetMapping//localhost:8080/productstypes
    public String showAll(Model model) {
        model.addAttribute("productsTypes", productTypeDAO.findAll());
//        int i = 0;
        return "/productstypes/show";
    }

//    @GetMapping("/new")     //todo
//    public String add(Model model) {
//        model.addAttribute("productType", new ProductTypeDto());
//        return "/productstypes/AddPage";
//    }

//    @PostMapping            //todo
//    public String add(@ModelAttribute("productType") ProductTypeDto productTypeDto) {
//        productsTypesService.newContent(productTypeDto);
//        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
//    }
//
//    @PatchMapping           //todo
//    public String update(@ModelAttribute ProductTypeDto productTypeDto) {
//
//        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
//    }
//
//    @DeleteMapping          //todo
//    public String delete(@ModelAttribute ProductTypeDto productTypeDto) {
//
//        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
//    }
}
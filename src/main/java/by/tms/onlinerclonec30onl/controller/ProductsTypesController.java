package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static by.tms.onlinerclonec30onl.Constants.PRODUCT_TYPES_CONTROLLER;

@Controller
@RequestMapping(PRODUCT_TYPES_CONTROLLER)
public class ProductsTypesController {
    private final ProductTypeDAO productTypeDAO;

    @Autowired
    public ProductsTypesController(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @GetMapping//localhost:8080/productstypes
    public String showAll(Model model) {
        model.addAttribute("productsTypes", productTypeDAO.findAll());
        return "/productstypes/show";
    }

    @GetMapping("/new")     //todo
    public String add(Model model) {
        model.addAttribute("productType", new ProductType());
        return "/productstypes/add";
    }

    @PostMapping            //todo
    public String add(@ModelAttribute("productType") ProductType productType) {
        productTypeDAO.save(productType);
        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
    }


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
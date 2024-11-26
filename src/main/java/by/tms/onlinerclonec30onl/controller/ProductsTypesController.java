package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.tms.onlinerclonec30onl.Constants.PRODUCT_TYPES_CONTROLLER;

@Controller
@RequestMapping(PRODUCT_TYPES_CONTROLLER)
public class ProductsTypesController {
    private final ProductTypeDAO productTypeDAO;

    @Autowired
    public ProductsTypesController(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("productsTypes", productTypeDAO.findAll());
        return "/productstypes/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("productType", new ProductType());
        return "/productstypes/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("productType") ProductType productType) {
        productTypeDAO.save(productType);
        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") long id, Model model) {
        Optional<ProductType> productType = productTypeDAO.findByID(id);
        if (productType.isPresent()) {
            model.addAttribute("productType", new ProductType());
            return "/productstypes/update"; // todo везде использовать PRODUCT_TYPES_CONTROLLER вместо /productstypes
        }
        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("productType") ProductType productType) {
        productTypeDAO.update(productType);
        return "redirect:" + PRODUCT_TYPES_CONTROLLER;
    }
}
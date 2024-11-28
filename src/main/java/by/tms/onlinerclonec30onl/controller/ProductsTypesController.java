package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.tms.onlinerclonec30onl.Constants.*;

@Controller
@RequestMapping(PRODUCT_TYPES_CONTEXT)
public class ProductsTypesController {
    private final ProductTypeDAO productTypeDAO;

    @Autowired
    public ProductsTypesController(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("productsTypes", productTypeDAO.findAll());
        return PRODUCT_TYPES_FOLDER + SHOW_PAGE;
    }

    @GetMapping(CREATE_CONTEXT)
    public String create(Model model) {
        model.addAttribute("productType", new ProductType());
        return PRODUCT_TYPES_FOLDER + CREATE_PAGE;
    }

    @PostMapping(CREATE_CONTEXT)
    public String create(@ModelAttribute("productType") ProductType productType) {
        productTypeDAO.save(productType);
        return "redirect:" + PRODUCT_TYPES_CONTEXT;
    }

    @GetMapping("/{id}" + UPDATE_CONTEXT)
    public String update(@PathVariable("id") long id, Model model) {
        Optional<ProductType> productType = productTypeDAO.findByID(id);
        if (productType.isPresent()) {
            model.addAttribute("productType", productType.get());
            return PRODUCT_TYPES_FOLDER + UPDATE_PAGE;
        }
        return "redirect:" + PRODUCT_TYPES_CONTEXT;
    }

    @PostMapping(UPDATE_CONTEXT)
    public String update(@ModelAttribute("productType") ProductType productType) {
        productTypeDAO.update(productType);
        return "redirect:" + PRODUCT_TYPES_CONTEXT;
    }

    @GetMapping("/{id}" + DELETE_CONTEXT)
    public String delete(@PathVariable("id") long id) {
        productTypeDAO.deleteById(id);
        return "redirect:" + PRODUCT_TYPES_CONTEXT;
    }
}
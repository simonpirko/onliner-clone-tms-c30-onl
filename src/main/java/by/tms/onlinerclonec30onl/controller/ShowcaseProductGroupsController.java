package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dao.ShowcaseProductGroupsDao;
import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

import static by.tms.onlinerclonec30onl.Constants.SHOWCASE_PRODUCT_GROUPS_CONTROLLER;

@Controller
@RequestMapping(SHOWCASE_PRODUCT_GROUPS_CONTROLLER)
public class ShowcaseProductGroupsController {
    @Autowired
    private ShowcaseProductGroupsDao showcaseProductGroupsDao;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private AccountDAO accountDAO;

    @GetMapping
    public String index() {
        ProductType productType = new ProductType("Велосипеды", "velosipedi");
//        productTypeDao.save(productType);

//        List<ProductType> productTypes = new ArrayList<>();
//        productTypes = productTypeDao.index();
//
//
//        List<ProductType> productTypesId = new ArrayList<>();
//        productTypesId = productTypeDao.show(2);
//
//        ProductType productTypeUpdate = new ProductType("Коляски", "koliaski");
//        productTypeDao.update(productTypeUpdate, 7);
//
//        productTypeDao.delete(11);


        return "index";
    }
}

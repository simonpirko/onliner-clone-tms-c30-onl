package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dao.ShowcaseProductGroupsDao;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.tms.onlinerclonec30onl.Constants.SHOWCASE_PRODUCT_GROUPS_CONTROLLER;
import static by.tms.onlinerclonec30onl.domain.Account.Role.USER;

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
        ProductType productType = new ProductType("Шины", "shinii");
        showcaseProductGroupsDao.save(productType);

        List<ProductType> productTypes = new ArrayList<>();
        productTypes = showcaseProductGroupsDao.index();


        return "index";
    }
}

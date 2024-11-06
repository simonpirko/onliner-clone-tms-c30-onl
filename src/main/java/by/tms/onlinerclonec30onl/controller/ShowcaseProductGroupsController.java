package by.tms.onlinerclonec30onl.controller;

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

    @GetMapping
    public String index() {

        List<ProductType> productTypes = new ArrayList<>();
        productTypes = showcaseProductGroupsDao.index();

        return "index";
    }
}

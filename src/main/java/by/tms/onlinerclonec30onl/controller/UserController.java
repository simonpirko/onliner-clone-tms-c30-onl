package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.dto.AddProductDTO;
import by.tms.onlinerclonec30onl.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
   private ProductService productService;
    @Autowired
    ProductTypeDAO productTypeDAO;
    @Autowired
    HttpSession session;
    @GetMapping("/admin/add-product")
   public String addProduct(Model model) {
        model.addAttribute("productTypes",productTypeDAO.findAll());
        if (session.getAttribute("productForAdd") == null) {
            Product product = new Product();
            product.setPhotos(new ArrayList<>());
            session.setAttribute("productForAdd",product);
        }
        model.addAttribute("addProductDTO",productService.getProductPageDataForAddProduct((Product) session.getAttribute("productForAdd")));
        return "addProduct";
    }
    @PostMapping("/admin/add-product")
    public String addProduct(AddProductDTO addProductDTO) {
        session.setAttribute("productForAdd",productService.convertAddProductDTOToProduct(addProductDTO));
        return "redirect:/user/admin/add-product";
    }
    @PostMapping("/admin/add-product/save-product")
    public String saveProduct(AddProductDTO addProductDTO) {
        session.setAttribute("productForAdd",productService.convertAddProductDTOToProduct(addProductDTO));
        return "redirect:/user/admin";
    }
    @PostMapping("/admin/add-product/save-product-type")
    public String saveProductType(AddProductDTO addProductDTO) {
        session.setAttribute("productForAdd",productService.convertAddProductDTOToProduct(addProductDTO));
        return "redirect:/user/admin/addProduct";
    }
    @GetMapping ("/test")
    public String test() {
        return "test";
    }

}

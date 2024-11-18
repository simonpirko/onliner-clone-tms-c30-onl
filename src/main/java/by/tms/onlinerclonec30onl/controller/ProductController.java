package by.tms.onlinerclonec30onl.controller;



import by.tms.onlinerclonec30onl.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductController {
    ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{idProduct}")
    public String index(@PathVariable(value = "idProduct") Long idProduct, Model model) {
        model.addAttribute("productDTO", productService.getProductPageData(idProduct));

        return "product";
    }
}

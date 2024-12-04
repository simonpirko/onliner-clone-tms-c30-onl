package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ProductPhotoDAO;
import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.dto.AddProductDTO;
import by.tms.onlinerclonec30onl.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.module.ModuleDescriptor;
import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProductService productService;
    @Autowired
    ProductTypeDAO productTypeDAO;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    ProductPhotoDAO photoDAO;
    @Autowired
    HttpSession session;
    @Autowired
    private ProductPhotoDAO productPhotoDAO;

    @GetMapping("/admin/add-product")
    public String addProduct(Model model) {
        if (session.getAttribute("productForAdd") == null) {
            session.setAttribute("productTypes", productTypeDAO.findAll());
            Product product = new Product();
            product.setPhotos(new ArrayList<>());
            session.setAttribute("productForAdd", product);
        }
        session.setAttribute("currentPage", "/user/admin/add-product");
        model.addAttribute("productTypes", session.getAttribute("productTypes"));
        model.addAttribute("addProductDTO", productService.convertProductToAddProductDTO((Product) session.getAttribute("productForAdd")));
        return "addProduct";
    }

    @PostMapping("/admin/add-product")
    public String addProductPreview(AddProductDTO addProductDTO) {
        session.setAttribute("productForAdd", productService.convertAddProductDTOToProduct(addProductDTO));
        return "redirect:/user/admin/add-product";
    }

    @GetMapping("/admin/add-product/save-product")
    public String saveProduct() {
        Product newProduct = (Product) session.getAttribute("productForAdd");
       Long idProduct = productDAO.saveAndReturnID(newProduct);
       photoDAO.save(productService.savePhoto(newProduct), idProduct);
        session.setAttribute("productForAdd", null);
        return "redirect:/user/admin/add-product";
    }

    @PostMapping("/admin/add-product/save-product-type")
    public String saveProductType(AddProductDTO addProductDTO) {
        productTypeDAO.save(productService.convertAddProductDTOToProductType(addProductDTO));
        session.setAttribute("productTypes", productTypeDAO.findAll());
        return "redirect:"+session.getAttribute("currentPage");
    }

    @GetMapping("/admin/edit-product")
    public String editProduct(Model model) {
        if (session.getAttribute("editProduct") == null) {
            session.setAttribute("productTypes", productTypeDAO.findAll());
            Product product = new Product();
            product.setPhotos(new ArrayList<>());
            session.setAttribute("editProduct", product);
        }
        session.setAttribute("currentPage", "/user/admin/edit-product");
        model.addAttribute("productTypes", session.getAttribute("productTypes"));
        model.addAttribute("editProductDTO", productService.convertProductToAddProductDTO((Product) session.getAttribute("editProduct")));
        model.addAttribute("allProducts", productDAO.findAll());
        return "editProduct";
    }

    @GetMapping("admin/edit-product/{id}")
    public String editProductPreview(@PathVariable(value = "id") long id) {
        session.setAttribute("editProduct", productDAO.findByID(id).get());
        return "redirect:/user/admin/edit-product";
    }

    @PostMapping("/admin/edit-product")
    public String previewUpdateProduct(AddProductDTO addProductDTO, Model model) {
        Product selectedProduct = (Product) session.getAttribute("editProduct");
        selectedProduct = productService.updateProductByAddProductDTO(selectedProduct, addProductDTO);
        session.setAttribute("editProduct", selectedProduct);
        return "redirect:/user/admin/edit-product";

    }
    @GetMapping("/admin/edit-product/update-product")
    public String updateProduct(Model model) {
        if (session.getAttribute("editProduct") == null) {
            return "redirect:/user/admin/edit-product";
        }
        Product selectedProduct = (Product) session.getAttribute("editProduct");
        productDAO.update(selectedProduct.getId(), selectedProduct);
        photoDAO.save(productService.savePhoto(selectedProduct), selectedProduct.getId());

        session.setAttribute("editProduct", null);
        return "redirect:/user/admin/edit-product";
    }
}

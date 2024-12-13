package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.*;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.dto.AddProductDTO;
import by.tms.onlinerclonec30onl.dto.CustomerDto;
import by.tms.onlinerclonec30onl.dto.UserLoginDTO;
import by.tms.onlinerclonec30onl.dto.UserRegistrationDto;
import by.tms.onlinerclonec30onl.service.AccountService;
import by.tms.onlinerclonec30onl.service.CustomerService;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private AccountDAO accountDAO;
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

    @GetMapping("/reg")
    public String registration(@ModelAttribute("userRegistrationDto") UserRegistrationDto accountDto) {
        return "registration";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO accountDto,Model model) {
        model.addAttribute("WrongLoginOrPassword", false);
        if(accountService.Login(accountDto)){
            Account account = accountDAO.findByUsername(accountDto.getUsername()).get();
            session.setAttribute("currentUser", account);
            return "redirect:/catalog";
        }
        model.addAttribute("WrongLoginOrPassword", true);
        return "redirect:/user/login";
    }

    @PostMapping("/reg")
    public String registration (@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto accountDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (accountService.checkUsernameExist(accountDto.getUsername())) {
            model.addAttribute("errorMessage", "Такой пользователь уже существует");
            return "registration";
        }
        accountService.reg(accountDto);
        return "redirect:/catalog";
    }

    @GetMapping("/profile")
    public String index(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("currentUser");
        CustomerDto customerDto=customerService.getCustomer(account.getId());

        model.addAttribute("lastname",customerDto.getLastName());
        model.addAttribute("firstname",customerDto.getFirstName());
        model.addAttribute("phone",customerDto.getPhone());
        model.addAttribute("address",customerDto.getAddress());
        model.addAttribute("role",account.getRole());

        return "accountProfile";
    }


    @PostMapping("/profile")
    public String registration(@RequestParam("lastname") String newLastName,
                               @RequestParam("name") String newFirstName,
                               @RequestParam("phone") String newPhone,
                               @RequestParam("address") String newAddress,
                               @RequestParam("role") String role,
                               HttpSession session) {

        Account account = (Account) session.getAttribute("currentUser");
        Customer customer=customerDAO.findByID(account.getId()).get();

        customer.setLastName(newLastName);
        customer.setFirstName(newFirstName);
        customer.setPhone(newPhone);
        customer.setAddress(newAddress);

        customerDAO.update(account.getId(), customer);

        account.setRole(Account.Role.valueOf(role));
        accountDAO.update(account.getId(), account);
        return "redirect:/user/profile";
    }
  
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
  
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/catalog";
    }
}

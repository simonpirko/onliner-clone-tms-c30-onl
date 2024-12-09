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



}

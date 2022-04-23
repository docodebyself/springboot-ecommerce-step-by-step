package com.project.customer.controller;

import com.project.library.dto.ProductDto;
import com.project.library.dto.ShoppingCartDto;
import com.project.library.model.Customer;
import com.project.library.model.ShoppingCart;
import com.project.library.service.CustomerService;
import com.project.library.service.ProductService;
import com.project.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session) {
        if(principal == null){
            return "redirect:/login";
        }
        else{
            ShoppingCart cart = cartService.getCart(principal.getName());
            model.addAttribute("shoppingCart", cart);
            model.addAttribute("title", "Cart");
            return "cart";
           /* ShoppingCartDto cartDto = (ShoppingCartDto) session.getAttribute("shoppingCartDto");
            model.addAttribute("shoppingCart", cartDto);*/
        }

    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(@RequestParam("id") Long id,
                                @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                                HttpServletRequest request,
                                Model model,
                                Principal principal,
                                HttpSession session) {


        ProductDto productDto = productService.getById(id);
        if (principal == null) {
            return "redirect:/login";
           /* ShoppingCartDto shoppingCartDto = (ShoppingCartDto) session.getAttribute("shoppingCartDto");
            shoppingCartDto = cartService.addItemToCartSession(shoppingCartDto, productDto, quantity);
            model.addAttribute("shoppingCart", shoppingCartDto);
            session.setAttribute("shoppingCartDto", shoppingCartDto);*/
        } else {
            String username = principal.getName();
            ShoppingCart shoppingCart = cartService.addItemToCart(productDto, quantity, username);
            session.setAttribute("totalItems", shoppingCart.getTotalItems());
            model.addAttribute("shoppingCart", shoppingCart);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("id") Long id,
                             @RequestParam("quantity") int quantity,
                             Model model,
                             HttpSession session,
                             Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            ProductDto productDto = productService.getById(id);
            String username = principal.getName();
            ShoppingCart shoppingCart = cartService.updateCart(productDto, quantity,username);
            model.addAttribute("shoppingCart", shoppingCart);
            return "redirect:/cart";
        }

    }

    @RequestMapping(value = "/delete-item", method = RequestMethod.POST, params = "action=delete")
    public String deleteItem(@RequestParam("id") Long id,
                             Model model,
                             HttpSession session,
                             Principal principal
                             ){
        if(principal == null){
            return "redirect:/login";
        }else{
            ProductDto productDto = productService.getById(id);
            String username = principal.getName();
            ShoppingCart shoppingCart = cartService.removeItemFromCart(productDto, username);
            model.addAttribute("shoppingCart", shoppingCart);
            return "redirect:/cart";
        }
    }

}

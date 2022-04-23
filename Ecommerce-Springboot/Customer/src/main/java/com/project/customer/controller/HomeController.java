package com.project.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session){
       /* ShoppingCartDto cartDto = (ShoppingCartDto) session.getAttribute("shoppingCartDto");
        if(principal == null && cartDto == null ){
            session.setAttribute("shoppingCartDto", new ShoppingCartDto());
        }else if(principal != null && cartDto != null){
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            ShoppingCart cart = customer.getCart();
            shoppingCartService.combineCart(cartDto, cart);
        }*/
        model.addAttribute("title", "Home");
        model.addAttribute("page", "Home");
        return "home";
    }

}

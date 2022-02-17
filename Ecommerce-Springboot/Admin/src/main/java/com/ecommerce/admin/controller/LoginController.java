package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.sasl.SaslServer;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }


   @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("adminDto", new AdminDto());
        return "register";
   }

   @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
   }

   @PostMapping("/register-new")
   public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto,
                             BindingResult result,
                             Model model,
                             HttpSession session){

        try {
            session.removeAttribute("message");
            if(result.hasErrors()){
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if(admin != null){
                model.addAttribute("adminDto", adminDto);
                System.out.println("admin not null");
                session.setAttribute("message", "Your email has been registered!");
                return "register";
            }
            if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("success");
                session.setAttribute("message", "Register successfully!");
                model.addAttribute("adminDto", adminDto);
            }else{
                model.addAttribute("adminDto", adminDto);
                session.setAttribute("message", "Password is not same!");
                System.out.println("password not same");
                return "register";
            }
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message", "Server is error, please try again later!");
        }
        return "register";

   }






}

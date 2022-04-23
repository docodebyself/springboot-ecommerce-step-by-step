package com.project.customer.controller;

import com.project.library.dto.CustomerDto;
import com.project.library.model.Country;
import com.project.library.service.CountryService;
import com.project.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            String username = principal.getName();
            CustomerDto customer = customerService.getCustomer(username);
            List<Country> countryList = countryService.findAll();
            model.addAttribute("customer", customer);
            model.addAttribute("countries", countryList);
            model.addAttribute("title", "Profile");
            model.addAttribute("page", "Profile");
            return "profile";
        }
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("profile")MultipartFile imageProfile,
                                @Valid @ModelAttribute("customer") CustomerDto customerDto,
                                BindingResult result ,
                                Model model,
                                Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            List<Country> countryList = countryService.findAll();
            model.addAttribute("countries", countryList);
            customerService.save(customerDto);
            CustomerDto customer = customerService.getCustomer(principal.getName());
            model.addAttribute("success", "Update successfully!");
            model.addAttribute("customer", customer);
            return "redirect:/profile";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("title", "Change password");
        model.addAttribute("page", "Change password");
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePass(@RequestParam("oldPassword") String oldPassword,
                             @RequestParam("newPassword")String newPassword,
                             @RequestParam("repeatNewPassword")String repeatPassword,
                             RedirectAttributes model,
                             Principal principal,
                             HttpSession session) {
       if(principal == null){
           return  "redirect:/login";
       }else{
           CustomerDto customer = customerService.getCustomer(principal.getName());
           if(passwordEncoder.matches(oldPassword, customer.getPassword())
                   && !passwordEncoder.matches(newPassword, oldPassword)
                   && !passwordEncoder.matches(newPassword, customer.getPassword())
                   && repeatPassword.equals(newPassword)) {
               customer.setPassword(passwordEncoder.encode(newPassword));
               customerService.changePass(customer);
               model.addFlashAttribute("message", "Your password is wrong");
           }else {
               model.addFlashAttribute("message", "Your password is wrong");
           }
       }
        return "redirect:/profile";

    }
}

package com.advboard.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller

public class AppController {


    @Autowired
    private AdvertisementService service;
    User currentUser = new User();
    String descriptionSearch;

    @RequestMapping("/")
    public String viewHomePage(Model model, HttpSession session) {
        User user = currentUser;
        model.addAttribute("user",user);
            return "index";
    }

@RequestMapping("/advertisementList")
public String viewAdvertisements(Model model, HttpSession session) {
    List<Advertisement> listAdvertisements = service.listAll();
    model.addAttribute("descriptionSearch", descriptionSearch);
    model.addAttribute("listAdvertisements", listAdvertisements);
    model.addAttribute("user", currentUser);
    return "advertisementListUser";
}

    @RequestMapping(value = "/searchAdvertisements", method = RequestMethod.GET)
    public String searchAdvertisements(Model model,@ModelAttribute("descriptionSearch") String descriptionSearch) {

        List<Advertisement> listAdvertisements = service.findSimilar(descriptionSearch);
        model.addAttribute("descriptionSearch", descriptionSearch);
        model.addAttribute("listAdvertisements", listAdvertisements);
        model.addAttribute("user", currentUser);
        return "advertisementListUser";
    }



    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement) {
        advertisement.setUser(currentUser.getId());
        service.save(advertisement);
        return "redirect:/advertisementList";
    }


    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(Model model,@ModelAttribute("user") User user) {
        service.saveUser(user);

        if(user.getIsAdmin()==1) {
            return "redirect:/showUsers";
        }
        else {
            currentUser = user;
            return "redirect:/advertisementList";
        }
    }

    @RequestMapping(value = "/decider", method = RequestMethod.POST)
    public String checkUser(Model model, @ModelAttribute("user") User user) {
        currentUser = service.findUserByName(user.getName());
        if (currentUser==null)
            return "registerUser";
        if(!user.getPassword().equals(currentUser.getPassword()))
        {
            model.addAttribute("user",user);
            return "registerUser";
        }
        if (currentUser.getIsAdmin()==1) {
            List<User> listUsers = service.listAllUser();
            model.addAttribute("listUsers", listUsers);
            return "userListAdmin";
        }
        if (currentUser.getIsAdmin()==0) {
            String descriptionSearch=null;
            List<Advertisement> listAdvertisements = service.listAll();
            model.addAttribute("listAdvertisements", listAdvertisements);
            model.addAttribute("user",currentUser);
            model.addAttribute("descriptionSearch",descriptionSearch);
            return "advertisementListUser";
        }

        else {
            return "redirect:/";
        }
    }

    @RequestMapping("/showUsers")
    public String showUsers(Model model, @ModelAttribute("user") User user) {
        List<User> listUsers = service.listAllUser();
        model.addAttribute("listUsers", listUsers);
        return "userListAdmin";
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = service.getUser(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable(name = "id") int id) {
        service.deleteUser(id);
        List<User> listUsers = service.listAllUser();
        model.addAttribute("listUsers", listUsers);
        return "userListAdmin";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        return "new_advertisement";
    }



    @RequestMapping("/edit/{id}")
    public ModelAndView showEditAdvertisementPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_advertisement");
        Advertisement advertisement = service.get(id);
        mav.addObject("advertisement", advertisement);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(Model model,@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/advertisementList";
    }
}

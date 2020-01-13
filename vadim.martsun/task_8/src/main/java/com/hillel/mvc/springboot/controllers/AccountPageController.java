package com.hillel.mvc.springboot.controllers;

import com.hillel.mvc.springboot.model.requests.AccountRequest;
import com.hillel.mvc.springboot.service.accountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/accounts")
public class AccountPageController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accountList")
    public ModelAndView getUsersList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountService.getAllAccounts());
        modelAndView.setViewName("accountsPage");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addAccountForm(Model model) {
        model.addAttribute("accountAttribute", new AccountRequest());
        return "addAccount";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAccount")
    public String addAccount(@ModelAttribute("accountAttribute") @Valid AccountRequest accountRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addAccount";
        }
        accountService.save(accountRequest);
        return "redirect:/accounts/accountList";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{accountId}")
    public String editAccountPage(@PathVariable("accountId") int accountId, Model model) {
        model.addAttribute("accountAttribute", accountService.getAccountRequestById(accountId));
        return "editAccount";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/update")
    public String editAccount(@ModelAttribute("accountAttribute") @Valid AccountRequest accountRequest,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editAccount";
        }
        accountService.update(accountRequest);
        return "redirect:/accounts/accountList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{accountId}")
    public String deleteAccount(@PathVariable("accountId") int accountId) {
        accountService.delete(accountId);
        return "redirect:/accounts/accountList";
    }
}

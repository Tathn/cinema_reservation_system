package com.cinema.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cinema.domain.User;
import com.cinema.domain.UserRepository;
import com.cinema.service.SecurityService;
import com.cinema.service.UserRepositoryUserDetailsService;
import com.cinema.service.UserService;

/**
 * Created by Patryk on 2017-05-01.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	private final UserService userService;
	private final SecurityService securityService;
	
    @Autowired
    public AccountController(UserRepository userRepository){
        userService = new UserService(userRepository);
        securityService = new SecurityService();
    }

    @GetMapping
    public String getManageAccountPanel(){
        return "account/manageAccountPanel";
    }

    @GetMapping("edit")
    public String initEditForm(Model model){
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        model.addAttribute("user", user);
        return "account/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@ModelAttribute User user, BindingResult result, RedirectAttributes redir){
        if (result.hasErrors()) {
            return "account/edit";
        } else {
        	// Get database record of edited user
        	User dbRecord = userService.findById(user.getId());
        	dbRecord.setUsername(user.getUsername());
        	dbRecord.setEmail(user.getEmail());
        	String userPassword = user.getPassword();
        	if(userPassword != ""){
        		String encodedPassword = securityService.encodePassword(userPassword);
        		dbRecord.setPassword(encodedPassword);
        	}
            userService.save(dbRecord);
            securityService.authenticate(dbRecord,userService);
            redir.addFlashAttribute("globalMessage","Account details saved!");
            return "redirect:/account";
        }
    }
}

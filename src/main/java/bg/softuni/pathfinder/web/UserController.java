package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.models.dto.UserRegisterDTO;
import bg.softuni.pathfinder.services.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initUserRegister() {
        return new UserRegisterDTO();
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);

            return "redirect:/users/register";
        }
        userService.registerUser(userRegisterDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        model.addAttribute("profile", userService.userProfile(principal.getName()));
        return "profile";
    }
}

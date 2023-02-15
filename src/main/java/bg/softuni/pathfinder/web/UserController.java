package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.models.dto.UserLoginDTO;
import bg.softuni.pathfinder.models.dto.UserRegisterDTO;
import bg.softuni.pathfinder.models.entities.User;
import bg.softuni.pathfinder.services.interfaces.UserService;
import bg.softuni.pathfinder.util.user.CurrentUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    private final CurrentUser currentUser;

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initUserLogin() {
        return new UserLoginDTO();
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);

            return "redirect:/users/login";
        }
        Optional<User> user = userService.loginUser(userLoginDTO);

        if (user.isPresent()) {
            currentUser.setId(user.get().getId());
            currentUser.setRole(userService.userRole(user.get().getId()));
            httpSession.setAttribute("userSession", currentUser);

            return "redirect:/home";
        }
        redirectAttributes.addFlashAttribute("invalidUser", "Incorrect username or password.");

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        currentUser.setId(null);
        httpSession.invalidate();

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "/register";
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
    public String profilePage(Model model) {
        model.addAttribute("profile", userService.userProfile(currentUser.getId()));

        return "profile";
    }
}

package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.models.dto.AddPictureDTO;
import bg.softuni.pathfinder.services.interfaces.PictureService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pictures")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PictureController {
    private final PictureService pictureService;

    @PostMapping("/upload")
    public String uploadPicture(@Valid AddPictureDTO pictureModel, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, HttpSession httpSession, Authentication auth) {
        boolean isValid = false;

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.pictureModel", bindingResult);
            redirectAttributes.addFlashAttribute("pictureModel", pictureModel);
        }

        if (auth == null) {
            redirectAttributes.addFlashAttribute("userNotLoggedIn", "You must login to post a picture.");
        } else {
            isValid = true;
        }
        Long routeId = (Long) httpSession.getAttribute("routeId");

        if (isValid) {
            pictureModel.setTrackId(routeId);
            pictureService.addPicture(pictureModel, auth.getName());
        }
        return "redirect:/routes/details/" + routeId;
    }
}

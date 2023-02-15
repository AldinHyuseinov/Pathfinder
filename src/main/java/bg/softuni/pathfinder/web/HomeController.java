package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.services.interfaces.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class HomeController {
    private final PictureService pictureService;

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("pictures", pictureService.allPictures());

        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}

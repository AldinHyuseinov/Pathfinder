package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.models.dto.AddPictureDTO;
import bg.softuni.pathfinder.models.dto.AddRouteDTO;
import bg.softuni.pathfinder.models.enums.CategoryType;
import bg.softuni.pathfinder.services.interfaces.PictureService;
import bg.softuni.pathfinder.services.interfaces.RouteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/routes")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RouteController {
    private final RouteService routeService;

    private final PictureService pictureService;

    @GetMapping("/all")
    public String allRoutes(Model model, HttpSession httpSession) {
        model.addAttribute("routes", routeService.allRoutes());
        model.addAttribute("pictures", pictureService.allDistinctPictures());

        if (httpSession.getAttribute("routeId") != null) {
            httpSession.removeAttribute("routeId");
        }
        return "routes";
    }

    @GetMapping("/add")
    public String addRoute() {
        return "add-route";
    }

    @ModelAttribute("routeModel")
    public AddRouteDTO initRoute() {
        return new AddRouteDTO();
    }

    @PostMapping("/add")
    public String addRoute(@Valid AddRouteDTO routeModel, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeModel", bindingResult);
            redirectAttributes.addFlashAttribute("routeModel", routeModel);

            return "redirect:/routes/add";
        }
        routeService.addRoute(routeModel);

        return "redirect:/routes/all";
    }

    @GetMapping("/details/{id}")
    private String routeDetails(@PathVariable("id") Long routeId, Model model, HttpSession httpSession) {
        model.addAttribute("routeDetails", routeService.routeDetails(routeId));
        model.addAttribute("pictures", pictureService.getAllByRoute(routeId));

        if (httpSession.getAttribute("routeId") == null) {
            httpSession.setAttribute("routeId", routeId);
        }
        return "route-details";
    }

    @ModelAttribute("pictureModel")
    public AddPictureDTO initPicture() {
        return new AddPictureDTO();
    }

    @GetMapping("/{type}")
    public String routesByType(@PathVariable("type") CategoryType categoryType, Model model) {
        model.addAttribute("routes", routeService.allRoutesByType(categoryType));
        model.addAttribute("pictures", pictureService.allDistinctPicturesByRouteType(categoryType));

        return "routes-by-Type";
    }
}

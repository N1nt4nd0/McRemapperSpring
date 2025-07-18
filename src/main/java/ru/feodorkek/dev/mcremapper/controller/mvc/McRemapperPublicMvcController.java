package ru.feodorkek.dev.mcremapper.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import ru.feodorkek.dev.mcremapper.properties.RestPublicEndpointsProperties;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class McRemapperPublicMvcController {

    private final RestPublicEndpointsProperties publicEndpoints;
    private final McRemapperService remapperService;

    @GetMapping("${mc-remapper.web.mvc.endpoints.public.index}")
    public String indexPage(final Model model) {
        model.addAttribute("maybeRemapApi", publicEndpoints.getMcRemapperMaybeRemap());
        model.addAttribute("mcVersions", remapperService.getRegisteredProviderNames());
        model.addAttribute("sourceMinLen", remapperService.getMaybeRemapSourceMinLen());
        model.addAttribute("sourceMaxLen", remapperService.getMaybeRemapSourceMaxLen());
        return "index";
    }

}

package ru.feodorkek.dev.mcremapper.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import ru.feodorkek.dev.mcremapper.properties.RestEndpointsProperties;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class McRemapperMvcController {

    private final RestEndpointsProperties restEndpoints;
    private final McRemapperService remapperService;

    @GetMapping("${web.mvc.endpoints.index}")
    public String indexPage(final Model model) {
        model.addAttribute("maybeRemapApi", restEndpoints.getMaybeRemap());
        model.addAttribute("mcVersions", remapperService.getRegisteredProviderNames());
        model.addAttribute("sourceMinLen", remapperService.getMaybeRemapSourceMinLen());
        model.addAttribute("sourceMaxLen", remapperService.getMaybeRemapSourceMaxLen());
        return "index";
    }

}

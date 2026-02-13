package ru.feodorkek.dev.mcremapper.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import ru.feodorkek.dev.mcremapper.properties.RemapperProperties;
import ru.feodorkek.dev.mcremapper.service.RemappingService;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class McRemapperMvcController {
    
    private final RemappingService remapperService;
    private final RemapperProperties properties;
    
    @GetMapping( "/" )
    public String indexPage( final Model model ) {
        model.addAttribute( "mcVersions", remapperService.getRegisteredProvidersNames() );
        model.addAttribute( "sourceMinLen", properties.getRemapSourceMinLen() );
        model.addAttribute( "sourceMaxLen", properties.getRemapSourceMaxLen() );
        return "index";
    }
    
}
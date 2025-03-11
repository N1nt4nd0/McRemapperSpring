package ru.feodorkek.dev.mcremapper.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.mcremapper.business.McRemapperUseCases;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoIn;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoOut;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoDtoOut;

@Tag(name = "McRemapper public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class McRemapperPublicRestController {

    private final McRemapperUseCases mcRemapperUseCases;

    @Operation(summary = "Remap input minecraft source code")
    @PostMapping("${mc-remapper.web.rest.endpoints.public.mc-remapper-maybe-remap}")
    public ResponseEntity<MaybeRemapDtoOut> maybeRemap(
            @RequestBody final MaybeRemapDtoIn maybeRemapDtoIn) {
        return ResponseEntity.ok(mcRemapperUseCases.maybeRemap(maybeRemapDtoIn));
    }

    @Operation(summary = "Get info")
    @GetMapping("${mc-remapper.web.rest.endpoints.public.mc-remapper-info}")
    public ResponseEntity<McRemapperInfoDtoOut> getInfo() {
        return ResponseEntity.ok(mcRemapperUseCases.getInfo());
    }

}

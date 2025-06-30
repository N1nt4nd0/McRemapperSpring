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
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapResponse;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoResponse;
import ru.feodorkek.dev.mcremapper.usecase.McRemapperUseCases;

@Tag(name = "McRemapper public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class McRemapperPublicRestController {

    private final McRemapperUseCases useCases;

    @Operation(summary = "Ping endpoint")
    @GetMapping("${mc-remapper.web.rest.endpoints.public.ping}")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @Operation(summary = "Remap input minecraft source code")
    @PostMapping("${mc-remapper.web.rest.endpoints.public.mc-remapper-maybe-remap}")
    public ResponseEntity<MaybeRemapResponse> maybeRemap(
            @RequestBody final MaybeRemapRequest request) {
        return ResponseEntity.ok(useCases.maybeRemap(request));
    }

    @Operation(summary = "Get info")
    @GetMapping("${mc-remapper.web.rest.endpoints.public.mc-remapper-info}")
    public ResponseEntity<McRemapperInfoResponse> getInfo() {
        return ResponseEntity.ok(useCases.getInfo());
    }

}

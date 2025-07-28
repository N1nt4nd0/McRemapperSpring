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
import ru.feodorkek.dev.mcremapper.dto.request.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.response.MaybeRemapResponse;
import ru.feodorkek.dev.mcremapper.usecase.McRemapperUseCases;

@Tag(name = "McRemapper usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class McRemapperRestController {

    private final McRemapperUseCases useCases;

    @Operation(summary = "ping endpoint")
    @GetMapping("${web.rest.endpoints.ping}")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @Operation(summary = "remap input minecraft source code")
    @PostMapping("${web.rest.endpoints.maybe-remap}")
    public ResponseEntity<MaybeRemapResponse> maybeRemap(@RequestBody final MaybeRemapRequest request) {
        return ResponseEntity.ok(useCases.maybeRemap(request));
    }

}

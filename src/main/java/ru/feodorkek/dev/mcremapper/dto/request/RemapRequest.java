package ru.feodorkek.dev.mcremapper.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RemapRequest {
    
    @Schema( description = "The name of the Minecraft version to remap from",
             example = "1.7.10" )
    private final String mcRemapperProviderName;
    
    @Schema( description = "The Minecraft source code to remap",
             example = "if (!world.field_72995_K && !world.func_72864_z(x, y, z)) world.func_147465_d(x, y, z, this, meta - 1, 2);" )
    private final String mappedSource;
    
}
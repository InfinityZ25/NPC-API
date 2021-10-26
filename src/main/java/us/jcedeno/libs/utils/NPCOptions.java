package us.jcedeno.libs.utils;

import org.bukkit.Location;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NPCOptions {
    private final String name;
    private final String texture;
    private final String signature;
    private final Location location;
    private boolean hideNametag = true;
    private boolean rotateHead = false;
    private String usingPlayerSkin;

}
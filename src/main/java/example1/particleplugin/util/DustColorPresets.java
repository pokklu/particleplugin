package example1.particleplugin.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;

import java.util.*;

public class DustColorPresets {
    public static final Map<String, Particle.DustOptions> COLORS = Map.ofEntries(
            Map.entry("赤", new Particle.DustOptions(Color.RED, 1f)),
            Map.entry("橙", new Particle.DustOptions(Color.ORANGE, 1f)),
            Map.entry("黄", new Particle.DustOptions(Color.YELLOW, 1f)),
            Map.entry("黄緑", new Particle.DustOptions(Color.LIME, 1f)),
            Map.entry("緑", new Particle.DustOptions(Color.GREEN, 1f)),
            Map.entry("水色", new Particle.DustOptions(Color.AQUA, 1f)),
            Map.entry("青", new Particle.DustOptions(Color.BLUE, 1f)),
            Map.entry("紫", new Particle.DustOptions(Color.PURPLE, 1f)),
            Map.entry("マゼンタ", new Particle.DustOptions(Color.fromRGB(255, 0, 255), 1f)),
            Map.entry("桃", new Particle.DustOptions(Color.fromRGB(255, 192, 203), 1f)),
            Map.entry("黒", new Particle.DustOptions(Color.BLACK, 1f)),
            Map.entry("灰", new Particle.DustOptions(Color.GRAY, 1f)),
            Map.entry("薄灰", new Particle.DustOptions(Color.fromRGB(211, 211, 211), 1f)),
            Map.entry("白", new Particle.DustOptions(Color.WHITE, 1f))
    );

    public static final List<Particle.DustOptions> RAINBOW = List.of(
            new Particle.DustOptions(Color.RED, 1f),
            new Particle.DustOptions(Color.ORANGE, 1f),
            new Particle.DustOptions(Color.YELLOW, 1f),
            new Particle.DustOptions(Color.LIME, 1f),
            new Particle.DustOptions(Color.AQUA, 1f),
            new Particle.DustOptions(Color.BLUE, 1f),
            new Particle.DustOptions(Color.PURPLE, 1f)
    );

    // 日本語の色名 → 正しい Material 染料を返すメソッドに修正しました
    public static Material fromName(String name) {
        if (name == null) return Material.GRAY_DYE;

        return switch (name) {
            case "赤" -> Material.RED_DYE;
            case "橙" -> Material.ORANGE_DYE;
            case "黄" -> Material.YELLOW_DYE;
            case "黄緑" -> Material.LIME_DYE;
            case "緑" -> Material.GREEN_DYE;
            case "水色" -> Material.LIGHT_BLUE_DYE;
            case "青" -> Material.BLUE_DYE;
            case "紫" -> Material.PURPLE_DYE;
            case "マゼンタ" -> Material.MAGENTA_DYE;
            case "桃" -> Material.PINK_DYE;
            case "黒" -> Material.BLACK_DYE;
            case "灰" -> Material.GRAY_DYE;
            case "薄灰" -> Material.LIGHT_GRAY_DYE;
            case "白" -> Material.WHITE_DYE;
            default -> Material.GRAY_DYE;
        };
    }
}
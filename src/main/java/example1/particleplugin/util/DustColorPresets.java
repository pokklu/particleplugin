package example1.particleplugin.util;

import org.bukkit.Color;
import org.bukkit.Particle;

import java.util.Map;
import java.util.List;

public class DustColorPresets {
    public static final Map<String, Particle.DustOptions> COLORS = Map.ofEntries(
            Map.entry("白", new Particle.DustOptions(Color.fromRGB(0xFFFFFF), 1.0f)),
            Map.entry("橙", new Particle.DustOptions(Color.fromRGB(0xD87F33), 1.0f)),
            Map.entry("マゼンタ", new Particle.DustOptions(Color.fromRGB(0xB24CD8), 1.0f)),
            Map.entry("水色", new Particle.DustOptions(Color.fromRGB(0x6699D8), 1.0f)),
            Map.entry("黄", new Particle.DustOptions(Color.fromRGB(0xE5E533), 1.0f)),
            Map.entry("黄緑", new Particle.DustOptions(Color.fromRGB(0x7FCC19), 1.0f)),
            Map.entry("桃", new Particle.DustOptions(Color.fromRGB(0xF27FA5), 1.0f)),
            Map.entry("灰", new Particle.DustOptions(Color.fromRGB(0x4C4C4C), 1.0f)),
            Map.entry("薄灰", new Particle.DustOptions(Color.fromRGB(0x999999), 1.0f)),
            Map.entry("シアン", new Particle.DustOptions(Color.fromRGB(0x4C7F99), 1.0f)),
            Map.entry("紫", new Particle.DustOptions(Color.fromRGB(0x7F3FB2), 1.0f)),
            Map.entry("青", new Particle.DustOptions(Color.fromRGB(0x334CB2), 1.0f)),
            Map.entry("茶", new Particle.DustOptions(Color.fromRGB(0x664C33), 1.0f)),
            Map.entry("緑", new Particle.DustOptions(Color.fromRGB(0x667F33), 1.0f)),
            Map.entry("赤", new Particle.DustOptions(Color.fromRGB(0x993333), 1.0f)),
            Map.entry("黒", new Particle.DustOptions(Color.fromRGB(0x191919), 1.0f))
    );
    public static final List<Particle.DustOptions> RAINBOW = List.of(
            new Particle.DustOptions(Color.RED, 1.0f),
            new Particle.DustOptions(Color.ORANGE, 1.0f),
            new Particle.DustOptions(Color.YELLOW, 1.0f),
            new Particle.DustOptions(Color.LIME, 1.0f),
            new Particle.DustOptions(Color.BLUE, 1.0f),
            new Particle.DustOptions(Color.PURPLE, 1.0f)
            //a
    );
}

package example1.particleplugin.util;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class DyeUtil {

    public static DyeColor getDyeColor(ItemStack item) {
        if (item == null || item.getType() == null) return null;

        Material material = item.getType();
        if (material.name().endsWith("_DYE")) {
            try {
                return DyeColor.valueOf(material.name().replace("_DYE", "").toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private static final Map<String, Material> NAME_TO_MATERIAL = new HashMap<>();

    static {
        NAME_TO_MATERIAL.put("赤", Material.RED_DYE);
        NAME_TO_MATERIAL.put("橙", Material.ORANGE_DYE);
        NAME_TO_MATERIAL.put("黄", Material.YELLOW_DYE);
        NAME_TO_MATERIAL.put("黄緑", Material.LIME_DYE);
        NAME_TO_MATERIAL.put("緑", Material.GREEN_DYE);
        NAME_TO_MATERIAL.put("水色", Material.LIGHT_BLUE_DYE);
        NAME_TO_MATERIAL.put("青", Material.BLUE_DYE);
        NAME_TO_MATERIAL.put("紫", Material.PURPLE_DYE);
        NAME_TO_MATERIAL.put("マゼンタ", Material.MAGENTA_DYE);
        NAME_TO_MATERIAL.put("桃", Material.PINK_DYE);
        NAME_TO_MATERIAL.put("黒", Material.BLACK_DYE);
        NAME_TO_MATERIAL.put("灰", Material.GRAY_DYE);
        NAME_TO_MATERIAL.put("薄灰", Material.LIGHT_GRAY_DYE);
        NAME_TO_MATERIAL.put("白", Material.WHITE_DYE);
    }

    public static Material fromName(String name) {
        return NAME_TO_MATERIAL.getOrDefault(name, Material.GRAY_DYE);
    }
}
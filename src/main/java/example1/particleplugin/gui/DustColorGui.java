package example1.particleplugin.gui;

import example1.particleplugin.util.DustColorPresets;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DustColorGui {

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "DUST色を選択 - 基本色");

        int slot = 0;
        for (Map.Entry<String, Particle.DustOptions> entry : DustColorPresets.COLORS.entrySet()) {
            String colorName = entry.getKey();
            Particle.DustOptions options = entry.getValue();

            // 色名に対応した染料アイテムを取得
            Material icon = getMaterialFromColorName(colorName);

            ItemStack item = new ItemStack(icon);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("§6" + colorName);
                List<String> lore = new ArrayList<>();
                lore.add("クリックでこの色を選択");
                meta.setLore(lore);
                item.setItemMeta(meta);
            }

            gui.setItem(slot++, item);
        }

        // 虹色選択ボタン（目立つ色で）
        ItemStack rainbow = new ItemStack(Material.WHITE_CONCRETE);
        ItemMeta rainbowMeta = rainbow.getItemMeta();
        if (rainbowMeta != null) {
            rainbowMeta.setDisplayName("§d虹色を選択");
            List<String> lore = new ArrayList<>();
            lore.add("クリックで虹色DUSTを選択");
            rainbowMeta.setLore(lore);
            rainbow.setItemMeta(rainbowMeta);
        }
        gui.setItem(26, rainbow);

        player.openInventory(gui);
    }

    private static Material getMaterialFromColorName(String colorName) {
        return switch (colorName) {
            case "赤" -> Material.RED_DYE;
            case "黄" -> Material.YELLOW_DYE;
            case "緑" -> Material.GREEN_DYE;
            case "水色" -> Material.LIGHT_BLUE_DYE;
            case "青" -> Material.BLUE_DYE;
            case "紫" -> Material.PURPLE_DYE;
            case "白" -> Material.WHITE_DYE;
            case "黒" -> Material.BLACK_DYE;
            case "黄緑" -> Material.LIME_DYE;
            case "橙" -> Material.ORANGE_DYE;
            case "マゼンタ" -> Material.MAGENTA_DYE;
            case "桃" -> Material.PINK_DYE;
            case "灰" -> Material.GRAY_DYE;
            case "薄灰" -> Material.LIGHT_GRAY_DYE;
            case "シアン" -> Material.CYAN_DYE;
            default -> Material.GRAY_DYE;
        };
    }
}
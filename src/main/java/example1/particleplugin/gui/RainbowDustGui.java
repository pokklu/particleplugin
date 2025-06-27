package example1.particleplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class RainbowDustGui {

    // 虹色の染料を順番に用意
    private static final Material[] rainbowDyes = new Material[] {
            Material.RED_DYE,
            Material.ORANGE_DYE,
            Material.YELLOW_DYE,
            Material.LIME_DYE,
            Material.GREEN_DYE,
            Material.LIGHT_BLUE_DYE,
            Material.BLUE_DYE,
            Material.PURPLE_DYE,
            Material.MAGENTA_DYE,
    };

    private static final String[] rainbowColorNames = new String[] {
            "赤", "オレンジ", "黄", "黄緑", "緑", "水色", "青", "紫", "マゼンタ"
    };

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "虹色DUSTを選択");

        for (int i = 0; i < rainbowDyes.length; i++) {
            Material dye = rainbowDyes[i];
            String name = rainbowColorNames[i];

            ItemStack item = new ItemStack(dye);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("§6" + name);
                List<String> lore = new ArrayList<>();
                lore.add("クリックでこの色を選択");
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
            gui.setItem(i, item);
        }

        // 戻るボタン
        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        if (backMeta != null) {
            backMeta.setDisplayName("§c戻る");
            back.setItemMeta(backMeta);
        }
        gui.setItem(26, back);

        player.openInventory(gui);
    }
}
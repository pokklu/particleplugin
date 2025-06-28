package example1.particleplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class EmoteCategoryGui {

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "エモートカテゴリを選択");

        ItemStack playerEmote = createItem(Material.PLAYER_HEAD, "プレイヤーのエモート",
                List.of("ここをクリックするとプレイヤーのエモートを選べます(左クリック:一種類)、(右クリック:二種類)"));

        ItemStack blockEmote = createItem(Material.IRON_PICKAXE, "ブロック破壊のエモート",
                List.of("ここをクリックするとブロック破壊時のエモートを選べます"));

        gui.setItem(11, playerEmote);
        gui.setItem(15, blockEmote);

        player.openInventory(gui);
    }

    static ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);  // ここで説明文をセット
            item.setItemMeta(meta);
        }
        return item;
    }
}
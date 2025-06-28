package example1.particleplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EmoteCategoryGui {

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "エモートカテゴリを選択");

        ItemStack playerEmote = createItem(Material.PLAYER_HEAD, "プレイヤーのエモート");
        ItemStack blockEmote = createItem(Material.IRON_PICKAXE, "ブロック破壊のエモート");

        // 真ん中の位置（13番スロット）に変更
        gui.setItem(13, playerEmote);
        // 今はブロック破壊は使わないなら削除かコメントアウトも可
        // gui.setItem(15, blockEmote);

        player.openInventory(gui);
    }

    static ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }
}
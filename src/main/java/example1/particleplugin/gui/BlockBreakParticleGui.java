package example1.particleplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakParticleGui {
    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "ブロック破壊パーティクル選択");

        int slot = 0;
        for (Particle particle : new Particle[]{
                Particle.BLOCK,
                Particle.EXPLOSION,
                Particle.SMOKE,
                Particle.FALLING_DUST,
                Particle.DAMAGE_INDICATOR,
                Particle.CRIT,
                Particle.FLAME
        }) {
            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE); // ブロック破壊感を出すアイテム
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(particle.name());
            item.setItemMeta(meta);

            gui.setItem(slot++, item);
        }

        player.openInventory(gui);
    }
}

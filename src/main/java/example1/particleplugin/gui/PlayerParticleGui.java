package example1.particleplugin.gui;

import example1.particleplugin.util.DustColorPresets;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import example1.particleplugin.util.DyeUtil;

import java.util.*;

import static example1.particleplugin.gui.EmoteCategoryGui.createItem;

public class PlayerParticleGui {

    public static final Map<Particle, Material> particleIcons = new LinkedHashMap<>();

    static {
        particleIcons.put(Particle.FLAME, Material.BLAZE_POWDER);
        particleIcons.put(Particle.HEART, Material.POPPY);
        particleIcons.put(Particle.CLOUD, Material.WHITE_WOOL);
        particleIcons.put(Particle.WITCH, Material.BREWING_STAND);
        particleIcons.put(Particle.DRAGON_BREATH, Material.DRAGON_BREATH);
        particleIcons.put(Particle.END_ROD, Material.END_ROD);
        particleIcons.put(Particle.NOTE, Material.NOTE_BLOCK);
        particleIcons.put(Particle.CRIT, Material.IRON_SWORD);
        particleIcons.put(Particle.HAPPY_VILLAGER, Material.EMERALD);
        particleIcons.put(Particle.PORTAL, Material.ENDER_PEARL);
        particleIcons.put(Particle.DAMAGE_INDICATOR, Material.MAGENTA_DYE);
        particleIcons.put(Particle.SOUL, Material.SOUL_SAND);
        particleIcons.put(Particle.ELECTRIC_SPARK, Material.REDSTONE);
        particleIcons.put(Particle.ANGRY_VILLAGER, Material.RED_DYE);
        particleIcons.put(Particle.BUBBLE_COLUMN_UP, Material.BUBBLE_CORAL_BLOCK);
        particleIcons.put(Particle.BUBBLE_POP, Material.BUBBLE_CORAL);
        particleIcons.put(Particle.CAMPFIRE_COSY_SMOKE, Material.CAMPFIRE);
        particleIcons.put(Particle.CAMPFIRE_SIGNAL_SMOKE, Material.CAMPFIRE);
        particleIcons.put(Particle.COMPOSTER, Material.COMPOSTER);
        particleIcons.put(Particle.FALLING_OBSIDIAN_TEAR, Material.OBSIDIAN);
        particleIcons.put(Particle.FIREWORK, Material.FIREWORK_ROCKET);
        particleIcons.put(Particle.FISHING, Material.FISHING_ROD);
        particleIcons.put(Particle.GLOW, Material.GLOW_BERRIES);
        particleIcons.put(Particle.LAVA, Material.LAVA_BUCKET);
        particleIcons.put(Particle.MYCELIUM, Material.MYCELIUM);
        particleIcons.put(Particle.NAUTILUS, Material.NAUTILUS_SHELL);
        particleIcons.put(Particle.SOUL_FIRE_FLAME, Material.SOUL_LANTERN);
        particleIcons.put(Particle.SQUID_INK, Material.INK_SAC);
        particleIcons.put(Particle.SWEEP_ATTACK, Material.IRON_SWORD);
        particleIcons.put(Particle.TOTEM_OF_UNDYING, Material.TOTEM_OF_UNDYING);
        particleIcons.put(Particle.WAX_OFF, Material.HONEYCOMB);
        particleIcons.put(Particle.WAX_ON, Material.HONEY_BOTTLE);
        particleIcons.put(Particle.ENCHANT, Material.ENCHANTING_TABLE);
        particleIcons.put(Particle.FALLING_LAVA, Material.LAVA_BUCKET);
        particleIcons.put(Particle.FALLING_WATER, Material.WATER_BUCKET);
        particleIcons.put(Particle.DRIPPING_LAVA, Material.LAVA_BUCKET);
        // 必要なら追加
    }

    public static ItemStack createNavigationItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static void open(Player pl, int page) {
        int per = 36, total = particleIcons.size(), pages = (int)Math.ceil(total/(double)per) + 1;
        if (page<1) page=1; if(page>pages) page=pages;
        Inventory gui = Bukkit.createInventory(null, 54, "プレイヤーエモート - ページ " + page);

        if (page == pages) {
            int slot=0;
            gui.setItem(slot++, createNav(Material.WHITE_CONCRETE, "§bDUST: 虹色"));
            for (String name : DustColorPresets.COLORS.keySet()) {
                Material icon = DyeUtil.fromName(name);
                ItemStack it = new ItemStack(icon);
                ItemMeta m = it.getItemMeta();
                m.setDisplayName("§bDUST: "+name);
                it.setItemMeta(m);
                gui.setItem(slot++, it);
            }
            if (page > 1) gui.setItem(45, createNav(Material.ARROW, "前のページ"));
            gui.setItem(49, createNav(Material.BARRIER, "§cカテゴリに戻る"));
            pl.openInventory(gui); return;
        }

        // 通常リスト表示
        List<Map.Entry<Particle,Material>> es = new ArrayList<>(particleIcons.entrySet());
        int start = (page-1)*per, end = Math.min(start+per, total);
        for(int i=start;i<end;i++){
            var e=es.get(i);
            ItemStack it=new ItemStack(e.getValue());
            ItemMeta m=it.getItemMeta();
            m.setDisplayName("§b"+ e.getKey().name());
            it.setItemMeta(m);
            gui.setItem(i-start, it);
        }
        gui.setItem(49, createNav(Material.BARRIER, "§cカテゴリに戻る"));
        if(page>1) gui.setItem(45, createNav(Material.ARROW,"前のページ"));
        if(page<pages) gui.setItem(53, createNav(Material.ARROW,"次のページ"));
        pl.openInventory(gui);
    }

    private static ItemStack createNav(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }
    public static int getTotalPages() {
        int total = particleIcons.size(), per=36;
        return (int)Math.ceil(total/(double)per) + 1;
    }
}
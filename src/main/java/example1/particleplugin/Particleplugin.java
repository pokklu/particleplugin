package example1.particleplugin;

import example1.particleplugin.gui.EmoteCategoryGui;
import example1.particleplugin.listener.BlockBreakListener;
import example1.particleplugin.listener.GuiClickListener;
import example1.particleplugin.util.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Particleplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // GUIクリックリスナー登録
        Bukkit.getPluginManager().registerEvents(new GuiClickListener(), this);

        // /gui コマンド登録
        getCommand("gui").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                EmoteCategoryGui.open(player);
            } else {
                sender.sendMessage("プレイヤーのみ使用可能です。");
            }
            return true;
        });

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Particle particle = PlayerSettings.getParticle(player);
                    if (particle == null) continue;
                    if (particle == Particle.DUST) {
                        Particle.DustOptions options;

                        if (PlayerSettings.isRainbowMode(player)) {
                            options = PlayerSettings.getNextRainbowOption(player); // 虹色の次の色を取得
                        } else {
                            options = PlayerSettings.getDustOption(player); // ✅ 1つだけ取得するメソッドに変更
                        }

                        player.getWorld().spawnParticle(
                                Particle.DUST,
                                player.getLocation().add(0, 2.5, 0),
                                20,
                                0.4, 0.5, 0.4,
                                1.0f,
                                options
                        );
                    } else {
                        double yOffset = 2.5;
                        int count = 4;
                        float offsetX = 0.2f;
                        float offsetY = 0.15f;
                        float offsetZ = 0.2f;

                        switch (particle) {
                            case FALLING_OBSIDIAN_TEAR,FIREWORK,NAUTILUS,SQUID_INK,TOTEM_OF_UNDYING,ENCHANT,FALLING_LAVA,FALLING_WATER,DRIPPING_LAVA:
                                yOffset = 0.5; // 足元
                                break;
                            case MYCELIUM:
                                count = 8;
                                break;
                            case LAVA:
                                yOffset = 0.5;
                                count = 1;
                                break;
                            case CAMPFIRE_COSY_SMOKE,CAMPFIRE_SIGNAL_SMOKE:
                                count = 1;
                                break;

                        }
                        player.getWorld().spawnParticle(
                                particle,
                                player.getLocation().add(0, yOffset, 0), // 高さを肩上〜頭上に調整
                                count, // 表示数（多く）
                                offsetX, offsetY, offsetZ, // 横幅・高さ・奥行きのばらつき
                                0 // 速度：0でその場に広がる感じ
                        );
                    }
                }
            }
        }.runTaskTimer(this, 0L, 2L); // 0.05秒実行
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    @Override
    public void onDisable() {
        // 何もしなくてOK
    }
}
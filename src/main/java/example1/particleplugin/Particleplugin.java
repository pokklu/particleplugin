package example1.particleplugin;

import example1.particleplugin.gui.EmoteCategoryGui;
import example1.particleplugin.listener.GuiClickListener;
import example1.particleplugin.util.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
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

                    Particle.DustOptions options = PlayerSettings.isRainbowMode(player)
                            ? PlayerSettings.getNextRainbowOption(player)
                            : PlayerSettings.getDustOption(player);

                    if (particle == Particle.DUST) {
                        player.getWorld().spawnParticle(
                                Particle.DUST,
                                player.getLocation().add(0, 2.5, 0),
                                20,
                                0.4, 0.5, 0.4,
                                1.0f,
                                options
                        );
                    } else {
                        // 通常パーティクル
                        double yOffset = 2.5;
                        int count = 4;
                        float offsetX = 0.2f;
                        float offsetY = 0.15f;
                        float offsetZ = 0.2f;

                        switch (particle) {
                            case FALLING_OBSIDIAN_TEAR, FIREWORK, NAUTILUS, SQUID_INK,
                                 TOTEM_OF_UNDYING, ENCHANT, FALLING_LAVA, FALLING_WATER, DRIPPING_LAVA:
                                yOffset = 0.5;
                                break;
                            case MYCELIUM:
                                count = 8;
                                break;
                            case LAVA:
                                yOffset = 0.5;
                                count = 1;
                                break;
                            case CAMPFIRE_COSY_SMOKE, CAMPFIRE_SIGNAL_SMOKE:
                                count = 1;
                                break;
                        }

                        player.getWorld().spawnParticle(
                                particle,
                                player.getLocation().add(0, yOffset, 0),
                                count,
                                offsetX, offsetY, offsetZ,
                                0
                        );

                        // 混ぜてDUSTも表示する
                        player.getWorld().spawnParticle(
                                Particle.DUST,
                                player.getLocation().add(0, 2.5, 0),
                                20,
                                0.4, 0.5, 0.4,
                                1.0f,
                                options
                        );
                    }
                }
            }
        }.runTaskTimer(this, 0L, 2L);
    }

    @Override
    public void onDisable() {
        // 何もしなくてOK
    }
}
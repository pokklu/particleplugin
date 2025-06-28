package example1.particleplugin;

import example1.particleplugin.gui.EmoteCategoryGui;
import example1.particleplugin.listener.GuiClickListener;
import example1.particleplugin.util.PlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public final class Particleplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new GuiClickListener(), this);

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
                    Set<Particle> particles = PlayerSettings.getSelectedParticles(player);
                    if (particles.isEmpty()) continue;

                    for (Particle particle : particles) {
                        if (particle == Particle.DUST) {
                            Particle.DustOptions options = PlayerSettings.isRainbowMode(player)
                                    ? PlayerSettings.getNextRainbowOption(player)
                                    : PlayerSettings.getDustOption(player);

                            player.getWorld().spawnParticle(
                                    Particle.DUST,
                                    player.getLocation().add(0, 2.5, 0),
                                    20,
                                    0.4, 0.5, 0.4,
                                    1.0f,
                                    options
                            );
                        } else {
                            // サイズ・位置調整例
                            double yOffset = 2.5;
                            int count = 5;
                            float offsetX = 0.4f;
                            float offsetY = 0.5f;
                            float offsetZ = 0.4f;

                            switch (particle) {
                                case FALLING_OBSIDIAN_TEAR:
                                case FIREWORK:
                                case NAUTILUS:
                                case SQUID_INK:
                                case TOTEM_OF_UNDYING:
                                case ENCHANT:
                                case FALLING_LAVA:
                                case FALLING_WATER:
                                case DRIPPING_LAVA:
                                    yOffset = 0.5; // 足元
                                    break;
                                case MYCELIUM:
                                    count = 8;
                                    break;
                                case LAVA:
                                    yOffset = 0.5;
                                    count = 1;
                                    break;
                                case CAMPFIRE_COSY_SMOKE:
                                case CAMPFIRE_SIGNAL_SMOKE:
                                    count = 1;
                                    break;
                                default:
                                    break;
                            }

                            player.getWorld().spawnParticle(
                                    particle,
                                    player.getLocation().add(0, yOffset, 0),
                                    count,
                                    offsetX, offsetY, offsetZ,
                                    0
                            );
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 2L); // 0.05秒毎に実行
    }

    @Override
    public void onDisable() {
        // 何もしなくてOK
    }
}
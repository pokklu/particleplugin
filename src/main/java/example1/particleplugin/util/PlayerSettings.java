package example1.particleplugin.util;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerSettings {

    private static final Map<UUID, Particle.DustOptions> dustOptionsMap = new HashMap<>();
    private static final Map<UUID, Boolean> rainbowModeMap = new HashMap<>();
    private static final Map<UUID, Integer> rainbowIndexMap = new HashMap<>();
    private static final Map<UUID, Set<Particle>> selectedParticles = new HashMap<>();

    // DUST色セット
    public static void setDustOptions(Player player, Particle.DustOptions options) {
        if (options == null) {
            dustOptionsMap.remove(player.getUniqueId());
        } else {
            dustOptionsMap.put(player.getUniqueId(), options);
        }
    }

    // DUST色取得（未設定は白色）
    public static Particle.DustOptions getDustOption(Player player) {
        return dustOptionsMap.getOrDefault(player.getUniqueId(), new Particle.DustOptions(Color.WHITE, 1.0F));
    }

    // 虹色モード設定
    public static void setRainbowMode(Player player, boolean on) {
        rainbowModeMap.put(player.getUniqueId(), on);
    }

    public static boolean isRainbowMode(Player player) {
        return rainbowModeMap.getOrDefault(player.getUniqueId(), false);
    }

    // 虹色の次の色を取得（インデックス更新）
    public static Particle.DustOptions getNextRainbowOption(Player player) {
        List<Particle.DustOptions> options = new ArrayList<>(DustColorPresets.COLORS.values());
        int index = rainbowIndexMap.getOrDefault(player.getUniqueId(), 0);
        index = (index + 1) % options.size();
        rainbowIndexMap.put(player.getUniqueId(), index);
        return options.get(index);
    }

    // パーティクル選択（最大2つまで、DUSTは重複禁止）
    public static void setParticleSelection(Player player, Particle particle) {
        Set<Particle> set = selectedParticles.getOrDefault(player.getUniqueId(), new HashSet<>());

        if (set.contains(particle)) {
            set.remove(particle); // もう一度押したら解除
        } else {
            if (set.size() >= 2) {
                player.sendMessage("§c最大2種類まで選択できます");
                return;
            }
            // DUSTが既にある場合は重複不可
            if (particle == Particle.DUST && set.contains(Particle.DUST)) {
                player.sendMessage("§cDUSTは複数選べません");
                return;
            }
            set.add(particle);
        }
        selectedParticles.put(player.getUniqueId(), set);
    }

    public static Set<Particle> getSelectedParticles(Player player) {
        return selectedParticles.getOrDefault(player.getUniqueId(), Set.of());
    }

    public static void clearSelectedParticles(Player player) {
        selectedParticles.remove(player.getUniqueId());
    }
}
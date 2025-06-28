package example1.particleplugin.util;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.*;


public class PlayerSettings {

    // プレイヤーごとの通常パーティクル管理
    private static final Map<UUID, Particle> particleMap = new HashMap<>();

    // プレイヤーごとのブロック破壊パーティクル管理
    private static final Map<UUID, Particle> blockBreakParticleMap = new HashMap<>();

    // プレイヤーごとのDUSTパーティクル色管理（DustOptions）
    private static final Map<UUID, Particle.DustOptions> dustOptionsMap = new HashMap<>();
    private static final Map<UUID, Boolean> rainbowModeMap = new HashMap<>();
    private static final Map<UUID, Integer> rainbowIndexMap = new HashMap<>();



    // 通常パーティクルをセット
    public static void setParticle(Player player, Particle particle) {
        if (particle == null) {
            particleMap.remove(player.getUniqueId());
        } else {
            particleMap.put(player.getUniqueId(), particle);
        }
    }

    // 通常パーティクルを取得
    public static Particle getParticle(Player player) {
        return particleMap.get(player.getUniqueId());
    }

    // ブロック破壊パーティクルをセット
    public static void setBlockBreakParticle(Player player, Particle particle) {
        if (particle == null) {
            blockBreakParticleMap.remove(player.getUniqueId());
        } else {
            blockBreakParticleMap.put(player.getUniqueId(), particle);
        }
    }

    // ブロック破壊パーティクルを取得
    public static Particle getBlockBreakParticle(Player player) {
        return blockBreakParticleMap.get(player.getUniqueId());
    }

    // DUSTパーティクルの色（DustOptions）をセット
    public static void setDustOptions(Player player, Particle.DustOptions options) {
        if (options == null) {
            dustOptionsMap.remove(player.getUniqueId());
        } else {
            dustOptionsMap.put(player.getUniqueId(), options);
        }
    }



    // DUSTパーティクルの色（DustOptions）を取得（未設定時は白色にフォールバック）
    public static void setRainbowMode(Player p, boolean on) {
        rainbowModeMap.put(p.getUniqueId(), on);
    }
    public static boolean isRainbowMode(Player p) {
        return rainbowModeMap.getOrDefault(p.getUniqueId(), false);
    }
    public static int nextRainbowIndex(Player p, int size) {
        int idx = rainbowIndexMap.getOrDefault(p.getUniqueId(), 0);
        idx = (idx + 1) % size;
        rainbowIndexMap.put(p.getUniqueId(), idx);
        return idx;
    }
    public static List<Particle.DustOptions> getDustOptions(Player player) {
        return new ArrayList<>(DustColorPresets.COLORS.values());
    }

    public static Particle.DustOptions getNextRainbowOption(Player player) {
        List<Particle.DustOptions> options = new ArrayList<>(DustColorPresets.COLORS.values());
        int index = nextRainbowIndex(player, options.size());
        return options.get(index);
    }
    public static Particle.DustOptions getDustOption(Player player) {
        return dustOptionsMap.getOrDefault(player.getUniqueId(),
                new Particle.DustOptions(Color.WHITE, 1.0F));
    }
}
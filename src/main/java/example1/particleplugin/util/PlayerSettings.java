package example1.particleplugin.util;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerSettings {

    // プレイヤーごとの通常パーティクル管理
    private static final Map<UUID, Particle> particleMap = new HashMap<>();

    // プレイヤーごとのブロック破壊パーティクル管理
    private static final Map<UUID, Particle> blockBreakParticleMap = new HashMap<>();

    // プレイヤーごとのDUSTパーティクル色管理（DustOptions）
    private static final Map<UUID, Particle.DustOptions> dustOptionsMap = new HashMap<>();
    private static final Map<UUID, Boolean> rainbowModeMap = new HashMap<>();


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
    public static Particle.DustOptions getDustOptions(Player player) {
        return dustOptionsMap.getOrDefault(player.getUniqueId(), new Particle.DustOptions(Color.WHITE, 1.0f));
    }
    private static final Map<UUID, Boolean> rainbowMode = new HashMap<>();

    public static void setRainbowMode(Player player, boolean enabled) {
        rainbowMode.put(player.getUniqueId(), enabled);
    }

    public static boolean isRainbowMode(Player player) {
        return rainbowMode.getOrDefault(player.getUniqueId(), false);
    }
    private static final Map<UUID, Integer> rainbowIndex = new HashMap<>();

    public static Particle.DustOptions getNextRainbowOption(Player player) {
        int index = rainbowIndex.getOrDefault(player.getUniqueId(), 0);
        index = (index + 1) % DustColorPresets.RAINBOW.size();
        rainbowIndex.put(player.getUniqueId(), index);
        return DustColorPresets.RAINBOW.get(index);
    }

    // プレイヤーが通常パーティクルを持っているか判定
    public static boolean hasParticle(Player player) {
        return particleMap.containsKey(player.getUniqueId());
    }

}
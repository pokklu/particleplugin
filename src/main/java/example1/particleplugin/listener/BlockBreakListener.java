package example1.particleplugin.listener;

import example1.particleplugin.util.PlayerSettings;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        // プレイヤーの設定したブロック破壊パーティクルを取得
        Particle particle = PlayerSettings.getBlockBreakParticle(player);

        // パーティクルが設定されていたら、その位置でパーティクルを表示
        if (particle != null) {
            player.getWorld().spawnParticle(particle, event.getBlock().getLocation().add(0.5, 0.5, 0.5), 10);
        }
    }
}


package example1.particleplugin.listener;

import example1.particleplugin.gui.PlayerParticleGui;
import example1.particleplugin.gui.EmoteCategoryGui;
import example1.particleplugin.util.DustColorPresets;
import example1.particleplugin.util.PlayerSettings;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        if (!clicked.hasItemMeta() || !clicked.getItemMeta().hasDisplayName()) return;

        String title = event.getView().getTitle();
        String displayName = clicked.getItemMeta().getDisplayName();

        event.setCancelled(true);

        if (title.equals("エモートカテゴリを選択")) {
            if (displayName.equals("プレイヤーのエモート")) {
                PlayerParticleGui.open(player, 1); // 開いたら選択リセットはここで行う
                return;
            }
        }

        if (title.startsWith("プレイヤーエモート")) {
            // ページナビゲーション
            if (clicked.getType() == Material.ARROW) {
                int page = 1;
                try {
                    page = Integer.parseInt(title.replace("プレイヤーエモート - ページ ", ""));
                } catch (NumberFormatException ignored) {}
                if (displayName.equals("前のページ")) {
                    PlayerParticleGui.open(player, page - 1);
                } else if (displayName.equals("次のページ")) {
                    PlayerParticleGui.open(player, page + 1);
                }
                return;
            }

            // DUST 虹色選択
            if (displayName.equals("§bDUST: 虹色")) {
                PlayerSettings.setRainbowMode(player, true);
                PlayerSettings.setParticleSelection(player, Particle.DUST);
                player.sendMessage("虹色DUSTパーティクルを選択しました！");
                return;
            }

            // DUST 基本色選択
            if (displayName.startsWith("§bDUST: ")) {
                String name = displayName.replace("§bDUST: ", "").trim();
                if (DustColorPresets.COLORS.containsKey(name)) {
                    PlayerSettings.setRainbowMode(player, false);
                    PlayerSettings.setDustOptions(player, DustColorPresets.COLORS.get(name));
                    PlayerSettings.setParticleSelection(player, Particle.DUST);
                    player.sendMessage(name + " のDUST色を選択しました！");
                }
                return;
            }

            // 通常パーティクル選択
            try {
                Particle p = Particle.valueOf(displayName.replace("§a[選択済] §b", "").replace("§b", ""));

                if (event.getClick() == ClickType.LEFT) {
                    PlayerSettings.clearSelectedParticles(player); // 左クリックはリセットして1つ選択
                    PlayerSettings.setParticleSelection(player, p);
                } else if (event.getClick() == ClickType.RIGHT) {
                    PlayerSettings.setParticleSelection(player, p); // 右クリックは追加選択
                }

                player.sendMessage("§a選択: " + p.name());
            } catch (IllegalArgumentException ignored) {
            }
            return;
        }

        // カテゴリ戻るボタン
        if (clicked.getType() == Material.BARRIER && displayName.equals("§cカテゴリに戻る")) {
            EmoteCategoryGui.open(player);
        }
    }
}
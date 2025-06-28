package example1.particleplugin.listener;

import example1.particleplugin.gui.*;
import example1.particleplugin.util.PlayerSettings;
import example1.particleplugin.util.DustColorPresets;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        String clickName = clicked.getItemMeta().getDisplayName();

        event.setCancelled(true);

        // --- DUST 色選択（基本色ページ） ---
        if (title.equals("DUST色を選択 - 基本色")) {
            if (clickName.equals("§d虹色を選択")) {
                RainbowDustGui.open(player);
                return;
            }

            String colorName = clickName.replace("§6", "").trim();
            if (DustColorPresets.COLORS.containsKey(colorName)) {
                PlayerSettings.setDustOptions(player, DustColorPresets.COLORS.get(colorName));
                PlayerSettings.setRainbowMode(player, false);
                PlayerSettings.setParticle(player, Particle.DUST);
                player.sendMessage(colorName + " のDUST色を選択しました！");
                player.closeInventory();
                return;
            }
        }

        // --- 虹色選択GUI ---
        if (title.equals("虹色DUSTを選択")) {
            if (clickName.equals("§c戻る")) {
                DustColorGui.open(player);
                return;
            }

            PlayerSettings.setRainbowMode(player, true);
            PlayerSettings.setParticle(player, Particle.DUST);
            player.sendMessage("虹色DUSTパーティクルを選択しました！");
            player.closeInventory();
            return;
        }

        // --- カテゴリ戻るボタン ---
        if (clicked.getType() == Material.BARRIER && clickName.equals("§cカテゴリに戻る")) {
            EmoteCategoryGui.open(player);
            return;
        }

        // --- カテゴリ選択画面 ---
        if (title.equals("エモートカテゴリを選択")) {
            if (clickName.equals("プレイヤーのエモート")) {
                PlayerParticleGui.open(player, 1);
            } else if (clickName.equals("ブロック破壊のエモート")) {
                BlockBreakParticleGui.open(player);
            }
            return;
        }

        // --- プレイヤーエモートページ ---
        if (title.startsWith("プレイヤーエモート")) {
            int page = 1;
            try {
                page = Integer.parseInt(title.replace("プレイヤーエモート - ページ ", ""));
            } catch (NumberFormatException ignored) {}

            // ページナビゲーション
            if (clicked.getType() == Material.ARROW) {
                if (clickName.equals("前のページ")) {
                    PlayerParticleGui.open(player, page - 1);
                } else if (clickName.equals("次のページ")) {
                    PlayerParticleGui.open(player, page + 1);
                }
                return;
            }

            // 最終ページ（DUST色対応ページ）
            int totalPages = PlayerParticleGui.getTotalPages();
            if (page == totalPages) {
                if (clickName.equals("§bDUST: 虹色")) {
                    PlayerSettings.setRainbowMode(player, true);
                    PlayerSettings.setParticle(player, Particle.DUST);
                    player.sendMessage("虹色DUSTパーティクルを選択しました！");
                    player.closeInventory();
                    return;
                }

                String colorName = clickName.replace("§bDUST: ", "").trim();
                if (DustColorPresets.COLORS.containsKey(colorName)) {
                    PlayerSettings.setDustOptions(player, DustColorPresets.COLORS.get(colorName));
                    PlayerSettings.setRainbowMode(player, false);
                    PlayerSettings.setParticle(player, Particle.DUST);
                    player.sendMessage(colorName + " のDUST色を選択しました！");
                    player.closeInventory();
                    return;
                }
            }

            // DUST選択されたとき：色選択GUIへ遷移
            if (page != totalPages) {
                String particleName = clickName.replace("§b", "");
                if (particleName.equals("DUST")) {
                    DustColorGui.open(player);
                    return;
                }

                try {
                    Particle selected = Particle.valueOf(particleName);
                    PlayerSettings.setParticle(player, selected);
                    player.sendMessage(selected.name() + " を選択しました！");
                    player.closeInventory();
                } catch (IllegalArgumentException e) {
                    player.sendMessage("無効なパーティクルです。");
                }
                return;
            }
        }

        // --- ブロック破壊パーティクル選択 ---
        if (title.equals("ブロック破壊パーティクル選択")) {
            try {
                Particle selected = Particle.valueOf(clickName);
                PlayerSettings.setBlockBreakParticle(player, selected);
                player.sendMessage(selected.name() + " をブロック破壊時に設定しました！");
                player.closeInventory();
            } catch (IllegalArgumentException e) {
                player.sendMessage("無効なパーティクルです: " + clickName);
            }
        }
    }
}
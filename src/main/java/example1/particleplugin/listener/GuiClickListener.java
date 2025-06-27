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

        // 「DUST色を選択 - 基本色」のGUI
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

        // 「虹色DUSTを選択」GUI
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

        // カテゴリに戻るボタン
        if (clicked.getType() == Material.BARRIER && clickName.equals("§cカテゴリに戻る")) {
            EmoteCategoryGui.open(player);
            return;
        }

        // エモートカテゴリ選択画面
        if (title.equals("エモートカテゴリを選択")) {
            if (clickName.equals("プレイヤーのエモート")) {
                PlayerParticleGui.open(player, 1);
            } else if (clickName.equals("ブロック破壊のエモート")) {
                BlockBreakParticleGui.open(player);
            }
            return;
        }

        // プレイヤーエモート画面（ページ1と2）
        if (title.startsWith("プレイヤーエモート")) {
            int page = 1;
            try {
                page = Integer.parseInt(title.replace("プレイヤーエモート - ページ ", ""));
            } catch (NumberFormatException ignored) {}

            // ページ切替用矢印ボタン
            if (clicked.getType() == Material.ARROW) {
                if (clickName.equals("前のページ")) {
                    PlayerParticleGui.open(player, page - 1);
                } else if (clickName.equals("次のページ")) {
                    PlayerParticleGui.open(player, page + 1);
                }
                return;
            }

            // ページ1でDUSTパーティクルを選択したらページ2（色選択）へ遷移
            if (page == 1) {
                String particleName = clickName.replace("§b", "");
                if (particleName.equals("DUST")) {
                    DustColorGui.open(player);
                    return;
                }

                // 通常のパーティクル選択処理
                try {
                    Particle selectedParticle = Particle.valueOf(particleName);
                    PlayerSettings.setParticle(player, selectedParticle);
                    player.sendMessage(particleName + " を選択しました！（ページ " + page + "）");
                } catch (IllegalArgumentException e) {
                    player.sendMessage("選択されたパーティクルは無効です。");
                }
                return;
            }
        }

        // ブロック破壊パーティクル選択画面
        if (title.equals("ブロック破壊パーティクル選択")) {
            try {
                Particle selected = Particle.valueOf(clickName);
                PlayerSettings.setBlockBreakParticle(player, selected);
                player.sendMessage(clickName + " をブロック破壊時のパーティクルに設定しました！");
                player.closeInventory();
            } catch (IllegalArgumentException e) {
                player.sendMessage("無効なパーティクルです: " + clickName);
            }
            return;
        }
    }
}
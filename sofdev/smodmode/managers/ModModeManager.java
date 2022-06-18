package sofdev.smodmode.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import sofdev.smodmode.Main;
import sofdev.smodmode.messages.Messages;
import sofdev.smodmode.util.CC;

import java.util.ArrayList;
import java.util.List;

public class ModModeManager {

    public static void GiveModModeItems(Player p) {

        List<String> LoreNoPerms = new ArrayList<String>();
        LoreNoPerms.add(" ");
        LoreNoPerms.add(CC.translate("&4&l★ &dStaff - Item &3&l★"));
        LoreNoPerms.add(CC.translate("&4&l★ &dThis item has been given to &3&l★"));
        LoreNoPerms.add(CC.translate("&4&l★ &dStaff: &6&l&n" + p.getName()));
        LoreNoPerms.add(" ");

        List<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(CC.translate("&3&l★ &dStaff - Item &3&l★"));
        Lore.add(CC.translate("&3&l★ &dThis item has been given to &3&l★"));
        Lore.add(CC.translate("&3&l★ &dStaff: &6&l&n" + p.getName()));
        Lore.add(" ");

        ItemStack randomteleport = new ItemStack(Material.COMPASS);
        ItemMeta rtpMeta = randomteleport.getItemMeta();
        rtpMeta.setLore(Lore);
        rtpMeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        rtpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        rtpMeta.setDisplayName(ChatColor.AQUA + "Random Teleport");
        randomteleport.setItemMeta(rtpMeta);

        @SuppressWarnings("deprecation")
        ItemStack wand = new ItemStack(271, 1);
        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        wandMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        if (!p.hasPermission("smodmode.admin")) {
            wandMeta.setDisplayName(ChatColor.RED + "WorldEdit Wand");
            wandMeta.setLore(LoreNoPerms);
        } else {
            wandMeta.setLore(Lore);
            wandMeta.setDisplayName(ChatColor.AQUA + "WorldEdit Wand");
        }
        wand.setItemMeta(wandMeta);

        ItemStack bMode = new ItemStack(Material.RED_MUSHROOM, 1);
        ItemMeta bModeMeta = bMode.getItemMeta();
        bModeMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 5, true);
        bModeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        if (!p.hasPermission("smodmode.admin")) {
            bModeMeta.setDisplayName(ChatColor.RED + "Build Mode");
            bModeMeta.setLore(LoreNoPerms);
        } else {
            bModeMeta.setLore(Lore);
            bModeMeta.setDisplayName(ChatColor.AQUA + "Build Mode");
        }
        bMode.setItemMeta(bModeMeta);


        @SuppressWarnings("deprecation")
        ItemStack vanishO = new ItemStack(351, 1, (short) 8);
        ItemMeta vanishOMeta = vanishO.getItemMeta();
        vanishOMeta.setLore(Lore);
        vanishOMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        vanishOMeta.setDisplayName(ChatColor.AQUA + "Vanish Off");
        vanishOMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        vanishO.setItemMeta(vanishOMeta);

        p.getInventory().setItem(0, wand); // W.E WAND //
        p.getInventory().setItem(1, randomteleport); // compass //
        p.getInventory().setItem(4, bMode); // build mode //
        p.getInventory().setItem(8, vanishO); // vanish //

    }

    public static void RemoveModModeItems(Player p) {
        PlayerInventory pi = p.getInventory();
        pi.clear();
    }

    public static void EnableModMode(Player p) {
        PlayerInventory pi = p.getInventory();
        Main.staff.add(p);

        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
            pl.hidePlayer(p);
        }
        Main.vanished.add(p);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.get(), new Runnable() {
            public void run() {
                if (Main.staff.contains(p)) {
                    Main.pArmor.put(p.getUniqueId(), pi.getArmorContents());
                    Main.pItems.put(p.getUniqueId(), pi.getContents());
                    Messages.titleStaffOn(p);
                    pi.setArmorContents(null);
                    pi.clear();
                    p.setGameMode(GameMode.CREATIVE);
                    Main.fix.add(p);
                    GiveModModeItems(p);
                }
            }
        }, 0L);
    }

    public static void DisableModMode(Player p) {
        PlayerInventory pi = p.getInventory();
        RemoveModModeItems(p);
        Main.staff.remove(p);
        pi.setArmorContents(null);
        p.setGameMode(GameMode.SURVIVAL);
        Messages.titleStaffOFF(p);

        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
            pl.showPlayer(p);
        }
        Main.vanished.remove(p);
        Main.fix.remove(p);

        if (Main.pArmor.containsKey(p.getUniqueId())) {
            pi.setArmorContents((ItemStack[]) Main.pArmor.get(p.getUniqueId()));
        }
        if (Main.pItems.containsKey(p.getUniqueId())) {
            pi.setContents((ItemStack[]) Main.pItems.get(p.getUniqueId()));
        }
    }

    public static boolean IsOnModMode(Player p) {
        return Main.staff.contains(p);
    }

    public static boolean IsVanished(Player p) {
        return Main.vanished.contains(p);
    }

    public static void setModBar(Player p, Boolean b) {
        if (b) {
            p.setMetadata("modactionbar", new FixedMetadataValue(Main.get(), "2"));
        } else {
            p.removeMetadata("modactionbar", Main.get());
        }
    }
}

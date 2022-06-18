package sofdev.smodmode.events;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sofdev.smodmode.Main;
import sofdev.smodmode.messages.Messages;
import sofdev.smodmode.util.CC;

import java.util.ArrayList;
import java.util.List;

/*
    Created by SofDev w/Apreciada
    14/06/2022 - 02:52:27
 */

public class AllEvents implements Listener {

    private final ArrayList<Player> vanished = new ArrayList<>();
    private final ArrayList<Player> respawn = new ArrayList<>();

    private final Main main;

    public AllEvents(Main core) {
        this.main = core;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getPlayer().hasPermission("smodmode.staff")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Player p = e.getPlayer();

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


                ItemStack stack = p.getItemInHand();
                if (Main.staff.contains(p)) {
                    if (stack != null && stack.getType() == Material.COMPASS && stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Random Teleport") && stack.getItemMeta().getLore().equals(Lore) && !stack.getEnchantments().isEmpty()) {
                        p.performCommand("srtp");
                    }
                }

                if (Main.staff.contains(p)) {
                    if (stack != null && stack.getDurability() == 10 && stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Vanish On") && stack.getItemMeta().getLore().equals(Lore) && !stack.getEnchantments().isEmpty()) {
                        Messages.titleVanishOn(p);

                        @SuppressWarnings("deprecation")
                        ItemStack vanishO = new ItemStack(351, 1, (short) 8);
                        ItemMeta vanishOMeta = vanishO.getItemMeta();
                        vanishOMeta.setLore(Lore);
                        vanishOMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                        vanishOMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        vanishOMeta.setDisplayName(ChatColor.AQUA + "Vanish Off");
                        vanishO.setItemMeta(vanishOMeta);

                        p.getInventory().remove(p.getItemInHand());
                        p.getInventory().setItem(8, vanishO);

                        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                            pl.hidePlayer(p);
                        }

                        vanished.add(p);
                    }
                }

                if (Main.staff.contains(p)) {
                    if (stack != null && stack.getDurability() == 8 && stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Vanish Off") && stack.getItemMeta().getLore().equals(Lore) && !stack.getEnchantments().isEmpty()) {
                        Messages.titleVanishOff(p);

                        @SuppressWarnings("deprecation")
                        ItemStack vanish = new ItemStack(351, 1, (short) 10);
                        ItemMeta vanishMeta = vanish.getItemMeta();
                        vanishMeta.setLore(Lore);
                        vanishMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 5, true);
                        vanishMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        vanishMeta.setDisplayName(ChatColor.AQUA + "Vanish On");
                        vanish.setItemMeta(vanishMeta);

                        p.getInventory().remove(p.getItemInHand());
                        p.getInventory().setItem(8, vanish);

                        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                            pl.showPlayer(p);
                        }

                        vanished.remove(p);
                    }
                }
                if (Main.staff.contains(p)) {
                    if (stack != null && stack.getType() == Material.RED_MUSHROOM && stack.hasItemMeta() && stack.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Build Mode") && stack.getItemMeta().getLore().equals(Lore) && !stack.getEnchantments().isEmpty()) {
                        p.performCommand("build");
                    }
                }
            }
        }
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent e) {
        if (Main.fix.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (Main.fix.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop() != null) {
            if (Main.fix.contains(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInvPickup(PlayerPickupItemEvent e) {
        if (Main.fix.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onInvMove(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            if (Main.fix.contains((Player) e.getWhoClicked())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (Main.fix.contains((Player) e.getDamager())) {
                e.setCancelled(true);
                return;
            }

            if (Main.vanished.contains((Player) e.getDamager())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (Main.staffchat.contains(p)) {
            for (Player staffs : Bukkit.getServer().getOnlinePlayers()) {
                staffs.sendMessage(CC.translate("&7[&BSC&7] " + ChatColor.DARK_AQUA + p.getName() + ChatColor.GRAY + ": " + ChatColor.AQUA + e.getMessage()));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location l = p.getLocation();

        if (Main.staff.contains(p)) {
            p.performCommand("staff");
            p.getWorld().strikeLightningEffect(l);
            e.getDrops().clear();
            respawn.add(p);
            if (vanished.contains(p)) {
                vanished.remove(p);
            }
        }
    }

    @EventHandler
    public void gamemode(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();

        if (e.getNewGameMode().equals(GameMode.SURVIVAL) && e.getNewGameMode().equals(GameMode.ADVENTURE) && e.getNewGameMode().equals(GameMode.SPECTATOR)) {
            p.setAllowFlight(true);
        }
    }

    @EventHandler
    public void setRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if (respawn.contains(p)) {
            p.performCommand("staff");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();

        if (Main.staff.contains(p)) {
            e.setCancelled(true);
        }
    }
}

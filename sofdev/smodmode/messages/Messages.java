package sofdev.smodmode.messages;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import sofdev.smodmode.Main;
import sofdev.smodmode.api.Title;
import sofdev.smodmode.util.CC;

import java.util.ArrayList;

public class Messages implements Listener {

    public ArrayList<String> chat = new ArrayList<>();

    public static void titleBuildOn(Player p) {
        Title buildOn = new Title(CC.translate("&bBuild Mode"), CC.translate("&aHas been Enabled"), 40, 30, 40);
        buildOn.send(p);
    }

    public static void titleBuildOff(Player p) {
        Title buildOff = new Title(CC.translate("&bBuild Mode"), CC.translate("&cHas been Disabled"), 40, 30, 40);
        buildOff.send(p);
    }

    public static void titleStaffOFF(Player p) {
        Title StaffOFFTITLE = new Title(CC.translate("&bStaff Mode"), CC.translate("&cHas been Disabled"), 40, 30, 40);
        StaffOFFTITLE.send(p);
    }

    public static void titleStaffOn(Player p) {
        Title StaffONTITLE = new Title(CC.translate("&bStaff Mode"), CC.translate("&aHas been Enabled"), 40, 30, 40);
        StaffONTITLE.send(p);
    }

    public static void titleVanishOff(Player p) {
        Title VanishOFF = new Title(CC.translate("&bVanish"), CC.translate("&cHas been Disabled"), 40, 30, 40);
        VanishOFF.send(p);
    }

    public static void titleVanishOn(Player p) {
        Title VanishON = new Title(CC.translate("&bVanish"), CC.translate("&aHas been Enabled"), 40, 30, 40);
        VanishON.send(p);
    }

    public static void noPermission(Player p) {
        p.sendMessage(CC.translate(Main.get().getConfig().getString("config.prefix") + Main.get().getConfig().getString("config.no-permission")));
    }

    public static void staffJoin(Player ons, Player p) {
        ons.sendMessage(CC.translate("&3[&bStaff&3] " + "&a" + p.getName() + " &bJoined &7(Vanished)"));
    }

    public static void staffLeave(Player p, Player osl) {
        osl.sendMessage(CC.translate("&3[&bStaff&3] " + "&a" + p.getName() + " &bLeft &7(Vanished)"));
    }

    public static void pluginInfo1(Player p) {
        TextComponent page2a3 = new TextComponent(CC.translate("&7&o(Page 1/3)"));

        page2a3.setColor(net.md_5.bungee.api.ChatColor.RED);
        page2a3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me for page 2!").create()));
        page2a3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "staffplugin 2"));

        p.sendMessage(" ");
        p.sendMessage(CC.translate("   &b&l&k||&6 SModMode &b&l&k||"));
        p.sendMessage(" ");
        p.sendMessage(CC.translate("&b&k|&6 -> &dAuthor: &5LSoofiaa / Apreciada &b&k|"));
        p.sendMessage(CC.translate("&b&k|&6 -> &dVersion: &f1.0.1 &b&k|"));
        p.sendMessage(CC.translate(" "));
        p.spigot().sendMessage(page2a3);
    }

    public static void pluginInfo2(Player p) {

        TextComponent page1a2 = new TextComponent(CC.translate("&7&o(Page 2/3)"));

        page1a2.setColor(net.md_5.bungee.api.ChatColor.RED);
        page1a2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me for page 3!").create()));
        page1a2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "staffplugin 3"));
        p.sendMessage(CC.translate("   &b&l&k||&6 Commands &b&l&k||"));
        p.sendMessage(" ");
        p.sendMessage(CC.translate("&b&k|&6 -> /staff &7(/mod, /staffmode) &b&k|"));
        p.sendMessage(CC.translate("&b&k|&6 -> /staffchat &7(/sc) &b&k|"));
        p.sendMessage(CC.translate("&b&k|&6 -> /build &b&k|"));
        p.sendMessage(CC.translate(" "));
        p.spigot().sendMessage(page1a2);
    }

    public static void pluginInfo3(Player p) {
        TextComponent ownclear = new TextComponent(CC.translate("&7&o(Page 3/3)"));

        ownclear.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me for clear YOUR chat!").create()));
        ownclear.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/smodmode selfchatclear"));

        p.sendMessage(CC.translate("   &b&l&k||&6 Permissions &b&l&k||"));
        p.sendMessage(" ");
        p.sendMessage(CC.translate("&b&k|&6 -> /staff = &fsmodmode.staff &b&k|"));
        p.sendMessage(CC.translate("&b&k|&6 -> /staffchat = &fsmodmode.staff &b&k|"));
        p.sendMessage(CC.translate("&b&k|&6 -> /build = &fsmodmode.admin &b&k|"));
        p.sendMessage(CC.translate(" "));
    }
}

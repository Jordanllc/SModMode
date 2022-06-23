package sofdev.smodmode.messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import sofdev.smodmode.Main;
import sofdev.smodmode.api.Title;
import sofdev.smodmode.util.CC;

/**
 *  Created by SofDev w/Apreciada
 *  14/06/2022 - 02:52:27
 */

public class Messages implements Listener {

    static FileConfiguration config = Main.get().getConfig();

    /**
     * Build Mode
     */

    static String buildNormal = config.getString("Titles.BuildMode.Normal");
    static String buildEnabled = config.getString("Titles.BuildMode.Enabled");
    static String buildDisabled = config.getString("Titles.BuildMode.Disabled");

    /**
     * Staff Mode
     */

    static String staffNormal = config.getString("Titles.StaffMode.Normal");
    static String staffEnabled = config.getString("Titles.StaffMode.Enabled");
    static String staffDisabled = config.getString("Titles.StaffMode.Disabled");
    static String staffJoin = config.getString("Messages.Staff.Join");
    static String staffLeft = config.getString("Messages.Staff.Leave");

    /**
     * Vanish
     */

    static String vanishNormal = config.getString("Titles.Vanish.Normal");
    static String vanishEnabled = config.getString("Titles.Vanish.Enabled");
    static String vanishDisabled = config.getString("Titles.Vanish.Disabled");


    public static void titleBuildOn(Player p) {
        Title buildOn = new Title(CC.translate(buildNormal), CC.translate(buildEnabled), 40, 30, 40);
        buildOn.send(p);
    }

    public static void titleBuildOff(Player p) {
        Title buildOff = new Title(CC.translate(buildNormal), CC.translate(buildDisabled), 40, 30, 40);
        buildOff.send(p);
    }

    public static void titleStaffOFF(Player p) {
        Title StaffOFFTITLE = new Title(CC.translate(staffNormal), CC.translate(staffDisabled), 40, 30, 40);
        StaffOFFTITLE.send(p);
    }

    public static void titleStaffOn(Player p) {
        Title StaffONTITLE = new Title(CC.translate(staffNormal), CC.translate(staffEnabled), 40, 30, 40);
        StaffONTITLE.send(p);
    }

    public static void titleVanishOff(Player p) {
        Title VanishOFF = new Title(CC.translate(vanishNormal), CC.translate(vanishDisabled), 40, 30, 40);
        VanishOFF.send(p);
    }

    public static void titleVanishOn(Player p) {
        Title VanishON = new Title(CC.translate(vanishNormal), CC.translate(vanishEnabled), 40, 30, 40);
        VanishON.send(p);
    }

    public static void noPermission(Player p) {
        p.sendMessage(CC.translate(Main.get().getConfig().getString("config.no-permission")));
    }

    public static void staffJoin(Player ons, Player p) {
        ons.sendMessage(CC.translate(staffJoin.replace("%player%", p.getName())));
    }

    public static void staffLeave(Player p, Player osl) {
        osl.sendMessage(CC.translate(staffLeft.replace("%player%", p.getName())));
    }

    public static void pluginInfo1(Player p) {
        for (String infoPl1 : config.getStringList("Messages.PluginInfo.Info1")) {
            if (infoPl1.contains("%version%")) {
                p.sendMessage(CC.translate(infoPl1.replace("%version%", Main.get().pdffile.getVersion())));
            } else {
                p.sendMessage(CC.translate(infoPl1));
            }
        }
    }

    public static void pluginInfo2(Player p) {
        for (String infoPl2 : config.getStringList("Messages.PluginInfo.Info2")) {
            p.sendMessage(CC.translate(infoPl2));
        }
    }

    public static void pluginInfo3(Player p) {
        for (String infoPl3 : config.getStringList("Messages.PluginInfo.Info3")) {
            p.sendMessage(CC.translate(infoPl3));
        }
    }
}

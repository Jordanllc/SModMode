package sofdev.smodmode.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sofdev.smodmode.Main;
import sofdev.smodmode.messages.Messages;


/**
 *  Created by SofDev w/Apreciada
 *  14/06/2022 - 02:52:27
 */

public class StaffListener implements Listener {

    /**
     * Staff Join
     *
     * Sends a message when a {@link Player} joins
     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("smodmode.staff")) {
            p.performCommand("staff");
            for (Player ons : Bukkit.getOnlinePlayers()) {
                if (ons.hasPermission("smodmode.staff") || ons.hasPermission("smodmode.admin")) {
                    Messages.staffJoin(ons, ons);
                }
            }
        }
    }

    /**
     * Staff Leave
     *
     * Sends a message when a {@link Player} leaves
     */

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("smodmode.staff")) {
            for (Player osl : Bukkit.getOnlinePlayers()) {
                if (osl.hasPermission("smodmode.staff") || osl.hasPermission("smodmode.admin")) {
                    Main.staff.remove(p);
                }
            }
        }
    }

    /**
     * Staff Kicked
     *
     * Send the {@link PlayerQuitEvent} message
     */

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("smodmode.staff")) {
            for (Player osl : Bukkit.getOnlinePlayers()) {
                if (osl.hasPermission("smodmode.staff") || osl.hasPermission("smodmode.admin")) {
                    Messages.staffLeave(osl, osl);
                }
            }
        }
    }
}


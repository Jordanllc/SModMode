package sofdev.smodmode.util;

import org.bukkit.ChatColor;


/**
 *  Created by SofDev w/Apreciada
 *  14/06/2022 - 02:52:27
 */

public class CC {
    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
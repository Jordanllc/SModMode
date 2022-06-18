package sofdev.smodmode.api;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ClickableText {

    public static void clickableText(Player p, String msg, String cmd) {
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(msg));

        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + cmd));

        p.spigot().sendMessage(component);
    }
}

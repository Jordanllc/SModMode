package sofdev.smodmode.api;

import org.bukkit.ChatColor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

	String noPm = ChatColor.RED + "No permission!";

	public String name();

	public String permission() default "";

	public String noPerm() default "No permission!";

	public String[] aliases() default {};

	public String description() default "";

	public String usage() default "";

	public boolean inGameOnly() default false;
}

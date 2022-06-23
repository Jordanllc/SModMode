package sofdev.smodmode.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Created by SofDev w/Apreciada
 *  14/06/2022 - 02:52:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	public String name();

	public String permission() default "";

	public String noPerm() default "No permission!";

	public String[] aliases() default {};

	public String description() default "";

	public String usage() default "";

	public boolean inGameOnly() default false;
}

package sofdev.smodmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import sofdev.smodmode.api.ActionBar;
import sofdev.smodmode.api.CommandFramework;
import sofdev.smodmode.commands.StaffCommands;
import sofdev.smodmode.events.AllEvents;
import sofdev.smodmode.listener.StaffListener;
import sofdev.smodmode.util.CC;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


/**
 *  Created by SofDev w/Apreciada
 *  14/06/2022 - 02:52:27
 */

public class Main extends JavaPlugin {

    CommandFramework framework;

    public static Main instance;

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }

    FileConfiguration config = this.getConfig();
    public PluginDescriptionFile pdffile = getDescription();
    public static HashMap<UUID, ItemStack[]> pArmor = new HashMap<UUID, ItemStack[]>();
    public static HashMap<UUID, ItemStack[]> pItems = new HashMap<UUID, ItemStack[]>();
    public static Main plugin;
    public static ArrayList<Player> staffchat = new ArrayList<Player>();
    public static ArrayList<Player> vanished = new ArrayList<Player>();
    public static ArrayList<Player> staff = new ArrayList<Player>();
    public static ArrayList<Player> fix = new ArrayList<Player>();
    public String rutaConfig;
    public String nombre = ChatColor.GRAY + "[" + ChatColor.WHITE + pdffile.getName() + ChatColor.GRAY + "]";

    public void onEnable() {
        mensajesIniciar();
    }

    public void onDisable() {
        msgApagar();
    }

    public void mensajesIniciar() {
        config.options().copyDefaults();
        registerConfig();
        this.saveDefaultConfig();
        this.getConfig();
        setInstance(this);
        Main.plugin = this;
        Main.instance = this;
        getServer().getPluginManager().registerEvents(new AllEvents(this), this);
        getServer().getPluginManager().registerEvents(new StaffListener(), this);
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.GRAY + " ");
        Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.RED + " StaffMode Has been enabled");
        Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.GRAY + " ");
        Bukkit.getConsoleSender().sendMessage(" ");
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player staffs : Bukkit.getServer().getOnlinePlayers()) {
                    if (staff.contains(staffs)) {
                        ActionBar.sendActionBar(staffs, CC.translate("&3&m  &b Staff Mode &3&m  "));
                    }
                }
            }
        }, 0L, 5L);
        framework = new CommandFramework(this);
        framework.registerCommands(new StaffCommands(this));
    }

    public void msgApagar() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.RED + " SModMode Has been disabled");
        Bukkit.getConsoleSender().sendMessage(" ");
        setInstance(null);
    }

    public void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        rutaConfig = config.getPath();
        if (!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public static Main get() {
        return getPlugin(Main.class);
    }

}






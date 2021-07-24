package com.MinceRetor.PVPManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import net.milkbowl.vault.permission.Permission;

public class PVPManager extends JavaPlugin
{
	enum PVPMode
	{
		Normal,
		Team
	}
	
	public static PVPMode pvpMode = PVPMode.Normal;
	public static boolean pvpEnabled = true;
	private static Permission perms = null;
	private static String configFileName = "config.data";
	
    @Override
    public void onEnable() 
    {
    	this.getCommand("pvpm").setExecutor(new CommandPVP());
    	getServer().getPluginManager().registerEvents(new PVPManagerListener(), this);
    	setupPermissions();
    	
    	File directory = this.getDataFolder();
	    if (! directory.exists())
	    {
	        directory.mkdir();
	    }
    	
    	PluginDataFile pluginData = new PluginDataFile();
    	if(pluginData.loadData(this.getDataFolder().getPath() + "/" + configFileName))
    	{
    		pvpEnabled = pluginData.pvpEnabled;
        	pvpMode = pluginData.pvpMode;
    	}
    }
    
    @Override
    public void onDisable() 
    {
    	File directory = this.getDataFolder();
	    if (! directory.exists())
	    {
	        directory.mkdir();
	    }
    	
    	PluginDataFile pluginData = new PluginDataFile();
    	pluginData.pvpEnabled = pvpEnabled;
    	pluginData.pvpMode = pvpMode;
    	pluginData.saveData(this.getDataFolder().getPath() + "/" + configFileName);
    }
    
    private boolean setupPermissions() 
    {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
    public static Permission getPerms()
    {
    	return perms;
    }
}
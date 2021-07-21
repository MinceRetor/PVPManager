package com.MinceRetor.PVPManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.Serializable;

import net.milkbowl.vault.permission.Permission;

public class PVPManager extends JavaPlugin
{
	enum PVPMode
	{
		Normal,
		Team
	}
	
	private class PluginDataFile implements Serializable
	{
		private PVPMode pvpMode = PVPMode.Normal;
		private boolean pvpEnabled = true;
		
		
		public boolean saveData(String filePath) 
		{
	        try 
	        {
	            FileOutputStream fileOut = new FileOutputStream(filePath);
	            GZIPOutputStream gzOut = new GZIPOutputStream(fileOut);
	            BukkitObjectOutputStream out = new BukkitObjectOutputStream(gzOut);
	            return true;
	        } catch (IOException e) 
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return false;
	        }
	    }
	}
	
	public static PVPMode pvpMode = PVPMode.Normal;
	public static boolean pvpEnabled = true;
	private static Permission perms = null;
	
    @Override
    public void onEnable() 
    {
    	this.getCommand("pvpm").setExecutor(new CommandPVP());
    	getServer().getPluginManager().registerEvents(new PVPManagerListener(), this);
    	setupPermissions();
    }
    
    @Override
    public void onDisable() 
    {
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

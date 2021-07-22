package com.MinceRetor.PVPManager;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class PluginDataFile implements Serializable
{
	private static final long serialVersionUID = 4637893747873823490L;
	public PVPManager.PVPMode pvpMode = PVPManager.PVPMode.Normal;
	public boolean pvpEnabled = true;
	
	
	public boolean saveData(String filePath) 
	{
		File configFile = new File(filePath);
    	if(!configFile.exists())
    	{ 
    		try 
    		{
				configFile.createNewFile();
			}
    		catch (IOException e) 
    		{
				e.printStackTrace();
				return false;
			}
    	}
		
        try 
        {
        	BukkitObjectOutputStream out = new BukkitObjectOutputStream(new FileOutputStream(configFile));
            out.writeObject(this);
            out.close();
            return true;
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
	
	public boolean loadData(String filePath) 
	{
		File configFile = new File(filePath);
    	if(!configFile.exists() || configFile.length() <= 0)
    	{ 
    		return false;
    	}
    	
        try 
        {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new FileInputStream(configFile));
            PluginDataFile data = (PluginDataFile) in.readObject();
            in.close();
            pvpEnabled = data.pvpEnabled;
            pvpMode = data.pvpMode;
            return true;
        }
        catch (ClassNotFoundException | IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}
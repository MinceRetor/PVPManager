package com.MinceRetor.PVPManager;
import org.bukkit.command.*;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;


public class CommandPVP implements CommandExecutor
{
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		BaseComponent message;
		
		if(args.length == 0)
		{
			message = new TextComponent("No arguments has been provided");
			message.setColor(ChatColor.RED);	
			return true;
		}
		
		
		String arg1 = args[0].toString();
		
		if(arg1.equalsIgnoreCase("on"))
		{
			PVPManager.pvpEnabled = true;
			message = new TextComponent("Enabled PVP");
		}
		else if(arg1.equalsIgnoreCase("off"))
		{
			PVPManager.pvpEnabled = false;
			message = new TextComponent("Disabled PVP");
		}
		else if(arg1.equalsIgnoreCase("info"))
		{
			message = new TextComponent("PVP Status: ");
			if(PVPManager.pvpEnabled)
			{
				message.addExtra("enabled");
			}
			else
			{
				message.addExtra("disabled");
			}
			sender.spigot().sendMessage(message);
			
			message = new TextComponent("PVP Mode: ");
			if(PVPManager.pvpMode == PVPManager.PVPMode.Normal)
			{
				message.addExtra("normal");
			}
			else if(PVPManager.pvpMode == PVPManager.PVPMode.Team)
			{
				message.addExtra("team");
			}
		}
		else if(args.length > 1 && arg1.equalsIgnoreCase("mode"))
		{
			
			String arg2 = args[1].toString().toLowerCase();
			
			if(arg2.equalsIgnoreCase("team"))
			{
				PVPManager.pvpMode = PVPManager.PVPMode.Team;
				message = new TextComponent("PVP mode set to Team");
			}
			else if(arg2.equalsIgnoreCase("normal"))
			{
				PVPManager.pvpMode = PVPManager.PVPMode.Normal;
				message = new TextComponent("PVP mode set to Normal");
			}
			else
			{
				message = new TextComponent("Incorrect argument: " + arg2 + ", must be [normal, team]");
				message.setColor(ChatColor.RED);
				return true;
			}
		}
		else
		{
			message = new TextComponent("Incorrect argument: " + arg1 + ", must be [on, off, mode]");
			message.setColor(ChatColor.RED);
			return true;
		}
		
		if(message != null)
		{
			sender.spigot().sendMessage(message);	
		}
		
        return true;
    }
}

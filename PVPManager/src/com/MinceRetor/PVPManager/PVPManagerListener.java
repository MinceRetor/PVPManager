package com.MinceRetor.PVPManager;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPManagerListener implements Listener 
{
	@EventHandler
    public void onPlayerJoin(EntityDamageByEntityEvent event) 
	{
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player)
		{
            Player targetPlayer = (Player) event.getEntity();
            Player sourcePlayer = (Player) event.getDamager();
            
            
            if(!PVPManager.pvpEnabled)
            {
            	event.setCancelled(true);
            }
        	else if(PVPManager.pvpMode == PVPManager.PVPMode.Team)
        	{
        		String[] targetPlayerGroups = PVPManager.getPerms().getPlayerGroups(targetPlayer);
        		String[] sourcePlayerGroups = PVPManager.getPerms().getPlayerGroups(sourcePlayer);
        		
        		//look for a method for optimization
        		for(int targetPlayerGroupsIterator = 0; targetPlayerGroupsIterator < targetPlayerGroups.length; targetPlayerGroupsIterator++)
        		{
        			for(int sourcePlayerGroupsIterator = 0; sourcePlayerGroupsIterator < targetPlayerGroups.length; sourcePlayerGroupsIterator++)
        			{
        				if(targetPlayerGroups[targetPlayerGroupsIterator] == sourcePlayerGroups[sourcePlayerGroupsIterator])
        				{
        					event.setCancelled(true);
        				}
        			}
        		}
        	}
        }
    }
}

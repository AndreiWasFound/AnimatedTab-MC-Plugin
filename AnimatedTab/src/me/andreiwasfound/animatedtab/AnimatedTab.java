package me.andreiwasfound.animatedtab;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.andreiwasfound.animatedtab.versions.TabManager;
import me.andreiwasfound.animatedtab.versions.TabManager_1_12_R1;
import me.andreiwasfound.animatedtab.versions.TabManager_1_15_R1;
import me.andreiwasfound.animatedtab.versions.TabManager_1_16_R1;

public class AnimatedTab extends JavaPlugin {
	
	public TabManager tab;
	public String sversion;
	
	@Override
	public void onEnable() {
		
		if (!setupManager()) {
			getLogger().severe("Failed to setup AnimatedTab! Running non-compatible version!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		
		tab.addHeader("&c&lCodedRed\n&9Join the help discord");
		tab.addHeader("&4&lCodedRed\n&9Join the help discord");
		tab.addHeader("&6&lCodedRed\n&9Join the help discord");
		
		tab.addFooter("&bPlayers Online: " + Bukkit.getOnlinePlayers().size());
		
		tab.showTab();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private boolean setupManager() {
		
		sversion = "N/A";
		
		try {
			sversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		
		if (sversion.equals("v1_12_R1"))
			tab = new TabManager_1_12_R1(this);
		else if (sversion.equals("v1_15_R1"))
			tab = new TabManager_1_15_R1(this);
		else if (sversion.equals("v1_16_R1"))
			tab = new TabManager_1_16_R1(this);
		
		return tab != null;
				
		
	}

}

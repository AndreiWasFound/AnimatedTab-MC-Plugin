package me.andreiwasfound.animatedtab;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AnimatedTab extends JavaPlugin {
	
	public TabManager tab;
	
	@Override
	public void onEnable() {
		this.tab = new TabManager(this);
		
		tab.addHeader("&c&lCodedRed\n&9Join the help discord");
		tab.addHeader("&4&lCodedRed\n&9Join the help discord");
		tab.addHeader("&6&lCodedRed\n&9Join the help discord");
		
		tab.addFooter("&bPlayers Online: " + Bukkit.getOnlinePlayers().size());
		
		tab.showTab();
	}
	
	@Override
	public void onDisable() {
		
	}

}

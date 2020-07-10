package me.andreiwasfound.animatedtab.versions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.andreiwasfound.animatedtab.AnimatedTab;
import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;



public class TabManager_1_12_R1 implements TabManager {

	private List<ChatComponentText> headers = new ArrayList<>();
	private List<ChatComponentText> footers = new ArrayList<>();
	
	 private AnimatedTab plugin;
	 public TabManager_1_12_R1(AnimatedTab plugin) {
		 this.plugin = plugin;
	 }
	 
	 public void showTab() {
		 if (headers.isEmpty() && footers.isEmpty())
			 return;
		 
		 Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			 PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
			 int count1 = 0; // headers
			 int count2 = 0; // footers
			 
			@Override
			public void run() {
				try {
					
					Field a = packet.getClass().getDeclaredField("a");
					a.setAccessible(true);
					Field b = packet.getClass().getDeclaredField("b");
					b.setAccessible(true);
					
					if (count1 >= headers.size())
						count1 = 0;
					if (count2 >= footers.size())
						count2 = 0;
					
					a.set(packet, headers.get(count1));
					b.set(packet, footers.get(count2));
					
					if (Bukkit.getOnlinePlayers().size() != 0) {
						for (Player player : Bukkit.getOnlinePlayers())
							((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
					}
					
					count1++;
					count2++;
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			 
		 }, 10, 40);
	 }
	
	 public void addHeader(String header) {
		 headers.add(new ChatComponentText(format(header)));
	 }
	 
	 public void addFooter(String footer) {
		 footers.add(new ChatComponentText(format(footer)));
	 }
	 
	 private String format(String msg) {
		 return ChatColor.translateAlternateColorCodes('&', msg);
	 }
	 
}

package fr.cyberdelta88.SendTitle.commands;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CmdSendTitle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length >= 1) {
                String playername = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playername);

                if (target == null) {
                    p.sendMessage("This player isn't on the server");

                } else {
                        if (args.length >= 2) {

                            String text = args[1];
                            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\",color:" + ChatColor.BLUE.name().toLowerCase() + "}");

                            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                            PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);

                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);

                            if (args.length >= 3) {

                                String text2 = args[2];
                                IChatBaseComponent chatsubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text2 + "\",color:" + ChatColor.DARK_BLUE.name().toLowerCase() + "}");

                                PacketPlayOutTitle title2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatsubtitle);
                                PacketPlayOutTitle length2 = new PacketPlayOutTitle(5, 20, 5);

                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title2);
                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length2);
                            }
                } else {
                            p.sendMessage(ChatColor.RED + "/" + label + " {Player} {title} {subtitle}");

                        }

                }
            } else {
                p.sendMessage(ChatColor.RED + "/" + label + " {Player} {title} {subtitle}");
            }


        }


        return false;
    }
}

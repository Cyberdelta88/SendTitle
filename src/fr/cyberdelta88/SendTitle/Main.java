package fr.cyberdelta88.SendTitle;

import fr.cyberdelta88.SendTitle.commands.CmdSendTitle;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("SendTitle has started");

        this.getCommand("sendtitle").setExecutor(new CmdSendTitle());

    }
}

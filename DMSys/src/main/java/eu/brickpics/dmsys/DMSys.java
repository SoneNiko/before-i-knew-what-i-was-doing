package eu.brickpics.dmsys;

import eu.brickpics.dmsys.commands.CommandMsg;
import eu.brickpics.dmsys.commands.CommandSpy;
import eu.brickpics.dmsys.commands.CommandTest;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;


public final class DMSys extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandMsg("msg", "msg.msg", "dm", "message"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandSpy("spy", "msg.spy", "ichbeobachtedich"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandTest("test", "msg.test"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

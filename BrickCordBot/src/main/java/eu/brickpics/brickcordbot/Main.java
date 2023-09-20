package eu.brickpics.brickcordbot;

import eu.brickpics.brickcordbot.listener.ReadyListener;
import eu.brickpics.brickcordbot.listener.commands.RegisterCommandListener;
import eu.brickpics.brickcordbot.manager.SQLWrapper;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

final class Main {

    public static void main(String[] args) throws LoginException
    {
        //SQLWrapper.makeConnection();

        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new RegisterCommandListener())
                .addEventListeners(new ReadyListener())
                .setActivity(Activity.listening("whispers"))
                .build();

    }


}
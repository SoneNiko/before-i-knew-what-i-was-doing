package eu.brickpics.brickcordbot.listener.commands;

import eu.brickpics.brickcordbot.listener.ReadyListener;
import eu.brickpics.brickcordbot.util.Misc;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegisterCommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("That is not a valid key") || !ReadyListener.isWorking || event.getAuthor().isBot()) {
            return;
        }
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        User user = event.getAuthor();

        if (channel.getId().equals("745653738911432794")) {
            if (Misc.isNumeric(msg.getContentRaw()) && msg.getContentRaw().length() == 10) {

            } else {
                channel.sendMessage("That is not a valid key").queue(message -> message.delete().queueAfter(5L, TimeUnit.SECONDS));
            }
            msg.delete().queue();
            //TODO: Save in Database
        }
    }

    private String gen_random_numbers() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt((9) + 1));
        }
        return id.toString();
    }
}

package eu.brickpics.brickcordbot.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class ReadyListener extends ListenerAdapter {
    public static boolean isWorking = false;
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        TextChannel textChannel = (TextChannel) Objects.requireNonNull(event.getJDA().getGuildById("745346189435863040")).getGuildChannelById("745653738911432794");
        isWorking = true;
        OffsetDateTime twoWeeksAgo = OffsetDateTime.now().minus(2, ChronoUnit.WEEKS);
        System.out.println("Deleting messages in channel: " + textChannel);
        new Thread(() -> {
            while (isWorking) {
                List<Message> messages = textChannel.getHistory().retrievePast(50).complete();
                messages.removeIf(m -> m.getTimeCreated().isBefore(twoWeeksAgo));
                if (messages.isEmpty()) {
                    isWorking = false;
                    System.out.println("Done deleting: " + textChannel);
                    return;
                }
                messages.forEach(m -> System.out.println("Deleting: " + m));
                if (messages.size() < 2) {
                    messages.get(0).delete().queue();
                } else {
                    textChannel.deleteMessages(messages).complete();
                }

            }
        }).start();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("BrickPics.eu");
        eb.setColor(Color.BLUE);
        eb.setDescription("Send in your key here to link your minecraft account to your discord account. You can get your key by typing /key on the minecraft server");
        eb.setFooter("Registration System");
        assert textChannel != null;
        textChannel.sendMessage(eb.build()).queue();

        isWorking = false;



        //TODO
    }
}

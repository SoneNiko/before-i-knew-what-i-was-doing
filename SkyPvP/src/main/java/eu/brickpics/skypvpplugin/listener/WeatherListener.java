package eu.brickpics.skypvpplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener{

    @EventHandler
    public void weather(WeatherChangeEvent e){

        e.setCancelled(true);
    }
}

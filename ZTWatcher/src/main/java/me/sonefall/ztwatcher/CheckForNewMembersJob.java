package me.sonefall.ztwatcher;

import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class CheckForNewMembersJob implements Job {

    Logger logger = LoggerFactory.getLogger(ZtWatcherApplication.class);

    private static Set<String> members = null;

    @Override
    public void execute(JobExecutionContext context) {
        // get member list
        WebClient client = WebClient.builder()
                .baseUrl("https://api.zerotier.com/api/v1/network/a84ac5c10abd0078/member")
                .defaultHeaders(httpHeaders ->{
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add("Authorization", "token " + ZtWatcherApplication.token);
                })
                .build();

        Mono<String> mono = client.get().retrieve().bodyToMono(String.class);

        String body;

        try {
            body = mono.toFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        JSONArray res = new JSONArray(body);
        List<JSONObject> onlineNames = new ArrayList<>();
        for (int i = 0; i < res.length(); i++) {
            JSONObject object = res.getJSONObject(i);
            if (object.getBoolean("online") && object.getString("name") != null && !Objects.equals(object.getString("name"), "")) onlineNames.add(object);
        }

        if (members == null) {
            members = new HashSet<>();
            for (JSONObject name : onlineNames) { members.add(name.getString("name")); }
            return;
        }

        List<String> newOnlineMembers = new ArrayList<>();
        List<String> newOfflineMembers = new ArrayList<>();

        // check if went online
        for (JSONObject onlineName : onlineNames) {
            String name = onlineName.getString("name");
            if (members.stream().noneMatch(s -> s.equals(name))) { newOnlineMembers.add(name); }
        }

        // check if went offline
        for (String name : members) {
            if (onlineNames.stream().noneMatch(jsonObject -> jsonObject.getString("name").equals(name))) {
                newOfflineMembers.add(name);
            }
        }

        ProcessBuilder processBuilder = null;

        for (String newOffline : newOfflineMembers) {
            logger.info(newOffline + " went offline");
            // this is for linux
            processBuilder = new ProcessBuilder("notify-send", "ZeroTier Watcher", newOffline + " went offline");
            // this is for windows
            ZtWatcherApplication.trayIcon.displayMessage("ZTWatcher", newOffline + " went offline", TrayIcon.MessageType.NONE);
        }

        for (String newOnline : newOnlineMembers) {
            logger.info(newOnline + " went online");
            // this is for linux
            processBuilder = new ProcessBuilder("notify-send", "ZeroTier Watcher", newOnline + " went online");
            // this is for windows
            ZtWatcherApplication.trayIcon.displayMessage("ZTWatcher", newOnline + " went online", TrayIcon.MessageType.NONE);
        }

        if (processBuilder != null && !System.getProperty("os.name").startsWith("Windows")) {
            processBuilder.redirectErrorStream(true);
            try { processBuilder.start(); } catch (IOException ignored) {} // TODO: 09.09.2022 dont silent fail
        }

        // update listing

        members.clear();
        for (JSONObject onlineName : onlineNames) {
            members.add(onlineName.getString("name"));
        }



    }


}
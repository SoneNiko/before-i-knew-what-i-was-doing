package me.sonefall.ztwatcher;

import org.quartz.*;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.awt.*;
import java.util.Collections;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SpringBootApplication
public class ZtWatcherApplication {

    protected static String token;
    protected static Integer interval = 5;
    protected static TrayIcon trayIcon = null;

    public static void main(String[] args) throws AWTException {

        switch (args.length) {
            case 0 -> {
                System.err.println("Error: token is required as first positional argument");
                return;
            }
            case 1 -> token = args[0];
            case 2 -> {
                token = args[0];
                interval = Integer.valueOf(args[1]);
            }
            default -> {
                System.err.println("Error: Wrong command format. expected token at first position and api refresh interval optionally at second position");
                return;
            }
        }


        // This stuff is for windows. No checks required cus it fails silently
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon t = new TrayIcon(image, "Tray Demo");
        t.setImageAutoSize(true);
        t.setToolTip("Test Tooltip");
        tray.add(t);
        trayIcon = t;

        SpringApplication springApplication = new SpringApplication();
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.setLogStartupInfo(true);
        springApplication.addPrimarySources(Collections.singleton(ZtWatcherApplication.class));
        springApplication.run(args);

        //tray.remove(trayIcon);

    }


    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(CheckForNewMembersJob.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(interval))
                .build();
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail jobDetail, SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.deleteJob(jobDetail.getKey()); // IDFK why it already exists, but I have to delete it here.
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        return scheduler;
    }










}
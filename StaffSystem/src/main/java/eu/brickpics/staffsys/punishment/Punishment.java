package eu.brickpics.staffsys.punishment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Punishment {

    private final UUID punished;
    private final String reason;
    private final LocalDateTime beginningTime;
    private final LocalDateTime endTime;
    private final PunishmentType punishmentType;
    private final UUID staffUUID;

    public Punishment(UUID punished, String reason, LocalDateTime beginningTime, LocalDateTime endTime, PunishmentType punishmentType, UUID staffUUID) {
        this.punished = punished;
        this.reason = reason;
        this.beginningTime = beginningTime;
        this.endTime = endTime;
        this.punishmentType = punishmentType;
        this.staffUUID = staffUUID;
    }



    public UUID getPunished() { return punished; }
    public String getReason() { return reason; }
    public LocalDateTime getBeginningTime() { return beginningTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public PunishmentType getPunishmentType() { return punishmentType; }
    public UUID getStaffUUID() { return staffUUID; }

    public enum PunishmentType {
        BAN,
        MUTE,
        KICK,
    }

}

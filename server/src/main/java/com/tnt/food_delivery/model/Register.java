package com.tnt.food_delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Builder(toBuilder = true)
@Document("register")
@Getter
@Setter
@AllArgsConstructor
public class Register {
    @Id
    String id;

    @NonNull String userID;

    @NonNull RegisterType type;

    @Builder.Default
    RegisterStatus status = RegisterStatus.PENDING;

    @Builder.Default
    String timeRegister = getCurrentTime();

    @Builder.Default
    String timeUpdate = getCurrentTime();

    public enum RegisterType {
        DELIVER, RESTAURANT
    }

    public enum RegisterStatus {
        PENDING, ACCEPT, CANCEL,
    }

    public static String getCurrentTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}

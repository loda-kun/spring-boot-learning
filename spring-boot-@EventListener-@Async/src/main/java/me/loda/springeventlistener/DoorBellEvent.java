package me.loda.springeventlistener;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-05-31
 * Github: https://github.com/loda-kun
 */
/*
DoorBellEvent phải kế thừa lớp ApplicationEvent của Spring
Như vậy nó mới được coi là một sự kiện hợp lệ.
 */
public class DoorBellEvent extends ApplicationEvent {
    // Tên của ngừoi bấm chuông cửa.
    public String guestName;

    /*
        Mọi Class kế thừa ApplicationEvent sẽ
        phải gọi Constructor tới lớp cha.
     */
    public DoorBellEvent(Object source, String guestName) {
        // Object source là object tham chiếu tới
        // nơi đã phát ra event này!
        super(source);
        this.guestName = guestName;
    }

    public String getGuestName() {
        return guestName;
    }
}

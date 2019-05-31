package me.loda.springeventlistener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-05-31
 * Github: https://github.com/loda-kun
 */
@Component
public class MyDog {

    /*
        @EventListener sẽ lắng nghe mọi sự kiện xảy ra
        Nếu có một sự kiện DoorBellEvent được bắn ra, nó sẽ đón lấy
        và đưa vào hàm để xử lý
     */
    /*
        @Async là cách lắng nghe sự kiện ở một Thread khác, không ảnh hưởng tới
        luồng chính
     */
    @Async
    @EventListener
    public void doorBellEventListener(DoorBellEvent doorBellEvent) throws InterruptedException {
        // Giả sử con chó đang ngủ, 1 giây sau mới dậy
        Thread.sleep(1000);
        // Sự kiện DoorBellEvent được lắng nghe và xử lý tại đây
        System.out.println(Thread.currentThread().getName() + ": Chó ngủ dậy!!!");
        System.out.println(String.format("%s: Go go!! Có người tên là %s gõ cửa!!!", Thread.currentThread().getName(), doorBellEvent.getGuestName()));
    }
}

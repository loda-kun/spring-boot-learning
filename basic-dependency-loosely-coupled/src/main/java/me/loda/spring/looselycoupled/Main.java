package me.loda.spring.looselycoupled;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/8/2019
 * Github: https://github.com/loda-kun
 */
public class Main {
    public static void main(String[] args) {
        SortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
        SortAlgorithm quickSortAlgorithm = new QuicksortAlgorithm();
        VeryComplexService business1 = new VeryComplexService(bubbleSortAlgorithm);
        VeryComplexService business2 = new VeryComplexService(quickSortAlgorithm);
    }
}

package cn.xavier.exception;

import lombok.SneakyThrows;

/**
 * @author Zheng-Wei Shui
 * @date 11/20/2021
 */
public class SneakyThrowTest {
    public static void main(String[] args) {
        throwException();
    }

    @SneakyThrows
    private static void throwException() {
        throw new TestException("test");
    }
}

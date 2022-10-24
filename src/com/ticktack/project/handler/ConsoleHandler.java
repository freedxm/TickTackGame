package com.ticktack.project.handler;

import java.util.Scanner;

public class ConsoleHandler {
    private Scanner scanner;

    public ConsoleHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        return scanner.nextLine();
    }

    public void writeFormatted(String s1, String s2) {
        System.out.println(String.format(s1, s2));
    }

    public void write(String string) {
        System.out.println(string);
    }

    public void writeWithoutLine(String string) {
        System.out.print(string);
    }
}

package com.ticktack.project.consolehandler;

import java.util.Scanner;

public class Handler {
    private Scanner scanner = new Scanner(System.in);
    public Handler(Scanner scanner){
        this.scanner = scanner;
    }
    public String read(){
        return scanner.nextLine();
    }
    public void writeFormatted(String s1, String s2){
        System.out.println(String.format(s1, s2));
    }
    public void write(String string){
        System.out.println(string);
    }
    public void writeWithoutLine(String string){
        System.out.print(string);
    }
}

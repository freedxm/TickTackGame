package com.ticktack.project;

import com.ticktack.project.service.Processor;

public class Application {
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.run();
    }
}

package ru.lazarev_am;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String path = "./";
        if(args.length > 0)
            path = args[0];

        vcpkg vcpkg = new vcpkg(path);

        MainWindow mw = new MainWindow(vcpkg);
        mw.setVisible(true);

    }
}

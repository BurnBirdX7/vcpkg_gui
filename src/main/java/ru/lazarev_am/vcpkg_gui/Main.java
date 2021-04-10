package ru.lazarev_am.vcpkg_gui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String path = "./";
        if(args.length > 0)
            path = args[0];

        try {
            Vcpkg vcpkg = new Vcpkg(path);
            MainWindow mw = new MainWindow(vcpkg);
            mw.setVisible(true);
        }
        catch (IOException e) {
            String message = "Tried path: " + path + "\n\n---- Exception ----\n";
            message += e.getMessage();
            DetailsDialog dialog = new DetailsDialog("Initialization failed", message, null);
            dialog.setVisible(true);
            System.exit(0);
        }

    }

}

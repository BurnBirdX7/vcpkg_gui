package ru.lazarev_am.vcpkg_gui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Vcpkg {

    private String mCall;

    public Vcpkg(String dirPath) throws IOException {

        Path dir = Paths.get(dirPath);
        try {
            String path = dir + "/vcpkg";
            ProcessBuilder builder = new ProcessBuilder(path);
            builder.start();
            mCall = path;
        }
        catch (IOException e) {
            ProcessBuilder builder = new ProcessBuilder("vcpkg");
            builder.start();
            mCall = "vcpkg";
        }

    }

    public static class Package {
        public String name;
        public String version;
        public String description;
    }

    public ArrayList<Package> getInstalledPackages() {
        return linesIntoPackages(runVcpkg("list"));
    }

    public ArrayList<Package> searchPackage(String packageName) {
        return linesIntoPackages(runVcpkg("search", packageName));
    }

    public String removePackage(String packageName, boolean withRecurse) {
        if (withRecurse)
            return linesIntoString(runVcpkg("remove", packageName, "--recurse"));
        else
            return linesIntoString(runVcpkg("remove", packageName));
    }

    public String installPackage(String packageName, boolean withRecurse) {
        if (withRecurse)
            return linesIntoString(runVcpkg("install", packageName, "--recurse"));
        else
            return linesIntoString(runVcpkg("install", packageName));
    }

    private ArrayList<String> runVcpkg(String... command) {
        List<String> arguments = new ArrayList<>();
        arguments.add(mCall);
        arguments.addAll(Arrays.asList(command));

        ProcessBuilder processBuilder = new ProcessBuilder(arguments);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            ArrayList<String> lines = new ArrayList<>();
            for(String line = reader.readLine(); line != null; line = reader.readLine())
                lines.add(line);

            return lines;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();

            return null;
        }
    }

    private static Package packageFromString(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string);
        Package pack = new Package();
        pack.name =  tokenizer.nextToken();
        pack.version = (pack.name.contains("[")) ? null : tokenizer.nextToken();

        StringBuilder desc = new StringBuilder();
        while (tokenizer.hasMoreTokens())
            desc.append(tokenizer.nextToken()).append(" ");

        pack.description = desc.toString();

        return pack;
    }

    private static ArrayList<Package> linesIntoPackages(List<String> lines) {
        if (lines == null)
            return null;

        ArrayList<Package> packages = new ArrayList<>();
        if (lines.size() == 1 && lines.iterator().next().startsWith("No packages are installed"))
            return packages; // Empty vector

        for (String line: lines) {
            if (line.isEmpty())
                break;
            packages.add(packageFromString(line));
        }

        return packages;
    }

    private static String linesIntoString(List<String> lines) {
        if(lines == null)
            return null;

        if(lines.isEmpty())
            return "";

        StringBuilder builder = new StringBuilder();

        var it = lines.iterator();
        builder.append(it.next());
        while (it.hasNext())
            builder.append(it.next()).append('\n');

        return builder.toString();
    }
}

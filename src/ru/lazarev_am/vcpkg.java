package ru.lazarev_am;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class vcpkg {

    private final Path mPath;

    public vcpkg() {
        this(".\\");
    }

    public vcpkg(String path) {
        mPath = Paths.get(path);
    }

    public static class Package {
        public String name;
        public String version;
        public String description;

        String[] getArray() {
            return new String[]{name, version, description};
        }
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

            StringTokenizer tokenizer = new StringTokenizer(line);
            Package pack = new Package();
            pack.name =  tokenizer.nextToken();
            pack.version = (pack.name.endsWith("]")) ? null : tokenizer.nextToken();
            pack.description = "";
            while (tokenizer.hasMoreTokens())
                pack.description += tokenizer.nextToken() + " ";

            packages.add(pack);
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

    public ArrayList<Package> getInstalledPackages() {
        return linesIntoPackages(runVcpkg("list"));
    }

    public ArrayList<Package> searchPackage(String packageName) {
        return linesIntoPackages(runVcpkg("search", packageName));
    }
    public String removePackage(String packageName) {
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
        arguments.add(mPath + "/vcpkg");
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

}

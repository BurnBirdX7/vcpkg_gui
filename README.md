# vcpkg_gui

A small GUI app for the vcpkg tool.

## Build and Execute
To build the project you need JDK 14 or 15. Build with JDK 16 will fail.\

***

### Preparation
Firstly, you have to clone the repo.
* Then you go to the repo's root
* *(for Unix)* permit execution of `.gradlew`.

**Windows:**
```shell
> cd path\to\the\repo\ # on Windows
```
**Unix:**
```shell
$ cd path/to/the/repo/
$ chmod +x gradlew
```

***

### Way 1 | Run with Gradle's `run` task
Execute in a terminal in the repository's root:
```shell
> .\gradlew run --args vcpkg_root
```
***
### Way 2 | Build and Run a jar
#### 2.1 Build
```shell
> .\gradlew jar
```
`vcpkg_gui-1.0.jar` will be located in `/build/libs/`.

#### 2.2 Run

Execute:
```shell  
> cd .\build\libs\                        # Go to the .jar file
> java -jar vcpkg_gui-1.0.jar vcpkg_root  # Execute with path to vcpkg as an argument
```

**----- or -----**

Move the .jar file into the `vcpkg_root`, and then execute:
```shell
> java -jar vcpkg_gui-1.0.jar
```
Or just with a double-click in your GUI

***
***

## Examples
[ Executed on Ubuntu 20.04 ] In my case, vcpkg was located in `~/vcpkg/`.

**Preparation**
```shell
$ git clone https://github.com/BurnBirdX7/vcpkg_gui.git # clone
$ cd ./vcpkg_gui                                        # go to the repo
$ chmod +x gradlew                                      # permit execution of ./gradlew
```

**Way 1**
```shell
$ ./gradlew run --args ~/vcpkg/                         # execute the program with "~/vcpkg/" argument
```
`chmod +x gradlew` is not required for Windows.

**Way 2**
```shell
$ ./gradlew jar                                         # build a .jar file

$ java -jar build/libs/vcpkg_gui-1.0.jar ~/vcpkg/       # execute the .jar file with "~/vcpkg" argument
```
*or*
```shell
$ ./gradlew jar                                         # build a .jar file

$ mv ./build/libs/vcpkg_gui-1.0.jar ~/vcpkg/            # move file to the vcpkg_root directory
$ cd ~/vcpkg/                                           # go to the directory
$ java -jar vcpkg_gui-1.0.jar                           # execute the .jar with no arguments
```

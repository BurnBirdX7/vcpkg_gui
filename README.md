[Русский](./README_ru.md)
# vcpkg_gui

A small GUI app for the vcpkg tool.
To run the program you need either:
 * Pass a path to the `vcpkg`'s root directory as an argument, *or*
 * Execute the program from the `vcpkg`'s root directory.
***

## Build and execute
To build the project you need JDK 14 or 15. Build with JDK 16 will fail.\
Firstly, you have to clone the repo. Then you should go to the repo's root.

### Way 1 | Run with Gradle's `run` task
Execute in the terminal:
```shell
> .\gradlew run --args path\to\vcpkg
```
(Replace `path\to\vcpkg` with the actual path to vcpkg's root)


### Way 2 | Build and execute a .jar file
#### 2.1 Build
```shell
> .\gradlew jar
```
`vcpkg_gui-1.0.jar` will be located in `/build/libs/`.

#### 2.2 Run

```shell  
> cd .\build\libs\                          # Go to the .jar file
> java -jar vcpkg_gui-1.0.jar path\to\vcpkg # Execute with path to vcpkg as an argument
```

*or*

Move it to the `vcpkg`'s root and execute it there.
***


## Example
[ Executed on Ubuntu 20.04 ] In my case, vcpkg was located in `~/vcpkg/`.

**Preparation**
```shell
$ git clone https://github.com/BurnBirdX7/vcpkg_gui.git # clone
$ cd ./vcpkg_gui                                        # go to the repo
```

**Way 1**
```shell
$ ./gradlew run --args ~/vcpkg/                         # execute the program with "~/vcpkg/" argument
```

**Way 2**
```shell
$ ./gradlew jar                                         # build a .jar file

$ java -jar build/libs/vcpkg_gui-1.0.jar ~/vcpkg/       # execute the .jar file with "~/vcpkg" argument
```
*or*
```shell
$ ./gradlew jar                                         # build a .jar file

$ mv ./build/libs/vcpkg_gui-1.0.jar ~/vcpkg/            # move file to the vcpkg root directory
$ cd ~/vcpkg/                                           # go to the directory
$ java -jar vcpkg_gui-1.0.jar                           # execute the .jar with no arguments
```

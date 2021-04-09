# vcpkg_gui

A small GUI app for vcpkg tool.

## Building and Running

To build the project you need JDK **15**.\
`vcpkg_root` is a directory that contain **vcpkg**.

### Way 1 | Run with Gradle's `run` task
Execute in terminal in the repository's root:
```shell
./gradlew run --args vcpkg_root
```
***
### Way 2 | Build and Run a jar
#### 2.1 Build
```shell
./gradlew jar
```
`vcpkg_gui-1.0.jar` will be located in `/build/libs/`.

#### 2.2 Run

Execute:
```shell  
cd ./build/libs/                        # Go to the .jar file
java -jar vcpkg_gui-1.0.jar vcpkg_root  # Execute with path to vcpkg as an argument
```

------------------------- **or** -------------------------

Move the .jar file into the `vcpkg_root`, and then execute:
```shell
java -jar vcpkg_gui-1.0.jar
```
Or just with double-click in your GUI

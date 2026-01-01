# Better: A Library Suite for Better Java Programming

The "Better" library is a collection of useful java utilities I have developed during my time working with the java languages. This library is designed to be modular, helpful, performant, free, secure, and slim.

## Build Instructions

This library comes with several modules. Some modules are dependent on other modules, which others are standalone. To build a specific module: checkout this repository onto your local machine, navigate to the root directory of the repository, and run the following command in your terminal of choice:

Windows:

```ps1
./gradlew.bat {MODULE_NAME}:build
```

Mac/Linux:
```bash
./gradlew {MODULE_NAME}
```

If you would like to build all modules, you can simply omit the `{MODULE_NAME}:` portion and simply specify `build`.

This will create a [Java Archive File](https://en.wikipedia.org/wiki/JAR_(file_format)) containing the library code, alongside source code JARs and auto-generated documentation, in each module's subfolder.

## Install Instructions

> [!NOTE]
> This library is a work-in-progress and will soon be available as a maven dependency under mavnen central.

For manual installs:

1. Reference the build instructions section above
2. For compilation: add the produced JARs to your java classpath
3. For execution: be sure to distribute the binary JARs alongside your final product!

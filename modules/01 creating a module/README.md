# How to compile the module

To compile provide the output directory and list all the java files.
```
java -d out src\com\example\greeter\Main.java src\module-info.java
```

To run the module we provide the module path and then the module name
with the full qualified name of the main class, because we didn't define
a main class in the module declaration.
```
java -p out -m com.example.greeter/com.example.greeter.Main
```

The second much easier way to compile a module is to include it in a module path (like in module2).
The module path is simply a folder with the exact name as the module and the module-info
directly inside this folder.

We define the output directory, we declare a module source path and list the modules to compile.
```
javac -d out --module-source-path src -m greeter
```

Then we compile it the same way.
```
java -p out -m greeter/com.example.greeter.Main
```
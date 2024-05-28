# Maze generator

**Prerequisites: JDK-21**
Generates maze using 

## Maze example
![image](https://github.com/Daniil-Vl/maze-generator/assets/68438400/13dd46aa-80aa-4992-b167-0a0827d8a122)


## Getting started

1. Build the project

```bash
./gradlew uberJar
```

2. Run project

```bash
java -jar maze-generator-1.0-SNAPSHOT-uber.jar ...options
```

## CLI options

**1. Paths**

Paths of files with nginx logs to analyze

```bash
java -jar ... --paths path1 path2 ...
```

**1. Width**

Maze width

```bash
java -jar ... --width number ...
```

**2. Height**

Maze height

```bash
java -jar ... --height number ...
```

**3. Start position**

Path's start position

```bash
java -jar ... --start x,y ...
```

**3. End position**

Path's end position

```bash
java -jar ... --end x,y ...
```

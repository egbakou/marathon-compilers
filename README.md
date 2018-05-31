Marathon Compilers
==================
### Continuous Integration
[![Build Status](https://travis-ci.org/egbakou/marathon-compilers.svg?branch=master)](https://travis-ci.org/egbakou/marathon-compilers)

### Quick Overview
This project was created to meet the need for compilation and execution of files during the programming contest we organized in [our university](http://www.iai-togo.com), a competition in which there was a web application with several compilers compiling and running the files of participants.
This project was based on the **zt-exec** project which allows to run external processes from **Java**. It provides several classes like **JavaCompiler, PythonCompiler, GccCompiler**, ... It is designed to be powerful but still easy to use.

## Installation
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.egbakou/marathon-compilers/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.egbakou/marathon-compilers)

The project artifacts are available in [Maven Central Repository](https://search.maven.org/#search%7Cga%7C1%7Ccom.github.egbakou).

To include it in your maven project then you have to specify the dependency.

```xml
<dependency>
    <groupId>com.github.egbakou</groupId>
    <artifactId>marathon-compilers</artifactId>
    <version>1.1</version>
</dependency>
```

## Motivation

There are many approaches to take when running external processes from Java. There are the **JRE** options such as the **Runtime.exec()** and **ProcessBuilder**. Also there is the [Apache Commons Exec](http://commons.apache.org/proper/commons-exec/).  

Some of the reasons for this library

* Represent each compiler of the programming languages used by a class.
* Return result after executing the uploded file
 * Reading/writing to streams
 * Redirecting stderr to stdout
* Improved handling of timeouts
* Improved checking of exit codes
* Improved API
 * One liners for quite complex usecases
 * One liners to get process output into a String
* Improved logging with [SLF4J API](http://www.slf4j.org/)
* Support for multiple processes


## Extension
This library can be used to configure online compilers or to set up a platform for compiling and running programs during programming competitions.

## Download
You can download it by Git:

    git clone https://github.com/egbakou/marathon-compilers.git

or download the [archive](https://github.com/egbakou/marathon-compilers/archive/master.zip).

## Requirements
To use this library, you must install:

	## =>Java
	sudo apt-get install openjdk-8-jre openjdk-8-jdk
	## configure JAVA_HOME
	## Test installation: Main.java example
  	javac Main.java && java Main 
  	--------------------------------------------------
  	## =>Python
	sudo apt-get install python3.6
	## Test installation: problem1.py example
  	python problem1.py
  	--------------------------------------------------
  	## =>PHP CLI
  	sudo apt-get install php5-cli
  	## Test installation: file.php example
  	php file.php
  	--------------------------------------------------
  	## =>Free Pascal
  	sudo apt-get install fp-compiler-3.0.2
	## Test installation: file.pass example
	fpc file.pass
	--------------------------------------------------
	## =>C & C++ compiler
	sudo apt-get install build-essential
	gcc --version
	sudo apt-get install manpages-dev man-db manpages-posix-dev
	make -v
	## Test installation: file.cpp example
	g++ file.cpp -o file
	--------------------------------------------------
	## =>Javascript(nodejs)
	sudo apt-get  install nodejs
	## Test installation: file.js example
	node file.js
	--------------------------------------------------
	## =>C# compiler
	sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 3FA7E0328081BFF6A14DA29AA6A19B38D3D831EF
	echo "deb https://download.mono-project.com/repo/ubuntu stable-bionic main" | sudo tee /etc/apt/sources.list.d/mono-official-stable.list
	sudo apt update
	sudo apt install mono-devel
	## Test installation: hello.cs example
	csc hello.cs
	--------------------------------------------------
	## =>Kotlin
	curl -s https://get.sdkman.io | bash
	sdk install kotlin
	## Test installation: Problem1.kt example
  	kotlinc Problem1.kt -include-runtime -d hello.jar
  	java -jar Problem1.jar

This library also works on the Windows OS. For use on the Windows OS, install the equivalent compilers but provide the same compilation and execution commands as those in the example above. 😊

**You can also install the compilers and interpreters that you need only.**
	
## Example
#### Java compiler
* Compile and Run java file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new JavaCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("Main.java",TimeUnit.SECONDS,3L);

        System.out.println(out);

    }
```

* Compile java file without timing constraints (Runis not recommanded in competition contest; Read method documentation for details)

```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new JavaCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileWithoutTiming("Main.java");

        System.out.println(out);

    }
```

#### Python interpreter
* Run PYTHON file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

	String out = new PythonCompiler()
                .directory(new File("/home/laurent/Documents"))
                .runInTiming("p001.py",TimeUnit.SECONDS,3L);
                
	System.out.println(out);

    }
```
* Method *runWithoutTiming* is also avialable for PythonCompiler class.

#### PHP interpreter
* Run PHP file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

	String out = new PhpCompiler()
                .directory(new File("/home/laurent/Documents"))
                .runInTiming("euler1.php",TimeUnit.SECONDS,3L);

	System.out.println(out);

    }
```
* Method *runWithoutTiming* is also avialable for PhpCompiler class.

#### JavaScript interpreter
* Run Js file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new JsCompiler()
                .directory(new File("/home/laurent/Documents"))
                .runInTiming("index.js",TimeUnit.SECONDS,3L);

        System.out.println(out);

    }
```
* Method *runWithoutTiming* is also avialable for JsCompiler class.

#### C an C++ compiler
* Compile and Run C or C++ file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new GccCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("problem1.c",TimeUnit.SECONDS,3L);

        System.out.println(out);

    }
```
* Method *compileAndRunWithoutTiming* is also avialable for GccCompiler class.

#### Pascal compiler
* Compile and Run file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new PascalCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("euler1.pas",TimeUnit.SECONDS,3L);

        System.out.println(out);

    }
```
* Method *compileAndRunWithoutTiming* is also avialable for PascalCompiler class.


#### C# compiler
* Compile and Run C# file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new CSharpCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("euler1.cs",TimeUnit.SECONDS,3L);

        System.out.println(out);

    }
```
* Method *compileAndRunWithoutTiming* is also avialable for CSharpCompiler class.

#### Kotlin compiler
* Compile and Run Kotlin file with timing constraints
* Running with a timeout of 3 seconds
```java
public static void main(String[] args) throws 
InterruptedException, IOException, TimeoutException {

        String out = new KotlinCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("Problem001.kt",TimeUnit.SECONDS,3L);

        System.out.println(out);

    }
```
* Method *compileAndRunWithoutTiming* is also avialable for KotlinCompiler class.
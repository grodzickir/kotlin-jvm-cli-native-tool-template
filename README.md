# Kotlin command-line native tool template

This template allows you to quickly build command-line tool using **Kotlin**
, **[Clikt](https://ajalt.github.io/clikt/)**
and build a native binary for your system.

It's using **Kotlin JVM** (not Kotlin Native) so you can use all those sweet Java libraries.

## Setup

- download and unpack GraalVM from their Github's releases site: https://github.com/graalvm/graalvm-ce-builds/releases
  (You don't need to replace your current JDK if you have one).
- run `<graalvm_dir>/Home/bin/gu install native-image` to download native-image tool
- in `pom.xml` set `kotlin.compiler.jvmTarget` and `graalvm.version` according to which GraalVM version you've
  downloaded

Now choose whether you want to build it only in your IDE or from your command-line:

### IDE-only build setup

- set the GraalVM as JDK in your IDE
- setup Maven in your IDE if it's not working out-of-the-box
- run Maven's `clean package` and make sure the IDE runs it with GraalVM

### CLI setup

- set GRAALVM_HOME environment variable to `Home` directory inside the unpacked GraalVM
- install Maven if you don't have it and make sure it's on PATH
- make sure Maven uses GraalVM JDK by running `mvn -v`
- run `build.sh` in this project's parent directory

## Build output

In both scenarios the native binary for the current system is generated in the `/target` directory. The name of the
binary is the same as maven's project name set in `pom.xml`.

Run it like any other native binary: `./target/kt-example`

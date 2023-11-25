## Get the source code
____
You can get the source code by cloning the repository and all its submodules using the following command:<br>

    git clone --recursive https://github.com/Andrea-Vigano/SoftwareEngineeringProject.git

## Dependencies
____
To compile this PIM application, you will need the latest version of openjdk(21.0.1 or later)
You can get it from [Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/).
Please download the required version for your operating system.

## Compiling
____
1. Get the source code and pit it in a folder. Create another folder to put in the compiled files.<br>
    ```
    mkdir ~/PIM/source_code
    mkdir ~/PIM/build
    ```
2. Enter the source code directory and execute `javac`. `App.java` is the main class.
    ```
    javac -d ../build App.java
    ```

3. Enter the build directory and you can find the compiled file `App.class`

## Usage
____
Enter the built directory and execute the software using the following command: `java App`.

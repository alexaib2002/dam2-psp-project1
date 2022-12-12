# Services and Processes Programming Project I
*Project made for the PSP module of DAM (1st semester)*

This program is a simple implementation of a process manager.
By reading an input file, defined on the program arguments or in its
shell implementation, the program will create a process for counting each
vowel and consonant of the file. It will then print the results on the screen.

## How to use it

You should pass the file path as an argument to the program. If you don't
pass any argument, the program will fall back to an interactive shell.

Make sure you have compiled both the ConsonantReader and VowelReader classes 
onto JAR files before running the program.

## Improvements over the original project

- The program now uses a shell to interact with the user.
- The program deletes the temporary files after the execution.
- Child processes inherit parent's IO streams.
- Child processes are independent of main execution, they will write found Vowels/Consonants to their temporary file
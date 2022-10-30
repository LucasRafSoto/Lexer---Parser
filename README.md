![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# Assignment 3 Documentation

Author: Lucas Soto

## Project Introduction

The parser project builds on what we completed in the lexer project. We can take the lexically analyled tokens and provide a structure to them with the parser. The parser checks for grammar and builds an abstract syntax tree of all the tokens, which we can then print with the Print Visitor class and create a GUI out of with the Draw Offset Visitor class.

## Execution and Development Environment

Java version: JDK 18
Development Environment: IDE/Codespaces

### Class Diagram

Provide a UML package & class diagram, including details for everything you added or modified. Provide enough information so that a reader would be able to understand the application (for those classes you did not modify, you may omit the details of the class, but should still include it in the class diagram, with relationships to other classes indicated). Ensure that your diagram is legible at standard screen resolutions, and does not require scrolling - break your diagram up into multiple parts if necessary.

## Scope of work and Project Discussion

Scope: Compiler, Lexer package, parser, codegen, ast package, ASTVisitor, OffsetVisitor, OffsetInfo, DrawOffsetVisitor, PrintVisitor

Compiler: 

Lexer Package: 

Parser: 

Codegen:

Ast Package: 

ASTVisitor: 

OffsetVisitor: 

OffsetInfo: 

DrawOffsetVisitor:

PrintVisitor: 


## Results and Conclusions

### What I Learned

Through this process I strengthened my understanding of utilizing object and creating instances to create better written code that is separated by tasks. Although, if I used my time more wisely then I could have done that a lot better aswell.

### What I Could Do Better

For this project we had more time than the previous ones, and I wish I had used that time more wisely. I didn't completely crunch, but I wish I had spent more time on the project earlier on so that I could have asked more questions that referred to later problems that I had. It eventually worked out, but I could have optomized and made the code look nicer. Additionally, while my draw offset visitor works, it can run into problems when it has to handle larger files. And my offset visitor also has trouble with larger files. So if I had used my time more wisely I could have gotten around to ironing those things out. 

### Challenges I Encountered

Besides what was mentioned previously with the visitor problems, I also had a big problem with the Output test. I looked through it manually and compared the output of a file copying it, to the expected output and they appear to be the exact same. However, the test always fails and doesn't provide any error message to tell me what exactly is the problem.

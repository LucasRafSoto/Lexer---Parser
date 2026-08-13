![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# Lexer & Parser

## Project Introduction

This parser project builds on my previous lexer project. We can take the lexically analyzed tokens and provide a structure to them with the parser. The parser checks for grammar and builds an abstract syntax tree of all the tokens, which we can then print with the Print Visitor class and create a GUI out of with the Draw Offset Visitor class.

## Execution and Development Environment

Java version: JDK 18
Development Environment: IDE/Codespaces

### Class Diagram

[![](https://mermaid.ink/img/pako:eNrtWN1P2zAQ_1eiPLUSvOyxQkgdHQyNUUTRNIny4DrX1MOxI8fhY4z_fec4ae3GCWul8URf0tzvd74P353dvsRUJhCPYspJUUwYSRXJ5iLCz3h2Ex39OTyMTqUinN8ogC3gmogUvjzlAWimFRNpN_CcQwikDIRmS0b7QU87J6oAZTkcnkBZ8YnMcsYbwJJCyESRx-lyWYD-wQqmZZB0hU734W8ugFGsw6lpnZrblA4Pt2ltH1uKnSY7WediKQ3F0qoicerhxYpxD8oFZ9RBBsMtiFAKuR44Tj_YZ018dS24hbW2sV7KRTd29rO0qdN3sVMX7n-25XXRu9l6l9iqBo9cG4o9EA3R-TWQBKFClorCBseeiDgTcFlmi2YANPJCE6WvJBpjUvgQiKQN1A5fGB8Gdkdre0vs9GEHsfFMVY9QUPUEC0R1I-9BRLRUCrNcvQQir8yYzLSxL6LMZqDRNicmFsKnedFNI0mCMfVSspJrlvPnFs-GbEPxk3OlpDlVhl3kTQDr9DikFDTigbpBOi21V1GqNuXJPnNJ713JQkoORNjtRzcnQHkfPtOYgAw3wFt2W0uZBvAEYT1sBOUJmvbwdVmWc2hxb0D5wZ0Sip3iiS5JtvYDy8wttOZMciqtEflbdurXM7WkQHbPQIDCMMeFPs9I6iUgI0wMCrssUWlxe-cXv-sZdr_j1Fgp8nzBCn2E8uPoniVOpeGphLnHk8YUtGsv7QEu8ZaDI2BL-o0lA9PtrCV27JEFxoBpDg0qRw97xyw39By9IAvgTWq5efEtWTy8WRs77ZFaTUjjpvEm0oGh2jg9XfwCfFQK_iG0m2przu-rvrPq-oTeSQ2vIuP6KrKLmndWuYpbm7O5HQWmtimpBEtlFYZkpdvyZ7NkVZMpewAxMascROt3ywkOyYoaGJNFL2QXHPQF2S5CdOf2rhVGIGojwiGPWz8NkCnhtMRzCSz4c4N-JcUqI7np_gMnL8e1ze8k78he7asbq1VpFQFm7ESWQo_b2UmNhaeQeNqYDyRz90bbr792b6vdumm3Jtqnd9wfKi8fedw7j62fd4FRtGR456u6TeAJ-PgWYdVHeAClZ5BHfZyVVOw3kvo4ub1mhofjI0u65uYKWLrS7v3CuxYmmA73neKFW8OZIvmK0eLTZKuXW9eVj_r75_prPvFBnOGNlLAkHsVV9c1jvcI77zwe4deEqPt5PBevyCOllrNnQeORViUcxGWe4O7UfzjFoyXhBbz-BSdP4kk?type=png)](https://mermaid-js.github.io/mermaid-live-editor/edit#pako:eNrtWN1P2zAQ_1eiPLUSvOyxQkgdHQyNUUTRNIny4DrX1MOxI8fhY4z_fec4ae3GCWul8URf0tzvd74P353dvsRUJhCPYspJUUwYSRXJ5iLCz3h2Ex39OTyMTqUinN8ogC3gmogUvjzlAWimFRNpN_CcQwikDIRmS0b7QU87J6oAZTkcnkBZ8YnMcsYbwJJCyESRx-lyWYD-wQqmZZB0hU734W8ugFGsw6lpnZrblA4Pt2ltH1uKnSY7WediKQ3F0qoicerhxYpxD8oFZ9RBBsMtiFAKuR44Tj_YZ018dS24hbW2sV7KRTd29rO0qdN3sVMX7n-25XXRu9l6l9iqBo9cG4o9EA3R-TWQBKFClorCBseeiDgTcFlmi2YANPJCE6WvJBpjUvgQiKQN1A5fGB8Gdkdre0vs9GEHsfFMVY9QUPUEC0R1I-9BRLRUCrNcvQQir8yYzLSxL6LMZqDRNicmFsKnedFNI0mCMfVSspJrlvPnFs-GbEPxk3OlpDlVhl3kTQDr9DikFDTigbpBOi21V1GqNuXJPnNJ713JQkoORNjtRzcnQHkfPtOYgAw3wFt2W0uZBvAEYT1sBOUJmvbwdVmWc2hxb0D5wZ0Sip3iiS5JtvYDy8wttOZMciqtEflbdurXM7WkQHbPQIDCMMeFPs9I6iUgI0wMCrssUWlxe-cXv-sZdr_j1Fgp8nzBCn2E8uPoniVOpeGphLnHk8YUtGsv7QEu8ZaDI2BL-o0lA9PtrCV27JEFxoBpDg0qRw97xyw39By9IAvgTWq5efEtWTy8WRs77ZFaTUjjpvEm0oGh2jg9XfwCfFQK_iG0m2przu-rvrPq-oTeSQ2vIuP6KrKLmndWuYpbm7O5HQWmtimpBEtlFYZkpdvyZ7NkVZMpewAxMascROt3ywkOyYoaGJNFL2QXHPQF2S5CdOf2rhVGIGojwiGPWz8NkCnhtMRzCSz4c4N-JcUqI7np_gMnL8e1ze8k78he7asbq1VpFQFm7ESWQo_b2UmNhaeQeNqYDyRz90bbr792b6vdumm3Jtqnd9wfKi8fedw7j62fd4FRtGR456u6TeAJ-PgWYdVHeAClZ5BHfZyVVOw3kvo4ub1mhofjI0u65uYKWLrS7v3CuxYmmA73neKFW8OZIvmK0eLTZKuXW9eVj_r75_prPvFBnOGNlLAkHsVV9c1jvcI77zwe4deEqPt5PBevyCOllrNnQeORViUcxGWe4O7UfzjFoyXhBbz-BSdP4kk)

## Scope of work and Project Discussion

Scope: Compiler, Lexer package, parser, ast package, ASTVisitor, OffsetVisitor, OffsetInfo, DrawOffsetVisitor, PrintVisitor

Compiler: Compilesa source file to bytecode. Delegates the responsibilities of parsing to parser, printing to PrintVisitor, offsetting to OffsetVisitor, and drawing to DrawOffsetVisitor. Then generating an image if requested.

Lexer Package: Takes in the source file and through lexical analysis, creates tokens for the parser to take in.

Parser: Makes sense of the tokens created by the lexer, by recursively going through the program to uphold grammar and provide a structure to the tokens so it can then build an abstract syntax tree from them.

Ast Package: The package incudes the abstract AST as well as new trees, specifically: ScientificTree, ScientificTypeTree, StringTree, StringTypeTree, ForallTree, and RangeExpTree. If the tree is accepted, then it will return the specific AST tree.

ASTVisitor: Provides an abstract layout for the visitor package which work by recursively visiting the abstract syntax trees that accept them.

OffsetVisitor: Recursively goes through the accepted syntax trees of a program to create a map of trees with their respective depth and offset values in the form of an OffsetInfo instance.

OffsetInfo: An object that is used to hold the depth and offset values of the abstract syntax trees in a program. Also of course provides constructors to get and set the offset values.

DrawOffsetVisitor: Takes in an instance of the OffsetVisitor class and from there is able to access the map created by the OffsetVisitor. With that map, it generates a 2d image and assigns x and y values of each ast based on their depth and offset values.

PrintVisitor: Visits all the ast's in a program and prints them according to their structure.

            LEXICAL ANALYSIS

- Initial stage of compilation.
- Reads from the user's source code.
    eg; int value = 32;
        if (value != 0) { print("Accurate"); }


- LEXEME (a series of valid stream of characters that are more a kin to 'words' of the 
          respective programming language)
- TOKEN (the category assigned to specific lexemes)

- A token can also carry additional information commonly called an attribute or literal value.
    eg;
        TOKEN:
            type: INTEGER_LITERAL
            lexeme: "32"
            value: 32


ROLES OF A LEXER:
    1. Reading source code character by character.
    2. Recognizing lexemes.
    3. Classifying lexemes into token categories.
    4. Identifying keywords and identifiers.
    5. Recognizing operators and delimiters.
    6. Recognizing numeric and string literals.
    7. Ignoring whitespace where appropriate.
    8. Ignoring comments where appropriate.
    9. Detecting lexical errors.
    10. Tracking source positions (line and column numbers).
    11. Producing a token stream for the next compilation stage.


  - Expected LEXER output from our example source code;
  
    LEXEME::  →    TOKEN-TYPE::
  
      int  →  KEYWORD
      value  →  IDENTIFIER
      =  →  ASSIGNMENT OPERATOR
      32  →  INTEGER LITERAL
      ;  →  SEMICOLON
      if  →  KEYWORD
      (  →  OPENING PARENTHESIS
      value  →  IDENTIFIER
      !=  →  NOT-EQUAL-TO OPERATOR
      0  →  INTEGER LITERAL
      )  →  CLOSING PARENTHESIS
      {  →  OPENING CURY BRACE
      print  →  KEYWORD
      (  →  OPENING PARENTHESIS
      "Accurate"  →  STRING LITERAL
      )  →  CLOSING PARENTHESIS
      ;  →  SEMICOLON
      }  →  CLOSING CURY BRACE

- Some programming languages treat built-in functions like 'print' as predefined identifiers.
- Here, PRINT is defined by our custom language, thus it is a KEYWORD.


TOKEN CATEGORIES:
    Category          Examples 

     Keywords                 `int`, `float`, `if`, `while` 
     Identifiers               `age`, `salary`, `total` 
     Integer literals          `10`, `250`, `0` 
     Floating-point literals   `12.5`, `3.14` 
     String literals           `"Hello"`, `"Compiler"` 
     Character literals        `'A'` 
     Arithmetic operators      `+`, `-`, `*`, `/` 
     Assignment operators      `=`, `+=`, `-=` 
     Relational operators      `>`, `<`, `>=`, `<=` 
     Equality operators        `==`, `!=` 
     Logical operators         `&&`, `||`, `!` 
     Delimiters                `(`, `)`, `{`, `}` 
     Separators                `,`, `;` 

- Spaces, tabs, newlines are generally treated as whitespaces and are ignored by the lexer.
- Whitespaces inside string literals must be preserved.
- Your language needs to define which comment style it supports as these comments are ignored by the lexer.

- A lexer must identify invalid character sequences and should report lexical error(s) ideally with the location of the error in the source file.
e.g. of errors;   invalid character,
                  Unterminated string literals,
                  Invalid numeric literals,
                  Unexpected characters,
                  Unterminated comments,
         etc...

e.g. of reporting;
    LEXICAL ERROR:
    Unexpected character '@'
    Line: 1
    Column: 11


- The lexer needs to inspect upcoming characters(lookahead) to fulfil the maximal munch (longest match) rule:
        When multiple valid tokens could match the input, choose the longest valid match.
e.g; =, ==, ===, !=, >=, etc...

    OVERVIEW OF HOW A LEXER ACTUALLY WORKS:

           SOURCE CODE
                |
                v
       CHARACTER STREAM
                |
                v
       CHARACTER SCANNING
                |
                v
       LEXEME RECOGNITION
                |
                v
       TOKEN CLASSIFICATION
                |
                v
          TOKEN STREAM
                |
                v
          END OF FILE


- A lexer can be implemented using finite state machines(FSM).
- Finite automata are fundamental to understanding how lexical analyzers recognize patterns.

Two important concepts:
- NFA — Nondeterministic Finite Automaton.
- DFA — Deterministic Finite Automaton.

- Regular expressions can describe token patterns, and finite automata can recognize those patterns.








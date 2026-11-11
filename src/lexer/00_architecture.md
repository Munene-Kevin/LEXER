WHY ENUM INSTEAD OF STRINGS?
    1. enum ensures only defined constants are accepted, catching invalid values before your program ever runs.
    2. enums improve code clarity by serving as built-in documentation for all legal values.
    3. modern IDEs provide instant autocomplete suggestions for enum constants, speeding up development and eliminating guesswork.
    4. enums are full-fledged classes, i.e; you can attach constructors, and custom methods directly to each enum constant to keep related logic bundled together.
    5. enums provide safer switch statements, as modern java compilers and IDEs can warn you if you forget to handle one of the possible cases.
    6. enum constants are singletons under the hood, meaning you can safely compare them using == instead of .equals().
        -comparing object references with == is faster than comparing strings character by character and avoids NullPointerException risks.


DIFFERENCE BETWEEN 'INT' AND 'INTEGER_LITERAL':
    INT → this is a keyword of integer data type, so is FLOAT, STRING.
    INTEGER_LITERAL → this is the actual value e.g; 23...


WHY MULTIPLE LEXEMES CAN SHARE THE SAME TOKEN TYPE?
    -this is because a token type represents a general category or rule(a pattern), while lexemes are the actual, specific character sequences found in the source code that match that rule.

HOW THE COMPILER KEEPS TRACK OF SPECIFIC LEXEMES:
    Because multiple distinct lexemes collapse into a single token type (like ID for both count and total), the lexical analyzer attaches an attribute or pointer to a symbol table.
    • The token tells the compiler what class it belongs to (e.g., ID).
    • The attribute tells later phases of the compiler which specific lexeme it was (e.g., pointing to the exact memory entry for count instead of total).


WHY 'EOF' IS NECESSARY?
    - this indicates that the lexer has reached the end of the source code.
UNKNOWN → represents an unrecognized token category, useful in some lexer designs.
    -we can report an error directly without producing this token.

























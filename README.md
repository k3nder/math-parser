## What is math-parser
math-parser is a simple parser for mathematical problems like 3*5+1
that can be used to create calculators.
## How to use math-parser
To use math parser it is very simple, you simply need a string with
The problem is the instance of the `MathParser` class that uses the **Singleton** pattern

A simple use of math-parser would be
``` java
var parser = MathParser.getInstance();
parser.parse("3*5+1");
```

## How math-parser works
Math-parser has a class called `Prob` which has 3 values:
* a
* b
* operator

a i b are numbers i operator is an enum called `Operation` that has the
following values:

| NAME | CHAR | DESC |
|:-----:|:----:|:---------------------|
|  SUM |  + | sum |
|  RES |  - | subtraction |
|  MUL |  * | multiplication |
|  DIV |  / | division |
|  NUM |  \0 | returns the number to |

The probs have a method to solve them, so if you have
a prob 1 SUM 1 if the `operate()` method is called it returns 2.

If you want to obtain all the Probs of a String expression you can use the
`MathParser` method called `getProbs()` this method returns a `ProbOp` which is a record
with the loose operators and the probs, this is an example that returns the Probs
``` java
var parser = MathParser.getInstance();
var probs = parser.getProbs("1+2+3").probs();
for (var prob: probs) {
    System.out.println( prob.getA() + " " + prob.getOperator() + " " + prob.getB() );
}
```
This will return 2 probs, one prob `1 SUM 2` and another `3 NUM 0` the second
represents a loose number since the probs can only join in pairs,
What the parser does is resolve all the Probs:
* 1 SUM 2 -> 3
* 3 NUM 0 -> 3

Optional Loose Operator with `.operators()` instead of `.probs()` in the ProbOp
which in this case is a SUM and he does the following: 3 SUM 3 and since there is only one Prob left he solves it and
bring back.

In case you only want to obtain all the numbers and all the operators exist `.getNumbers()` and `.getOperators()` in
the `MathParser` class.


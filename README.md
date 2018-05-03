# testy
Unit Test Framework (vastly simpler than JUnit)

!(docs/testy.jpg "Testy")

If your unit tests aren't passing, it might make you easily annoyed,
or marked by impatence or ill humor.  But unit tests are extremely
valuable, and learning about them is an important part of a computer
science curriculum.

A popular tool for unit testing is JUnit, from https://junit.org/.  It
works, well, but it's a large and complex tool.  It also makes extensive
use of reflection to identify and invoke tests.  It's an understandable
use of reflection, but now that Java has lambdas, normal Java techniques
can easily be used to build a list of tests to be executed, instead of
making a new set of extra-lingual rules using annotations and reflective
access.  

In an introductory course on OO programming, for example, it's better to
limit the number of new concepts students are exposed to down to a
manageable set.  Something much simpler than JUnit is called called for.



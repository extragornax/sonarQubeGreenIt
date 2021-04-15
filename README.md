## Writing a rule

In this section we will write a custom rule from scratch. To do so, we will use a [Test Driven Development](https://en.wikipedia.org/wiki/Test-driven_development) (TDD) approach, relying on writing some test cases first, followed by the implementation a solution.

### Three files to forge a rule

When implementing a rule, there is always a minimum of 3 distinct files to create:
1. A test file, which contains Java code used as input data for testing the rule
1. A test class, which contains the rule's unit test
1. A rule class, which contains the implementation of the rule.

To create our first custom rule (usually called a "*check*"), let's start by creating these 3 files in the template project, as described below:

1. In folder `/src/test/files`, create a new empty file named `MyFirstCustomCheck.java`, and copy-paste the content of the following code snippet.
```java
class MyClass {
}
```

2. In package `org.sonar.samples.java.checks` of `/src/test/java`, create a new test class called `MyFirstCustomCheckTest` and copy-paste the content of the following code snippet.
```java
package org.sonar.samples.java.checks;
 
import org.junit.jupiter.api.Test;

class MyFirstCustomCheckTest {

  @Test
  void test() {
  }

}
```

3. In package `org.sonar.samples.java.checks` of `/src/main/java`, create a new class called `MyFirstCustomCheck` extending class `org.sonar.plugins.java.api.IssuableSubscriptionVisitor` provided by the Java Plugin API. Then, replace the content of the `nodesToVisit()` method with the content from the following code snippet. This file will be described when dealing with implementation of the rule!
```java
package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import java.util.Collections;
import java.util.List;

@Rule(key = "MyFirstCustomRule")
public class MyFirstCustomCheck extends IssuableSubscriptionVisitor {

  @Override
  public List<Kind> nodesToVisit() {
    return Collections.emptyList();
  }
}

```

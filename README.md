# Modul 1 : Coding Standards

#### M Azmy Arya Rizaldi M.
##### 2206081704
##### Advanced Programming - C

---

### Reflection 1

I've learned how to build a Springboot project, starts from how to set up the project, and learned the flow of the project itself. Additionally, I've learned and applied clean coding principles to ensure the maintainability and readability of my codebase. These principles serve as the foundation for my work, enabling me to produce clean and organized code.

a. **Meaningful Variable Names:** All variables are named descriptively to enhance readability and understanding of their purpose.

b. **Consistent Formatting:** The codebase follows a consistent formatting style to maintain uniformity and readability across the project.

c. **Modularity and Reusability:** Code is organized into modular components, promoting reusability and maintainability.

---

### Reflection 2
1. After writing unit tests, I feel more confident about the reliability of my code. The number of unit tests to be written for a class depends on various factors such as the complexity of the class, the number of methods it contains, and the different scenarios that need to be tested. However, a good rule of thumb is to aim for sufficient coverage to test all critical paths and edge cases within the class.
   Getting 100% code coverage **doesn't ensure the code is completely free of bugs.** It just means that the tests have checked most parts of the code. However, we might miss testing some tricky situations, complex logic, overall system behavior, and other important aspects where bugs could hide.

2. When you repeatedly copy setup procedures and instance variables, it can cause a problem known as **code duplication**. This makes it necessary to rewrite the same code multiple times, which isn't ideal.
   Similarly, if you create numerous tests with similar setups and instance variables, it can make the code harder to read and maintain. **This can ultimately lead to errors.**
   To address this issue, it's recommended to write setup procedures and instance variables in **reusable methods or functions.** This makes them adaptable for various scenarios and reduces the need for duplication. Additionally, automating setup and teardown procedures can make the process more efficient and easier to maintain.
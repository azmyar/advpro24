# Advanced Programming

#### M Azmy Arya Rizaldi M.
##### 2206081704
##### Advanced Programming - C

---

## Modul 1 : Coding Standards

### Reflection 1

I've learned how to build a Springboot project, starts from how to set up the project, and learned the flow of the project itself. Additionally, I've learned and applied clean coding principles to ensure the maintainability and readability of my codebase. These principles serve as the foundation for my work, enabling me to produce clean and organized code.

a. **Meaningful Variable Names:** All variables are named descriptively to enhance readability and understanding of their purpose.

b. **Consistent Formatting:** The codebase follows a consistent formatting style to maintain uniformity and readability across the project.

c. **Modularity and Reusability:** Code is organized into modular components, promoting reusability and maintainability.

### Reflection 2
1. After writing unit tests, I feel more confident about the reliability of my code. The number of unit tests to be written for a class depends on various factors such as the complexity of the class, the number of methods it contains, and the different scenarios that need to be tested. However, a good rule of thumb is to aim for sufficient coverage to test all critical paths and edge cases within the class.
   Getting 100% code coverage **doesn't ensure the code is completely free of bugs.** It just means that the tests have checked most parts of the code. However, we might miss testing some tricky situations, complex logic, overall system behavior, and other important aspects where bugs could hide.

2. When you repeatedly copy setup procedures and instance variables, it can cause a problem known as **code duplication**. This makes it necessary to rewrite the same code multiple times, which isn't ideal.
   Similarly, if you create numerous tests with similar setups and instance variables, it can make the code harder to read and maintain. **This can ultimately lead to errors.**
   To address this issue, it's recommended to write setup procedures and instance variables in **reusable methods or functions.** This makes them adaptable for various scenarios and reduces the need for duplication. Additionally, automating setup and teardown procedures can make the process more efficient and easier to maintain.

---

## Modul 2 : CI/CD and DevOps

### Reflection 1

List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

#### **Unused Code:**
**Issue:** I noticed there were bits of code that weren't doing anything useful – like variables or imports that were just taking up space.
**Fix:** I went through each file and removed anything that wasn't being used. That way, the code is cleaner and easier to understand.

#### **Poor Test Coverage:**
**Issue:** Without enough tests you never know when something might break unexpectedly.
**Fix:** I wrote tests to cover all the important parts of the code, making sure it works properly and catches any bugs. I also set up automated testing so that these tests run automatically whenever changes are made, keeping everything in check and ensuring the code stays reliable. Currently my code is already on 100% coverage (jacoco)

#### **Inconsistent Logic Flow Between Service and Repository:**
**Issue:** I noticed that the logic flow between the service layer (where business logic is implemented) and the repository layer (where data is accessed) was inconsistent. Sometimes, the service layer was handling tasks that should ideally be handled by the repository, leading to a confusing separation of concerns.
**Fix:** To address this, I restructured the code so that the service layer focuses solely on business logic and delegates data access tasks to the repository layer. This ensures a cleaner and more maintainable codebase, with each layer responsible for its specific tasks. By following this separation of concerns principle, the code becomes easier to understand and maintain over time.

### Reflection 2

It appears that my project's CI/CD setup might not be fully optimized. While Jacoco, our code coverage tool, is running smoothly, SonarCloud seems to be overlooked or not properly integrated. Additionally, the deployment (using Kayeb) is incomplete due to an encountered error.

A poor CI/CD setup slows down development, compromises code quality. It leads to inefficiencies, delays in delivering features, and increased risk of errors in production.

In the future, I will prioritize improving my understanding of CI/CD principles, exploring advanced automation techniques, and staying updated with emerging DevOps tools.


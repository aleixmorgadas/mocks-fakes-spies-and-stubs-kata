# Mocks, Fakes, Spies and Stubs Kata

## Requirements

- Java 8
- Gradle
- An IDE, I would suggest IntelliJ

## Setup Environment

1. Clone the repository

    `git clone git@github.com:aleixmp/mocks-fakes-spies-and-stubs-kata.git`
2. Check all tests are green
    `./gradlew test` 

## How is the kata structured ?

- `kata.domain.film`: Test-Doubles examples made with Mockito and hand-made examples.
- `kata.domain.user`: Support domain to represent the UserId
- `kata.domain.rate`: Actual kata.
- `kata.support`: InMemoryRepositories

## How to do the Kata?

1. Check the tests at `kata.domain.rate`.
 
    You will see the `RateServiceTest_<Test Double Type>`. Implement each Test following the named pattern. 
    :info: Remember that using two different Test Double types in the same test is allowed.  
    
2. After you implemented all tests using different Test Double types, change the implementation of `RateService`. 
The goal is to break see which tests break and which aren't. So, you notice which Test Doubles are safer when changing the implementation.

### FAQ

- Should I expect miss behaviour in the code?

No, but maybe there are bugs in the code. Feel free to open an issue or talk with me for clarification.

- Should I only use **one** Test-Double type in each class?

No, it is expected that you have to use a combination of two or more test-double types to achieve the desired behaviour.

## Supportive docs

- [Business Definitions](docs/BusinessDefinitions.md)
- [Kata Slides](https://docs.google.com/presentation/d/1RTIjilK8zIiKfilBqD8x9UavBJFn089ORiLlBBgjidg)

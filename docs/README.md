# Kordinator

Kordinator is a powerful Kotlin library designed to simplify communication between components in your application,
leveraging the full potential of Kotlin Coroutines for asynchronous and non-blocking operations. Perfect for projects of
any size, from small to enterprise-level, Kordinator introduces a robust way to manage command and event handling with
dynamic dependency injection and support for interruptible behaviors, all while seamlessly integrating with Spring Boot 3.x

## Features

- Native Kotlin Coroutine Support: Write clean, concise, and asynchronous code that is easy to understand and maintain.
- Interruptible Handlers with Behaviors: Control the flow of your application with advanced handler interruption based
  on custom logic.
- Dynamic Dependency Injectors: Effortlessly manage dependencies with flexible and dynamic injection, making your code
  cleaner and more modular.
- Spring Boot 3 Integration: Enjoy out-of-the-box support for Spring Boot 3, allowing you to leverage the latest
  features of one of the most popular frameworks in the Kotlin/Java ecosystem.

## Installation

Core library is available on Maven Central Repository.

### Maven

```xml

<dependency>
    <groupId>dev.ceviz</groupId>
    <artifactId>kordinator</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle (Groovy)

```groovy
implementation 'dev.ceviz:kordinator:1.0.0'
```

### Gradle (Kotlin)

```kotlin
implementation("dev.ceviz:kordinator:1.0.0")
```

## Usage

### Basic Usage

You can find basic usage examples in the docs page [here](examples/basic.md).

### Spring Boot 3 Integration

You can find Spring Boot 3 integration examples in the docs page [here](examples/spring-boot.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Buy Me a Coffee

If you like this project, consider buying me a coffee!

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/peacecwz)

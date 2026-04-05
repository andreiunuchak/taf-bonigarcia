# taf-bonigarcia

Java test automation project: **Selenium 4** UI tests against the practice pages at [bonigarcia.dev/selenium-webdriver-java](https://bonigarcia.dev/selenium-webdriver-java/), using **JUnit 5**, **Allure** reporting, and a simple Page Object layout.

## Requirements

- **JDK 22**
- **Maven 3.x**
- For local runs: **Chrome** or **Firefox** with matching drivers available to Selenium

## Run tests

```bash
mvn clean test
```

- Default browser: **Chrome** (`-Dbrowser=firefox` for Firefox).
- **Headed mode**: tests or classes annotated with `@Headed`
- **Headless mode**: tests or classes annotated with `@Headless`
- **Remote grid**: set environment variable `remote_url` or JVM property `-Dremote_url=`, e.g. `http://localhost:4444/wd/hub`.

## Allure report

After a test run, results are under `target/allure-results`. Generate and open a report with the [Allure CLI](https://github.com/allure-framework/allure2), for example:

```bash
allure serve target/allure-results
```

## CI

GitHub Actions (`.github/workflows/github.yml`) builds the project and runs tests against a **Selenium Standalone Chrome** service, with Allure artifacts uploaded on completion.

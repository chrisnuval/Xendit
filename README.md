# Xendit Exam

## Tools Used
1. Selenium - tests are using Page Object Model
    - Uses Webdriver manager to manage web drivers
2. Circle CI - run nightly and PR builds for test changes. 
    - This will represent the reporting of tests (Coverage, Statistics, Regression issues etc.)
    - Failing tests will generate screenshots
3. TestNG
    - Test Framework used to identify tests to be run
    - Can support parallel runs

## How to Run
Note: Given that different OS have different renderings of websites, tests are expected to be flaky. To battle this, the test should be run in a docker image. Circle CI base docker image uses ubuntu 20.04.3. See https://circleci.com/developer/images/image/cimg/openjdk then see openjdk 11.0.

1. Checkout project
2. Run `mvn verify` 
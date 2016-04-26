# Android Base

[![Build Status](https://travis-ci.org/jordifierro/android-base.svg?branch=master)](https://travis-ci.org/jordifierro/android-base)



## Introduction

This projects aims to be:

* A mobile client app example to discuss about native Android setup and development.
* A basic template to start projects from it.

Specification summary:

* App boilerplate (users, settings, info...)
* Notes app example.
* MVP Clean Architecture.
* Dependency Inversion (Dagger 2 and Butterknife).
* Reactive Programming (RxJava and RxAndroid).
* RESTful client with version, language and authentication (Retrofit).
* Full unit testing coverage (Espresso, Mockito and Dagger 2).
* Other patterns and good practices.
* Continuous Integration system.
* Scripts to reset project.

Here is its counterpart server api from where this app consumes the data
-> [rails-api-base](https://github.com/jordifierro/rails-api-base)



## Quick start

* Install your favourite IDE or just the Android sdk
(buildTools 23.0.2 and compileSdk 23,
or define your own versions in the `dependencies.gradle`).
I use
[Android Studio](http://developer.android.com/sdk/index.html).


* Clone the repository and get inside it:
```
git clone git://github.com/jordifierro/android-base.git --origin android-base YourProjectName
cd YourProjectName
```

* Remove all 'note' related code (optional):
```
./bin/remove_notes
```

* Rename the project and package name:
```
./bin/rename_project YourProjectName your.package.name
```

* Connect a device (or start the emulator) and run the tests:
```
./gradlew clean build cAT
```

* Once the build is successful, create a new remote repository
and then execute this to reset the repo and push it:
```
./bin/reset_git https://github.com/yourusername/YourProjectName.git
```

That's it, you can now start developing your own app!

*__Note:__ To run the app (you can run only the tests now)
remember to setup an api server
([rails-api-base](https://github.com/jordifierro/rails-api-base)
is already developed to serve this app,
but you can also make your own)
and configure the `RestApi` to connect to it
(simply modify
[RestApi.java](https://github.com/jordifierro/android-base/blob/master/data/src/main/java/com/jordifierro/androidbase/data/net/RestApi.java)
params).*

## Documentation

The application itself is almost empty,
it only aims to provide some basic modules,
implement the structures with some patterns and give sample code.
Here are the specifications:

#### App boilerplate
The main goal of this application is to avoid writing app basic boilerplate
code, so the following functionalities are already implemented:

* User login
* User register
* User password recovery
* Settings screen with logout, delete account and information links
* Terms and conditions screen
* Privacy policy screen
* Session persistence
* Navigation
* RESTful client
* App architecture skeleton
* Multiple testing examples

This can be used from now,
and it also provides a structured way to add more code.

#### Notes Example Code
To provide more sample code, it has been developed code to manage `notes`
(like handwritten paper notes representation),
composed by a `title` and a `content`.
Thus, the app has notes index, detail, create and edit screens.

Its unique purpose is to be a sample code,
so it will be deleted when you run `./bin/remove_notes` script.

#### MVP Clean Architecture
The code has been structured following the Clean Architecture approach
and the model-view-presenter (MVP) pattern.
Check
[this](https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html)
and
[this](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
posts, and also
[this project](https://github.com/android10/Android-CleanArchitecture),
for more information about it.

#### Dependency Inversion Principle
Dependency Inversion Principle is used on the app basically
to make testing easier and make the components decoupled and reusable.
It also reduces boilerplate code and
helps with the management of the instance scopes.
The library used to achieve that is [Dagger 2](http://google.github.io/dagger/).
[Butterknife](http://jakewharton.github.io/butterknife/) is used
for view injection.

#### Reactive Programming
Clean architecture makes you create different layers and,
in order to avoid a callback hell, reactive programming
paradigm is applied using [ReactiveX](http://reactivex.io/) library.

#### RESTful client with version, language and authentication
As a client app, its main goal is to let the user interact with data from
a server api. The project shows how to deal with that easily.
[Retrofit](http://square.github.io/retrofit/)
library is used to implement the api call methods.
Authentication, api version and internationalization
are included to the requests.

#### Full unit testing coverage
One of the most interesting things of this project is that
it has full unit testing coverage, from data modules to android views,
including all intermediate layers. That is the result of the usage
of clean architecture, dependency inversion and good testing practices.
One example of that is the view testing, where the view presenter is
mocked by mockito and injected by testing dagger component, provided by
a custom junit runner.

Here you can find tutorials that explain some of this tests:
* [View unit testing](http://jordifierro.com/android-view-unit-testing)
* [HttpInterceptor testing](http://jordifierro.com/android-http-interceptor-testing)

#### Patterns and good practices
Another patterns and good practices are used in the project
such as implement activities and fragments common behavior
with inheritance, use fragments and define the navigation in the activities
to make screen remodeling painless, use interfaces to decouple components...
Android specific good practices like string internationalization,
usage of style sheet and other little methodologies are followed too.
Here one of the used resources ->
[android best practices](https://github.com/futurice/android-best-practices).

#### Continuous Integration System
[![Build Status](https://travis-ci.org/jordifierro/android-base.svg?branch=master)](https://travis-ci.org/jordifierro/android-base)

[Travis-CI](https://travis-ci.org/) has been added as a
continous integration system to run all the test on each push
(both java and android tests).

To run the tests locally, simply connect a device
or run the emulator and execute:
```
./gradlew clean build cAT
```
That will run both java and Android tests.

#### Scripts to reset project
There are some scripts under `/bin` folder to make really easy start a new
project from scratch using this template.
Just follow the 'Quick start' section placed above.

All shell scripts are verified by
[ShellCheck tool](https://github.com/koalaman/shellcheck).


#### Todo List

- [ ] Add sample integration testing.


Here is its counterpart server api from where this app consumes the data
-> [rails-api-base](https://github.com/jordifierro/rails-api-base).

## Contribute

All suggestions and contributions are more than welcome!

* Fork this repo.
* Create your feature branch (git checkout -b feature-name).
* Develop your feature and test it.
* Run tests successfully:
```
./gradlew clean build cAT
```
* Commit your changes (git commit -m 'Implement new function').
* Push the changes (git push origin feature-name).
* Create a pull request and I'll merge it with the project.

#### Contributors

Unfortunately, there are no contributors yet.

______________________
http://jordifierro.com

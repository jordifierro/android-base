# Android Base

[![Build Status](https://travis-ci.org/jordifierro/android-base.svg?branch=master)](https://travis-ci.org/jordifierro/android-base)

## Introduction

This projects aims to be:

* A mobile client app example to discuss about native Android setup and development.
* A basic template to start projects from it.

Specification summary:

* Notes app example.
* MVP Clean Architecture.
* Dependency Inversion (Dagger 2 and Butterknife).
* Reactive Programming (RxJava and RxAndroid).
* RESTful client with version, language and authentication (Retrofit).
* Whole app unit tested (Espresso, Mockito and Dagger 2).
* Other patterns and good practices.
* Continuous Integration system.

Here is its counterpart server api from where this app consumes the data
-> [rails-api-base](https://github.com/jordifierro/rails-api-base)


## Documentation

The application itself is almost empty,
it only aims to provide some basic modules,
implement the structures with some patterns and give sample code.
Here are the specifications:

#### Notes Example Code
To provide the app sample code, it has been developed code to manage `notes`
(like handwritten paper notes representation),
composed by a `title` and a `content`.
Thus, the app has notes index, detail, create and edit screens.

Its unique purpose is to be a guide of how to add new code,
so you can delete it when it's useless.

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

#### Whole app unit tested
One of the most interesting things of this project is that
it has full unit testing coverage, from data modules to android views,
including all intermediate layers. That is the result of the usage
of clean architecture, dependency inversion and good testing practices.
One example of that is the view testing, where the view presenter is
mocked by mockito and injected by testing dagger component, provided by
a custom junit runner.

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


#### Todo List

- [ ] Implement reset project scripts and tutorial.
- [ ] Add sample integration testing.
- [ ] Add sample privacy policy.
- [ ] Implement caching.
- [ ] Version expiration alert messages and redirect.
- [ ] Implement deep linking navigation.
- [ ] Add code style, test coverage and other code quality tools.


Here is its counterpart server api from where this app consumes the data
-> [rails-api-base](https://github.com/jordifierro/rails-api-base).

## Contribute

All suggestions and contributions are more than welcome!

* Fork this repo.
* Create your feature branch (git checkout -b feature-name).
* Commit your changes (git commit -m 'Implement new function').
* Push the changes (git push origin feature-name).
* Create a pull request and I'll merge it with the project.

#### Contributors

Unfortunately, there are no contributors yet.

______________________
http://jordifierro.com

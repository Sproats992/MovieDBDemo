# MovieDBTest

Movie information from The Movie DB. (https://www.themoviedb.org/)

This Android app is a demo showing MVVM and Clean architecture layering principles.

It fetches a list of the latest, most popular movies from The Movie DB, diplays them in a list and allows movies to be selected to display more detailed information.

# To run this app, you need an API key!

Add your TMDB api key to the RepositoryConstants.kt file to get this app to work.
(I didn't want to expose my personal API key publically on the internet)

# Software Architecture

The architecture employed attempts to align with the latest Android architectural patterns (Jetpack) and incorporate strong Clean Architecture (Robert C. Martin type) in the software design.

To that end, the app is modular and employs seperate Presentaton/UI feature modules (app and movies), a repository module to contain data and network requests and Domain module. In line with Clean Architecture the Domain module is completely platform independent and is  isolated by the Gradle dependencies which contain no references to the Android target platform.

The UI feature modules incorporate a Model, View, ViewModel (MVVM) structure.

Entity mappers are provided at the boundaries of the Presentation and Repository layers to convert data models into entities suitable for each layer.

The Domain specifies repository interfaces, data models and UseCases.


# Technologies used

* Dependencies are managed (for the most part) with Dagger2. 
* UseCases are isolated from the main thread using Kotlin Coroutines and Kotlin Flow.
* Android Jetpack (AndroidX) is used for most of the app, including Navigation.
* Application structure is based on Clean Architure, breaking the app into the "onion" as far as possible.
* Presentation software structure is based on MVVM using Android ViewModels.
* Networking and Internet access uses Retrofit2.

# TODOs.

TODO - Fix build.gradle dependency versioning.

TODO - Finish testsuite for ViewModels and Data.

TODO - Add Room based persistent storage.

TODO - Add background data refresh.

TODO - Allow sorting of data.

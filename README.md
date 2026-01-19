# News Reader - A Modern Android Showcase App

This repository contains a feature-rich News Reader app for Android, built to demonstrate a modern, scalable, and production-ready mobile architecture.

## Project Goal

The primary objective was to build a clean, well-architected news application that not only meets a set of functional requirements but also serves as a strong example of modern Android development best practices. The focus was on creating a separation of concerns, ensuring testability, and making solid engineering decisions.

## Features

- **Dynamic News Feed**: An infinitely scrolling list of the latest news headlines, powered by the **Paging 3** library for efficient data loading.
- **Keyword Search**: A responsive search bar with a 500ms debounce on user input (using Kotlin Flow) to prevent excessive API calls while typing.
- **Offline Bookmarking**: Users can save articles for later. Bookmarks are persisted locally using **Room** and can be viewed on a dedicated screen, even without an internet connection.
- **WebView Integration**: Articles are opened in a `WebView` within the app, providing a seamless reading experience.
- **Core Article Actions**: The detail screen includes actions to **Share** an article, **Bookmark** it, or open it in an **External Browser**.
- **Robust Error Handling**: The app gracefully handles API errors. Instead of showing a generic message, it parses the error response and displays the specific message from the server (e.g., "Your API key is invalid...").

## Architectural Deep-Dive

The app is built upon **Clean Architecture** principles. This was a deliberate choice to create a codebase that is decoupled, easy to maintain, and highly testable.

### The Layers

1.  **UI (Presentation) Layer**: Built entirely with **Jetpack Compose** and **Material 3**. This layer follows the **MVVM** pattern, where `ViewModel`s expose state via `StateFlow` to the Composables. The UI is reactive and observes state changes to update itself.

2.  **Domain Layer**: The heart of the app. It contains the core business logic encapsulated in **Use Cases** (e.g., `GetNewsFeedUseCase`, `BookmarkArticleUseCase`). This layer is a pure Kotlin module with no dependencies on the Android framework, making its logic independently testable and reusable.

3.  **Data Layer**: This layer acts as the single source of truth for all app data. It contains a **Repository** implementation that abstracts the data sources. It's responsible for fetching data from the network (**Retrofit**) and the local database (**Room**), and mapping data models to the domain models required by the use cases.

### Why this architecture?

- **Testability**: Separating the business logic into use cases allows for simple, fast, and reliable unit tests. The data layer can be easily faked to test the domain and UI layers in isolation.
- **Scalability**: As new features are added, the clear separation of concerns makes it easy to add new use cases or data sources without disrupting existing code.
- **Maintainability**: With a clean structure, debugging is easier, and onboarding new developers is faster.

## Tech Stack

-   **Core**: Kotlin, Coroutines, Flow
-   **UI**: Jetpack Compose, Material 3, Coil (for image loading)
-   **Architecture**: MVVM, Clean Architecture, Hilt (for dependency injection)
-   **Navigation**: Jetpack Navigation for Compose
-   **Data**: 
    -   Retrofit & OkHttp (for networking)
    -   Gson (for JSON parsing)
    -   Room (for local database/persistence)
    -   Paging 3 (for pagination)
-   **Testing**: JUnit, Mockito, Turbine

## How to Run

1.  Clone this repository.
2.  You will need an API key from [newsapi.org](https://newsapi.org).
3.  Open the project in Android Studio and navigate to `app/src/main/java/com/assignment/news/data/ApiKey.kt`.
4.  Replace the placeholder string with your API key.
5.  Build and run the app.

## Future Work

-   **UI Polish**: Implement a pull-to-refresh gesture on the news feed and a shimmer effect for a more polished loading state.
-   **Bookmark Status in Feed**: Add a visual indicator (e.g., a filled bookmark icon) to articles in the main feed that have been saved.
-   **Database Migrations**: Implement a proper Room migration strategy to handle future schema changes gracefully.
-   **Tablet Layout**: Create an adaptive two-pane layout to provide an enhanced experience on tablets.
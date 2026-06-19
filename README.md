# Khabar App

A modern Android News Application built using
**Jetpack Compose**,
**MVVM Architecture**,
**Hilt Dependency Injection**, 
**Retrofit**, and
**Paging 3**.

This app fetches real-time news from the **NewsData API** and displays category-based news with smooth pagination and a clean modern UI.
---------------------------
## ✨ Features

* 📱 Modern UI with Jetpack Compose
* 🗂️ Category-based news filtering
* 🔄 Infinite scrolling using Paging 3
* ⚡ Real-time API integration with Retrofit
* 🧠 Clean Architecture using MVVM
* 💉 Dependency Injection with Hilt
* 🔍 Search Bar UI
* 📑 Horizontal category tabs
* 🌙 Material Design 3 support
* ⏳ Shimmer Loading Effect

--------------------------

## 🛠️ Tech Stack & Libraries

| Technology                                                                                | Description                             |
| ----------------------------------------------------------------------------------------- | --------------------------------------- |
| [Android Studio](https://developer.android.com/studio)                                    | Official IDE for Android Development    |
| [Kotlin](https://kotlinlang.org/)                                                         | Modern programming language for Android |
| [Jetpack Compose](https://developer.android.com/jetpack/compose)                          | Modern UI toolkit for Android           |
| [MVVM Architecture](https://developer.android.com/topic/architecture)                     | Clean architecture pattern              |
| [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)          | Dependency Injection library            |
| [Retrofit](https://square.github.io/retrofit/)                                            | REST API networking library             |
| [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) | Pagination library for large datasets   |
| [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)                 | Asynchronous programming                |
| [Flow](https://developer.android.com/kotlin/flow)                                         | Reactive stream handling                |
| [Material Design 3](https://m3.material.io/)                                              | Modern UI design system                 |

---

## 📂 Project Structure

```txt
com.example.news
│── data
│   ├── model
│   ├── remote
│   ├── repository
│   └── pagging
│
│── domain
│   └── NewsViewModel
│
│── presentation
│   ├── common
│   ├── onboarding
│   └── animationEffects
```

---------------------

## 🚀 API Used

This project uses the **NewsData API** for fetching latest news.

🔗 API Documentation:
https://newsdata.io/

### Base URL
```
https://newsdata.io/api/1/
```

### Endpoint
```
latest
```

------------------

## 🏗️ Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture.

```
UI (Jetpack Compose)
        ↓
ViewModel
        ↓
Repository
        ↓
Retrofit API
        ↓
NewsData API
```

-------------------

## ⚙️ Installation

### 1. Clone the repository

```bash
git clone https://github.com/your-username/news-app.git
```

### 2. Open the project in Android Studio

Download Android Studio:
https://developer.android.com/studio

### 3. Sync Gradle

### 4. Run the App 

## 🔑 API Key Setup

https://newsdata.io/

Replace your API key inside:

```kotlin
ApiService.kt
```

```kotlin
@Query("apikey")
apiKey: String = "YOUR_API_KEY"
```

---

## 👨‍💻 Developer

**Mohit Prajapati**

Android Developer | Kotlin | Jetpack Compose

GitHub:
https://github.com/MohitMohit235

LinkedIn (Optional):
https://www.linkedin.com/in/mohit-prajapati-58830830b/

---

## ⭐ Support

If you like this project, give it a **star ⭐ on GitHub**.

# UKTV Task App

This Android application allows users to search for **Star Wars-related data** using the public [SWAPI](https://swapi.dev) API. 
Users can search by entering a keyword such as `films`, `starships`, or `vehicles` and view detailed results with sorting functionality.

---

## 🔍 Solution Overview

- **MainActivity**: Accepts user input and validates it.
- **ResultActivity**: Displays the list of results based on the input keyword. It dynamically renders different view types (`films`, `starships`/`vehicles`) using a `RecyclerView`.
- **InfoListAdapter**: Determines the appropriate view type and binds data to either a film or transportation layout.
- **Gson** is used for deserialization of cached JSON responses stored in SharedPreferences.

---

## ✨ Features

- Search for Star Wars data by category
- Dynamic list rendering depending on result type
- Sort results alphabetically (ascending/descending)
- Input validation with an alert dialog

---

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI**: Android XML + RecyclerView + Spinner
- **Data**: Gson for JSON parsing
- **Persistence**: SharedPreferences for result caching

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/neomistletoe/uktv-task.git
2. Open the project in Android Studio.
3. Run the app on a physical device or emulator.

---

## 📁 Directory Structure
<pre> <code>
   com.mistletoe.uktvtask 
   ├── data 
   │   ├── model
   │   │   ├── Film.kt 
   │   │   └── Transportation.kt 
   │   └── service
   │       └── StarWarsService.kt    
   ├── ui 
   │   ├── main
   │   │   └── MainActivity.kt 
   │   ├── result
   │   │   ├── ResultActivity.kt
   │   │   └── ResultListAdapter.kt
   │   └── theme
</code> </pre>


⚠️ GitHub commit history includes contributions from a different account due to an initial setup issue.


# 📱 Password Manager App  

A clean, secure, and modern **Password Manager** built using **Kotlin**, **Jetpack Compose**, **MVVM + Clean Architecture**, **Koin DI**, and **Room Database**.  
This app allows users to **store, view, and generate strong passwords** with an intuitive UI and smooth bottom-sheet interactions.

---

## ⭐ Features

### 🔐 Password Management
- Add new password entries  
- View all saved passwords  
- Tap any item to view/edit details  
- Secure password field with eye-toggle  
- Real-time password strength meter  

### 🔧 Productivity Features
- Strong password generator  
- Floating action button for quick add  
- Bottom sheet for Add/Edit  
- Toast-based error handling  
- Responsive and clean UI  

---

## 🧱 Architecture Overview

The project follows **Clean Architecture** and **MVVM**, ensuring testability, scalability, and separation of concerns.

### **1. Domain Layer**
- `Password` model  
- Repository abstraction  
- Use Cases  
  - `GetPasswordsUseCase`  
  - `SavePasswordUseCase`  
  - `DeletePasswordUseCase`  

### **2. Data Layer**
- Room Database  
- DAO (`PasswordDao`)  
- Repository Implementation (`PasswordRepositoryImpl`)  

### **3. Presentation Layer**
- Jetpack Compose UI  
- Koin-based ViewModels  
- StateFlow for reactive state  
- Bottom sheets & custom components  

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| UI | Jetpack Compose, Material3 |
| Architecture | MVVM + Clean Architecture |
| Dependency Injection | Koin |
| Database | Room |
| Async | Coroutines & Flow |
| Language | Kotlin |
| State Management | StateFlow, SharedFlow |

---

## 📂 Project Structure

```

/domain
/model
/repository
/usecase

/data
/local
/dao
/database
/entity
/repository

/ui
/screens
/home
/add_edit
/components

/di (Koin Modules)

````

---

## 🔍 Key UI Components

### 🏠 **Home Screen**
- Displays list of saved passwords  
- Always white background  
- Floating “Add” button  
- Top App Bar → *Password Manager*  
- Opens Add/Edit bottom sheet  

### ➕ **Add/Edit Password Bottom Sheet**
Includes:
- Account Name field  
- Username field  
- Password field with eye toggle  
- Underlined “Generate Password” button  
- Password strength meter (Weak → Orange, Medium → Yellow, Strong → Green)  
- “Add New Account” button styled like mockup  

### 🔑 **PasswordItem**
Styled card showing:
- Account Type (bold)  
- Username  
- Clickable → open details or edit  

---

## 🚀 Running the App

### 1️⃣ Clone the repo
```bash
git clone https://github.com/Abhishek-Bharti-1/PasswordManagerApp.git
cd password-manager
````

### 2️⃣ Open in Android Studio

### 3️⃣ Sync Gradle

### 4️⃣ Run on emulator or device

---

## 📸 Screenshots

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/11f61067-bbf9-4bf2-a185-325f7c1322f5" width="250"/></td>
    <td><img src="https://github.com/user-attachments/assets/15424151-e225-4285-9e6e-999f08058faa" width="250"/></td>
    <td><img src="https://github.com/user-attachments/assets/c5f4c9b3-b0d1-43df-be26-a8bc1a819bf7" width="250"/></td>
  </tr>
</table>

---

## 🧪 Future Enhancements

* Biometric unlock (Fingerprint/FaceID)
* SQLCipher for encrypted database
* Password categories (Work/Personal/Banking)
* Search bar
* Export/Import passwords securely

---

## 🤝 Contribution

Pull requests are welcome!
Please follow **MVVM + Clean Architecture** and the project structure.

---


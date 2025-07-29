# 📱 Android Attendance App with Spring Boot Backend

A client-server based attendance management system built using:

- **Spring Boot** for the backend REST API
- **Android (Java)** for the mobile app frontend
- **Retrofit** for communication between app and server

---

## 🧩 Features

### Android App (Frontend)
- Mark attendance (Present/Absent) for students
- Send attendance data to server via HTTP POST
- Display success/error messages
- Simple UI using checkboxes or radio buttons

### Spring Boot Server (Backend)
- REST API to receive attendance data from the Android app
- Endpoint to view all stored attendance
- Can be extended to use databases like MySQL or MongoDB

---

## 🛠️ Technologies Used

| Layer         | Technology        |
|---------------|------------------|
| Frontend      | Android (Java)   |
| Networking    | Retrofit         |
| Backend       | Spring Boot      |
| Data Format   | JSON             |
| API Protocol  | REST             |

---

## 🔌 API Endpoints

### `POST /mark`
Send attendance record to the server.

**Request Body:**
```json
{
  "id": 1,
  "name": "Rahul",
  "present": true
}
```

### `GET /all`
Returns a list of all attendance records.

---

## 🚀 How to Run

### 🖥️ Backend (Spring Boot)
1. Clone the repo or download the backend source.
2. Open in IntelliJ / Eclipse / VS Code.
3. Run the `main` class.
4. Server runs on: `http://localhost:8080`

### 📱 Android App
1. Open in Android Studio.
2. Update base URL in Retrofit client to match your backend IP.
3. Run on Emulator or Physical Device.

---

## 📦 Folder Structure

```
├── android-app/
│   └── (Android Studio project files)
├── springboot-backend/
│   └── src/main/java/com/example/attendance/
│       ├── AttendanceController.java
│       └── Attendance.java
```

---


## 📌 Future Improvements
- Add login for teachers/students
- Store attendance in database
- Generate attendance reports (PDF/Excel)
- Firebase integration for real-time sync

---

## 🧑‍💻 Developed By

**Members:**  
- [Devaram S]  
- [Harish M]  
- [Vishal s]  

--- 
Feel free to use and modify.

---
```

---

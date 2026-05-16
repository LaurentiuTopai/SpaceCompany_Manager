# 🚀 SpaceCompany Manager

A desktop application built with **Java 17** and **JavaFX** for managing a space company — tracking resources, missions, employees, or other company operations in a sleek GUI environment.

---

## 🛠️ Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| JavaFX | 17.0.2 |
| Build Tool | Maven |

---

## 📋 Prerequisites

Before running the project, make sure you have the following installed:

- [Java 17 JDK](https://adoptium.net/)
- [Apache Maven 3.6+](https://maven.apache.org/download.cgi)
- An IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommended)

---

## 🚀 Getting Started

### Clone the repository

```bash
git clone https://github.com/LaurentiuTopai/SpaceCompany_Manager.git
cd SpaceCompany_Manager
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn javafx:run
```

---

## 📁 Project Structure

```
SpaceCompany_Manager/
├── src/
│   └── main/
│       ├── java/
│       │   └── org/example/
│       │       └── Main.java       # Application entry point
│       └── resources/              # FXML views, CSS, images
├── pom.xml                         # Maven build configuration
└── .gitignore
```

---

## ⚙️ Configuration

The main entry point is `org.example.Main`. If you need to change the startup class, update the `<mainClass>` field in `pom.xml`:

```xml
<mainClass>org.example.Main</mainClass>
```

---

## 📄 License

This project is open source. Feel free to use and modify it.

---

## 👤 Author

**Laurentiu Topai**
- GitHub: [@LaurentiuTopai](https://github.com/LaurentiuTopai)

# SPRING BOOT + SQL SERVER (DOCKER)

## 📁 Estructura de Carpetas

```text
🍃 PROJECT/
├── 📁 .github/
├── 📁 .idea/
├── 📁 .mvn/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/pe/edu/vallegrande/project
│   │   │   ├── 📁 model/
│   │   │   ├── 📁 repository/
│   │   │   ├── 📁 rest/
│   │   │   └── 📁 service/
│   │   ├── 📄 ProjectApplication.java
│   │   └── 📁 resources/
│   │       ├── 📁 sql/db.sql
│   │       └── 📄 application.yml
│   └── 📁 test/
├── 📁 target/
├── 📄 .gitattributes
├── 📄 .gitignore
├── 📄 HELP.md
├── 📄 mvnw
├── 📄 mvnw.cmd
├── 📄 pom.cmd
└── 📄 README.xml
````

---

## 🧪 Ejecución del Proyecto Spring Boot


* **Opción 1:** `ProjectApplication.java`, archivo principal para ejecutar e iniciar el proyecto en la ruta:

```bash
🍃 PROJECT/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/pe/edu/vallegrande/project
│   │   │   ├── 📁 model/
│   │   │   ├── 📁 repository/
│   │   │   ├── 📁 rest/
│   │   │   └── 📁 service/
│   │   ├── 📄 ProjectApplication.java
````

* **Opción 2:** a nivel de terminal con Maven, ubicandose dentro del directorio del proyecto:

```bash
mvn spring-boot:run
````

# Endpoint del proyecto Spring Boot

__[http://localhost:8085/v1/api/customer](http://localhost:8085/v1/api/customer)__


* **Resultado:**
```bash
[
  {
    "id": 1,
    "dni": "12345678",
    "cellPhone": "987654321",
    "firstName": "Juan",
    "lastName": "Sanchez",
    "state": "A"
  }
]
````

---

# Spring Boot + Thymeleaf + Bootstrap — Secure Auth (Login/Register)

Application web Spring Boot avec rendu côté serveur (**Thymeleaf**) et interface responsive (**Bootstrap**), intégrant une authentification sécurisée (**Spring Security**) et une gestion des rôles.

---

## ✨ Fonctionnalités

- ✅ Inscription utilisateur (validation + messages d’erreurs)
- 🔐 Connexion sécurisée (Spring Security)
- 👤 Autorisations par rôles : `ROLE_USER`, `ROLE_ADMIN`
- 🎨 UI responsive (Thymeleaf + Bootstrap)
- 🗃️ Persistance MySQL (Spring Data JPA)
- 📈 Actuator (health/metrics)
- 📚 Documentation API via Swagger UI (springdoc-openapi)

---

## 🧱 Stack technique

- **Java** (recommandé : 21 LTS)
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **Bootstrap 5**
- **MySQL**
- **Lombok**
- **Spring Boot Actuator**
- **springdoc-openapi (Swagger UI)**

---

## 📁 Structure du projet

```txt
src/main/
  java/com/patdimby/simplerest/
    config/        # Security/HTTPS/Swagger configs
    controller/    # Controllers web & API
    dto/           # DTOs
    model/         # Entities
    repository/    # Repositories JPA
    service/       # Services métier
    validator/     # Validations personnalisées
  resources/
    templates/     # Vues Thymeleaf
    static/        # CSS/JS/Images
    application.properties (ou .yml)

School Manager - Multi-module example (backend + frontend)
- Backend: Spring Boot (module 'backend'), builds frontend automatically during Maven build.
- Frontend: Angular (module 'frontend') using Material for a modern UI.
- MySQL: the JDBC URL is configured to create the database if not exists:
  spring.datasource.url=jdbc:mysql://localhost:3306/schooldb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
- Angular CLI used in dev: 20.3.9
- Node: 24.11.0+, npm: 11.6.2+

To build everything (CI/CD friendly):
  mvn clean package

What happens:
- Maven (parent) builds modules. The backend module uses frontend-maven-plugin to install Node and npm, run 'npm install' and 'npm run build', then copies the resulting 'dist/frontend' into backend JAR static resources. Result: a single runnable JAR with Angular inside.

Run locally (dev):
- Start MySQL and ensure credentials in backend/src/main/resources/application.properties are correct.
- Frontend dev: cd frontend && npm install && npm start
- Backend dev: cd backend && mvn spring-boot:run

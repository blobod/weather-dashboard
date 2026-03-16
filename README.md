# Weather Dashboard

A full-stack weather dashboard built with Spring Boot and Vue.js.
Search for any city and get real-time weather data powered by the OpenWeatherMap API,
with search history persisted to a PostgreSQL database.

## Tech Stack

- **Frontend:** Vue 3, Pinia, Axios
- **Backend:** Java 21, Spring Boot 3.5
- **Database:** PostgreSQL 15
- **DevOps:** Docker, Docker Compose

## Getting Started

**1. Clone the repository**
```bash
git clone https://github.com/blobod/weather-dashboard.git
cd weather-dashboard
```

**2. Create a `.env` file in the root folder**
```
DB_USER=weatheruser
DB_PASSWORD=yourpassword
OPENWEATHER_API_KEY=your_api_key_here
```

**3. Create a `.env` file inside the `frontend` folder**
```
VITE_API_BASE_URL=/api/weather
```

> Free OpenWeatherMap API keys are available at [openweathermap.org](https://openweathermap.org/api).

**4. Run the application**
```bash
docker compose up --build
```

**5. Open your browser at `http://localhost`**
<template>
  <div class="weather-card" v-if="store.currentWeather">
    <div class="location">
      <h2>{{ store.currentWeather.name }}, {{ store.currentWeather.sys.country }}</h2>
    </div>
    <div class="temp">
      {{ Math.round(store.currentWeather.main.temp) }}°C
    </div>
    <div class="description">
      {{ store.currentWeather.weather[0].description }}
    </div>
    <div class="details">
      <div class="detail">
        <span class="label">Feels like</span>
        <span>{{ Math.round(store.currentWeather.main.feelsLike) }}°C</span>
      </div>
      <div class="detail">
        <span class="label">Humidity</span>
        <span>{{ store.currentWeather.main.humidity }}%</span>
      </div>
      <div class="detail">
        <span class="label">Wind</span>
        <span>{{ store.currentWeather.wind.speed }} m/s</span>
      </div>
    </div>
    <div class="error" v-if="store.error">{{ store.error }}</div>
  </div>
</template>

<script setup>
import { useWeatherStore } from '../stores/weatherStore'
const store = useWeatherStore()
</script>

<style scoped>
.weather-card {
  background: rgba(255,255,255,0.05);
  border-radius: 20px;
  padding: 2rem;
  text-align: center;
  margin-bottom: 2rem;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255,255,255,0.1);
}

.location h2 {
  font-size: 1.8rem;
  margin-bottom: 1rem;
}

.temp {
  font-size: 5rem;
  font-weight: bold;
  margin: 1rem 0;
}

.description {
  font-size: 1.2rem;
  text-transform: capitalize;
  color: rgba(255,255,255,0.7);
  margin-bottom: 1.5rem;
}

.details {
  display: flex;
  justify-content: center;
  gap: 3rem;
}

.detail {
  display: flex;
  flex-direction: column;
  gap: 0.3rem;
}

.label {
  font-size: 0.85rem;
  color: rgba(255,255,255,0.5);
}

.error {
  color: #e94560;
  margin-top: 1rem;
}
</style>
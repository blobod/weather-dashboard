<template>
  <div class="history" v-if="store.history.length">
    <h3>Recent Searches</h3>
    <div class="history-list">
      <div
        class="history-item"
        v-for="item in store.history"
        :key="item.id"
        @click="store.fetchWeather(item.cityName)"
      >
        <span class="city">{{ item.cityName }}, {{ item.country }}</span>
        <span class="temp">{{ Math.round(item.temperature) }}°C</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useWeatherStore } from '../stores/weatherStore'
const store = useWeatherStore()
</script>

<style scoped>
.history h3 {
  margin-bottom: 1rem;
  color: rgba(255,255,255,0.7);
}

.history-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.history-item {
  display: flex;
  justify-content: space-between;
  padding: 0.8rem 1.2rem;
  background: rgba(255,255,255,0.05);
  border-radius: 10px;
  cursor: pointer;
  transition: background 0.2s;
  border: 1px solid rgba(255,255,255,0.1);
}

.history-item:hover {
  background: rgba(255,255,255,0.1);
}

.city {
  color: rgba(255,255,255,0.9);
}

.temp {
  color: #e94560;
  font-weight: bold;
}
</style>
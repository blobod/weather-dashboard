<template>
  <div class="search-bar">
    <input
      v-model="city"
      @keyup.enter="search"
      type="text"
      placeholder="Search for a city..."
    />
    <button @click="search" :disabled="store.loading">
      {{ store.loading ? 'Searching...' : 'Search' }}
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useWeatherStore } from '../stores/weatherStore'

const store = useWeatherStore()
const city = ref('')

function search() {
  if (city.value.trim()) {
    store.fetchWeather(city.value.trim())
  }
}
</script>

<style scoped>
.search-bar {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
}

input {
  flex: 1;
  padding: 0.9rem 1.2rem;
  border-radius: 12px;
  border: none;
  font-size: 1rem;
  background: rgba(255,255,255,0.1);
  color: white;
  outline: none;
}

input::placeholder {
  color: rgba(255,255,255,0.5);
}

input:focus {
  background: rgba(255,255,255,0.15);
}

button {
  padding: 0.9rem 1.8rem;
  border-radius: 12px;
  border: none;
  background: #e94560;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  transition: background 0.2s;
}

button:hover {
  background: #c73652;
}

button:disabled {
  background: #888;
  cursor: not-allowed;
}
</style>
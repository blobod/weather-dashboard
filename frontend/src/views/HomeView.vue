<template>
  <div>
    <SearchBar />
    <p v-if="store.error" class="error">{{ store.error }}</p>
    <WeatherCard />
    <SearchHistory />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useWeatherStore } from '../stores/weatherStore'
import SearchBar from '../components/SearchBar.vue'
import WeatherCard from '../components/WeatherCard.vue'
import SearchHistory from '../components/SearchHistory.vue'

const store = useWeatherStore()

onMounted(() => {
  store.fetchHistory()
})
</script>

<style scoped>
.error {
  color: #e94560;
  text-align: center;
  margin-bottom: 1rem;
}
</style>
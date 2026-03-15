import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getWeather, getHistory } from '../services/weatherService'

export const useWeatherStore = defineStore('weather', () => {
    const currentWeather = ref(null)
    const history = ref([])
    const loading = ref(false)
    const error = ref(null)

    async function fetchWeather(city) {
        loading.value = true
        error.value = null
        try {
            const response = await getWeather(city)
            currentWeather.value = response.data
            await fetchHistory()
        } catch (e) {
            error.value = 'City not found. Please try again.'
        } finally {
            loading.value = false
        }
    }

    async function fetchHistory() {
        const response = await getHistory()
        history.value = response.data
    }

    return { currentWeather, history, loading, error, fetchWeather, fetchHistory }
})
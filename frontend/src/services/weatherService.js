import axios from 'axios'

const API_BASE = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/weather'

export const getWeather = (city) => {
    return axios.get(`${API_BASE}/current`, { params: { city } })
}

export const getHistory = () => {
    return axios.get(`${API_BASE}/history`)
}
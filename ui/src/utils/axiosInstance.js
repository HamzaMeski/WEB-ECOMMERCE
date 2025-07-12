import axios from 'axios'

const axiosInstance = axios.create({
    baseURL: process.env.VUE_APP_SPRING_BOOT_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        Accept: 'application/json'
    }
})

// Add Authorization token if exists
axiosInstance.interceptors.request.use(config => {
    const token = localStorage.getItem('authToken')
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
}, error => {
    return Promise.reject(error)
})

export default axiosInstance

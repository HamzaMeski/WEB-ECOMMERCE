import axiosInstance from '@/utils/axiosInstance'

export const login = async (email, password) => {
    const response = await axiosInstance.post('/auth/login', { email, password })
    return response.data
}

export const register = async (payload) => {
    const response = await axiosInstance.post('/users/register', payload)
    return response.data
}

export const getProfile = async () => {
    const response = await axiosInstance.get('/users/profile')
    return response.data
}
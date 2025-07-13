import axiosInstance from '@/utils/axiosInstance'

export const login = async (email, password) => {
    const response = await axiosInstance.post('/auth/login', { email, password })
    return response.data
}

export const register = async (payload) => {
    const response = await axiosInstance.post('/auth/register', payload)
    return response.data
}
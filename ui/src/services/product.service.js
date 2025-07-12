import axiosInstance from '@/utils/axiosInstance'

export const getAllProducts = async () => {
    const response = await axiosInstance.get('/products')
    return response.data
}

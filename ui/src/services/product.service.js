import axiosInstance from '@/utils/axiosInstance'

export const getAllProducts = async () => {
    const response = await axiosInstance.get('/products')
    return response.data
}

export const createProduct = async (productData) => {
    const response = await axiosInstance.post('/products', productData)
    return response.data
}

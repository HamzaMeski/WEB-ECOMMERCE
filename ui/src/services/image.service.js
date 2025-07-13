import axiosInstance from '@/utils/axiosInstance'

export const getImage = async (id) => {
    const response = await axiosInstance.get(`/images/${id}`, {
        responseType: 'blob'
    })
    return response.data
}

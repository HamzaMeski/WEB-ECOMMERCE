import axiosInstance from "@/utils/axiosInstance";

export const submitBasket = async (data) => {
    const response = await axiosInstance.post('/baskets', data)
    return response.data
}

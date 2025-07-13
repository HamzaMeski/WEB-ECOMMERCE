import axiosInstance from '@/utils/axiosInstance'

export const getAllProducts = async () => {
    const response = await axiosInstance.get('/products')
    return response.data
}

export const createProduct = async (productData, imageFile) => {
    const formData = new FormData()

    // Add product data as JSON blob
    const productBlob = new Blob([JSON.stringify(productData)], {
        type: 'application/json'
    })
    formData.append('product', productBlob)

    // Add image file
    formData.append('file', imageFile)

    const response = await axiosInstance.post('/products', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
    return response.data
}

export const updateProduct = async (id, productData, imageFile = null) => {
    const formData = new FormData()

    // Add product data as JSON blob
    const productBlob = new Blob([JSON.stringify(productData)], {
        type: 'application/json'
    })
    formData.append('product', productBlob)

    // Add image file if provided (optional for updates)
    if (imageFile) {
        formData.append('file', imageFile)
    }

    const response = await axiosInstance.put(`/products/${id}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
    return response.data
}
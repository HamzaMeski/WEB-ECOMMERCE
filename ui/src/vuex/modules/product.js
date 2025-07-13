import { getAllProducts, createProduct, updateProduct } from '@/services/product.service'

const state = () => ({
    products: [],
    loading: false,
    error: null
})

const mutations = {
    SET_PRODUCTS(state, products) {
        state.products = products
    },
    SET_LOADING(state, loading) {
        state.loading = loading
    },
    SET_ERROR(state, error) {
        state.error = error
    }
}

const actions = {
    async fetchProducts({ commit }) {
        commit('SET_LOADING', true)
        try {
            const data = await getAllProducts()
            commit('SET_PRODUCTS', data)
            commit('SET_ERROR', null)
        } catch (err) {
            commit('SET_ERROR', err.message || 'Failed to fetch products')
        } finally {
            commit('SET_LOADING', false)
        }
    },

    async createProduct({ commit, dispatch }, { data, image }) {
        commit('SET_LOADING', true)
        try {
            await createProduct(data, image)
            commit('SET_ERROR', null)
            // Refresh product list after successful creation
            dispatch('fetchProducts')
        } catch (err) {
            commit('SET_ERROR', err.message || 'Failed to create product')
            throw err
        } finally {
            commit('SET_LOADING', false)
        }
    },

    async updateProduct({ commit, dispatch }, { id, data, image }) {
        commit('SET_LOADING', true)
        try {
            await updateProduct(id, data, image)
            commit('SET_ERROR', null)
            dispatch('fetchProducts')
        } catch (err) {
            commit('SET_ERROR', err.message || 'Failed to update product')
            throw err
        } finally {
            commit('SET_LOADING', false)
        }
    }
}

const getters = {
    allProducts: (state) => state.products,
    isLoading: (state) => state.loading,
    productError: (state) => state.error
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
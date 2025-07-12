import { submitBasket } from '@/services/basket.service'

const state = () => ({
    basket: [],
})

const mutations = {
    SET_BASKET(state, products) {
        state.basket = products.map((p) => ({ ...p, quantity: 0 }))
    },
    UPDATE_QUANTITY(state, { id, quantity }) {
        const item = state.basket.find((p) => p.id === id)
        if (item) item.quantity = quantity
    },
    CLEAR_BASKET(state) {
        state.basket = state.basket.map((p) => ({ ...p, quantity: 0 }))
    },
}

const actions = {
    initializeBasket({ commit }, products) {
        commit('SET_BASKET', products)
    },
    incrementQuantity({ commit, state }, id) {
        const item = state.basket.find((p) => p.id === id)
        if (item) {
            commit('UPDATE_QUANTITY', { id, quantity: item.quantity + 1 })
        }
    },
    decrementQuantity({ commit, state }, id) {
        const item = state.basket.find((p) => p.id === id)
        if (item && item.quantity > 0) {
            commit('UPDATE_QUANTITY', { id, quantity: item.quantity - 1 })
        }
    },
    async submitBasket({ state, commit }) {
        const filteredProducts = state.basket
            .filter((item) => item.quantity > 0)
            .map((item) => ({
                productId: item.id,
                quantity: item.quantity,
            }))

        const payload = { products: filteredProducts }

        try {
            const result = await submitBasket(payload)
            console.log('Basket submitted:', result)
            commit('CLEAR_BASKET') // Optional: clear after successful submit
            return result
        } catch (error) {
            console.error('Failed to submit basket:', error)
            throw error
        }
    },
}

const getters = {
    allBasketItems: (state) => state.basket,
    basketItems: (state) => state.basket.filter((item) => item.quantity > 0),
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}

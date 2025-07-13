import { login, register } from '@/services/auth.service'

const state = () => ({
    user: null,
    token: null,
    loading: false,
    error: null
})

const mutations = {
    SET_LOADING(state, loading) {
        state.loading = loading
    },
    SET_ERROR(state, error) {
        state.error = error
    },
    SET_AUTH(state, { token, user }) {
        state.token = token
        state.user = user
    },
    CLEAR_AUTH(state) {
        state.token = null
        state.user = null
        state.error = null
    }
}

const actions = {
    async login({ commit }, { email, password }) {
        console.log('dispatching login action...')
        commit('SET_LOADING', true)
        commit('SET_ERROR', null)

        try {
            const data = await login(email, password)

            const user = {
                id: data.id,
                email: data.email,
                firstName: data.firstName,
                lastName: data.lastName
            }

            commit('SET_AUTH', { token: data.token, user })
            localStorage.setItem('authToken', data.token)

            return true
        } catch (error) {
            commit('SET_ERROR', error.response?.data?.message || 'Login failed')
            throw error
        } finally {
            commit('SET_LOADING', false)
        }
    },

    async register({ commit }, payload) {
        console.log('dispatching register action...')
        commit('SET_LOADING', true)
        commit('SET_ERROR', null)

        try {
            const data = await register(payload)

            const user = {
                id: data.id,
                email: data.email,
                firstName: data.firstName,
                lastName: data.lastName
            }

            commit('SET_AUTH', { token: data.token, user })
            localStorage.setItem('authToken', data.token)

            return true
        } catch (error) {
            commit('SET_ERROR', error.response?.data?.message || 'Registration failed')
            throw error
        } finally {
            commit('SET_LOADING', false)
        }
    },

    logout({ commit }) {
        localStorage.removeItem('authToken')
        commit('CLEAR_AUTH')
    }
}

const getters = {
    isAuthenticated: (state) => !!state.token,
    currentUser: (state) => state.user,
    authError: (state) => state.error,
    isLoading: (state) => state.loading
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}

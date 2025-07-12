import { login } from '@/services/auth.service'

export default {
    namespaced: true,
    state: () => ({
        user: null,
        token: null,
        loading: false,
        error: null
    }),
    mutations: {
        setLoading(state, loading) {
            state.loading = loading
        },
        setError(state, error) {
            state.error = error
        },
        setAuth(state, { token, user }) {
            state.token = token
            state.user = user
        },
        clearAuth(state) {
            state.token = null
            state.user = null
            state.error = null
        }
    },
    actions: {
        async login({ commit }, { email, password }) {
            console.log('dispatching login action...')
            commit('setLoading', true)
            commit('setError', null)

            try {
                const data = await login(email, password)

                const user = {
                    id: data.id,
                    email: data.email,
                    firstName: data.firstName,
                    lastName: data.lastName
                }

                commit('setAuth', { token: data.token, user })

                // Save token to localStorage for persistence & axios interceptor
                localStorage.setItem('authToken', data.token)

                return true
            } catch (error) {
                commit('setError', error.response?.data?.message || 'Login failed')
                throw error
            } finally {
                commit('setLoading', false)
            }
        },

        logout({ commit }) {
            localStorage.removeItem('authToken')
            commit('clearAuth')
        }
    },
    getters: {
        isAuthenticated(state) {
            return !!state.token
        },
        currentUser(state) {
            return state.user
        },
        authError(state) {
            return state.error
        },
        isLoading(state) {
            return state.loading
        }
    }
}

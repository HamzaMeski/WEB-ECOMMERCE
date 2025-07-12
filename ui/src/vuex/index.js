import { createStore } from 'vuex'
import auth from './modules/auth.js'
import product from './modules/product.js'

export default createStore({
    modules: {
        auth,
        product
    }
})
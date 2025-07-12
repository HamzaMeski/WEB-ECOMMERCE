import { createStore } from 'vuex'
import auth from './modules/auth.js'
import product from './modules/product.js'
import basket from './modules/basket.js'

export default createStore({
    modules: {
        auth,
        product,
        basket
    }
})
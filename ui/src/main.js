import { createApp } from 'vue'
import App from './App.vue'
import './tailwind.css'

import store from './vuex/index.js'
import route from './routes/index'

createApp(App)
    .use(store)
    .use(route)
    .mount('#app')

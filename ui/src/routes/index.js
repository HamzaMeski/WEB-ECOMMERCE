import { createRouter, createWebHistory } from 'vue-router'

import DashboardLayout from '@/components/layouts/DashboardLayout.vue'
import AuthLayout from '@/components/layouts/AuthLayout.vue'

const routes = [
    {
        path: '/',
        component: AuthLayout
    },
    {
        path: '/dashboard',
        component: DashboardLayout,
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router

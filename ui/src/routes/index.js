import { createRouter, createWebHistory } from 'vue-router'

import DashboardLayout from '@/components/layouts/DashboardLayout.vue'
import AuthLayout from '@/components/layouts/AuthLayout.vue'
import ManagerSection from "@/components/sections/dashboard/ManagerSection.vue";
import ClientSection from "@/components/sections/dashboard/ClientSection.vue";
import ProfileSection from "@/components/sections/auth/ProfileSection.vue";

const routes = [
    {
        path: '/',
        component: AuthLayout
    },
    {
        path: '/dashboard',
        component: DashboardLayout,
        children: [
            {
                path: '',
                component: ClientSection
            },
            {
                path: 'management',
                component: ManagerSection
            }
        ]
    },
    {
        path: '/profile',
        component: ProfileSection
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router

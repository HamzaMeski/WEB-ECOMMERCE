
<template>
  <div class="flex h-screen overflow-hidden">
    <!-- Sidebar -->
    <aside class="w-64 bg-white border-r border-gray-200 shadow-md hidden md:block">
      <div class="h-full p-4 space-y-6">
        <!-- Title -->
        <h2 class="text-lg font-semibold text-gray-700 px-2">Menu Items</h2>

        <!-- Navigation -->
        <ul class="space-y-1">
          <!-- Fresh Fruits -->
          <li>
            <router-link
                to="/dashboard"
                class="flex items-center gap-3 px-3 py-2 rounded-lg text-gray-600 hover:text-blue-600 hover:bg-blue-50 transition-colors"
                exact-active-class="text-blue-600 bg-blue-100 font-semibold"
            >
              <HomeIcon class="w-5 h-5" />
              <span>Fresh Fruits</span>
            </router-link>
          </li>

          <!-- Management -->
          <li>
            <router-link
                to="/dashboard/management"
                class="flex items-center gap-3 px-3 py-2 rounded-lg text-gray-600 hover:text-blue-600 hover:bg-blue-50 transition-colors"
                active-class="text-blue-600 bg-blue-100 font-semibold"
            >
              <Cog6ToothIcon class="w-5 h-5" />
              <span>Management</span>
            </router-link>
          </li>

          <!-- Drinks -->
          <li>
            <a
                href="#"
                class="flex items-center gap-3 px-3 py-2 rounded-lg text-gray-600 hover:text-blue-600 hover:bg-blue-50 transition-colors"
            >
              <BeakerIcon class="w-5 h-5" />
              <span>Drinks</span>
            </a>
          </li>

          <!-- Snacks -->
          <li>
            <a
                href="#"
                class="flex items-center gap-3 px-3 py-2 rounded-lg text-gray-600 hover:text-blue-600 hover:bg-blue-50 transition-colors"
            >
              <CakeIcon class="w-5 h-5" />
              <span>Snacks</span>
            </a>
          </li>

          <!-- Dairy -->
          <li>
            <a
                href="#"
                class="flex items-center gap-3 px-3 py-2 rounded-lg text-gray-600 hover:text-blue-600 hover:bg-blue-50 transition-colors"
            >
              <CubeIcon class="w-5 h-5" />
              <span>Dairy</span>
            </a>
          </li>
        </ul>
      </div>
    </aside>

    <!-- Main area -->
    <div class="flex-1 flex flex-col">
      <!-- Top Nav -->
      <header class="bg-white shadow-sm border-b border-gray-200 px-6 py-4 flex justify-between items-center">
        <div class="flex items-center space-x-3">
          <img src="/images/fruitslogo.png" alt="Fruits logo" class="h-12 w-auto" />
          <h1 class="text-2xl font-semibold text-gray-800">FreshMart Dashboard</h1>
        </div>
        <div class="flex items-center space-x-4">
          <button @click="openBasket" class="text-gray-600 hover:text-blue-600" aria-label="Shopping Cart">
            <ShoppingCartIcon class="w-6 h-6" />
          </button>
          <button class="text-gray-600 hover:text-blue-600" aria-label="User Profile">
            <UserCircleIcon class="w-6 h-6" />
          </button>
        </div>
      </header>

      <!-- Page Content -->
      <main class="flex-1 bg-gray-50 p-6 overflow-y-auto">
        <router-view />
      </main>
    </div>

    <!-- Basket popup -->
    <BasketContent v-if="showBasket" @close="closeBasket" />
  </div>
</template>


<script setup>
import { ref } from 'vue'
import { ShoppingCartIcon, UserCircleIcon } from '@heroicons/vue/24/outline'
import BasketContent from '@/components/base/BasketContent.vue'
import { HomeIcon, Cog6ToothIcon, BeakerIcon, CakeIcon, CubeIcon } from '@heroicons/vue/24/outline'

const showBasket = ref(false)

function openBasket() {
  showBasket.value = true
}

function closeBasket() {
  showBasket.value = false
}
</script>
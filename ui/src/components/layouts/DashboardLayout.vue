
<template>
  <div class="flex h-screen overflow-hidden">
    <!-- Sidebar -->
    <aside class="w-64 bg-white border-r border-gray-200 shadow-md hidden md:block">
      <div class="h-full p-4 space-y-4">
        <h2 class="text-lg font-semibold text-gray-700">Categories</h2>
        <ul class="space-y-2">
          <li>
            <router-link to="/dashboard" class="text-gray-600 hover:text-blue-600">
              Fresh Fruits
            </router-link>
          </li>
          <li>
            <router-link to="/dashboard/management" class="text-gray-600 hover:text-blue-600">
              Management
            </router-link>
          </li>
          <li><a href="#" class="text-gray-600 hover:text-blue-600">Drinks</a></li>
          <li><a href="#" class="text-gray-600 hover:text-blue-600">Snacks</a></li>
          <li><a href="#" class="text-gray-600 hover:text-blue-600">Dairy</a></li>
        </ul>
      </div>
    </aside>

    <!-- Main area -->
    <div class="flex-1 flex flex-col">
      <!-- Top Nav -->
      <header class="bg-white shadow-sm border-b border-gray-200 px-6 py-4 flex justify-between items-center">
        <h1 class="text-2xl font-bold text-gray-800">FreshMart Dashboard</h1>
        <div class="flex items-center space-x-4">
          <input
              type="text"
              placeholder="Search products..."
              class="px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
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

const showBasket = ref(false)

function openBasket() {
  showBasket.value = true
}

function closeBasket() {
  showBasket.value = false
}
</script>
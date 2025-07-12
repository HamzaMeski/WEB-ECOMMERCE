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
          <button class="text-gray-600 hover:text-blue-600">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
              <path d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-1.5 6h13l-1.5-6M10 21a1 1 0 100-2 1 1 0 000 2zm8 0a1 1 0 100-2 1 1 0 000 2z"/>
            </svg>
          </button>
          <button class="text-gray-600 hover:text-blue-600">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
              <path d="M5.121 17.804A11.955 11.955 0 0112 15c2.09 0 4.078.537 5.879 1.485M15 10a3 3 0 11-6 0 3 3 0 016 0z"/>
              <path d="M4 4h16v16H4z" stroke="none"/>
            </svg>
          </button>
        </div>
      </header>

      <!-- Page Content -->
      <main class="flex-1 bg-gray-50 p-6 overflow-y-auto">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

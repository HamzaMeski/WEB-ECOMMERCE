<template>
  <!-- Overlay -->
  <div class="fixed inset-0 z-50 p-4">
    <!-- Modal -->
    <div class="fixed bg-white rounded-xl shadow-2xl w-full max-w-sm p-6 space-y-6 top-20 right-4 ">

      <!-- Cancel/Close button as router-link -->
      <button
          @click="$emit('close')"
          class="absolute top-3 right-3 text-gray-400 hover:text-gray-600 transition"
          aria-label="Close"
      >
        <XMarkIcon class="w-5 h-5" />
      </button>

      <!-- Header -->
      <div class="flex items-center gap-3">
        <UserIcon class="w-6 h-6 text-blue-600" />
        <h2 class="text-lg font-semibold text-gray-800">User Settings</h2>
      </div>

      <!-- Action Buttons -->
      <div class="space-y-4">
        <!-- Profile Management -->
        <router-link
            to="/profile"
            class="w-full flex items-center gap-3 px-4 py-2 text-gray-700 hover:bg-blue-50 rounded-md transition"
        >
          <UserIcon class="w-5 h-5 text-blue-500" />
          <span>Manage Profile</span>
        </router-link>

        <!-- Logout -->
        <button
            @click="logout"
            class="w-full flex items-center gap-3 px-4 py-2 text-red-600 hover:bg-red-50 rounded-md transition"
        >
          <ArrowRightOnRectangleIcon class="w-5 h-5 text-red-500" />
          <span>Logout</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { UserIcon, ArrowRightOnRectangleIcon, XMarkIcon } from '@heroicons/vue/24/outline'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const store = useStore()
const router = useRouter()

const logout =  async () => {
  await store.dispatch('auth/logout')
  router.replace('/')
}
</script>

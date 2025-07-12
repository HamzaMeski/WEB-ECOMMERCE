<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
    <div class="w-full max-w-6xl bg-white rounded-3xl shadow-2xl overflow-hidden">
      <div class="flex flex-col lg:flex-row min-h-[600px]">
        <!-- Left Side - Illustration -->
        <div class="lg:w-1/2 bg-gradient-to-br from-teal-400 to-blue-500 p-8 flex items-center justify-center relative overflow-hidden">
          <div class="absolute inset-0 bg-black/10"></div>
          <div class="relative z-10 text-center text-white">
            <div class="mb-8">
              <div class="w-32 h-32 bg-white/20 rounded-full mx-auto mb-6 flex items-center justify-center">
                <svg class="w-16 h-16 text-white" fill="currentColor" viewBox="0 0 24 24">
                  <path d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-5 14H7v-2h7v2zm3-4H7v-2h10v2zm0-4H7V7h10v2z"/>
                </svg>
              </div>
              <h2 class="text-3xl font-bold mb-4">Ready to start your success story?</h2>
              <p class="text-lg text-white/90 leading-relaxed">
                Join our community and start exploring your favorite literature today!
              </p>
            </div>
            <div class="flex justify-center space-x-4">
              <div class="w-3 h-3 bg-white/30 rounded-full"></div>
              <div class="w-3 h-3 bg-white/50 rounded-full"></div>
              <div class="w-3 h-3 bg-white rounded-full"></div>
            </div>
          </div>
        </div>

        <!-- Right Side - Auth Forms -->
        <div class="lg:w-1/2 p-8 flex items-center justify-center">
          <div class="w-full max-w-md">
            <!-- Toggle Buttons -->
            <div class="flex bg-gray-100 rounded-xl p-1 mb-8">
              <button
                  @click="currentSection = 'login'"
                  :class="currentSection === 'login' ? 'bg-white text-gray-900 shadow-md' : 'text-gray-600'"
                  class="flex-1 py-3 px-4 rounded-lg font-medium transition-all duration-200"
              >
                Login
              </button>
              <button
                  @click="currentSection = 'register'"
                  :class="currentSection === 'register' ? 'bg-white text-gray-900 shadow-md' : 'text-gray-600'"
                  class="flex-1 py-3 px-4 rounded-lg font-medium transition-all duration-200"
              >
                Register
              </button>
            </div>

            <!-- Dynamic Component -->
            <div class="transition-all duration-300 ease-in-out">
              <LoginSection v-if="currentSection === 'login'" />
              <RegisterSection v-if="currentSection === 'register'" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import LoginSection from "@/components/sections/auth/LoginSection.vue";
import RegisterSection from "@/components/sections/auth/RegisterSection.vue";

const currentSection = ref('login')
</script>
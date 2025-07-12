<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
    <div class="w-full max-w-6xl bg-white rounded-3xl shadow-2xl overflow-hidden">
      <div class="flex flex-col lg:flex-row min-h-[600px]">
        <!-- Left Side - Fruits Showcase -->
        <div class="lg:w-1/2 relative overflow-hidden">
          <!-- Background Image -->
          <div class="absolute inset-0">
            <img
                src="/images/category.jpg"
                alt="Fresh Fruits"
                class="w-full h-full object-cover"
            />
            <div class="absolute inset-0 bg-gradient-to-br from-black/40 to-black/60"></div>
          </div>

          <!-- Content Overlay -->
          <div class="relative z-10 h-full flex items-center justify-center p-8">
            <div class="text-center text-white max-w-md">
              <!-- App Logo/Icon -->
              <div class="mb-8">
                <div class="w-24 h-24 bg-white/20 backdrop-blur-sm rounded-full mx-auto mb-6 flex items-center justify-center border-2 border-white/30">
                  <svg class="w-12 h-12 text-white" fill="currentColor" viewBox="0 0 24 24">
                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z"/>
                  </svg>
                </div>

                <!-- App Title -->
                <h1 class="text-4xl font-bold mb-4 text-white drop-shadow-lg">
                  Fresh<span class="text-green-300">Mart</span>
                </h1>

                <!-- App Description -->
                <p class="text-lg text-white/90 leading-relaxed mb-6 drop-shadow-sm">
                  Discover the freshest fruits delivered straight to your doorstep.
                  Quality, freshness, and convenience in every order.
                </p>

                <!-- Key Features -->
                <div class="space-y-3 text-left">
                  <div class="flex items-center text-white/90">
                    <svg class="w-5 h-5 mr-3 text-green-300" fill="currentColor" viewBox="0 0 20 20">
                      <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"/>
                    </svg>
                    <span class="text-sm">Fresh & Premium Quality</span>
                  </div>
                  <div class="flex items-center text-white/90">
                    <svg class="w-5 h-5 mr-3 text-green-300" fill="currentColor" viewBox="0 0 20 20">
                      <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"/>
                    </svg>
                    <span class="text-sm">Fast Delivery</span>
                  </div>
                  <div class="flex items-center text-white/90">
                    <svg class="w-5 h-5 mr-3 text-green-300" fill="currentColor" viewBox="0 0 20 20">
                      <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"/>
                    </svg>
                    <span class="text-sm">Best Prices Guaranteed</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Decorative Elements -->
          <div class="absolute top-4 right-4 w-16 h-16 bg-white/10 rounded-full blur-xl"></div>
          <div class="absolute bottom-8 left-8 w-12 h-12 bg-green-300/20 rounded-full blur-lg"></div>
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
</template>>

<script setup>
import { ref } from 'vue'
import LoginSection from "@/components/sections/auth/LoginSection.vue";
import RegisterSection from "@/components/sections/auth/RegisterSection.vue";

const currentSection = ref('login')
</script>
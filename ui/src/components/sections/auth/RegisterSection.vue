<template>
  <div class="space-y-6">
    <div class="text-center">
      <h3 class="text-2xl font-bold text-gray-900 mb-2">Create your account</h3>
      <p class="text-gray-600">Join us and start your reading journey</p>
    </div>

    <form @submit.prevent="handleRegister" class="space-y-5">
      <!-- Full Name Input -->
      <div>
        <label for="fullName" class="block text-sm font-medium text-gray-700 mb-2">
          Full name
        </label>
        <input
            id="fullName"
            v-model="form.fullName"
            type="text"
            required
            class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors"
            placeholder="Enter your full name"
        />
      </div>

      <!-- Email Input -->
      <div>
        <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
          Email address
        </label>
        <input
            id="email"
            v-model="form.email"
            type="email"
            required
            class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors"
            placeholder="Enter your email"
        />
      </div>

      <!-- Password Input -->
      <div>
        <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
          Password
        </label>
        <div class="relative">
          <input
              id="password"
              v-model="form.password"
              :type="showPassword ? 'text' : 'password'"
              required
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors pr-12"
              placeholder="Create a password"
          />
          <button
              type="button"
              @click="showPassword = !showPassword"
              class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
          >
            <svg v-if="showPassword" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L3 3m6.878 6.878L21 21"></path>
            </svg>
            <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path>
            </svg>
          </button>
        </div>
        <div class="mt-2 text-sm text-gray-600">
          Password must be at least 8 characters long
        </div>
      </div>

      <!-- Confirm Password Input -->
      <div>
        <label for="confirmPassword" class="block text-sm font-medium text-gray-700 mb-2">
          Confirm password
        </label>
        <div class="relative">
          <input
              id="confirmPassword"
              v-model="form.confirmPassword"
              :type="showConfirmPassword ? 'text' : 'password'"
              required
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors pr-12"
              placeholder="Confirm your password"
          />
          <button
              type="button"
              @click="showConfirmPassword = !showConfirmPassword"
              class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
          >
            <svg v-if="showConfirmPassword" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L3 3m6.878 6.878L21 21"></path>
            </svg>
            <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path>
            </svg>
          </button>
        </div>
        <div v-if="form.confirmPassword && form.password !== form.confirmPassword" class="mt-2 text-sm text-red-600">
          Passwords do not match
        </div>
      </div>

      <!-- Area of Interest -->
      <div>
        <label for="areaOfInterest" class="block text-sm font-medium text-gray-700 mb-2">
          Area of interest
        </label>
        <select
            id="areaOfInterest"
            v-model="form.areaOfInterest"
            required
            class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors bg-white"
        >
          <option value="">Select your area of interest</option>
          <option value="fiction">Fiction</option>
          <option value="non-fiction">Non-Fiction</option>
          <option value="science">Science</option>
          <option value="history">History</option>
          <option value="biography">Biography</option>
          <option value="mystery">Mystery</option>
          <option value="romance">Romance</option>
          <option value="fantasy">Fantasy</option>
          <option value="self-help">Self-Help</option>
          <option value="business">Business</option>
        </select>
      </div>

      <!-- Terms and Conditions -->
      <div class="flex items-start">
        <div class="flex items-center h-5">
          <input
              id="terms"
              v-model="form.agreeToTerms"
              type="checkbox"
              required
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2"
          />
        </div>
        <div class="ml-3 text-sm">
          <label for="terms" class="text-gray-700">
            I agree to the
            <a href="#" class="text-blue-600 hover:text-blue-500 font-medium">Terms & Conditions</a>
            and
            <a href="#" class="text-blue-600 hover:text-blue-500 font-medium">Privacy Policy</a>
          </label>
        </div>
      </div>

      <!-- Submit Button -->
      <button
          type="submit"
          :disabled="loading || !isFormValid"
          class="w-full bg-gradient-to-r from-blue-500 to-teal-500 text-white py-3 px-6 rounded-lg font-medium hover:from-blue-600 hover:to-teal-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-all duration-200 disabled:opacity-50 disabled:cursor-not-allowed"
      >
        <span v-if="loading" class="flex items-center justify-center">
          <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
            <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
            <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
          </svg>
          Creating account...
        </span>
        <span v-else>Create account</span>
      </button>
    </form>

    <!-- Social Register -->
    <div class="relative">
      <div class="absolute inset-0 flex items-center">
        <div class="w-full border-t border-gray-300"></div>
      </div>
      <div class="relative flex justify-center text-sm">
        <span class="px-2 bg-white text-gray-500">Or register with</span>
      </div>
    </div>

    <div class="grid grid-cols-2 gap-3">
      <button
          type="button"
          class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50 transition-colors"
      >
        <svg class="w-5 h-5" viewBox="0 0 24 24">
          <path fill="currentColor" d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"/>
          <path fill="currentColor" d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"/>
          <path fill="currentColor" d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"/>
          <path fill="currentColor" d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"/>
        </svg>
      </button>
      <button
          type="button"
          class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50 transition-colors"
      >
        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
          <path d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"/>
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'

const loading = ref(false)
const showPassword = ref(false)
const showConfirmPassword = ref(false)

const form = reactive({
  fullName: '',
  email: '',
  password: '',
  confirmPassword: '',
  areaOfInterest: '',
  agreeToTerms: false
})

const isFormValid = computed(() => {
  return form.fullName &&
      form.email &&
      form.password &&
      form.confirmPassword &&
      form.password === form.confirmPassword &&
      form.password.length >= 8 &&
      form.areaOfInterest &&
      form.agreeToTerms
})

const handleRegister = async () => {
  loading.value = true

  try {
    // Simulate API call
    await new Promise(resolve => setTimeout(resolve, 1000))

    console.log('Register form submitted:', form)
    // Handle registration logic here

  } catch (error) {
    console.error('Registration error:', error)
  } finally {
    loading.value = false
  }
}
</script>
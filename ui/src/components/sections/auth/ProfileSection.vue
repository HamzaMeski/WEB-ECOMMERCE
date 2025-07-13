<template>
  <div class="max-w-xl mx-auto space-y-8 relative">
    <!-- Cancel Icon -->
    <router-link
        to="/dashboard"
        class="absolute top-4 left-4 text-gray-600 hover:text-gray-900"
        aria-label="Cancel and go back"
    >
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
           viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round"
              d="M6 18L18 6M6 6l12 12" />
      </svg>
    </router-link>

    <h2 class="text-2xl font-semibold text-gray-800 text-center">Manage Profile</h2>

    <!-- Success Message -->
    <div
        v-if="successMessage"
        class="p-4 bg-green-100 text-green-800 rounded-lg border border-green-300"
    >
      {{ successMessage }}
    </div>

    <!-- Error Message -->
    <div
        v-if="errorMessage"
        class="p-4 bg-red-100 text-red-800 rounded-lg border border-red-300"
    >
      {{ errorMessage }}
    </div>

    <!-- Profile Form -->
    <form @submit.prevent="updateProfile" class="space-y-6">
      <div>
        <label for="fullName" class="block text-sm font-medium text-gray-700">Full Name</label>
        <input
            v-model="form.fullName"
            id="fullName"
            type="text"
            required
            class="w-full px-4 py-2 mt-1 border rounded-lg focus:ring focus:ring-blue-300"
        />
      </div>

      <div>
        <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
        <input
            v-model="form.email"
            id="email"
            type="email"
            required
            class="w-full px-4 py-2 mt-1 border rounded-lg focus:ring focus:ring-blue-300"
        />
      </div>

      <button
          type="submit"
          :disabled="loadingProfile"
          class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 disabled:opacity-50"
      >
        {{ loadingProfile ? 'Saving...' : 'Save Profile' }}
      </button>
    </form>

    <hr />

    <!-- Password Update -->
    <form @submit.prevent="updatePassword" class="space-y-6">
      <div>
        <label for="newPassword" class="block text-sm font-medium text-gray-700">New Password</label>
        <input
            v-model="passwordForm.newPassword"
            :type="showNewPassword ? 'text' : 'password'"
            id="newPassword"
            required
            minlength="8"
            class="w-full px-4 py-2 mt-1 border rounded-lg focus:ring focus:ring-blue-300"
        />
        <button
            type="button"
            class="mt-1 text-sm text-blue-600 hover:underline"
            @click="showNewPassword = !showNewPassword"
        >
          {{ showNewPassword ? 'Hide' : 'Show' }} Password
        </button>
      </div>

      <div>
        <label for="confirmPassword" class="block text-sm font-medium text-gray-700">Confirm Password</label>
        <input
            v-model="passwordForm.confirmPassword"
            :type="showConfirmPassword ? 'text' : 'password'"
            id="confirmPassword"
            required
            class="w-full px-4 py-2 mt-1 border rounded-lg focus:ring focus:ring-blue-300"
        />
        <div
            v-if="passwordForm.confirmPassword && passwordForm.confirmPassword !== passwordForm.newPassword"
            class="text-sm text-red-600 mt-1"
        >
          Passwords do not match.
        </div>
        <button
            type="button"
            class="mt-1 text-sm text-blue-600 hover:underline"
            @click="showConfirmPassword = !showConfirmPassword"
        >
          {{ showConfirmPassword ? 'Hide' : 'Show' }} Password
        </button>
      </div>

      <button
          type="submit"
          :disabled="loadingPassword || passwordForm.newPassword !== passwordForm.confirmPassword"
          class="bg-teal-600 text-white px-6 py-2 rounded-lg hover:bg-teal-700 disabled:opacity-50"
      >
        {{ loadingPassword ? 'Updating...' : 'Update Password' }}
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const successMessage = ref('')
const errorMessage = ref('')

const loadingProfile = ref(false)
const loadingPassword = ref(false)

const showNewPassword = ref(false)
const showConfirmPassword = ref(false)

const form = reactive({
  fullName: '',
  email: ''
})

const passwordForm = reactive({
  newPassword: '',
  confirmPassword: ''
})

// Fetch profile when component mounts
const fetchProfile = async () => {
  loadingProfile.value = true
  errorMessage.value = ''
  try {
    await store.dispatch('auth/getProfile')
    const user = store.getters['auth/currentUser']
    if (user) {
      form.fullName = user.fullName || ''
      form.email = user.email || ''
    }
  } catch (error) {
    errorMessage.value = error.message || 'Failed to load profile'
  } finally {
    loadingProfile.value = false
  }
}
fetchProfile()


// profile update logic will be here
const updateProfile = async () => {

}


// password update logic will be here
const updatePassword = async () => {

}
</script>

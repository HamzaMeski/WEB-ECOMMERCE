<template>
  <div class="fixed inset-0 flex items-center justify-center bg-white/10 backdrop-blur-sm z-50 p-4">
    <div class="bg-white rounded-lg shadow-lg max-w-4xl w-full flex flex-col md:flex-row overflow-hidden">

      <!-- Left image side -->
      <div class="md:w-1/2 hidden md:block">
        <img
            src="/images/cost.jpeg"
            alt="Basket"
            class="object-cover h-full w-full"
            loading="lazy"
        />
      </div>

      <!-- Right basket content -->
      <div class="md:w-1/2 p-6 flex flex-col max-h-[90vh]">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">🧺 Your Basket</h2>

        <div class="flex-grow overflow-y-auto pr-2">
          <div v-if="basketItems.length">
            <!-- Basket Items -->
            <ul class="divide-y divide-gray-200">
              <li
                  v-for="item in basketItems"
                  :key="item.id"
                  class="flex justify-between items-center py-3"
              >
                <div>
                  <h3 class="text-gray-800 font-semibold">{{ item.name }}</h3>
                  <p class="text-sm text-gray-500">
                    ${{ item.price }} × {{ item.quantity }}
                  </p>
                </div>
                <p class="text-green-600 font-bold">
                  ${{ (item.price * item.quantity).toFixed(2) }}
                </p>
              </li>
            </ul>

            <!-- Total -->
            <div class="mt-4 border-t pt-4 flex justify-between items-center">
              <span class="text-lg font-semibold text-gray-800">Total</span>
              <span class="text-lg font-bold text-green-700">
                ${{ totalPrice }}
              </span>
            </div>
          </div>

          <div v-else class="text-gray-500 text-center my-6">
            No items in your basket yet.
          </div>
        </div>

        <!-- Buttons -->
        <div class="mt-6 flex justify-between space-x-3">
          <button
              @click="submit"
              class="flex-1 px-5 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
          >
           Buy
          </button>
          <button
              @click="emit('close')"
              class="flex-1 px-5 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { defineEmits } from 'vue'

const store = useStore()
const emit = defineEmits(['close'])

const basketItems = computed(() => store.getters['basket/basketItems'])

const totalPrice = computed(() => {
  return basketItems.value
      .reduce((total, item) => total + item.price * item.quantity, 0)
      .toFixed(2)
})

const submit = async () => {
  try {
    await store.dispatch('basket/submitBasket')
    emit('close')
  } catch (err) {
    alert('Submission failed.')
  }
}
</script>

<template>
  <div class="p-6">
    <div v-if="loading" class="text-gray-500">Loading products...</div>
    <div v-if="error" class="text-red-500">Error: {{ error }}</div>

    <div
        v-if="products.length"
        class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6"
    >
      <div
          v-for="product in products"
          :key="product.id"
          class="bg-white rounded-xl shadow-md p-4 hover:shadow-lg transition duration-200 relative"
      >
        <div class="mb-3">
          <img
              src="/images/fruits.png"
              :alt="product.name"
              class="w-full h-40 object-cover rounded-md"
          />
        </div>
        <h2 class="text-xl font-semibold text-gray-800">{{ product.name }}</h2>
        <p class="text-gray-600 text-sm">{{ product.nutritionalValue }}</p>
        <p class="mt-2 text-sm text-gray-500">Weight: {{ product.weight }} kg</p>
        <p class="mt-1 text-lg font-bold text-green-600">$ {{ product.price }}</p>

        <!-- Quantity Controls -->
        <div class="mt-4 flex items-center gap-2">
          <div class="flex flex-col gap-1">
            <button
                class="p-1 bg-gray-100 rounded hover:bg-gray-200"
                @click="increment(product.id)"
            >
              <PlusIcon class="w-4 h-4 text-gray-700" />
            </button>
            <button
                class="p-1 bg-gray-100 rounded hover:bg-gray-200"
                @click="decrement(product.id)"
            >
              <MinusIcon class="w-4 h-4 text-gray-700" />
            </button>
          </div>

          <input
              type="number"
              readonly
              :value="getQuantity(product.id)"
              class="w-12 text-center border border-gray-300 rounded"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useStore } from 'vuex'
import { PlusIcon, MinusIcon } from '@heroicons/vue/24/solid'

const store = useStore()

const products = computed(() => store.getters['product/allProducts'])
const loading = computed(() => store.getters['product/isLoading'])
const error = computed(() => store.getters['product/productError'])

const basket = ref([])

onMounted(() => {
  store.dispatch('product/fetchProducts')
})

watch(products, (newVal) => {
  basket.value = newVal.map(product => ({
    ...product,
    quantity: 0
  }))
})

// Helpers
const getQuantity = (id) => {
  const item = basket.value.find(p => p.id === id)
  return item ? item.quantity : 0
}

const increment = (id) => basket.value.find(p => p.id === id).quantity++

const decrement = (id) => {
  const item = basket.value.find(p => p.id === id)
  if (item.quantity > 0) item.quantity--
}
</script>

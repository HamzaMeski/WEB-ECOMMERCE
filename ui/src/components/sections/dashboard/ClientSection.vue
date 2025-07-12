<template>
  <div class="p-6">
    <!-- Create button -->
    <button
        @click="openCreatePopup"
        class="mb-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
    >
      Create Product
    </button>

    <div v-if="loading" class="text-gray-500">Loading products...</div>
    <div v-if="error" class="text-red-500">Error: {{ error }}</div>

    <div v-if="products.length" class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
      <div
          v-for="product in products"
          :key="product.id"
          class="bg-white rounded-xl shadow-md p-4 hover:shadow-lg transition duration-200"
      >
        <div class="mb-3">
          <img src="/images/fruits.png" :alt="product.name" class="w-full h-40 object-cover rounded-md" />
        </div>
        <h2 class="text-xl font-semibold text-gray-800">{{ product.name }}</h2>
        <p class="text-gray-600 text-sm">{{ product.nutritionalValue }}</p>
        <p class="mt-2 text-sm text-gray-500">Weight: {{ product.weight }} kg</p>
        <p class="mt-1 text-lg font-bold text-green-600">$ {{ product.price }}</p>
      </div>
    </div>


    <!-- Popup form, visible only if showCreatePopup is true -->
    <CreateEditProductForm v-if="showCreatePopup" @close="closeCreatePopup" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import CreateEditProductForm from "@/components/base/CreateEditProductForm.vue";

const store = useStore()

// fetching all products
const products = computed(() => store.getters['product/allProducts'])
const loading = computed(() => store.getters['product/isLoading'])
const error = computed(() => store.getters['product/productError'])

onMounted(() => {
  store.dispatch('product/fetchProducts')
})


// displaying createEdit popup
const showCreatePopup = ref(false)
function openCreatePopup() {
  showCreatePopup.value = true
}
function closeCreatePopup() {
  showCreatePopup.value = false
}
</script>

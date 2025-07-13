<template>
  <div class="p-6">
    <!-- Create button -->
    <button
        @click="openCreatePopup()"
        class="mb-4 px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
    >
      Create Product
    </button>

    <div v-if="loading" class="text-gray-500">Loading products...</div>
    <div v-if="error" class="text-red-500">Error: {{ error }}</div>

    <div v-if="products.length" class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
      <div
          v-for="product in products"
          :key="product.id"
          class="bg-white rounded-xl shadow-md p-4 hover:shadow-lg transition duration-200 relative"
      >
        <div class="mb-3">
          <img
              :src="getProductImageUrl(product.id)"
              :alt="product.name"
              class="w-full h-40 object-cover rounded-md"
              @error="handleImageError"
          />
        </div>
        <h2 class="text-xl font-semibold text-gray-800">{{ product.name }}</h2>
        <p class="text-gray-600 text-sm">{{ product.nutritionalValue }}</p>
        <p class="mt-2 text-sm text-gray-500">Weight: {{ product.weight }} kg</p>
        <p class="mt-1 text-lg font-bold text-green-600">$ {{ product.price }}</p>

        <!-- Edit button -->
        <button
            @click="openEditPopup(product)"
            class="absolute top-3 right-3 bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700 transition text-sm"
            title="Edit Product"
        >
          Edit
        </button>
      </div>
    </div>

    <!-- Popup form, passing selectedProduct as prop -->
    <CreateEditProductForm
        v-if="showCreatePopup"
        :product="selectedProduct"
        @close="closeCreatePopup"
    />
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useStore } from 'vuex'
import CreateEditProductForm from "@/components/base/CreateEditProductForm.vue"
import { getImage } from '@/services/image.service'

const store = useStore()

const products = computed(() => store.getters['product/allProducts'])
const loading = computed(() => store.getters['product/isLoading'])
const error = computed(() => store.getters['product/productError'])

const productImages = ref({})

onMounted(() => {
  store.dispatch('product/fetchProducts')
})

// Watch for products and load their images
watch(products, async (newProducts) => {
  if (newProducts && newProducts.length > 0) {
    for (const product of newProducts) {
      if (product.imageId && !productImages.value[product.id]) {
        try {
          const imageBlob = await getImage(product.imageId)
          productImages.value[product.id] = URL.createObjectURL(imageBlob)
        } catch (error) {
          console.error(`Error loading image for product ${product.id}:`, error)
          productImages.value[product.id] = '/images/fruits.png'
        }
      }
    }
  }
})

const showCreatePopup = ref(false)
const selectedProduct = ref(null)

// Get image URL for a product
function getProductImageUrl(productId) {
  return productImages.value[productId] || '/images/fruits.png'
}

// Handle image loading errors
function handleImageError(event) {
  event.target.src = '/images/fruits.png' // fallback to default image
}

// Open popup for creating new product
function openCreatePopup() {
  selectedProduct.value = null // clear any selected product
  showCreatePopup.value = true
}

// Open popup for editing existing product
function openEditPopup(product) {
  selectedProduct.value = { ...product } // clone product to avoid direct mutations
  showCreatePopup.value = true
}

function closeCreatePopup() {
  showCreatePopup.value = false
  selectedProduct.value = null
}
</script>
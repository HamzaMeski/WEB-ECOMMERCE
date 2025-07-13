<template>
  <div class="p-6">
    <!-- Header with Create button and Search -->
    <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 mb-6">
      <button
          @click="openCreatePopup()"
          class="px-6 py-3 bg-green-600 text-white rounded-lg hover:bg-green-700 transition duration-200 font-medium shadow-sm"
      >
        Create Product
      </button>

      <!-- Search bar -->
      <div class="relative max-w-md">
        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
          <svg class="h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"/>
          </svg>
        </div>
        <input
            v-model="searchQuery"
            type="text"
            placeholder="Search products by name..."
            class="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
        />
        <button
            v-if="searchQuery"
            @click="clearSearch"
            class="absolute inset-y-0 right-0 pr-3 flex items-center text-gray-400 hover:text-gray-600"
        >
          <svg class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"/>
          </svg>
        </button>
      </div>
    </div>

    <div v-if="loading" class="text-gray-500 flex items-center justify-center py-8">
      <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-gray-900 mr-3"></div>
      Loading products...
    </div>
    <div v-if="error" class="text-red-500 bg-red-50 border border-red-200 rounded-lg p-4 mb-6">
      Error: {{ error }}
    </div>

    <div v-if="filteredProducts.length && !loading" class="bg-white rounded-xl shadow-lg overflow-hidden">
      <!-- Table Header -->
      <div class="bg-gray-50 px-6 py-4 border-b border-gray-200">
        <h3 class="text-lg font-semibold text-gray-900">Products</h3>
        <p class="text-sm text-gray-600 mt-1">
          <span v-if="searchQuery">
            Showing {{ startIndex + 1 }} to {{ Math.min(startIndex + itemsPerPage, filteredProducts.length) }} of {{ filteredProducts.length }} filtered products
            <span class="text-gray-400">({{ products.length }} total)</span>
          </span>
          <span v-else>
            Showing {{ startIndex + 1 }} to {{ Math.min(startIndex + itemsPerPage, filteredProducts.length) }} of {{ filteredProducts.length }} products
          </span>
        </p>
      </div>

      <!-- Table -->
      <div class="overflow-x-auto">
        <table class="w-full">
          <thead class="bg-gray-50 border-b border-gray-200">
          <tr>
            <th class="px-6 py-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Product
            </th>
            <th class="px-6 py-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Nutritional Value
            </th>
            <th class="px-6 py-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Weight
            </th>
            <th class="px-6 py-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Price
            </th>
            <th class="px-6 py-4 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
              Actions
            </th>
          </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
          <tr
              v-for="product in paginatedProducts"
              :key="product.id"
              class="hover:bg-gray-50 transition duration-150"
          >
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="flex items-center">
                <div class="flex-shrink-0 h-16 w-16">
                  <img
                      :src="getProductImageUrl(product.id)"
                      :alt="product.name"
                      class="h-16 w-16 rounded-lg object-cover shadow-sm"
                  />
                </div>
                <div class="ml-4">
                  <div class="text-sm font-medium text-gray-900">{{ product.name }}</div>
                  <div class="text-sm text-gray-500">ID: {{ product.id }}</div>
                </div>
              </div>
            </td>
            <td class="px-6 py-4">
              <div class="text-sm text-gray-900 max-w-xs">{{ product.nutritionalValue }}</div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="text-sm text-gray-900">{{ product.weight }} kg</div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="text-lg font-semibold text-green-600">${{ product.price }}</div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
              <button
                  @click="openEditPopup(product)"
                  class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition duration-200 shadow-sm"
                  title="Edit Product"
              >
                Edit
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>

      <!-- Pagination -->
      <div class="bg-gray-50 px-6 py-4 border-t border-gray-200">
        <div class="flex items-center justify-between">
          <div class="flex items-center space-x-2">
            <label class="text-sm text-gray-700">Items per page:</label>
            <select
                v-model="itemsPerPage"
                class="border border-gray-300 rounded-md px-3 py-1 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="5">5</option>
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="50">50</option>
            </select>
          </div>

          <div class="flex items-center space-x-2">
            <button
                @click="currentPage = 1"
                :disabled="currentPage === 1"
                class="px-3 py-1 rounded-md text-sm font-medium transition duration-200"
                :class="currentPage === 1 ? 'text-gray-400 cursor-not-allowed' : 'text-gray-700 hover:bg-gray-200'"
            >
              First
            </button>

            <button
                @click="currentPage--"
                :disabled="currentPage === 1"
                class="px-3 py-1 rounded-md text-sm font-medium transition duration-200"
                :class="currentPage === 1 ? 'text-gray-400 cursor-not-allowed' : 'text-gray-700 hover:bg-gray-200'"
            >
              Previous
            </button>

            <div class="flex space-x-1">
              <button
                  v-for="page in visiblePages"
                  :key="page"
                  @click="currentPage = page"
                  class="px-3 py-1 rounded-md text-sm font-medium transition duration-200"
                  :class="currentPage === page
                  ? 'bg-blue-600 text-white'
                  : 'text-gray-700 hover:bg-gray-200'"
              >
                {{ page }}
              </button>
            </div>

            <button
                @click="currentPage++"
                :disabled="currentPage === totalPages"
                class="px-3 py-1 rounded-md text-sm font-medium transition duration-200"
                :class="currentPage === totalPages ? 'text-gray-400 cursor-not-allowed' : 'text-gray-700 hover:bg-gray-200'"
            >
              Next
            </button>

            <button
                @click="currentPage = totalPages"
                :disabled="currentPage === totalPages"
                class="px-3 py-1 rounded-md text-sm font-medium transition duration-200"
                :class="currentPage === totalPages ? 'text-gray-400 cursor-not-allowed' : 'text-gray-700 hover:bg-gray-200'"
            >
              Last
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Empty state -->
    <div v-if="!filteredProducts.length && !loading" class="text-center py-12">
      <div v-if="searchQuery" class="text-gray-500 text-lg">
        No products found matching "{{ searchQuery }}"
      </div>
      <div v-else class="text-gray-500 text-lg">No products found</div>
      <p v-if="searchQuery" class="text-gray-400 mt-2">
        Try adjusting your search terms or
        <button @click="clearSearch" class="text-blue-600 hover:text-blue-800 underline">clear the search</button>
      </p>
      <p v-else class="text-gray-400 mt-2">Create your first product to get started</p>
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

// Search functionality
const searchQuery = ref('')

// Filter products based on search query
const filteredProducts = computed(() => {
  if (!searchQuery.value.trim()) {
    return products.value
  }

  const query = searchQuery.value.toLowerCase().trim()
  return products.value.filter(product =>
      product.name.toLowerCase().includes(query)
  )
})

// Clear search
function clearSearch() {
  searchQuery.value = ''
}

// Pagination state
const currentPage = ref(1)
const itemsPerPage = ref(10)

// Pagination computed properties (now based on filtered products)
const totalPages = computed(() => Math.ceil(filteredProducts.value.length / itemsPerPage.value))
const startIndex = computed(() => (currentPage.value - 1) * itemsPerPage.value)
const paginatedProducts = computed(() => {
  const start = startIndex.value
  const end = start + itemsPerPage.value
  return filteredProducts.value.slice(start, end)
})

// Visible page numbers for pagination
const visiblePages = computed(() => {
  const pages = []
  const total = totalPages.value
  const current = currentPage.value

  if (total <= 7) {
    // Show all pages if total is 7 or less
    for (let i = 1; i <= total; i++) {
      pages.push(i)
    }
  } else {
    // Show first page, last page, and pages around current
    if (current <= 4) {
      for (let i = 1; i <= 5; i++) {
        pages.push(i)
      }
      pages.push('...')
      pages.push(total)
    } else if (current >= total - 3) {
      pages.push(1)
      pages.push('...')
      for (let i = total - 4; i <= total; i++) {
        pages.push(i)
      }
    } else {
      pages.push(1)
      pages.push('...')
      for (let i = current - 1; i <= current + 1; i++) {
        pages.push(i)
      }
      pages.push('...')
      pages.push(total)
    }
  }

  return pages.filter(page => page !== '...' || pages.indexOf(page) === pages.lastIndexOf(page))
})

// Reset to first page when items per page changes
watch(itemsPerPage, () => {
  currentPage.value = 1
})

// Reset to first page when search query changes
watch(searchQuery, () => {
  currentPage.value = 1
})

// Reset to first page when products change
watch(products, () => {
  if (currentPage.value > totalPages.value) {
    currentPage.value = 1
  }
})

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

// Open popup for creating new product
function openCreatePopup() {
  selectedProduct.value = null
  showCreatePopup.value = true
}

// Open popup for editing existing product
function openEditPopup(product) {
  selectedProduct.value = { ...product }
  showCreatePopup.value = true
}

function closeCreatePopup() {
  showCreatePopup.value = false
  selectedProduct.value = null
}
</script>
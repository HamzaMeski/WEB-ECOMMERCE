<template>
  <div
      class="fixed inset-0 flex items-center justify-center bg-white/10 backdrop-blur-sm z-50 p-4"
  >
    <div
        class="bg-white rounded-lg shadow-lg max-w-4xl w-full flex flex-col md:flex-row overflow-hidden"
    >
      <!-- Left image side -->
      <div class="md:w-1/2 hidden md:block">
        <img
            src="/images/fruits2.webp"
            alt="Fruits"
            class="object-cover h-full w-full"
            loading="lazy"
        />
      </div>

      <!-- Right form side -->
      <div class="md:w-1/2 p-6 flex flex-col">
        <h2 class="text-2xl font-bold mb-6 text-green-700">
          {{ props.product ? 'Edit Product' : 'Create Product' }}
        </h2>

        <form @submit.prevent="submitForm" class="space-y-5 flex-grow flex flex-col justify-between">
          <div>
            <label class="block text-gray-700 mb-1" for="name">Name</label>
            <input
                id="name"
                v-model="name"
                type="text"
                class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-400"
                placeholder="Enter fruit name"
                required
            />
          </div>

          <div>
            <label class="block text-gray-700 mb-1" for="nutritionalValue">Nutritional Value</label>
            <textarea
                id="nutritionalValue"
                v-model="nutritionalValue"
                class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-400"
                rows="3"
                placeholder="Describe nutritional benefits"
            ></textarea>
          </div>

          <div>
            <label class="block text-gray-700 mb-1" for="weight">Weight (kg)</label>
            <input
                id="weight"
                v-model.number="weight"
                type="number"
                min="0"
                step="0.01"
                class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-400"
                placeholder="e.g. 0.5"
                required
            />
          </div>

          <div>
            <label class="block text-gray-700 mb-1" for="price">Price ($)</label>
            <input
                id="price"
                v-model.number="price"
                type="number"
                min="0"
                step="0.01"
                class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-400"
                placeholder="e.g. 1.99"
                required
            />
          </div>

          <div class="flex justify-end space-x-3 mt-4">
            <button
                type="button"
                @click="cancel"
                class="px-5 py-2 rounded border border-gray-300 hover:bg-gray-100 transition"
            >
              Cancel
            </button>
            <button
                type="submit"
                class="px-5 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
            >
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
>

<script setup>
import { ref, watch, defineEmits, defineProps } from 'vue'
import { useStore } from 'vuex'

const emits = defineEmits(['close'])
const props = defineProps({
  product: Object
})

const store = useStore()

// Form fields
const name = ref('')
const nutritionalValue = ref('')
const weight = ref(null)
const price = ref(null)

// If prop product changes, update form fields accordingly (for editing)
watch(
    () => props.product,
    (newProduct) => {
      if (newProduct) {
        name.value = newProduct.name || ''
        nutritionalValue.value = newProduct.nutritionalValue || ''
        weight.value = newProduct.weight || null
        price.value = newProduct.price || null
      } else {
        // Clear form when no product passed (create mode)
        name.value = ''
        nutritionalValue.value = ''
        weight.value = null
        price.value = null
      }
    },
    { immediate: true }
)

async function submitForm() {
  const productData = {
    name: name.value,
    nutritionalValue: nutritionalValue.value,
    weight: weight.value,
    price: price.value
  }

  try {
    if (props.product && props.product.id) {
      // Edit mode — update existing product
      await store.dispatch('product/updateProduct', { id: props.product.id, data: productData })
    } else {
      // Create mode — add new product
      await store.dispatch('product/createProduct', productData)
    }
    emits('close')
  } catch (error) {
    alert(error.message || 'Error saving product')
  }
}

function cancel() {
  emits('close')
}
</script>

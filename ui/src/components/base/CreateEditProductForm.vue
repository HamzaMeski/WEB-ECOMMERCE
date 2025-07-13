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

          <!-- Image upload field -->
          <div>
            <label class="block text-gray-700 mb-1" for="image">Product Image</label>
            <input
                id="image"
                type="file"
                accept="image/*"
                @change="handleImageChange"
                class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-400"
                :required="!props.product"
            />
            <p v-if="selectedImage" class="text-sm text-gray-600 mt-1">
              Selected: {{ selectedImage.name }}
            </p>
            <p v-else-if="props.product && !selectedImage" class="text-sm text-gray-500 mt-1">
              Leave empty to keep current image
            </p>
          </div>

          <!-- Image preview -->
          <div v-if="imagePreview || (props.product && props.product.imageUrl && !selectedImage)" class="mt-4">
            <label class="block text-gray-700 mb-1">
              {{ selectedImage ? 'New Image Preview' : 'Current Image' }}
            </label>
            <img
                :src="imagePreview || props.product.imageUrl"
                alt="Preview"
                class="w-32 h-32 object-cover rounded border"
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
                :disabled="loading"
                class="px-5 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition disabled:opacity-50"
            >
              {{ loading ? 'Saving...' : 'Save' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

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
const selectedImage = ref(null)
const imagePreview = ref(null)
const loading = ref(false)

// If prop product changes, update form fields accordingly (for editing)
watch(
    () => props.product,
    (newProduct) => {
      if (newProduct) {
        name.value = newProduct.name || ''
        nutritionalValue.value = newProduct.nutritionalValue || ''
        weight.value = newProduct.weight || null
        price.value = newProduct.price || null
        // Clear image selection when editing (user can choose new image if desired)
        selectedImage.value = null
        imagePreview.value = null
      } else {
        // Clear form when no product passed (create mode)
        name.value = ''
        nutritionalValue.value = ''
        weight.value = null
        price.value = null
        selectedImage.value = null
        imagePreview.value = null
      }
    },
    { immediate: true }
)

function handleImageChange(event) {
  const file = event.target.files[0]
  if (file) {
    selectedImage.value = file

    // Create preview
    const reader = new FileReader()
    reader.onload = (e) => {
      imagePreview.value = e.target.result
    }
    reader.readAsDataURL(file)
  } else {
    selectedImage.value = null
    imagePreview.value = null
  }
}

async function submitForm() {
  // Validate image for new products
  if (!props.product && !selectedImage.value) {
    alert('Please select an image for the product')
    return
  }

  const productData = {
    name: name.value,
    nutritionalValue: nutritionalValue.value,
    weight: weight.value,
    price: price.value
  }

  loading.value = true

  try {
    if (props.product && props.product.id) {
      // Edit mode — update existing product
      await store.dispatch('product/updateProduct', {
        id: props.product.id,
        data: productData,
        image: selectedImage.value // null if no new image selected
      })
    } else {
      // Create mode — add new product
      await store.dispatch('product/createProduct', {
        data: productData,
        image: selectedImage.value
      })
    }
    emits('close')
  } catch (error) {
    alert(error.message || 'Error saving product')
  } finally {
    loading.value = false
  }
}

function cancel() {
  emits('close')
}
</script>
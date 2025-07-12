<template>
  <div class="fixed inset-0 flex items-center justify-center  backdrop-blur-sm z-50">
    <div class="bg-white rounded-lg shadow-lg p-6 max-w-md w-full mx-4">
      <h2 class="text-xl font-bold text-gray-800 mb-4">Your Basket</h2>
      <p class="text-gray-600">Lorem ipsum dolor sit amet, consectetur adipiscing elit. 🍎</p>

      <div class="mt-6 flex justify-end">
        <button
            @click="$emit('close')"
            class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition"
        >
          Close
        </button>
      </div>
    </div>
  </div>
</template>

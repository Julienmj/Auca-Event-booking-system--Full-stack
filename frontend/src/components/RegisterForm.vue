<template>
  <div class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
    <div class="bg-white rounded-xl shadow-xl w-full max-w-md p-6">

      <h2 class="text-xl font-bold text-gray-800 mb-1">Register for Event</h2>
      <p class="text-sm text-gray-500 mb-5">{{ event.title }}</p>

      <form @submit.prevent="submit">
        <label class="block text-sm font-medium text-gray-700 mb-1">Your Name</label>
        <input
          v-model="name"
          type="text"
          placeholder="Enter your full name"
          required
          class="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
        />

        <p v-if="error" class="text-red-500 text-sm mb-3">{{ error }}</p>
        <p v-if="success" class="text-green-600 text-sm mb-3">{{ success }}</p>

        <div class="flex gap-3">
          <button
            type="submit"
            :disabled="loading"
            class="flex-1 bg-blue-600 text-white py-2 rounded-lg text-sm font-semibold hover:bg-blue-700 transition disabled:opacity-50"
          >
            {{ loading ? 'Registering...' : 'Confirm' }}
          </button>
          <button
            type="button"
            @click="$emit('close')"
            class="flex-1 bg-gray-100 text-gray-700 py-2 rounded-lg text-sm font-semibold hover:bg-gray-200 transition"
          >
            Cancel
          </button>
        </div>
      </form>

    </div>
  </div>
</template>

<script>
import api from '../axios.js'

export default {
  props: {
    event: {
      type: Object,
      required: true,
    },
  },
  emits: ['close'],
  data() {
    return {
      name: '',
      loading: false,
      error: null,
      success: null,
    }
  },
  methods: {
    async submit() {
      this.loading = true
      this.error = null
      this.success = null
      try {
        await api.post('/registrations', {
          name: this.name,
          eventId: this.event.eventId,
        })
        this.success = 'Registration successful!'
        this.name = ''
        setTimeout(() => this.$emit('close'), 1500)
      } catch (err) {
        this.error = err.response?.data || 'Registration failed.'
      } finally {
        this.loading = false
      }
    },
  },
}
</script>

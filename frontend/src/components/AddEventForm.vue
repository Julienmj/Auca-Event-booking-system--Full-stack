<template>
  <div class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
    <div class="bg-white rounded-xl shadow-xl w-full max-w-md p-6">

      <h2 class="text-xl font-bold text-gray-800 mb-5">Add New Event</h2>

      <form @submit.prevent="submit">
        <label class="block text-sm font-medium text-gray-700 mb-1">Title</label>
        <input
          v-model="form.title"
          type="text"
          placeholder="Event title"
          required
          class="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
        />

        <label class="block text-sm font-medium text-gray-700 mb-1">Location</label>
        <input
          v-model="form.location"
          type="text"
          placeholder="Event location"
          required
          class="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
        />

        <label class="block text-sm font-medium text-gray-700 mb-1">Date</label>
        <input
          v-model="form.date"
          type="date"
          required
          class="w-full border border-gray-300 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
        />

        <label class="block text-sm font-medium text-gray-700 mb-1">Available Seats</label>
        <input
          v-model.number="form.availableSeats"
          type="number"
          min="1"
          placeholder="Number of seats"
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
            {{ loading ? 'Saving...' : 'Add Event' }}
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
  emits: ['close', 'added'],
  data() {
    return {
      form: {
        title: '',
        location: '',
        date: '',
        availableSeats: null,
      },
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
        await api.post('/events', this.form)
        this.success = 'Event added successfully!'
        setTimeout(() => this.$emit('added'), 1500)
      } catch {
        this.error = 'Failed to add event.'
      } finally {
        this.loading = false
      }
    },
  },
}
</script>

<template>
  <div class="bg-white rounded-xl shadow p-5 flex flex-col justify-between hover:shadow-md transition">
    <div>
      <h3 class="text-lg font-bold text-gray-800 mb-1">{{ event.title }}</h3>
      <p class="text-sm text-gray-500 mb-1">📍 {{ event.location }}</p>
      <p class="text-sm text-gray-500 mb-3">📅 {{ event.date }}</p>
      <span
        class="inline-block text-xs font-semibold px-3 py-1 rounded-full"
        :class="event.availableSeats > 0 ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-600'"
      >
        {{ event.availableSeats > 0 ? `${event.availableSeats} seats left` : 'Fully Booked' }}
      </span>
    </div>

    <button
      @click="$emit('register')"
      :disabled="event.availableSeats === 0"
      class="mt-4 w-full py-2 rounded-lg text-sm font-semibold transition"
      :class="event.availableSeats > 0
        ? 'bg-blue-600 text-white hover:bg-blue-700'
        : 'bg-gray-200 text-gray-400 cursor-not-allowed'"
    >
      {{ event.availableSeats > 0 ? 'Register' : 'Unavailable' }}
    </button>
  </div>
</template>

<script>
export default {
  props: {
    event: {
      type: Object,
      required: true,
    },
  },
  emits: ['register'],
}
</script>

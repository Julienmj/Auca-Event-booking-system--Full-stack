<template>
  <div>
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Upcoming Events</h2>

    <div v-if="loading" class="text-center text-gray-500 py-12">Loading events...</div>

    <div v-else-if="events.length === 0" class="text-center text-gray-500 py-12">
      No events available. Be the first to add one!
    </div>

    <div v-else class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
      <EventCard
        v-for="event in events"
        :key="event.eventId"
        :event="event"
        @register="$emit('register', event)"
      />
    </div>

    <p v-if="error" class="text-red-500 mt-4">{{ error }}</p>
  </div>
</template>

<script>
import api from '../axios.js'
import EventCard from './EventCard.vue'

export default {
  components: { EventCard },
  props: {
    refreshTrigger: Number,
  },
  emits: ['register'],
  data() {
    return {
      events: [],
      loading: false,
      error: null,
    }
  },
  watch: {
    refreshTrigger() {
      this.fetchEvents()
    },
  },
  mounted() {
    this.fetchEvents()
  },
  methods: {
    async fetchEvents() {
      this.loading = true
      this.error = null
      try {
        const res = await api.get('/events')
        this.events = res.data
      } catch {
        this.error = 'Failed to load events.'
      } finally {
        this.loading = false
      }
    },
  },
}
</script>

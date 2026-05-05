<template>
  <div class="min-h-screen bg-gray-100">

    <!-- Navbar -->
    <nav class="bg-blue-700 text-white px-6 py-4 flex items-center justify-between shadow">
      <h1 class="text-xl font-bold tracking-wide">🎓 AUCA Event Booking</h1>
      <button
        @click="showAddEvent = true"
        class="bg-white text-blue-700 font-semibold px-4 py-2 rounded hover:bg-blue-50 transition"
      >
        + Add Event
      </button>
    </nav>

    <!-- Main Content -->
    <main class="max-w-6xl mx-auto px-4 py-8">
      <EventList :refresh-trigger="refreshTrigger" @register="openRegisterModal" />
    </main>

    <!-- Register Modal -->
    <RegisterForm
      v-if="showRegister"
      :event="selectedEvent"
      @close="showRegister = false"
    />

    <!-- Add Event Modal -->
    <AddEventForm
      v-if="showAddEvent"
      @close="showAddEvent = false"
      @added="onEventAdded"
    />

  </div>
</template>

<script>
import EventList from './components/EventList.vue'
import RegisterForm from './components/RegisterForm.vue'
import AddEventForm from './components/AddEventForm.vue'

export default {
  components: { EventList, RegisterForm, AddEventForm },
  data() {
    return {
      showRegister: false,
      showAddEvent: false,
      selectedEvent: null,
      refreshTrigger: 0,
    }
  },
  methods: {
    openRegisterModal(event) {
      this.selectedEvent = event
      this.showRegister = true
    },
    onEventAdded() {
      this.showAddEvent = false
      this.refreshTrigger++
    },
  },
}
</script>

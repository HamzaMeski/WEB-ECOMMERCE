<template>
  <DashboardLayout v-if="getAuthToken()"/>
  <AuthLayout v-else/>
</template>

<script>
import DashboardLayout from "@/components/layouts/DashboardLayout.vue";
import AuthLayout from "@/components/layouts/AuthLayout.vue";


export default {
  name: 'App',
  computed: {
    AuthLayout() {
      return AuthLayout
    },
    DashboardLayout() {
      return DashboardLayout
    }
  },
  components: {
    DashboardLayout,
    AuthLayout
  },
  data() {
    return {
    }
  },
  methods: {
    getAuthToken() {
      return localStorage.getItem('authToken')
    }
  }
}
</script>

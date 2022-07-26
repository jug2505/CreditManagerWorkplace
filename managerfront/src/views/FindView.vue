<template>
  <div>
    <SearchPerson/>
  </div>
</template>

<script>
// @ is an alias to /src
import SearchPerson from '@/components/SearchPerson.vue'

export default {
  name: 'FindView',
  components: {
    SearchPerson
}
}
</script>

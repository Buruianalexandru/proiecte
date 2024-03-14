<template>
    <input type="file" v-on:change="setFile" />
    <button v-on:click="uploadFile">trimite</button>
</template>
<script>
import { fetchV1FormDataWithAuth } from '@/lib/fetchers';

export default {
    name: "FileUpload",
    data() {
        return {
            files: null,
        }
    },
    methods: {
        setFile(event) {
            this.files = event.target.files
        },
        async uploadFile() {
            const formData = new FormData()
            formData.append('files', this.files[0])
            const res = await fetchV1FormDataWithAuth('files/me', {method: "POST", body: formData})
            if (res.ok) {
                this.$router.push('/profile')
            }
            // const data = await res.json()
        }
    }
}
</script>
<template>
  <div>
       <h3>Search books</h3>
       
   <b-form  style="width:500px">
       <v-row>
           <v-col>
      <b-form-group
        id="input-group-1"
        label="Title:"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          v-model="form.title"
          type="text"
          placeholder="Enter title"
   
        ></b-form-input>
      </b-form-group>
           </v-col>
<v-col>
      <b-form-group id="input-group-2" label="keywords:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.keywords"
          placeholder="Enter keywords"

        ></b-form-input>
      </b-form-group>
</v-col>
       </v-row>
       <v-row>
           <v-col>
            <b-form-group id="input-group-2" label="author name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          placeholder="Enter name"
  
        ></b-form-input>
      </b-form-group>
           </v-col>
           <v-col>
            <b-form-group id="input-group-2" label="author surname:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.surname"
          placeholder="Enter surname"

        ></b-form-input>
      </b-form-group>
           </v-col>
       </v-row>
    <b-form-group id="input-group-2" label="genres:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.genre"
          placeholder="Enter genre"

        ></b-form-input>

      </b-form-group>
        <b-form-group id="input-group-3" label="operation:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.operation"
          :options="operations"
   
        ></b-form-select>
      </b-form-group> 
       <b-form-group id="input-group-2" label="text:" label-for="input-2">
        <b-form-input 
          id="input-2"
          v-model="form.text"
          placeholder="Enter text"

        ></b-form-input>
      </b-form-group>

     
        <!-- <b-form-group id="input-group-3" label="search type:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.searchType"
          :options="types"
   
        ></b-form-select>
      </b-form-group>  -->
        <button @click="search" type="button"
                class="btn btn-outline-primary  z-depth-2" >Search</button>
   </b-form>
    <v-data-table
    @click="download()"
    :headers="headers"
    :items="results"
    :items-per-page="5"
    class="elevation-1"
  ><template v-slot:item.actions="{ item }">
                <v-btn icon @click="download(item)">
                  <v-icon color="green darken-2">mdi-download</v-icon>
                </v-btn>
              </template></v-data-table> 
  </div>
</template>

<script>
import axios from 'axios';

  export default {
      data() {
       return {
        results : [],
        operations : ["AND","OR"],
        types : ["phrase","regular"],
        headers: [
         
          { text: 'Title', value: 'title' },
          { text: 'keywords', value: 'keywords' },
          { text: 'author name', value: 'name' },
          { text: 'author surname', value: 'surname' },
          { text: 'genre', value: 'genre' },
          { text: "Download", value: "actions", sortable: false },
        
          
        ],
        form: {
            title : '',
            keywords : '',
            name : '',
            surname : '',
            genre : '',
            text : '',
            operation : '',
           
        }
      }
    },
    methods : {
        search() {
         axios
        .post("/search/phrase" ,this.form)
        .then(results => {
        this.results = results.data;
        console.log(this.results)
        
      })
        .catch(function(){
          console.log('FAILURE!!');
        });
        },
        download(item) {
            let formData = new FormData();
      formData.append("filename", item.location);
      console.log(item.location)
        axios
        .post("/download", formData)
        .then((response) => {
          let blob = new Blob([response.data], { type: "application/pdf" });
          let link = document.createElement("a");
          link.href = window.URL.createObjectURL(blob);
          link.download = item.location;
          link.click();
          console.log(response.data);
        })
        .catch((error) => {});
        }
    }
    
  }
</script>

<style>

</style>
<template>
  <div>
    <h3>Add book information</h3>
   <b-form  style="width:500px">
      <b-form-group
        id="input-group-1"
        label="Title:"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
           v-model="uploadPdf.title"
          type="text"
          placeholder="Enter title"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="keywords:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="uploadPdf.keywords"
          placeholder="Enter keywords"
          required
        ></b-form-input>
      </b-form-group>

       <b-form-group id="input-group-2" label="Author name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="uploadPdf.name"
          placeholder="Enter name"
          required
        ></b-form-input>
      </b-form-group>
       <b-form-group id="input-group-2" label="Author surname:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="uploadPdf.surname"
          placeholder="Enter surname"
          required
        ></b-form-input>
      </b-form-group>
       <b-form-group id="input-group-2" label="genre:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="uploadPdf.genre"
          placeholder="Enter genre"
          required
        ></b-form-input>
      </b-form-group>

      <!-- <b-form-group id="input-group-3" label="Food:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.food"
          :options="foods"
          required
        ></b-form-select>
      </b-form-group> -->
         <label > you can upload files
      </label>
      <input  style="display:none"  ref="fileInput" accept=".pdf" type="file" multiple @change="onFileSelected($event)"/>
              <button type="button"
                class="btn btn-primary ml-4 mr-4  z-depth-2" @click="$refs.fileInput.click()">Choose file</button>
              <button type="button"
                class="btn btn-outline-primary  z-depth-2"  @click="uploadFiles()">Upload file</button>
   </b-form>

  <!-- <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label > you can upload files
      </label>
      <input  style="display:none"  ref="fileInput" type="file" multiple @change="onFileSelected($event)"/>
              <button type="button"
                class="btn btn-primary ml-4 mr-4  z-depth-2" @click="$refs.fileInput.click()">Choose file</button>
              <button type="button"
                class="btn btn-outline-primary  z-depth-2"  @click="uploadFiles()">Upload file</button>
    </div>
  </div> -->
</div>
</template>

<script>
import axios from "axios";
import { mapGetters, mapMutations } from "vuex";
export default {
     data(){
      return {
        files: '',
        selectedFile: [],
        uploadPdf: {
            pdfs : new FormData(),
            title : '',
            keywords : '',
            genre : '',
            name: '',
            surname: ''
        }
      }
    } ,
    methods: {
 
      onFileSelected(event){
             console.log(event.target.files.length);
            for( var i = 0; i < event.target.files.length; i++ ){
              this.selectedFile.push(event.target.files[i]);
              //this
              
        }
      },
        uploadFiles() {
          console.log(this.uploadPdf.title)
          console.log(this.selectedFile)
          const uploadFile = new FormData();
          this.selectedFile.forEach(f => { 
             uploadFile.append('files', f);
          });
          console.log(uploadFile);
          this.uploadPdf.pdfs = uploadFile;

          console.log(this.uploadPdf.title)
        axios
        .post("/index/add/" + this.uploadPdf.title + "/" + this.uploadPdf.keywords + "/" + this.uploadPdf.name + "/" + this.uploadPdf.surname + "/" + this.uploadPdf.genre,uploadFile )
        .then(function(){
          console.log('SUCCESS!!');
        })
        .catch(function(){
          console.log('FAILURE!!');
        });
          
        },
    }
}
</script>

<style>

</style>
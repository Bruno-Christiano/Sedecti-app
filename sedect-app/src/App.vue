
<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper " >
        <a href="#" class="brand-logo center">Cadastrar Usúarios</a>
      </div>
    </nav>

    <div class="container">
      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          <div class="card-panel red lighten-4">
            Campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
          </div>
        </li>
      </ul>

      <form @submit.prevent="save">
        <label>Nome</label>
        <input type="text" placeholder="Nome" v-model="user.nome" />
        <label>Idade</label>
        <input type="number"  placeholder="Idade" v-model="user.idade" />
        <label>Cpf</label>
        <input type="text" placeholder="CPF" v-model="user.cpf" />
        <label>Sexo</label>
        <p>
          <label>
            <input name="sexo" type="radio" value="m" v-model="user.sexo"/>
            <span>Masculino</span>
          </label>
        </p>
        <p>
          <label>
            <input name="sexo" type="radio" value="f" v-model="user.sexo"/>
            <span>Feminino</span>
          </label>
        </p>
       <fieldset id="fild">
    <legend>Endereço</legend>
    <label>Rua</label>
        <input type="text" pattern="[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$" placeholder="Rua" v-model="rua" />
        <label>Bairro</label>
        <input type="text" pattern="[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$" placeholder="Bairro" v-model="bairro" />
        <label>Número</label>
        <input type="text" pattern="^[A-Za-z0-9]*\d+[A-Za-z0-9]*$" placeholder="Numero" v-model="numero" />
  </fieldset>
        
        
        <button class="waves-effect waves-light btn-small">
          Salvar<i class="material-icons left">save</i>
        </button>
      </form>

      <table>
        <thead>
          <tr>
            <th>NOME</th>
            <th>IDADE</th>
            <th>CPF</th>
            <th>ENDEREÇO</th>
            <th>SEXO</th>
            <th>OPÇÕES</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="user of users" :key="user.id">
            <td>{{ user.nome }}</td>
            <td>{{ user.idade }}</td>
            <td>{{ user.cpf }}</td>
            <td>{{ user.endereco }}</td>
            <td>{{ user.sexo }}</td>
            <td>
              <button
                @click="update(user)"
                class="waves-effect btn-small blue darken-1 btn"
              >
                <i class="material-icons">create</i>
              </button>
              <button
                @click="remove(user)"
                class="waves-effect btn-small red darken-1"
              >
                <i class="material-icons">delete_sweep</i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import User from "./services/user";

export default {
  name: "app",
  data() {
    return {
      user: {
        id: "",
        nome: "",
        idade:"",
        cpf: "",
        endereco: "",
        sexo: "",
      },
      rua:"",
      bairro:"",
      numero:"",
      users: [],
      errors: [],
    };
  },
  mounted() {
    this.findAll();
  },
  methods: {
    findAll() {
      User.findAll()
        .then((resposta) => {
          this.users = resposta.data;
          console.log(this.users)
        })
        .catch((e) => {
          console.log(e);
        });
    },
    save() {

        if (this.rua != "" && this.bairro != "" && this.numero != "" ) {
          this.user.endereco=`${this.rua},${this.bairro},${this.numero}`
        }
      if (!this.user.id) {
        User.save(this.user)
          .then((resposta) => {
            this.user = {};
            alert("Cadastrado com sucesso!");
            this.findAll();
            this.errors = {};
            this.rua="";
            this.bairro="";
            this.numero="";
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
            window.scrollTo(0,0);
          });
      } else {
        User.update(this.user)
          .then((resposta) => {
            this.user = {};
            this.errors = {};
            this.rua="";
            this.bairro="";
            this.numero="";
            alert("Atualizado com sucesso!");
            this.findAll();
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
            window.scrollTo(0,0);
          });
      }
    },
    update(user) {
      this.user = user;
    let addres = this.user.endereco.split(',')
    console.log(addres.length)
    if (addres.length==3) {

        this.rua=addres[0]
        this.bairro=addres[1]
        this.numero=addres[2]
    }
    },
    remove(user) {
      if (confirm("Deseja excluir o usuário?")) {
        User.delete(user)
          .then((resposta) => {
            this.findAll();
            this.errors = {};
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
            window.scrollTo(0,0);
          });
      }
    },
  },
};
</script>

<style scoped>
  .nav-wrapper{
    background: #135763;
  }
  .btn-small{
    background: #135763;
    top: 7px;
  }
 

  #fild{
    padding: 10px;
    border: 1px solid #135763;
    border-radius: 5px 5px 5px 5px ;
  }
</style>>


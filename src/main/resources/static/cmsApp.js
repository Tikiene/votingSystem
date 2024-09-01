const { createApp, nextTick } = Vue;

const navbar = {
	template:
		`<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <span class="navbar-brand" >投票系統</span>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarText">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="http://localhost:8081/vote">投票區</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="http://localhost:8081/vote/cms.html">後台編輯區</a>
		      </li>
		    </ul>
		  </div>
		</nav>`
}

const cmsTable = {
	props: ['items'],
	data() {
		return {
			newTopicName: '',
			editMode: null,
			editTopicName: ''
		};
	},
	methods: {
		addTopic() {
			axios({
				method: "put",
				url: "/vote/topic/edit",
				data: {
					name: this.newTopicName
				}
			}).then(response => {
				window.alert(this.newTopicName + " 新增成功");
				this.newTopicName = '';
				this.$emit('refresh-items');
			}).catch(error => {
				console.error("error", error);
			});
		},
		toggleEditTopic(item) {
			this.editMode = item.id;
			this.editTopicName = item.name;
		},
		editTopic(item){
			axios({
				method: "put",
				url: "/vote/topic/edit",
				data: {
					id: item.id,
					name: this.editTopicName
				}
			}).then(response => {
				window.alert(this.editTopicName + " 修改成功");
				this.editMode = null;
				this.editTopicName = '';
				this.$emit('refresh-items');
			}).catch(error => {
				console.error("error", error);
			});
		},
		cancelEditTopic(){
			this.editMode = null;
			this.editTopicName = '';
		},
		deleteTopic(id) {
			axios({
				method: "delete",
				url: "/vote/topic/delete/" + id,
				data: {
					id: id,
				}
			}).then(response => {
				window.alert("刪除成功");
				this.newTopicName = '';
				this.$emit('refresh-items');
			}).catch(error => {
				console.error("error", error);
			});

		}
	},
	template: `
    <table class="table table-striped">
      <thead class="thead-dark">
        <tr>
          <th scope="col">投票項目名稱</th>
          <th scope="col">票數</th>
          <th scope="col">更新/修改</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in items" :key="item.id">
          <td>
		  	<div v-if="editMode == item.id">
		  		<input type="text" v-model="editTopicName"/>
			</div>
			<div v-else>
				{{item.name}} 
			</div>
		  </td>
          <td>{{item.votecount}}</td>
          <td>
		  	<div v-if="editMode == item.id">
				<button type="button" class="btn btn-success" @click="editTopic(item)">送出</button>
              	<button type="button" class="btn btn-secondary" @click="cancelEditTopic">取消</button>
			</div>
			<div v-else>
				<button type="button" class="btn btn-success" @click="toggleEditTopic(item)">修改</button>
				<button type="button" class="btn btn-danger" @click="deleteTopic(item.id)">刪除</button>
			</div>
          </td>
        </tr>
        <tr>
          <td>
            <input type="text" class="form-control" placeholder="投票項目" 
              style="margin: 0 auto;" v-model="newTopicName">
          </td>
          <td></td>
          <td>
            <button type="button" class="btn btn-primary" @click="addTopic">新增</button>
          </td>
        </tr>
      </tbody>
    </table>
  `,
	watch: {
		newTopicName(val) {
			this.$emit('update:newTopicName', val);
		},

	}
};

const cmsApp = createApp({
	components: {
		navbar,
		cmsTable
	},
	data() {
		return {
			items: [],//data from api
			newTopicName: "",
		}
	},
	methods: {
		getCountTopics() {
			axios({
				method: "get",
				url: "/vote/topic/countAll"
			}).then(response => {
				this.items = response.data;
				console.log(this.items);
			}).catch(error => {
				console.error("error", error);
			})
		},
		refreshTable() {
			this.getCountTopics();
		}
	},
	template: `<navbar></navbar>
		<div class="container">
			<cmsTable :items="items"  @refresh-items="refreshTable"></cmsTable>
		</div>`
	, mounted() {
		this.getCountTopics();
	}
})
cmsApp.mount('#cmsApp');




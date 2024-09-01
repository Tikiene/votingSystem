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

const voteArea = {
	props: ['items'],
	data() {
		return {
			topicId: []
		}
	},
	template:
		`<div class="card" style="width: 18rem;" v-for="item in items">
	  <div class="card-body">
	    <h5 class="card-title" style="text-align:center;font-weight:bold">{{item.name}}</h5>
	    <br/>
	    <h6 style="text-align:center;"> 票數:{{item.votecount}}</h6>
	    <input type="checkbox" :value="item.id" v-model="topicId">
	  </div>
	</div>`,
	watch: {
		topicId(val) {
			this.$emit('update:topicId', val);
		}
	}
}

const app = createApp({
	components: {
		navbar,
		voteArea
	},
	data() {
		return {
			items: [],//data from api
			topicId: [],//取得topicId資料,
			voterName: ''
		}
	},
	methods: {
		getCountTopics() {
			axios({
				method: "get",
				url: "/vote/topic/countAll"
			}).then(response => {
				this.items = response.data;
			}).catch(error => {
				console.error("error", error);
			})
		},
		addVoteData() {
			console.log("this.topicId:" + this.topicId);
			for (topicId of this.topicId) {
				axios.put("/vote/voting/edit", {
					voter: this.voterName,
					topic: {
						id: topicId
					}
				}).then(response => {
					window.alert(this.voterName + "投票成功");
					this.getCountTopics();
				}).catch(error => {
					console.error("error", error);
				});
			}
		}

	},
	template: `<navbar></navbar>
				<div class="container">
					<div style="display:flex; flex-direction:row;">
						<voteArea :items="items" @update:topicId="topicId = $event"></voteArea>
					</div>
					<div style="width: 10%;margin: 0 auto;">
					<input type="text" class="form-control" placeholder="投票人名字" 
						style="margin: 0 auto;" v-model="voterName">
				    	<button type="button" class="btn btn-primary" style="margin: 0 auto;" @click="addVoteData">投票</button>	
			    	</div>
		    	</div>`
	,
	mounted() {
		this.getCountTopics();
	}

})

app.mount('#app');




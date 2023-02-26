<template>
	 <view class="content-class">
	        <view class="uni-padding-wrap uni-common-mt ">
	            <view>
	                <video id="myVideo"  v-if="showVieo"  :src="baseUrl+video_info.videoPath" :poster="baseUrl+video_info.imgPath" :title="video_info.videoBrief"
	                    @timeupdate="timeUpdateCallback" :danmu-list="danmuList" enable-danmu danmu-btn controls></video>
	            </view>
				<!-- #ifndef MP-ALIPAY -->
				    <view class="uni-list uni-common-mt">
				        <view class="uni-list-cell">
				            <view>
				                <view class="uni-label">弹幕内容</view>
				            </view>
				            <view class="uni-list-cell-db">
				                <input v-model="danmuValue" class="uni-input" type="text" placeholder="在此处输入弹幕内容" />
				            </view>
				        </view>
				    </view>
				    <view class="uni-btn-v">
				        <button @click="sendDanmu" class="page-body-button">发送弹幕</button>
				    </view>
				<!-- #endif -->
	        </view>
			<uni-section title="最新热评" type="line" >
				<uni-list class="imgclass">
					<!-- to 属性携带参数跳转详情页面，当前只为参考 -->
					<uni-list-item direction="column" v-for="item in danmuList" :key="item.id">
						<!-- 通过header插槽定义列表顶部显示为图片 -->
						<template v-slot:header>
							<view class="uni-title">{{item.text}}</view>
						</template>
					</uni-list-item>
				</uni-list>
			</uni-section>
	    </view>
</template>

<script>
	import { getVideo,addDanmu,listDanmu } from "@/api/video/video"
	import config from '@/config'
	
	export default {
		data() {
			return {
			    video_id:"",
				video_info:{},
				baseUrl:config.baseUrl,
				danmuList: [],
				danmuValue:'',
				video_time:0,
				showVieo:false
			  }
		},
		onLoad(option){
			this.video_id = JSON.parse(option.videoId)
			console.log(this.video_id)
		},
		onReady() {
			this.getDanwuList()
			this.getVideo()
			 // #ifndef MP-ALIPAY
			    this.videoContext = uni.createVideoContext('myVideo')
			// #endif
		},
		methods: {
			getVideo(){
				getVideo(this.video_id).then(response => {
					this.video_info=response.data
					console.log(this.video_info)
				})
			},
			timeUpdateCallback: function(e) {
				this.video_time=e.detail.currentTime
			},
			sendDanmu: function() {
				if(this.danmuValue.length<1){
					this.$modal.msgSuccess("请输入弹幕内容")
					return
				}
			    this.videoContext.sendDanmu({
			        text: this.danmuValue,
			        color: '#ff0000'
			    });
				this.addSenDanmu()
			},
			getDanwuList(){
				let data={
					videoId:this.video_id
				}
				listDanmu().then(response=>{
					this.danmuList=response
					this.showVieo=true
					console.log(response)
				})
			},
			addSenDanmu(){
				let damu ={
					text: this.danmuValue,
					time:this.video_time,
					videoId:this.video_id
				}
				addDanmu(damu).then(response => {
				});
				this.danmuValue = '';
			}
		}
	}
</script>

<style lang="scss">
  /* #ifndef APP-NVUE */
  page {
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    background-color: #fff;
    min-height: 100%;
    height: auto;

	// justify-content: center;  
  }

  view {
    font-size: 14px;
    line-height: inherit;
  }
  video{
	  width: 100%;
	  height: 450rpx;
  }
   .content-class{
	   width: 100%;
	   align-items: center;
   }
  /* #endif */
</style>
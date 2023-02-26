<template>
	<view>
		<!-- 轮播图 -->
		<uni-swiper-dot class="uni-swiper-dot-box" :info="swiperDatas" :current="swiperCurrent" field="content">
		  <swiper class="swiper-box" :current="swiperDotIndex" @change="changeSwiper">
		    <swiper-item v-for="(item, index) in swiperDatas" :key="index">
		      <view class="swiper-item" @click="clickBannerItem(item)">
		        <image :src="baseUrl+item.imgPath" mode="aspectFill" :draggable="false" />
		      </view>
		    </swiper-item>
		  </swiper>
		</uni-swiper-dot>
		
       <uni-section title="视频课程" type="line" >
			<view class="uni-padding-wrap uni-common-mt">
				<uni-segmented-control :current="current" :values="video_type_items" 
					:active-color="'#dd524d'" @clickItem="onClickItem" />
			</view>
			<uni-list class="imgclass">
				<!-- to 属性携带参数跳转详情页面，当前只为参考 -->
				<uni-list-item direction="column" v-for="item in vide_goods" :key="item.id" :note="item.createTime" :to="'/pages/detail/detail?videoId='+item.videoId">
					<!-- 通过header插槽定义列表顶部显示为图片 -->
					<template v-slot:header>
						<view class="uni-title">{{item.videoBrief}}</view>
						<view class="uni-thumb uni-content list-picture">
							<!-- 当前判断长度只为简单判断类型，实际业务中，根据逻辑直接渲染即可 -->
							<image :src="baseUrl+item.imgPath" mode="aspectFill"></image>
						</view>
					</template>
				</uni-list-item>
			</uni-list>
			<!-- 通过 loadMore 组件实现上拉加载效果，如需自定义显示内容，可参考：https://ext.dcloud.net.cn/plugin?id=29 -->
			<uni-load-more :status="loadStatus" ></uni-load-more>
		</uni-section>
	</view>
</template>

<script>
	import { listDictData } from "@/api/system/dict"
	import { listVideo } from "@/api/video/video"
	import config from '@/config'
	export default {
		data() {
			return {
			    video_type_items:[],
				current: 0,
				swiperCurrent: 0,
				swiperDotIndex: 0,
				param:{
					pageNum:1,
					pageSize:10,
					status:0
				},
				swiperDatas: [],
				baseUrl:config.baseUrl,
				loadStatus:'loading',  //加载样式：more-加载前样式，loading-加载中样式，nomore-没有数据样式
				isLoadMore:false,  //是否加载中
				vide_goods:[]
			  }
		},
		onLoad() {
			this.getVideoType()
			this.getswiperDatas(3)
			this.getVideos(0)
		},
		methods: {
			getVideoType() {
			  listDictData({'dictType':'video_type'}).then(response => {
				let list=[]
			    for (var idx in response.rows) {
			        list.push( response.rows[idx].dictLabel);
			    }
				this.video_type_items=list
			  })
			},
			getVideos(){
				this.param.videoType=this.current
				listVideo(this.param).then(response => {
					console.log(response)
					this.vide_goods=this.vide_goods.concat(response.rows)
					uni.stopPullDownRefresh()
					this.isLoadMore=false
				})
			},
			getswiperDatas(videoType){
				this.param.videoType=3
				listVideo(this.param).then(response => {
					console.log(response)
					this.swiperDatas=response.rows
				})
			},
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex
					this.vide_goods=[]
					this.getVideos()
				}
			},
			clickBannerItem(item) {
			  console.info(item)
			},
			changeSwiper(e) {
			  this.swiperCurrent = e.detail.current
			},
			onPullDownRefresh() {
				this.param.pageNum=1
				this.vide_goods=[]
				this.getVideos()
			},
			onReachBottom() {
				if(!this.isLoadMore){
					if(this.vide_goods.length<this.param.pageNum*10){
						 this.isLoadMore=true
						 this.loadStatus='noMore'
						 return
					}
					this.param.pageNum +=1
					this.getVideos()
					
				}
				
				
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
  }

  view {
    font-size: 14px;
    line-height: inherit;

  }

  /* #endif */

  .text {
    text-align: center;
    font-size: 26rpx;
    margin-top: 10rpx;
  }
  .imgclass{
	align-items: center;
	justify-content: center;  
  }
  .grid-item-box {
    flex: 1;
    /* #ifndef APP-NVUE */
    display: flex;
    /* #endif */
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 15px 0;
  }

  .uni-margin-wrap {
    // width: 690rpx;
    width: 100%;
  }

  .swiper {
    height: 300rpx;
  }

  .swiper-box {
    height: 150px;
  }

  .swiper-item {
    /* #ifndef APP-NVUE */
    display: flex;
    /* #endif */
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: #fff;
    height: 300rpx;
    line-height: 300rpx;
  }

  @media screen and (min-width: 100%) {
    .uni-swiper-dot-box {
      width: 400px;
      /* #ifndef APP-NVUE */
      margin: 0 auto;
      /* #endif */
      margin-top: 8px;
    }

    .image {
      width: 100%;
    }
	.list-picture {
		width: 100%;
		height: 145px;
	}
  }
</style>

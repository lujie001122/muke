<template>
	<view>
       <!-- <uni-section title="实心标签" type="line">
			<view class="uni-padding-wrap uni-common-mt">
				<uni-segmented-control :current="current" :values="items" :style-type="styleType"
					:active-color="activeColor" @clickItem="onClickItem" />
			</view>
			<view class="content">
				<view v-if="current === 0"><text class="content-text">选项卡1的内容</text></view>
				<view v-if="current === 1"><text class="content-text">选项卡2的内容</text></view>
				<view v-if="current === 2"><text class="content-text">选项卡3的内容</text></view>
			</view>
		</uni-section> -->
	</view>
</template>

<script>
	import { listDictData } from "@/api/system/dict"
	export default {
		data() {
			return {
			    video_type_items:[],
			  }
		},
		onLoad() {
			this.getVideoType()
		},
		methods: {
			getVideoType() {
			  listDictData({'dictType':'sys_user_sex'}).then(response => {
				console.log(response)
			    this.user = response.data
			    this.roleGroup = response.roleGroup
			    this.postGroup = response.postGroup
			  })
			}
		}
	}
</script>

<style lang="scss">
  page {
    background-color: #ffffff;
  }
</style>

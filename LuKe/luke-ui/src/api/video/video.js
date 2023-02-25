import request from '@/utils/request'

// 查询视频信息列表
export function listVideo(query) {
  return request({
    url: '/video/video/list',
    method: 'get',
    params: query
  })
}

// 查询视频信息详细
export function getVideo(videoId) {
  return request({
    url: '/video/video/' + videoId,
    method: 'get'
  })
}

// 新增视频信息
export function addVideo(data) {
  return request({
    url: '/video/video',
    method: 'post',
    data: data
  })
}

// 修改视频信息
export function updateVideo(data) {
  return request({
    url: '/video/video',
    method: 'put',
    data: data
  })
}

// 删除视频信息
export function delVideo(videoId) {
  return request({
    url: '/video/video/' + videoId,
    method: 'delete'
  })
}

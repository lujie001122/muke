import request from '@/utils/request'
// 查询字典数据列表
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

// 查询学习记录列表
export function listRecord(query) {
  return request({
    url: '/video/record/list',
    method: 'get',
    params: query
  })
}

// 新增弹幕信息
export function addDanmu(data) {
  return request({
    url: '/video/danmu',
    method: 'post',
    data: data
  })
}

// 查询弹幕信息列表
export function listDanmu(query) {
  return request({
    url: '/video/danmu/damus',
    method: 'get',
    params: query
  })
}

// 新增学习记录
export function addRecord(data) {
  return request({
    url: '/video/record',
    method: 'post',
    data: data
  })
}
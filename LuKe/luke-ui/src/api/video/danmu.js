import request from '@/utils/request'

// 查询弹幕信息列表
export function listDanmu(query) {
  return request({
    url: '/video/danmu/list',
    method: 'get',
    params: query
  })
}

// 查询弹幕信息详细
export function getDanmu(danmuId) {
  return request({
    url: '/video/danmu/' + danmuId,
    method: 'get'
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

// 修改弹幕信息
export function updateDanmu(data) {
  return request({
    url: '/video/danmu',
    method: 'put',
    data: data
  })
}

// 删除弹幕信息
export function delDanmu(danmuId) {
  return request({
    url: '/video/danmu/' + danmuId,
    method: 'delete'
  })
}

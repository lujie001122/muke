import request from '@/utils/request'

// 查询学习记录列表
export function listRecord(query) {
  return request({
    url: '/video/record/list',
    method: 'get',
    params: query
  })
}

// 查询学习记录详细
export function getRecord(recordId) {
  return request({
    url: '/video/record/' + recordId,
    method: 'get'
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

// 修改学习记录
export function updateRecord(data) {
  return request({
    url: '/video/record',
    method: 'put',
    data: data
  })
}

// 删除学习记录
export function delRecord(recordId) {
  return request({
    url: '/video/record/' + recordId,
    method: 'delete'
  })
}

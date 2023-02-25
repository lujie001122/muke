import request from '@/utils/request'
// 查询字典数据列表
export function listVideo(query) {
  return request({
    url: '/video/video/list',
    method: 'get',
    params: query
  })
}
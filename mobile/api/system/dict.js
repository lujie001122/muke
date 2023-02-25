import request from '@/utils/request'
// 查询字典数据列表
export function listDictData(query) {
  return request({
    url: '/system/dict/data/list',
    method: 'get',
    params: query
  })
}
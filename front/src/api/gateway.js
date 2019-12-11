import request from '@/utils/request'

export function getGatewayList(token) {
  return request({
    url: '/api/v1/gateway',
    method: 'get',
    params: { token }
  })
}

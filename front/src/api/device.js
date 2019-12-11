import request from '@/utils/request'

export function getDeviceServiceList(token) {
  return request({
    url: '/core-metadata/api/v1/deviceservice',
    method: 'get',
    params: { token }
  })
}

export function getDeviceDetailList(deviceServiceName) {
  return request({
    url: '/core-metadata/api/v1/device/servicename/' + deviceServiceName,
    method: 'get'
  })
}

export function getDeviceProfileList() {
  return request({
    url: '/core-metadata/api/v1/deviceprofile',
    method: 'get'
  })
}

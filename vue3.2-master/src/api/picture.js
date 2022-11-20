// 发起请求得到用户数据
import request from './request'

export const getPicture = (params) => {
  return request({
    url: '/picture/1/5',
    params
  })
}

// // 添加用户
// export const addUser = (data) => {
//   return request({
//     url: 'users',
//     method: 'post',
//     data
//   })
// }

// export const editUser = (data) => {
//   return request({
//     url: `users/${data.id}`,
//     method: 'put',
//     data
//   })
// }

export const deletePicture = () => {
  return request({
    url: '/picture',
    method: 'delete'
  })
}

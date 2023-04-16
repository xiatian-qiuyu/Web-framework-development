## 地址映射
```
PutMapping,RequestMapping,GetMapping,PostMapping都是用来映射请求的
PutMapping用来更新资源，
GetMapping用来获取资源，
PostMapping用来创建资源，
RequestMapping用来映射请求，可以用来映射任何请求。
```
## 传递参数
```
表单中的name属性和实体类中的属性名一致，就可以自动封装到实体类中
后端就可以直接用实体类接收
```

## 实现模态框修改以及数据回显
修改按钮
```html
<button class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal" th:onclick="'updateAddress('+${address.id}+')'">编辑</button>
```
按钮的onclick事件
```js
//修改收货地址
//获取收货地址信息
function updateAddress(id) {
    $.ajax({
        type: 'get',
        url: 'shippingAddress/getAddress?addressId=' + id,
        success: function (data) {
            $('#Mdname').val(data.name)
            $('#MdphoneNum').val(data.phoneNum)
            $('#Mdaddress').val(data.address)
            $('#MdaddressId').val(id)
        },
        error: function () {
            alert("获取失败")
        },
    })
    }
```
后端获取数据，传值给前端（json类型）
```
//获取收货地址信息
@GetMapping("/getAddress")
@ResponseBody
public ShippingAddress getAddress(Long addressId) {
        ShippingAddress shippingAddress= shippingAddressService.findAddressById(addressId);
        return shippingAddress;
        }

```
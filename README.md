使用Jmeter对访问量较大的查询分享接口进行压测，通过在10s内创建1000个线程访问该接口，压测结果表明平均响应时间在65毫秒内，吞吐量可以达到7000。同时对下载1G文件测试，单用户可在6秒内下载完成，上传文件可在7秒内完成，满足实验室的日常使用

![8abe5bffca30755412f164567a9c4ee](C:\Users\41036\AppData\Local\Temp\WeChat Files\8abe5bffca30755412f164567a9c4ee.png)

**iccd-storage-server docs**


**简介**：iccd-storage-server docs

**HOST**:

**联系人**:


**Version**:1.0

**接口路径**：/v2/api-docs?group=storage-server


# file-controller

## 文件重命名

**接口描述**:该接口提供了文件重命名的功能

**接口地址**:`/file`


**请求方式**：`PUT`


**consumes**:`["application/json","application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"fileId": "",
	"newFilename": ""
}
```


**请求参数**：

| 参数名称         | 参数说明         | in   | 是否必须 | 数据类型       | schema         |
| ---------------- | ---------------- | ---- | -------- | -------------- | -------------- |
| updateFilenamePO | updateFilenamePO | body | true     | 文件重命名参数 | 文件重命名参数 |

**schema属性说明**



**文件重命名参数**

| 参数名称    | 参数说明     | in   | 是否必须 | 数据类型 | schema |
| ----------- | ------------ | ---- | -------- | -------- | ------ |
| fileId      | 更新的文件ID | body | true     | string   |        |
| newFilename | 新的文件名称 | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 批量删除文件

**接口描述**:该接口提供了批量删除文件的功能

**接口地址**:`/file`


**请求方式**：`DELETE`


**consumes**:`["application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"fileIds": ""
}
```


**请求参数**：

| 参数名称     | 参数说明     | in   | 是否必须 | 数据类型     | schema       |
| ------------ | ------------ | ---- | -------- | ------------ | ------------ |
| deleteFilePO | deleteFilePO | body | true     | 删除文件参数 | 删除文件参数 |

**schema属性说明**



**删除文件参数**

| 参数名称 | 参数说明                                 | in   | 是否必须 | 数据类型 | schema |
| -------- | ---------------------------------------- | ---- | -------- | -------- | ------ |
| fileIds  | 要删除的文件ID，多个使用公用的分隔符分割 | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 查询已经上传的文件分片列表

**接口描述**:该接口提供了查询已经上传的文件分片列表的功能

**接口地址**:`/file/chunk-upload`


**请求方式**：`GET`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：

| 参数名称   | 参数说明       | in    | 是否必须 | 数据类型 | schema |
| ---------- | -------------- | ----- | -------- | -------- | ------ |
| identifier | 文件的唯一标识 | query | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"uploadedChunks": []
	},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型                                 | schema                               |
| -------- | -------- | ------------------------------------ | ------------------------------------ |
| code     |          | integer(int32)                       | integer(int32)                       |
| data     |          | 查询用户已上传的文件分片列表返回实体 | 查询用户已上传的文件分片列表返回实体 |
| msg      |          | string                               |                                      |



**schema属性说明**




**查询用户已上传的文件分片列表返回实体**

| 参数名称       | 参数说明             | 类型  | schema |
| -------------- | -------------------- | ----- | ------ |
| uploadedChunks | 已上传的分片编号列表 | array |        |

**响应状态**:


| 状态码 | 说明 | schema                                  |
| ------ | ---- | --------------------------------------- |
| 200    | OK   | R«查询用户已上传的文件分片列表返回实体» |
## 文件分片上传

**接口描述**:该接口提供了分片文件上传功能

**接口地址**:`/file/chunk-upload`


**请求方式**：`POST`


**consumes**:`["application/json","multipart/form-data"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"chunkNumber": 0,
	"currentChunkSize": 0,
	"file": "",
	"filename": "",
	"identifier": "",
	"totalChunks": 0,
	"totalSize": 0
}
```


**请求参数**：

| 参数名称          | 参数说明          | in   | 是否必须 | 数据类型             | schema               |
| ----------------- | ----------------- | ---- | -------- | -------------------- | -------------------- |
| fileChunkUploadPO | fileChunkUploadPO | body | true     | 文件分片上传参数实体 | 文件分片上传参数实体 |

**schema属性说明**



**文件分片上传参数实体**

| 参数名称         | 参数说明       | in   | 是否必须 | 数据类型       | schema |
| ---------------- | -------------- | ---- | -------- | -------------- | ------ |
| chunkNumber      | 当前分片的下标 | body | true     | integer(int32) |        |
| currentChunkSize | 当前分片的大小 | body | true     | integer(int64) |        |
| file             | 分片文件实体   | body | true     | file           |        |
| filename         | 文件名称       | body | true     | string         |        |
| identifier       | 文件唯一标识   | body | true     | string         |        |
| totalChunks      | 总体的分片数   | body | true     | integer(int32) |        |
| totalSize        | 文件总大小     | body | true     | integer(int64) |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"mergeFlag": 0
	},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型                   | schema                 |
| -------- | -------- | ---------------------- | ---------------------- |
| code     |          | integer(int32)         | integer(int32)         |
| data     |          | 文件分片上传的响应实体 | 文件分片上传的响应实体 |
| msg      |          | string                 |                        |



**schema属性说明**




**文件分片上传的响应实体**

| 参数名称  | 参数说明                         | 类型           | schema |
| --------- | -------------------------------- | -------------- | ------ |
| mergeFlag | 是否需要合并文件 0 不需要 1 需要 | integer(int32) |        |

**响应状态**:


| 状态码 | 说明 | schema                    |
| ------ | ---- | ------------------------- |
| 200    | OK   | R«文件分片上传的响应实体» |
## 文件下载

**接口描述**:该接口提供了文件下载的功能

**接口地址**:`/file/download`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/octet-stream"]`



**请求参数**：

| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
| fileId   | fileId   | query | false    | string   |        |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   |        |
## 查询文件夹树

**接口描述**:该接口提供了查询文件夹树的功能

**接口地址**:`/file/folder/tree`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": [
		{
			"children": [
				{
					"children": [
						{}
					],
					"id": 0,
					"label": "",
					"parentId": 0
				}
			],
			"id": 0,
			"label": "",
			"parentId": 0
		}
	],
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema           |
| -------- | -------- | -------------- | ---------------- |
| code     |          | integer(int32) | integer(int32)   |
| data     |          | array          | 文件夹树节点实体 |
| msg      |          | string         |                  |



**schema属性说明**




**文件夹树节点实体**

| 参数名称 | 参数说明   | 类型           | schema           |
| -------- | ---------- | -------------- | ---------------- |
| children | 子节点集合 | array          | 文件夹树节点实体 |
| id       | 文件ID     | integer(int64) |                  |
| label    | 文件夹名称 | string         |                  |
| parentId | 父文件ID   | integer(int64) |                  |

**响应状态**:


| 状态码 | 说明 | schema                    |
| ------ | ---- | ------------------------- |
| 200    | OK   | R«List«文件夹树节点实体»» |
## 文件分片合并

**接口描述**:该接口提供了文件分片合并的功能

**接口地址**:`/file/merge`


**请求方式**：`POST`


**consumes**:`["application/json","application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"filename": "",
	"identifier": "",
	"parentId": "",
	"totalSize": 0
}
```


**请求参数**：

| 参数名称         | 参数说明         | in   | 是否必须 | 数据类型             | schema               |
| ---------------- | ---------------- | ---- | -------- | -------------------- | -------------------- |
| fileChunkMergePO | fileChunkMergePO | body | true     | 文件分片合并参数对象 | 文件分片合并参数对象 |

**schema属性说明**



**文件分片合并参数对象**

| 参数名称   | 参数说明         | in   | 是否必须 | 数据类型       | schema |
| ---------- | ---------------- | ---- | -------- | -------------- | ------ |
| filename   | 文件名称         | body | true     | string         |        |
| identifier | 文件唯一标识     | body | true     | string         |        |
| parentId   | 文件的父文件夹ID | body | true     | string         |        |
| totalSize  | 文件总大小       | body | true     | integer(int64) |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 文件预览

**接口描述**:该接口提供了文件预览的功能

**接口地址**:`/file/preview`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/octet-stream"]`



**请求参数**：

| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
| fileId   | fileId   | query | false    | string   |        |

**响应示例**:

```json

```

**响应参数**:


暂无





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   |        |
## 文件搜索

**接口描述**:该接口提供了文件搜索的功能

**接口地址**:`/file/search`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：

| 参数名称  | 参数说明                                 | in    | 是否必须 | 数据类型 | schema |
| --------- | ---------------------------------------- | ----- | -------- | -------- | ------ |
| keyword   | 搜索的关键字                             | query | true     | string   |        |
| fileTypes | 文件类型，多个文件类型使用公用分隔符拼接 | query | false    | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": [
		{
			"fileId": 0,
			"fileSizeDesc": "",
			"fileType": 0,
			"filename": "",
			"folderFlag": 0,
			"parentFilename": "",
			"parentId": 0,
			"updateTime": ""
		}
	],
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema                   |
| -------- | -------- | -------------- | ------------------------ |
| code     |          | integer(int32) | integer(int32)           |
| data     |          | array          | 用户搜索文件列表相应实体 |
| msg      |          | string         |                          |



**schema属性说明**




**用户搜索文件列表相应实体**

| 参数名称       | 参数说明                                                     | 类型              | schema |
| -------------- | ------------------------------------------------------------ | ----------------- | ------ |
| fileId         | 文件ID                                                       | integer(int64)    |        |
| fileSizeDesc   | 文件大小描述                                                 | string            |        |
| fileType       | 文件类型 1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv | integer(int32)    |        |
| filename       | 文件名称                                                     | string            |        |
| folderFlag     | 文件夹标识 0 否 1 是                                         | integer(int32)    |        |
| parentFilename | 父文件夹名称                                                 | string            |        |
| parentId       | 父文件夹ID                                                   | integer(int64)    |        |
| updateTime     | 文件更新时间                                                 | string(date-time) |        |

**响应状态**:


| 状态码 | 说明 | schema                            |
| ------ | ---- | --------------------------------- |
| 200    | OK   | R«List«用户搜索文件列表相应实体»» |
## 文件秒传

**接口描述**:该接口提供了批量文件秒传

**接口地址**:`/file/sec-upload`


**请求方式**：`POST`


**consumes**:`["application/json","application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"filename": "",
	"identifier": "",
	"parentId": ""
}
```


**请求参数**：

| 参数名称        | 参数说明        | in   | 是否必须 | 数据类型     | schema       |
| --------------- | --------------- | ---- | -------- | ------------ | ------------ |
| secUploadFilePO | secUploadFilePO | body | true     | 文件秒传参数 | 文件秒传参数 |

**schema属性说明**



**文件秒传参数**

| 参数名称   | 参数说明         | in   | 是否必须 | 数据类型 | schema |
| ---------- | ---------------- | ---- | -------- | -------- | ------ |
| filename   | 文件名称         | body | true     | string   |        |
| identifier | 文件的唯一标识   | body | true     | string   |        |
| parentId   | 秒传的父文件夹ID | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 单文件上传

**接口描述**:该接口提供了单文件上传功能

**接口地址**:`/file/upload`


**请求方式**：`POST`


**consumes**:`["application/json","multipart/form-data"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"file": "",
	"filename": "",
	"identifier": "",
	"parentId": "",
	"totalSize": 0
}
```


**请求参数**：

| 参数名称     | 参数说明     | in   | 是否必须 | 数据类型               | schema                 |
| ------------ | ------------ | ---- | -------- | ---------------------- | ---------------------- |
| fileUploadPO | fileUploadPO | body | true     | 单文件上传参数实体对象 | 单文件上传参数实体对象 |

**schema属性说明**



**单文件上传参数实体对象**

| 参数名称   | 参数说明         | in   | 是否必须 | 数据类型       | schema |
| ---------- | ---------------- | ---- | -------- | -------------- | ------ |
| file       | 文件实体         | body | true     | file           |        |
| filename   | 文件名称         | body | true     | string         |        |
| identifier | 文件的唯一标识   | body | true     | string         |        |
| parentId   | 文件的父文件夹ID | body | true     | string         |        |
| totalSize  | 文件的总大小     | body | true     | integer(int64) |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 查询文件列表

**接口描述**:该接口提供用户查询某文件夹下面某些文件类型的文件列表功能

**接口地址**:`/files`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json"]`



**请求参数**：

| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
| parentId | parentId | query | true     | string   |        |
| fileType | fileType | query | false    | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": [
		{
			"fileId": 0,
			"fileSizeDesc": "",
			"fileType": 0,
			"filename": "",
			"folderFlag": 0,
			"parentId": 0,
			"updateTime": ""
		}
	],
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema           |
| -------- | -------- | -------------- | ---------------- |
| code     |          | integer(int32) | integer(int32)   |
| data     |          | array          | 文件列表相应实体 |
| msg      |          | string         |                  |



**schema属性说明**




**文件列表相应实体**

| 参数名称     | 参数说明                                                     | 类型              | schema |
| ------------ | ------------------------------------------------------------ | ----------------- | ------ |
| fileId       | 文件ID                                                       | integer(int64)    |        |
| fileSizeDesc | 文件大小描述                                                 | string            |        |
| fileType     | 文件类型 1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv | integer(int32)    |        |
| filename     | 文件名称                                                     | string            |        |
| folderFlag   | 文件夹标识 0 否 1 是                                         | integer(int32)    |        |
| parentId     | 父文件夹ID                                                   | integer(int64)    |        |
| updateTime   | 文件更新时间                                                 | string(date-time) |        |

**响应状态**:


| 状态码 | 说明 | schema                    |
| ------ | ---- | ------------------------- |
| 200    | OK   | R«List«文件列表相应实体»» |
## 创建文件夹

**接口描述**:该接口提供用户创建文件夹功能

**接口地址**:`/folder`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*","application/json"]`


**请求示例**：
```json
{
	"folderName": "",
	"parentId": ""
}
```


**请求参数**：

| 参数名称       | 参数说明       | in   | 是否必须 | 数据类型       | schema         |
| -------------- | -------------- | ---- | -------- | -------------- | -------------- |
| createFolderPO | createFolderPO | body | true     | 创建文件夹参数 | 创建文件夹参数 |

**schema属性说明**



**创建文件夹参数**

| 参数名称   | 参数说明         | in   | 是否必须 | 数据类型 | schema |
| ---------- | ---------------- | ---- | -------- | -------- | ------ |
| folderName | 文件夹名称       | body | true     | string   |        |
| parentId   | 加密的父文件夹ID | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": "",
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | string         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema    |
| ------ | ---- | --------- |
| 200    | OK   | R«string» |
# user-controller

## 查询登录用户的基本信息

**接口描述**:该接口提供了查询登录用户的基本信息的功能

**接口地址**:`/user/`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": {
		"rootFileId": 0,
		"rootFilename": "",
		"username": ""
	},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型             | schema           |
| -------- | -------- | ---------------- | ---------------- |
| code     |          | integer(int32)   | integer(int32)   |
| data     |          | 用户基本信息实体 | 用户基本信息实体 |
| msg      |          | string           |                  |



**schema属性说明**




**用户基本信息实体**

| 参数名称     | 参数说明           | 类型           | schema |
| ------------ | ------------------ | -------------- | ------ |
| rootFileId   | 用户根目录的加密ID | integer(int64) |        |
| rootFilename | 用户根目录名称     | string         |        |
| username     | 用户名称           | string         |        |

**响应状态**:


| 状态码 | 说明 | schema              |
| ------ | ---- | ------------------- |
| 200    | OK   | R«用户基本信息实体» |
## 用户登录

**接口描述**:该接口提供用户登录功能，成功登录后，会返回有时效性的accessToken供后续使用

**接口地址**:`/user/login`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*","application/json"]`


**请求示例**：
```json
{
	"password": "",
	"username": ""
}
```


**请求参数**：

| 参数名称    | 参数说明    | in   | 是否必须 | 数据类型     | schema       |
| ----------- | ----------- | ---- | -------- | ------------ | ------------ |
| userLoginPO | userLoginPO | body | true     | 用户登出参数 | 用户登出参数 |

**schema属性说明**



**用户登出参数**

| 参数名称 | 参数说明 | in   | 是否必须 | 数据类型 | schema |
| -------- | -------- | ---- | -------- | -------- | ------ |
| password | 密码     | body | true     | string   |        |
| username | 用户名   | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 用户登出

**接口描述**:该接口提供用户注销登录功能

**接口地址**:`/user/logout`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*","application/json"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 用户注册

**接口描述**:该接口提供用户注册功能，保证了接口的幂等性，可以并发调用

**接口地址**:`/user/register`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*","application/json"]`


**请求示例**：
```json
{
	"answer": "",
	"password": "",
	"question": "",
	"username": ""
}
```


**请求参数**：

| 参数名称       | 参数说明       | in   | 是否必须 | 数据类型     | schema       |
| -------------- | -------------- | ---- | -------- | ------------ | ------------ |
| userRegisterPO | userRegisterPO | body | true     | 用户注册参数 | 用户注册参数 |

**schema属性说明**



**用户注册参数**

| 参数名称 | 参数说明 | in   | 是否必须 | 数据类型 | schema |
| -------- | -------- | ---- | -------- | -------- | ------ |
| answer   | 密码答案 | body | true     | string   |        |
| password | 密码     | body | true     | string   |        |
| question | 密码问题 | body | true     | string   |        |
| username | 用户名   | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
# 分享模块

## 查询分享的详情

**接口描述**:该接口提供了查询分享的详情的功能

**接口地址**:`/share`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": {
		"createTime": "",
		"rpanUserFileVOList": [
			{
				"fileId": 0,
				"fileSizeDesc": "",
				"fileType": 0,
				"filename": "",
				"folderFlag": 0,
				"parentId": 0,
				"updateTime": ""
			}
		],
		"shareDay": 0,
		"shareEndTime": "",
		"shareId": 0,
		"shareName": "",
		"shareUserInfoVO": {
			"userId": 0,
			"username": ""
		}
	},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型                   | schema                 |
| -------- | -------- | ---------------------- | ---------------------- |
| code     |          | integer(int32)         | integer(int32)         |
| data     |          | 分享详情的返回实体对象 | 分享详情的返回实体对象 |
| msg      |          | string                 |                        |



**schema属性说明**




**分享详情的返回实体对象**

| 参数名称           | 参数说明       | 类型                   | schema                 |
| ------------------ | -------------- | ---------------------- | ---------------------- |
| createTime         | 分享的创建时间 | string(date-time)      |                        |
| rpanUserFileVOList |                | array                  | 文件列表相应实体       |
| shareDay           | 分享的过期类型 | integer(int32)         |                        |
| shareEndTime       | 分享的截止时间 | string(date-time)      |                        |
| shareId            | 分享的ID       | integer(int64)         |                        |
| shareName          | 分享的名称     | string                 |                        |
| shareUserInfoVO    | 分享者的信息   | 分享者信息返回实体对象 | 分享者信息返回实体对象 |

**文件列表相应实体**

| 参数名称     | 参数说明                                                     | 类型              | schema |
| ------------ | ------------------------------------------------------------ | ----------------- | ------ |
| fileId       | 文件ID                                                       | integer(int64)    |        |
| fileSizeDesc | 文件大小描述                                                 | string            |        |
| fileType     | 文件类型 1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv | integer(int32)    |        |
| filename     | 文件名称                                                     | string            |        |
| folderFlag   | 文件夹标识 0 否 1 是                                         | integer(int32)    |        |
| parentId     | 父文件夹ID                                                   | integer(int64)    |        |
| updateTime   | 文件更新时间                                                 | string(date-time) |        |

**分享者信息返回实体对象**

| 参数名称 | 参数说明     | 类型           | schema |
| -------- | ------------ | -------------- | ------ |
| userId   | 分享者的ID   | integer(int64) |        |
| username | 分享者的名称 | string         |        |

**响应状态**:


| 状态码 | 说明 | schema                    |
| ------ | ---- | ------------------------- |
| 200    | OK   | R«分享详情的返回实体对象» |
## 创建分享链接

**接口描述**:该接口提供了创建分享链接的功能

**接口地址**:`/share`


**请求方式**：`POST`


**consumes**:`["application/json","application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"shareDayType": 0,
	"shareFileIds": "",
	"shareName": "",
	"shareType": 0
}
```


**请求参数**：

| 参数名称         | 参数说明         | in   | 是否必须 | 数据类型                   | schema                     |
| ---------------- | ---------------- | ---- | -------- | -------------------------- | -------------------------- |
| createShareUrlPO | createShareUrlPO | body | true     | 创建分享链接的参数对象实体 | 创建分享链接的参数对象实体 |

**schema属性说明**



**创建分享链接的参数对象实体**

| 参数名称     | 参数说明                                     | in   | 是否必须 | 数据类型       | schema |
| ------------ | -------------------------------------------- | ---- | -------- | -------------- | ------ |
| shareDayType | 分享的日期类型                               | body | true     | integer(int32) |        |
| shareFileIds | 分享的文件ID集合，多个使用公用的分割符去拼接 | body | true     | string         |        |
| shareName    | 分享的名称                                   | body | true     | string         |        |
| shareType    | 分享的类型                                   | body | true     | integer(int32) |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"shareCode": "",
		"shareId": 0,
		"shareName": "",
		"shareStatus": 0,
		"shareUrl": ""
	},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型                       | schema                     |
| -------- | -------- | -------------------------- | -------------------------- |
| code     |          | integer(int32)             | integer(int32)             |
| data     |          | 创建分享链接的返回实体对象 | 创建分享链接的返回实体对象 |
| msg      |          | string                     |                            |



**schema属性说明**




**创建分享链接的返回实体对象**

| 参数名称    | 参数说明         | 类型           | schema |
| ----------- | ---------------- | -------------- | ------ |
| shareCode   | 分享链接的分享码 | string         |        |
| shareId     | 分享链接的ID     | integer(int64) |        |
| shareName   | 分享链接的名称   | string         |        |
| shareStatus | 分享链接的状态   | integer(int32) |        |
| shareUrl    | 分享链接的URL    | string         |        |

**响应状态**:


| 状态码 | 说明 | schema                        |
| ------ | ---- | ----------------------------- |
| 200    | OK   | R«创建分享链接的返回实体对象» |
## 取消分享

**接口描述**:该接口提供了取消分享的功能

**接口地址**:`/share`


**请求方式**：`DELETE`


**consumes**:`["application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"shareIds": ""
}
```


**请求参数**：

| 参数名称      | 参数说明      | in   | 是否必须 | 数据类型             | schema               |
| ------------- | ------------- | ---- | -------- | -------------------- | -------------------- |
| cancelSharePO | cancelSharePO | body | true     | 取消分享参数实体对象 | 取消分享参数实体对象 |

**schema属性说明**



**取消分享参数实体对象**

| 参数名称 | 参数说明                                       | in   | 是否必须 | 数据类型 | schema |
| -------- | ---------------------------------------------- | ---- | -------- | -------- | ------ |
| shareIds | 要取消的分享ID的集合，多个使用公用的分割符拼接 | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 校验分享码

**接口描述**:该接口提供了校验分享码的功能

**接口地址**:`/share/code/check`


**请求方式**：`POST`


**consumes**:`["application/json","application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"shareCode": "",
	"shareId": ""
}
```


**请求参数**：

| 参数名称         | 参数说明         | in   | 是否必须 | 数据类型               | schema                 |
| ---------------- | ---------------- | ---- | -------- | ---------------------- | ---------------------- |
| checkShareCodePO | checkShareCodePO | body | true     | 校验分享码参数实体对象 | 校验分享码参数实体对象 |

**schema属性说明**



**校验分享码参数实体对象**

| 参数名称  | 参数说明     | in   | 是否必须 | 数据类型 | schema |
| --------- | ------------ | ---- | -------- | -------- | ------ |
| shareCode | 分享的分享码 | body | true     | string   |        |
| shareId   | 分享的ID     | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": "",
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | string         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema    |
| ------ | ---- | --------- |
| 200    | OK   | R«string» |
## 获取下一级文件列表

**接口描述**:该接口提供了获取下一级文件列表的功能

**接口地址**:`/share/file/list`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：

| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
| parentId | parentId | query | false    | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": [
		{
			"fileId": 0,
			"fileSizeDesc": "",
			"fileType": 0,
			"filename": "",
			"folderFlag": 0,
			"parentId": 0,
			"updateTime": ""
		}
	],
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema           |
| -------- | -------- | -------------- | ---------------- |
| code     |          | integer(int32) | integer(int32)   |
| data     |          | array          | 文件列表相应实体 |
| msg      |          | string         |                  |



**schema属性说明**




**文件列表相应实体**

| 参数名称     | 参数说明                                                     | 类型              | schema |
| ------------ | ------------------------------------------------------------ | ----------------- | ------ |
| fileId       | 文件ID                                                       | integer(int64)    |        |
| fileSizeDesc | 文件大小描述                                                 | string            |        |
| fileType     | 文件类型 1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv | integer(int32)    |        |
| filename     | 文件名称                                                     | string            |        |
| folderFlag   | 文件夹标识 0 否 1 是                                         | integer(int32)    |        |
| parentId     | 父文件夹ID                                                   | integer(int64)    |        |
| updateTime   | 文件更新时间                                                 | string(date-time) |        |

**响应状态**:


| 状态码 | 说明 | schema                    |
| ------ | ---- | ------------------------- |
| 200    | OK   | R«List«文件列表相应实体»» |
## 查询分享的简单详情

**接口描述**:该接口提供了查询分享的简单详情的功能

**接口地址**:`/share/simple`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：

| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
| shareId  | shareId  | query | false    | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {
		"shareId": 0,
		"shareName": "",
		"shareUserInfoVO": {
			"userId": 0,
			"username": ""
		}
	},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型                         | schema                       |
| -------- | -------- | ---------------------------- | ---------------------------- |
| code     |          | integer(int32)               | integer(int32)               |
| data     |          | 查询分享简单详情返回实体对象 | 查询分享简单详情返回实体对象 |
| msg      |          | string                       |                              |



**schema属性说明**




**查询分享简单详情返回实体对象**

| 参数名称        | 参数说明   | 类型                   | schema                 |
| --------------- | ---------- | ---------------------- | ---------------------- |
| shareId         | 分享ID     | integer(int64)         |                        |
| shareName       | 分享名称   | string                 |                        |
| shareUserInfoVO | 分享人信息 | 分享者信息返回实体对象 | 分享者信息返回实体对象 |

**分享者信息返回实体对象**

| 参数名称 | 参数说明     | 类型           | schema |
| -------- | ------------ | -------------- | ------ |
| userId   | 分享者的ID   | integer(int64) |        |
| username | 分享者的名称 | string         |        |

**响应状态**:


| 状态码 | 说明 | schema                          |
| ------ | ---- | ------------------------------- |
| 200    | OK   | R«查询分享简单详情返回实体对象» |
## 查询分享链接列表

**接口描述**:该接口提供了查询分享链接列表的功能

**接口地址**:`/shares`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": [
		{
			"createTime": "",
			"shareCode": "",
			"shareDayType": 0,
			"shareEndTime": "",
			"shareId": 0,
			"shareName": "",
			"shareStatus": 0,
			"shareType": 0,
			"shareUrl": ""
		}
	],
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema                   |
| -------- | -------- | -------------- | ------------------------ |
| code     |          | integer(int32) | integer(int32)           |
| data     |          | array          | 分享链接列表结果实体对象 |
| msg      |          | string         |                          |



**schema属性说明**




**分享链接列表结果实体对象**

| 参数名称     | 参数说明       | 类型              | schema |
| ------------ | -------------- | ----------------- | ------ |
| createTime   | 分享的创建时间 | string(date-time) |        |
| shareCode    | 分享的分享码   | string            |        |
| shareDayType | 分享的过期类型 | integer(int32)    |        |
| shareEndTime | 分享的过期时间 | string(date-time) |        |
| shareId      | 分享的ID       | integer(int64)    |        |
| shareName    | 分享的名称     | string            |        |
| shareStatus  | 分享的状态     | integer(int32)    |        |
| shareType    | 分享的类型     | integer(int32)    |        |
| shareUrl     | 分享的URL      | string            |        |

**响应状态**:


| 状态码 | 说明 | schema                            |
| ------ | ---- | --------------------------------- |
| 200    | OK   | R«List«分享链接列表结果实体对象»» |
# 回收站模块

## 删除的文件批量彻底删除

**接口描述**:该接口提供了删除的文件批量彻底删除的功能

**接口地址**:`/recycle`


**请求方式**：`DELETE`


**consumes**:`["application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"fileIds": ""
}
```


**请求参数**：

| 参数名称 | 参数说明 | in   | 是否必须 | 数据类型         | schema           |
| -------- | -------- | ---- | -------- | ---------------- | ---------------- |
| deletePO | deletePO | body | true     | 文件删除参数实体 | 文件删除参数实体 |

**schema属性说明**



**文件删除参数实体**

| 参数名称 | 参数说明                                   | in   | 是否必须 | 数据类型 | schema |
| -------- | ------------------------------------------ | ---- | -------- | -------- | ------ |
| fileIds  | 要删除的文件ID集合，多个使用公用分割符分隔 | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 删除的文件批量还原

**接口描述**:该接口提供了删除的文件批量还原的功能

**接口地址**:`/recycle/restore`


**请求方式**：`PUT`


**consumes**:`["application/json","application/json;charset=UTF-8"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`


**请求示例**：
```json
{
	"fileIds": ""
}
```


**请求参数**：

| 参数名称  | 参数说明  | in   | 是否必须 | 数据类型         | schema           |
| --------- | --------- | ---- | -------- | ---------------- | ---------------- |
| restorePO | restorePO | body | true     | 文件还原参数实体 | 文件还原参数实体 |

**schema属性说明**



**文件还原参数实体**

| 参数名称 | 参数说明                                   | in   | 是否必须 | 数据类型 | schema |
| -------- | ------------------------------------------ | ---- | -------- | -------- | ------ |
| fileIds  | 要还原的文件ID集合，多个使用公用分割符分隔 | body | true     | string   |        |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema         |
| -------- | -------- | -------------- | -------------- |
| code     |          | integer(int32) | integer(int32) |
| data     |          | object         |                |
| msg      |          | string         |                |





**响应状态**:


| 状态码 | 说明 | schema |
| ------ | ---- | ------ |
| 200    | OK   | R      |
## 获取回收站文件列表

**接口描述**:该接口提供了获取回收站文件列表的功能

**接口地址**:`/recycles`


**请求方式**：`GET`


**consumes**:`["application/x-www-form-urlencoded"]`


**produces**:`["*/*","application/json;charset=UTF-8"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"code": 0,
	"data": [
		{
			"fileId": 0,
			"fileSizeDesc": "",
			"fileType": 0,
			"filename": "",
			"folderFlag": 0,
			"parentId": 0,
			"updateTime": ""
		}
	],
	"msg": ""
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型           | schema           |
| -------- | -------- | -------------- | ---------------- |
| code     |          | integer(int32) | integer(int32)   |
| data     |          | array          | 文件列表相应实体 |
| msg      |          | string         |                  |



**schema属性说明**




**文件列表相应实体**

| 参数名称     | 参数说明                                                     | 类型              | schema |
| ------------ | ------------------------------------------------------------ | ----------------- | ------ |
| fileId       | 文件ID                                                       | integer(int64)    |        |
| fileSizeDesc | 文件大小描述                                                 | string            |        |
| fileType     | 文件类型 1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv | integer(int32)    |        |
| filename     | 文件名称                                                     | string            |        |
| folderFlag   | 文件夹标识 0 否 1 是                                         | integer(int32)    |        |
| parentId     | 父文件夹ID                                                   | integer(int64)    |        |
| updateTime   | 文件更新时间                                                 | string(date-time) |        |

**响应状态**:


| 状态码 | 说明 | schema                    |
| ------ | ---- | ------------------------- |
| 200    | OK   | R«List«文件列表相应实体»» |
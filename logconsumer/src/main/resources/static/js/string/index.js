var prefix = "/string";
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix +"/list", // 服务器数据的加载地址
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : false, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"
						queryParams : function queryParams(params){
                            var param = {
                                limit: params.limit,
                                offset: params.offset,
                                sort: params.sort,
                                order: params.order,
                                appVersion: $("#appVersion").val(),
                                domain: $("#domain").val(),
								flag: $("#flag").val()
                            };
                            return param;
                        },
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									field : 'name',
									title : 'Key',
                                    sortable: true
								},
								{
									field : 'valueEn',
									title : 'En',
                                    sortable: true
								},
								{
									field : 'valueEs',
									title : 'Es',
									sortable: true
								},
								{
									field : 'valueFr',
									title : 'Fr',
									sortable: true
								},
								{
									field : 'valueAr',
									title : 'Ar',
									sortable: true
								},
								{
									field : 'valueId',
									title : 'Id',
									sortable: true
								},
								{
									field : 'valueTh',
									title : 'Th',
									sortable: true
								},{
									field : 'valueDe',
									title : 'De',
									sortable: true
								},{
									field : 'valueIn',
									title : 'In',
									sortable: true
                            	},{
									field : 'valueIt',
									title : 'It',
									sortable: true
                            	},{
									field : 'valuePt',
									title : 'Pt',
									sortable: true
                            	},{
									field : 'flag',
									title : '状态',
                                    sortable: true,
									formatter : function(value, row, index) {
										if (value == '1') {
											return '<span class="badge badge-secondary">不翻译</span>';
										} else{
                                            return '<span class="badge badge-success">需翻译</span>';
                                        }
									}
								}

								 ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

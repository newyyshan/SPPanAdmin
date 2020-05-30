<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>日志列表</title>
  <meta name="keywords" content="">
  <meta name="remark" content="">
  <link rel="shortcut icon" href="favicon.ico">
  <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
  <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
  <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css"
        rel="stylesheet">
  <link href="${ctx!}/assets/css/plugins/datapicker/datepicker3.css"
        rel="stylesheet">
  <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
  <link href="${ctx!}/assets/css/plugins/chosen/chosen.css" rel="stylesheet">
  <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox ">
        <div class="ibox-title">
          <h5>日志管理</h5>
        </div>
        <div class="ibox-content">
          <p>
            <@shiro.hasPermission name="system:oplog:add">
              <button class="btn btn-success " type="button" onclick="add();">
                <i class="fa fa-plus"></i>&nbsp;添加
              </button>
            </@shiro.hasPermission>
          </p>
          <hr>
          <form class="form-horizontal space-20">
            <div class="row">
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">状态：</label>
                <div class="col-sm-8">
                  <select name="state" class="form-control" id="state-select">
                    <option value="">全部</option>
                    <option value="0">新建</option>
                    <option value="1">跟进</option>
                    <option value="2">办结</option>
                  </select>
                </div>
              </div>
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">区域：</label>
                <div class="col-sm-8">
                  <select name="region" class="form-control" id="region-select">
                    <option value="">全部</option>
                    <option value="沪苏区域">沪苏区域</option>
                    <option value="其他">其他</option>
                  </select>
                </div>
              </div>
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">广场：</label>
                <div class="col-sm-8">
                  <select name="square" class="form-control" id="square-select">
                    <option value="">全部</option>
                    <option value="狮山广场">狮山广场</option>
                    <option value="虹桥广场">虹桥广场</option>
                    <option value="宝山广场">宝山广场</option>
                    <option value="华泾广场">华泾广场</option>
                    <option value="马桥广场">马桥广场</option>
                    <option value="闵行广场">闵行广场</option>
                    <option value="其他">其他</option>
                  </select>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">创建人：</label>
                <div class="col-sm-8">
                  <input type="text" placeholder="请输入用户名" class="form-control"
                         name="create-user" id="create-user-input">
                </div>
              </div>
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">更新人：</label>
                <div class="col-sm-8">
                  <input type="text" placeholder="请输入用户名" class="form-control"
                         name="update-user" id="update-user-input">
                </div>
              </div>
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">事项名称：</label>
                <div class="col-sm-8">
                  <input type="text" placeholder="请输入事项名称关键词"
                         class="form-control" name="event-name" id="event-name">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">创建时间：</label>
                <div class="col-sm-8">
                  <div class="input-group input-daterange"
                       id="create-time-datepicker">
                    <input type="text" class="input-sm form-control"
                           name="start" placeholder="开始日期"/>
                    <span class="input-group-addon">到</span>
                    <input type="text" class="input-sm form-control"
                           name="end" placeholder="结束日期"/>
                  </div>
                </div>
              </div>
              <div class="form-group col-sm-4">
                <label class="col-sm-4 control-label">更新时间：</label>
                <div class="col-sm-8">
                  <div class="input-group input-daterange"
                       id="update-time-datepicker">
                    <input type="text" class="input-sm form-control"
                           name="start" placeholder="开始日期"/>
                    <span class="input-group-addon">到</span>
                    <input type="text" class="input-sm form-control"
                           name="end" placeholder="结束日期"/>
                  </div>
                </div>
              </div>
              <div class="form-group col-sm-4">
                <div class="pull-right m-r">
                  <button class="btn btn-default" type="button" id="reset-btn">
                    重置
                  </button>
                  <button class="btn btn-info" type="button" id="search-btn">
                    <i class="fa fa-search"></i>&nbsp;搜索
                  </button>
                </div>
              </div>
            </div>
          </form>
          <div class="row row-lg">
            <div class="col-sm-12">
              <!-- Example Card View -->
              <div class="example-wrap">
                <div class="example">
                  <table id="table_list"></table>
                </div>
              </div>
              <!-- End Example Card View -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- 全局js -->
<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
<!-- Bootstrap table -->
<script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<!-- Data picker -->
<script src="${ctx!}/assets/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<!-- Peity -->
<script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>
<!-- Layer -->
<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
<!-- Chosen -->
<script src="${ctx!}/assets/js/plugins/chosen/chosen.jquery.js"></script>
<!-- 自定义js -->
<script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>
<!-- Page-Level Scripts -->
<script>
  $(document).ready(function () {
    var $stateSelect = $('#state-select');
    var $regionSelect = $('#region-select');
    var $squareSelect = $('#square-select');
    var $createUserInput = $('#create-user-input');
    var $updateUserInput = $('#update-user-input');
    var $eventName = $('#event-name');
    var $createTimeDatepicker = $('#create-time-datepicker');
    var $updateTimeDatepicker = $('#update-time-datepicker');
    var $resetBtn = $('#reset-btn');
    var $searchBtn = $('#search-btn');
    var $table = $("#table_list");

    /** 下拉菜单默认配置 */
    var chosenOptions = {
      width: '100%',
      no_results_text: '没有匹配的结果',
      placeholder_text_multiple: '选择多个选项',
      placeholder_text_single: '选择一个选项'
    };

    $stateSelect.chosen(chosenOptions);
    $regionSelect.chosen(chosenOptions);
    $squareSelect.chosen(chosenOptions);

    $createTimeDatepicker.datepicker({});
    $updateTimeDatepicker.datepicker({});

    $resetBtn.on('click', resetForm);
    $searchBtn.on('click', search);

    //初始化表格,动态从服务器加载数据
    $table.bootstrapTable({
      //使用get请求到服务器获取数据
      method: "POST",
      //必须设置，不然request.getParameter获取不到请求参数
      contentType: "application/x-www-form-urlencoded",
      //获取数据的Servlet地址
      url: "${ctx!}/admin/oplog/list2",
      //表格显示条纹
      striped: true,
      //启动分页
      pagination: true,
      //服务端分页
      sidePagination: "server",
      //每页显示的记录数
      pageSize: 10,
      //当前第几页
      pageNumber: 1,
      //记录数可选列表
      pageList: [5, 10, 15, 20, 25],
      //是否启用查询
      search: false,
      //是否启用详细信息视图
      detailView: true,
      //详细信息视图内容
      detailFormatter: function (index, row, element) {
        var html = [];
        html.push('<p><b>工作日报:</b> ' + row.remark + '</p>');
        return html.join('');
      },
      //Ajax请求参数
      queryParams: function (tableParams) {
        var formParams = getFormParams();
        var params = Object.assign(formParams, tableParams);
        console.log('tableParams', params);
        console.log('formParams', formParams);
        return $.param(params, true);
      },
      //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
      //设置为limit可以获取limit, offset, search, sort, order
      queryParamsType: "undefined",
      //json数据解析
      responseHandler: function (res) {
        return {
          "rows": res.content,
          "total": res.totalElements
        };
      },
      //数据列
      columns: [{
        title: "ID",
        field: "id",
        sortable: true
      }, {
        title: "状态",
        field: "state",
        sortable: true,
        formatter: function (value, row, index) {
          if (value === 0)
            return '<span class="label label-new">新增</span>';
          else if (value === 1)
            return '<span class="label label-warning">跟进</span>';
          else if (value === 2)
            return '<span class="label label-end">办结</span>';
        }
      }, {
        title: "区域",
        field: "region",
        sortable: true
        // formatter: function(value, row, index) {
        // 	if (value == '0')
        // 		return '<span class="label label-warning">沪苏区域</span>';
        // 	return '<span class="label label-primary">其他</span>';
        // }
      }, {
        title: "广场",
        field: "square",
        sortable: true
        // formatter: function(value, row, index) {
        // 	if (value == '0')
        // 		return '<span class="label label-warning">吴中</span>';
        // 	return '<span class="label label-primary">其他</span>';
        // }
      }, {
        title: "事项名称",
        field: "eventname"
        // },{
        //     title: "资源类型",
        //     field: "type",
        //     formatter: function(value,row,index){
        //         if(value == 0)
        //             return '<span class="label label-info">目录</span>';
        //         else if(value == 1)
        //             return '<span class="label label-primary">菜单</span>';
        //         else if(value == 2)
        //             return '<span class="label label-warning">按钮</span>';
        //     }

        // },{
        //     title: "状态",
        //     sortable: true,
        //     field: "isHide",
        //     formatter: function (value, row, index) {
        //         if(value == 0)
        //             return '<span class="label label-info">显示</span>';
        //         else if(value == 1)
        //             return '<span class="label label-danger">隐藏</span>';
        //     }
      }, {
        title: "创建时间",
        field: "createTime",
        sortable: true
      }, {
        title: "创建人",
        field: "createUser",
        sortable: true
      }, {
        title: "更新时间",
        field: "updateTime",
        sortable: true
      }, {
        title: "更新人",
        field: "updateUser",
        sortable: true
      }, {
        title: "操作",
        field: "empty",
        formatter: function (value, row, index) {
          var operateHtml = '<@shiro.hasPermission name="system:oplog:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;</@shiro.hasPermission>';
          operateHtml = operateHtml + '<@shiro.hasPermission name="system:oplog:deleteBatch"><button class="btn btn-danger btn-xs" type="button" onclick="del(\'' + row.id + '\')"><i class="fa fa-remove"></i>&nbsp;删除</button></@shiro.hasPermission>';
          return operateHtml;
        }
      }]
    });

    /**
     * 获取表格参数，并没有真正获取表格插件的参数，只是给搜索使用
     */
    function getTableParams() {
      return {
        pageSize: 10,
        pageNumber: 1,
        searchText: undefined,
        sortName: undefined,
        sortOrder: 'asc' // 'asc', 'desc'
      }
    }

    /**
     * 获取表单参数
     */
    function getFormParams() {
      var params = {
        state: $stateSelect.val(),
        region: $regionSelect.val(),
        square: $squareSelect.val(),
        createUser: $createUserInput.val().trim(),
        updateUser: $updateUserInput.val().trim(),
        eventname: $eventName.val().trim(),
        createTime: [],
        updateTime: [],
      };

      $createTimeDatepicker.find('input').each(function (index, el) {
        params.createTime[index] = $(el).val();
      });

      $updateTimeDatepicker.find('input').each(function (index, el) {
        params.updateTime[index] = $(el).val();
      });

      function isEmptyString(item) {
        return item.length === 0;
      }

      // ["", ""] => []
      if (params.createTime.filter(isEmptyString).length === 2) {
        params.createTime = [];
      }

      if (params.updateTime.filter(isEmptyString).length === 2) {
        params.updateTime = [];
      }

      return params;
    }

    /**
     * 重置表单
     */
    function resetForm() {
      $stateSelect.val('');
      $regionSelect.val('');
      $squareSelect.val('');
      $stateSelect.trigger("chosen:updated");
      $regionSelect.trigger("chosen:updated");
      $squareSelect.trigger("chosen:updated");

      $createUserInput.val('');
      $updateUserInput.val('');
      $eventName.val('');

      $createTimeDatepicker.find('input').each(function (index, el) {
        $(el).val('');
      });
      $updateTimeDatepicker.find('input').each(function (index, el) {
        $(el).val('');
      });
    }

    /**
     * 搜索
     */
    function search() {
      var formParams = getFormParams();
      var tableParams = getTableParams();
      var params = Object.assign(formParams, tableParams);
      console.log('搜索', formParams);
      console.log('搜索', $.param(params, true));
      // 获取运维日志列表数据
      $.ajax({
        url: "${ctx!}/admin/oplog/list2",
        method: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',
        data: $.param(params, true)
        // data: params
      }).done(function (data, textStatus, jqXHR) {
        // 更新表格数据
        // TODO: 搜索时，表格默认应该重置成第一页
        $table.bootstrapTable('load', {
          rows: data.content,
          total: data.totalElements,
        });
      }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log("error");
        // TODO: 处理 302 状态码
      });
    }
  });

  function edit(id) {
    layer.open({
      type: 2,
      title: '日志修改',
      shadeClose: true,
      shade: false,
      area: ['893px', '600px'],
      content: '${ctx!}/admin/oplog/edit/' + id,
      end: function (index) {
        $('#table_list').bootstrapTable("refresh");
      }
    });
  }

  function add() {
    layer.open({
      type: 2,
      title: '日志添加',
      shadeClose: true,
      shade: false,
      area: ['893px', '600px'],
      content: '${ctx!}/admin/oplog/add',
      end: function (index) {
        $('#table_list').bootstrapTable("refresh");
      }
    });
  }

  function del(id) {
    layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
      $.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctx!}/admin/oplog/delete/" + id,
        success: function (msg) {
          layer.msg(msg.message, {time: 2000}, function () {
            $('#table_list').bootstrapTable("refresh");
            layer.close(index);
          });
        }
      });
    });
  }
</script>
</body>
</html>

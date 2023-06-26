/*$(document).ready(function() {
loadStudents(); // 页面加载时调用函数，展示
});*/
/*// 查询列表的函数
function loadStudents() {
$.ajax({
    url: '/api/quiz3List',
    method: 'GET',
    dataType: 'json',
    success: function (data) {
        $('#studentsTable tbody').empty(); // clear table contents

        // 循环遍历数据，并添加到表格中
        $.each(data.data, function (index, item) {
            var row = '<tr>' +
                '<td>' + item.quiz3Time + '</td>' +
                '<td>' + item.grade + '</td>' +
                '<td>' + item.id + '</td>' +
                '<td>' + item.notes + '</td>' +
                '<td><button type="button" class="btnClick">Delete</button></td>' +
                '</tr>';
            $('#studentsTable tbody').append(row);
        });
        /!*$('.btnClick').off('click').on('click'.function(){
        const quiz3Time = $(this).data('quiz3Time');
        deleteStudent(quiz3Time);
    })*!/
},
error: function (xhr, status, error) {
console.error('Failed to load students:', error);
}
});
}*/
// 给“删除”按钮绑定单击事件
function deleteItem(city) {
    // 保存当前选中要删除的项
    //debugger
    let selectedItem = $("#"+city);
    //dom.remove();
    //return;
    //let selectedItem = $(obj).closest('tr');
    //let quiz3Time = selectedItem.find('td:first-child').text();
    //懒懒的
   // let quiz3Time = selectedItem.find('td:first-child').text();

    // 显示确认对话框
    if (confirm("Are you sure you want to delete this item？")) {
        // Ajax
        var url = '/api/quiz3/delete/' + city;
        $.ajax({
            type: 'PUT',
            url: url,
            dataType: 'json',
            success: function (result) {
                // 删除成功后从表格中删除该行
                alert("success!");
                selectedItem.remove();
                selectedItem = null;
            },
            error: function (xhr, status, error) {
                // 删除失败时弹出错误消息
                alert('failed：' + error);
            }
        });
    }
}
/*// delete
function deleteStudent(quiz3Time) {
$.ajax({
    url: '/api/quiz3/delete/' + quiz3Time,
    method: 'DELETE',
    success: function (data) {
        loadStudents(); // 成功删除后重新加载列表
        alert('success！');
    },
    error: function (xhr, status, error) {
        console.error('Failed to delete student:', error);
        alert('failed！');
    }
});
}

// 点击“删除”按钮时调用函数，弹出确认对话框并删除
$(document).on('click', '.deleteStudent', function () {
const quiz3Time = $(this).data('quiz3Time');
if (confirm('Are you sure？')) {
deleteStudent(quiz3Time);
}
});*/

// Functions for querying students by name
function searchByTime(city) {
    if(city == null || city == ''){
        city = $('#city').val();
    }
    console.log(city);
    $.ajax({
        url: '/api/quiz3/queryByTime/' + city,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#studentsTable tbody').empty(); // 清空表格内容
            console.log(data.data);
            if (data.data.length == 0) {
                alert('no data')
            } else {
                // 循环遍历数据，并添加到表格中
                $.each(data.data, function (index, item) {
                    console.log(item);
                    var row = '<tr id="'+item.city+'">' +
                        '<td>' + item.city + '</td>' +
                        '<td>' + item.state + '</td>' +
                        '<td>' + item.population + '</td>' +
                        '<td>' + item.lat + '</td>' +
                        '<td>' + item.lon + '</td>' +
                        // '<td><button type="button" class="btnClick" >Delete</button></td>' +
                        "<td><button type='button' onclick='deleteItem(\""+item.city+"\")' class='btnClick' >Delete</button></td>"+
                        '</tr>';
                    $('#studentsTable tbody').append(row);
                });
                /*$('#studentsTable').on('click', 'button.btnClick', function () {
                    deleteItem(this);
                });*/
            }
        },
        error: function (xhr, status, error) {
            console.error('Failed to load students:', error);
        }
    });
}

function searchByMag() {
    var startId = $('#minMag').val();
    var endId = $('#maxMag').val();
   /* var net = $('#net').val();*/
    $.ajax({
        url: '/api/quiz3/queryByMag/' + startId + '/' + endId ,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#studentsTable tbody').empty(); // 清空表格内容

            // 循环遍历数据，并添加到表格中
            $.each(data.data, function (index, item) {
                var row = '<tr id="'+item.city+'">' +
                    '<td>' + item.city + '</td>' +
                    '<td>' + item.state + '</td>' +
                    '<td>' + item.population + '</td>' +
                    '<td>' + item.lat + '</td>' +
                    '<td>' + item.lon + '</td>' +
                   // '<td><button type="button" class="btnClick" >Delete</button></td>' +
                    "<td><button type='button' onclick='deleteItem(\""+item.city+"\")' class='btnClick' >Delete</button></td>"+
                    '</tr>';
                $('#studentsTable tbody').append(row);
            });

        },
        error: function (xhr, status, error) {
            console.error('Failed to load students:', error);
        }
    });
}

function searchByNum() {
    var startId = $('#minMag2').val();
    var endId = $('#maxMag2').val();
    var num = $('#num').val();
    /* var net = $('#net').val();*/
    $.ajax({
        url: '/api/quiz3/randomQuery/' + startId + '/' + endId +'/' + num ,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#studentsTable tbody').empty(); // 清空表格内容

            // 循环遍历数据，并添加到表格中
            $.each(data.data, function (index, item) {
                var row = '<tr id="'+item.city+'">' +
                    '<td>' + item.city + '</td>' +
                    '<td>' + item.state + '</td>' +
                    '<td>' + item.population + '</td>' +
                    '<td>' + item.lat + '</td>' +
                    '<td>' + item.lon + '</td>' +
                    // '<td><button type="button" class="btnClick" >Delete</button></td>' +
                    "<td><button type='button' onclick='deleteItem(\""+item.city+"\")' class='btnClick' >Delete</button></td>"+
                    '</tr>';
                $('#studentsTable tbody').append(row);
            });

        },
        error: function (xhr, status, error) {
            console.error('Failed to load students:', error);
        }
    });
}

$(document).ready(function() {
    $('#add').submit(function (event) {
        event.preventDefault();

        const formData = $(this).serializeArray();
        const jsonData = {};

        $.each(formData, function (index, field) {
            jsonData[field.name] = field.value;
        });

        $.ajax({
            url: '/api/quiz3/save',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            success: function (data) {
                alert('Success!');
                console.log(data);
            },
            error: function (error) {
                console.error(error);
            }
        });
    });
});
$(document).ready(function() {
    $('#update').submit(function (event) {
        event.preventDefault();

        const formData = $(this).serializeArray();
        const jsonData = {};

        $.each(formData, function (index, field) {
            jsonData[field.name] = field.value;
        });

        $.ajax({
            url: '/api/quiz3/save',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            success: function (data) {
                alert('Success!');
                console.log(data);
                console.log(data.data.quiz3Time);
                searchByTime(data.data.quiz3Time);
            },
            error: function (error) {
                console.error(error);
            }
        });
    });
});
$(document).ready(function (){
    $('#updateMag').submit(function (event) {
        event.preventDefault();

        const formData = $(this).serializeArray();
        const jsonData = {};

        $.each(formData, function (index, field) {
            jsonData[field.name] = field.value;
        });
        $.ajax({
            url: '/api/quiz3/addMagNum',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            success: function (data) {
                alert('Success!');
                $('#updateMag').nextAll().remove();
                // 创建新行
                var newRow = '<p>' + 'count:' + data.data + '</p>';
                // 将新行追加到表格中
                $('#updateMag').append(newRow);
                console.log(data);
            },
            error: function (error) {
                console.error(error);
            }
        });
    });
});


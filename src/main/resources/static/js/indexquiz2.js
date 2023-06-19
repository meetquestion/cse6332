/*$(document).ready(function() {
loadStudents(); // 页面加载时调用函数，展示
});*/
/*// 查询列表的函数
function loadStudents() {
$.ajax({
    url: '/api/quiz2List',
    method: 'GET',
    dataType: 'json',
    success: function (data) {
        $('#studentsTable tbody').empty(); // clear table contents

        // 循环遍历数据，并添加到表格中
        $.each(data.data, function (index, item) {
            var row = '<tr>' +
                '<td>' + item.quiz2Time + '</td>' +
                '<td>' + item.grade + '</td>' +
                '<td>' + item.id + '</td>' +
                '<td>' + item.notes + '</td>' +
                '<td><button type="button" class="btnClick">Delete</button></td>' +
                '</tr>';
            $('#studentsTable tbody').append(row);
        });
        /!*$('.btnClick').off('click').on('click'.function(){
        const quiz2Time = $(this).data('quiz2Time');
        deleteStudent(quiz2Time);
    })*!/
},
error: function (xhr, status, error) {
console.error('Failed to load students:', error);
}
});
}*/
// 给“删除”按钮绑定单击事件
function deleteItem(quiz2Time) {
    // 保存当前选中要删除的项
    var selectedItem = $(this).closest('tr');

    // 显示确认对话框
    if (confirm("Are you sure you want to delete this item？")) {
        // Ajax
        var url = '/api/quiz2/delete/' + quiz2Time;
        $.ajax({
            type: 'PUT',
            url: url,
            dataType: 'json',
            success: function (result) {
                // 删除成功后从表格中删除该行
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
function deleteStudent(quiz2Time) {
$.ajax({
    url: '/api/quiz2/delete/' + quiz2Time,
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
const quiz2Time = $(this).data('quiz2Time');
if (confirm('Are you sure？')) {
deleteStudent(quiz2Time);
}
});*/

// Functions for querying students by name
function searchByTime() {
    const quiz2Time = $('#quiz2Time').val();
    console.log(quiz2Time);
    $.ajax({
        url: '/api/quiz2/queryByTime/' + quiz2Time,
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
                    console.log(item.quiz2Time);
                    var row = '<tr>' +
                        '<td>' + item.quiz2Time + '</td>' +
                        '<td>' + item.latitude + '</td>' +
                        '<td>' + item.longitude + '</td>' +
                        '<td>' + item.mag + '</td>' +
                        '<td>' + item.net + '</td>' +
                        '<td>' + item.place + '</td>' +
                        '<td><button type="button" class="btnClick">Delete</button></td>' +
                        '</tr>';
                    $('#studentsTable tbody').append(row);
                    $('#studentsTable').on('click', 'button.btnClick', function () {
                        deleteItem(item.quiz2Time);
                    });
                });
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
    var net = $('#net').val();
    $.ajax({
        url: '/api/quiz2/queryByMag/' + startId + '/' + endId + '/' + net,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#studentsTable tbody').empty(); // 清空表格内容

            // 循环遍历数据，并添加到表格中
            $.each(data.data, function (index, item) {
                var row = '<tr>' +
                    '<td>' + item.quiz2Time + '</td>' +
                    '<td>' + item.latitude + '</td>' +
                    '<td>' + item.longitude + '</td>' +
                    '<td>' + item.mag + '</td>' +
                    '<td>' + item.net + '</td>' +
                    '<td>' + item.place + '</td>' +
                    '<td><button type="button" class="btnClick">Delete</button></td>' +
                    /* '<td><button class="delete">删除</button></td>'+*/
                    '</tr>';
                $('#studentsTable tbody').append(row);
                $('#studentsTable').on('click', 'button.btnClick', function () {
                    deleteItem(item.quiz2Time);
                });
            });

        },
        error: function (xhr, status, error) {
            console.error('Failed to load students:', error);
        }
    });
}

$('#add').submit(function (event) {
    event.preventDefault();

    const formData = $(this).serializeArray();
    const jsonData = {};

    $.each(formData, function (index, field) {
        jsonData[field.name] = field.value;
    });

    $.ajax({
        url: '/api/quiz2/save',
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

$('#update').submit(function (event) {
    event.preventDefault();

    const formData = $(this).serializeArray();
    const jsonData = {};

    $.each(formData, function (index, field) {
        jsonData[field.name] = field.value;
    });

    $.ajax({
        url: '/api/quiz2/save',
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

$('#updateMag').submit(function (event) {
    event.preventDefault();

    const formData = $(this).serializeArray();
    const jsonData = {};

    $.each(formData, function (index, field) {
        jsonData[field.name] = field.value;
    });

    $.ajax({
        url: '/api/quiz2/addMagNum',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            alert('Success!');
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


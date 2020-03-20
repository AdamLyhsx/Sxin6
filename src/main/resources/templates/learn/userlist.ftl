<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>

<input type="text" name="name" id="name">
<button type="submit" id="getUserByName" >查询</button>

<table border="1">
    <thead>
        <tr>
            <td>姓名</td>
            <td>性别</td>
            <td>年龄</td>
            <td>电话</td>
        </tr>
    </thead>
<#--<#if users?exists && (users.size()>0) >-->
<tbody id="container">
    <#list users as user>
        <tr>
        <td >${user.name}</td>
        <td >${user.gender}</td>
        <td >${user.age}</td>
        <td >${user.telphone}</td>
        </tr>
    </#list>
</tbody>
<#--</#if>-->
</table>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script>
    var g_userList=[];
    $(document).ready(function () {
        $("#getUserByName").on("click",function () {
            var name=$("#name").val();
            if(name==null||name==""){
                alert("姓名不能为空");
                return false;
            }
            $.ajax({
                url:"/learnmvc/userlist2",
                data:{
                    "name":$("#name").val(),
                },
                type:"GET",
                // contentType:"application/x-www-form-urlencoded",
                // contentType:"application/json",
                // dataType:"json",
                xhrFields:{withCredentials:true},
                success:function (data) {
                    g_userList=data;
                    reloadDom();
                },
                error:function (data) {
                    alert("用户列表获取失败2");
                }
            })
            return false;
        })
    })
    function reloadDom() {
        $("#container").text('');
        for(var i=0,l=g_userList.length;i<l;i++){
            var user=g_userList[i];
            var dom="<tr><td >" +user.name+ "</td>" +
                "        <td >" +user.gender+"</td>" +
                "        <td >" +user.age+"</td>" +
                "        <td >" +user.telphone+"</td></tr>" ;
            $("#container").append($(dom));
        }
    }
</script>

</body>
</html>
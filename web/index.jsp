<%--
  Created by IntelliJ IDEA.
  User: Akita
  Date: 2020/3/26
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script>
        window.onload = function () {
            new Vue({
                el: '#ajaxTest',
                data:{
                    username:"",
                    password:""
                },
                methods: {
                    register: function () {
                        $.ajax({
                            type: "POST",
                            url: "register",
                            data: JSON.stringify({username:this.username, password:this.password}),
							contentType: 'application/json',
                            success: function (msg) {
                                alert(msg)
                            }
                        });
                    },
                    login: function () {
                        $.ajax({
                            type: "POST",
                            url: "login",
                            data: JSON.stringify({username:this.username, password:this.password}),
                            contentType: 'application/json',
                            success: function (msg) {
                                alert(msg)
                            }
                        });
                    }
                }
            })
        }
    </script>
</head>

<body>
<div id="ajaxTest">
    <input name="username" v-model="username" /><br/>
    <input name="password" v-model="password"  /><br/>
    <button @click="register()">register</button>
    <button @click="login()">login</button>
</div>
</body>
</html>

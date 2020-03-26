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
                methods: {
                    send: function () {
                        $.ajax({
                            type: "POST",
                            url: "test",
                            contentType: 'application/json',
                            data: JSON.stringify({username:"shit",password:"123"}),
                            success: function (msg) {
                                alert(msg.toString());
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
    <button @click="send()"></button>
</div>
</body>
</html>

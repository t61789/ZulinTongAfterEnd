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
                data: {
                    username: "",
                    password: "",
                    email: "",
                    verifycode: ""
                },
                methods: {
                    send: function (url,data) {
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: JSON.stringify(data),
                            contentType: 'application/json',
                            success: function (msg) {
                                alert(msg)
                            }
                        });
                    },
                    register:function () {
                        this.send("register",{username: this.username, password: this.password});
                    },
                    login:function () {
                        this.send("login",{username: this.username, password: this.password});
                    },
                    test:function () {
                        this.send("test");
                    },
                    bindingEmailVerify:function () {
                        this.send("bindingEmail/verify",{
                            username: this.username,
                            verifycode:this.verifycode,
                            email: this.email
                        });
                    },
                    bindingEmailSend:function () {
                        this.send("bindingEmail/send",{
                            username: this.username,
                            email: this.email
                        });
                    },
                    retrieveEmailVerify:function () {
                        this.send("retrievePassword/verify",{
                            username: this.username,
                            verifycode:this.verifycode,
                            password:this.password
                        });
                    },
                    retrieveEmailSend:function () {
                        this.send("retrievePassword/send",{
                            username: this.username,
                            email: this.email
                        });
                    }
                }
            })
        }
    </script>
</head>

<body>
<div id="ajaxTest">
    username:<input name="username" v-model="username"/><br/>
    password:<input name="password" v-model="password"/><br/>
    email:<input name="email" v-model="email"/><br/>
    verifycode:<input name="verifycode" v-model="verifycode"/><br/>
    <button @click="register()">register</button>
    <button @click="login()">login</button>
    <button @click="test()">test</button>
    <button @click="bindingEmailSend()">bindingSend</button>
    <button @click="bindingEmailVerify()">bindingVerify</button>
    <button @click="retrieveEmailSend()">retrieveSend</button>
    <button @click="retrieveEmailVerify()">retrieveVerify</button>
</div>
</body>
</html>
